package com.example.androidbloodbank;

public class MedicalType {

    String UserId,Allergy,Fitness,Disease;

    public MedicalType(){

    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAllergy() {
        return Allergy;
    }

    public void setAllergy(String allergy) {
        Allergy = allergy;
    }

    public String getFitness() {
        return Fitness;
    }

    public void setFitness(String fitness) {
        Fitness = fitness;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public MedicalType(String uid, String all, String fit, String dis){
        UserId = uid;
        Fitness = fit;
        Disease = dis;
        Allergy = all;

    }
}
