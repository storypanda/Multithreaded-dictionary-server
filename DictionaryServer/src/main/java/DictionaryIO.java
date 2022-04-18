import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.*;


/**
 * Author: Haohong Zhu
 * Student ID: 1305370
 */
public class DictionaryIO {
    public String addJsonToTxt(String newWord) throws Exception {
        JSONArray jsonArray = txtToJson(readLine(System.getProperty("path")));
        if(jsonArray.equals(JSONArray.parseArray("[{\"error\":\"Dictionary file does not exist\"}]"))){
            System.out.println("Dictionary file does not exist");
            return "Dictionary file does not exist";
        }
        BufferedWriter file = new BufferedWriter(new FileWriter(System.getProperty("path")));
        try {
            if (jsonArray != null) {
                for(int i=0;i<jsonArray.size();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if(jsonObject.get("word").equals(JSON.parseObject(newWord).get("word"))){
                        file.write(String.valueOf(jsonArray));
                        file.close();
                        System.out.println("Word already exists, add failed");
                        return "Word already exists, add failed";
                    }
                }
                jsonArray.add(JSON.parseObject(newWord));
                file.write(String.valueOf(jsonArray));
                file.close();
                System.out.println("add successful");
            } else {
                JSONArray newJsonArray = new JSONArray();
                newJsonArray.add(JSON.parseObject(newWord));
                file.write(String.valueOf(newJsonArray));
                file.close();
                System.out.println("add successful");
            }
        }catch (JSONException jsonException){
            file.write(String.valueOf(jsonArray));
            file.close();
            System.out.println("Incorrect add format, add failed");
            return "Incorrect add format, add failed";
        }
        return "add successful";
    }

    public void removeWordFromDictionary(JSONArray jsonArray) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(System.getProperty("path")));
        file.write(String.valueOf(jsonArray));
        file.close();
        System.out.println("remove successful");
    }

    public String updateJsonToTxt(String newWord) throws Exception {
        JSONArray jsonArray = txtToJson(readLine(System.getProperty("path")));
        if(jsonArray.equals(JSONArray.parseArray("[{\"error\":\"Dictionary file does not exist\"}]"))){
            System.out.println("Dictionary file does not exist");
            return "Dictionary file does not exist";
        }
        BufferedWriter file = new BufferedWriter(new FileWriter(System.getProperty("path")));
        try {
            if (jsonArray != null) {
                for(int i=0;i<jsonArray.size();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if(jsonObject.get("word").equals(JSON.parseObject(newWord).get("word"))){
                        jsonArray.remove(i);
                        jsonArray.add(i,JSON.parseObject(newWord));
                        file.write(String.valueOf(jsonArray));
                        file.close();
                        System.out.println("update successful");
                        return "update successful";
                    }
                }
                file.write(String.valueOf(jsonArray));
                file.close();
                System.out.println("Word not found, update failed");
                return "Word not found, update failed";
            } else {
                file.write(String.valueOf(jsonArray));
                file.close();
                System.out.println("update failed");
                return "update failed";
            }
        }catch (JSONException jsonException){
            file.write(String.valueOf(jsonArray));
            file.close();
            System.out.println("Incorrect update format, update failed");
            return "Incorrect update format, update failed";
        }
    }

    public JSONArray txtToJson(String dictionaryContent) throws Exception {
        if(dictionaryContent.equals("Dictionary file does not exist")){
            return JSONArray.parseArray("[{\"error\":\"Dictionary file does not exist\"}]");
        }
        JSONArray jsonArray = JSONArray.parseArray(dictionaryContent);
        return jsonArray;
    }

    public String readLine(String filePath) {
        //Try to use StringBuffer to keep thread safety
        StringBuffer dictionaryContent = null;
        RandomAccessFile file = null;
        if (new File(filePath).exists()) {
            try {
                dictionaryContent = new StringBuffer();
                file = new RandomAccessFile(filePath, "r");
                String line = null;
                while (null != (line = file.readLine())) {
                    dictionaryContent.append(line);
                }
                file.close();
                return dictionaryContent.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Dictionary file does not exist";
    }
}
