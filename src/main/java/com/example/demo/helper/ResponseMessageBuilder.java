package com.example.demo.helper;

public class ResponseMessageBuilder {
    public static String build(String...strings) {
        StringBuilder sbResponseMessage = new StringBuilder();
        for (String string : strings) {
            sbResponseMessage.append(string);
        }
        return sbResponseMessage.toString();
    }
}
