package example.model.service;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;

public class SingerServiceModel {

    private String name;
    private String careerInformation;

    public String getName() {
        return name;
    }

    public SingerServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public SingerServiceModel setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
