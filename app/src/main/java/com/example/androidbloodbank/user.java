package com.example.androidbloodbank;

public class user {
    String name;
    String email;
    String contact;
    String address;
    String BloodGroup;
    String Image;
    String Gender;
    String Type;

    public user()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public user(String name, String email, String contact, String address, String bg, String img, String gender, String type)
    {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        Image = img;
        BloodGroup = bg;
        Gender = gender;
        Type = type;
    }





}
