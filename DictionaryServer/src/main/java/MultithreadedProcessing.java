import com.alibaba.fastjson.JSONArray;

import java.util.Iterator;
import java.util.List;


/**
 * Author: Haohong Zhu
 * Student ID: 1305370
 */
public class MultithreadedProcessing extends Thread {
    ServerConnection connection;

    public MultithreadedProcessing(ServerConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {

            //Send answer
            short action = 0;
            connection.sendString(handleRequest(connection),action);

            //Use sleep to test Multithreading
            //sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Close connection
        connection.close();
    }

    private String handleRequest(ServerConnection conn) throws Exception {
        System.out.println("Receiving ...");
        ExchangeProtocol packet = conn.receiveString();
        short action = packet.getAction();
        String msg = packet.getContent();
        System.out.println(">>  " + msg);
        DictionaryIO dictionaryIO = new DictionaryIO();

        if(action==1){
            //Query
            String dictionaryContent = dictionaryIO.readLine(System.getProperty("path"));
            List<Dictionary> wordList = JSONArray.parseArray(dictionaryContent, Dictionary.class);
            for(Dictionary word:wordList){
                if(word.getWord().equals(msg)){
                    return word.getMeaning();
                }
            }
        }else if(action==2){
            //Add
            dictionaryIO.addJsonToTxt(msg);
            return "Success";
        }else if(action==3){
            //Remove
            String dictionaryContent = dictionaryIO.readLine(System.getProperty("path"));
            List<Dictionary> wordList = JSONArray.parseArray(dictionaryContent, Dictionary.class);
            // Creating iterator object
            Iterator<Dictionary> itr = wordList.iterator();
            while (itr.hasNext()) {
                // Use Iterator.remove() to delete word
                Dictionary word = itr.next();
                if (word.getWord().equals(msg)){
                    itr.remove();
                }
            }
            String jsonArrStr = JSONArray.toJSONString(wordList);
            JSONArray jsonArray = JSONArray.parseArray(jsonArrStr);
            dictionaryIO.removeWordFromDictionary(jsonArray);
            return "Success";
        }
        return "Fail";
    }
}
