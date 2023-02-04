package org.example;

public class RequestInput {
    private String message = "hello Lambda";
    private Object data = "hello world";

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    
}
