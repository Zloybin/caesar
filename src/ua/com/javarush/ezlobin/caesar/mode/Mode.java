package ua.com.javarush.ezlobin.caesar.mode;

public enum Mode {
    ENCODE("encoded"),
    DECODE("decoded"),
    BRUTFORCE("decoded");

    private String operationName;

    Mode(String operationName){
        this.operationName = operationName;
    }

    public String getOperationName(){
        return operationName;
    }

}
