package com.example.androidbloodbank;

public class ListType {

    private String Id,Name,Contact;

    public ListType(){

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ListType(String id, String n, String c){
        Name = n;
        Contact = c;
        Id = id;
    }
}
