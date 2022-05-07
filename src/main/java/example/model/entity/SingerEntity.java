package example.model.entity;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "singers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SingerEntity extends BaseEntity{

    @Expose
    @Column(nullable = false)
    private String name;
    @Expose
    @Column(nullable = false, columnDefinition = "TEXT")
    private String careerInformation;


    public String getName(String name) {
        return this.name;
    }


    public String getCareerInformation(String careerInformation) {
        return this.careerInformation;
    }

    public SingerEntity setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
