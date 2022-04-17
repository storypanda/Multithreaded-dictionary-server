/**
 * Author: Haohong Zhu
 * Student ID: 1305370
 */
public class ExchangeProtocol {
    public short action;
    public String content;
    public ExchangeProtocol(){

    }
    public ExchangeProtocol(short action,String content){
        this.action=action;
        this.content=content;
    }

    public short getAction() {
        return action;
    }

    public void setAction(short action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
