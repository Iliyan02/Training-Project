package example.model.binding;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SingerAddBindingModel {

    @Size(min = 3, max = 50)
    private String name;
    @NotEmpty
    private String careerInformation;

    public String getName() {
        return name;
    }

    public SingerAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public SingerAddBindingModel setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
