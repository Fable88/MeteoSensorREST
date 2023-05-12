package ru.karpunin.meteosensor.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @Column(name = "name")
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public Sensor(String name, List<Measurement> measurements) {
        this.name = name;
        this.measurements = measurements;
    }

    public Sensor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
