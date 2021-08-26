package com.example.imedicine;

public class Medicines {

    private int medId;
    private String medName;
    private String medDate;
    private String medTime;

    public Medicines(int medId, String medName, String medDate, String medTime) {
        this.medName = medName;
        this.medDate = medDate;
        this.medTime = medTime;
        this.medId = medId;
    }

    public Medicines(String medName, String medDate, String medTime) {
        this.medName = medName;
        this.medDate = medDate;
        this.medTime = medTime;
    }

    public Medicines() {
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedDate() {
        return medDate;
    }

    public void setMedDate(String medDate) {
        this.medDate = medDate;
    }

    public String getMedTime() {
        return medTime;
    }

    public void setMedTime(String medTime) {
        this.medTime = medTime;
    }
}
