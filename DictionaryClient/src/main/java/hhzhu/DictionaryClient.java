package hhzhu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * Author: Haohong Zhu
 * Student ID: 1305370
 */
public class DictionaryClient {
    public String send(String msg, short action){
        ClientConnection conn = new ClientConnection();
        try {
            conn.connect( System.getProperty("address"), Integer.parseInt(System.getProperty("port")));
            conn.sendString(msg,action);

            String reply = conn.receiveString();
            System.out.println("<< " + reply);

            conn.close();
            return reply;
        } catch (Exception e) {
            return "Cannot Connect to Server, please check the command line parameters.";
        }
    }

}
