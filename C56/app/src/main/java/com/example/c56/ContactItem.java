package com.example.c56;

import org.json.JSONException;
import org.json.JSONObject;

public class ContactItem {
    private String name;
    private String number;

    public ContactItem(String name, String number){
        this.name = name;
        this.number = number;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    public String toJsonString() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("number", number);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
