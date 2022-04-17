package hhzhu;


/**
 * Author: Haohong Zhu
 * Student ID: 1305370
 */
public class Dictionary {
    private String word;
    private String meaning;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Dictionary(){

    }

    public Dictionary(String word, String meaning){
        this.word=word;
        this.meaning=meaning;
    }

}
