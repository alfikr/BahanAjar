package com.sinau.bareng.singleton;

public class BelajarSingleton {

    private static BelajarSingleton _instance;
    private String val;
    public static synchronized BelajarSingleton getInstance(){
        if (_instance==null){
            _instance=new BelajarSingleton();
        }
        return _instance;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
