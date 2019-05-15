package com.github.dspanah.skincancerdetector;


import java.io.Serializable;


public class SkinLesion implements Serializable, Comparable<SkinLesion> {

    private String lesionType;
    private String probability;

    public void setLesionType(String lesionType) {
        this.lesionType = lesionType;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getLesionType() {
        return lesionType;
    }

    public String getProbability() {
        return probability;
    }

    public SkinLesion() {
        this.lesionType = lesionType;
        this.probability = probability;
    }

    @Override
    public int compareTo(SkinLesion s) {
        return Float.valueOf(this.getProbability()).compareTo(Float.valueOf(s.getProbability()));
    }
}
