import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;


/**
 * Author: Haohong Zhu
 * Student ID: 1305370
 */
public class DictionaryIO {
    public void addJsonToTxt(String newWord) throws Exception {
        JSONArray jsonArray = txtToJson(readLine(System.getProperty("path")));
        BufferedWriter file = new BufferedWriter(new FileWriter(System.getProperty("path")));
        if (jsonArray != null) {
            jsonArray.add(JSON.parseObject(newWord));
            file.write(String.valueOf(jsonArray));
            file.close();
            System.out.println("success");
        } else {
            JSONArray newJsonArray = new JSONArray();
            newJsonArray.add(JSON.parseObject(newWord));
            file.write(String.valueOf(newJsonArray));
            file.close();
            System.out.println("success");
        }
    }

    public void removeWordFromDictionary(JSONArray jsonArray) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(System.getProperty("path")));
        file.write(String.valueOf(jsonArray));
        file.close();
        System.out.println("success");
    }

    public JSONArray txtToJson(String dictionaryContent) throws Exception {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dictionaryContent.toString();
    }
}
