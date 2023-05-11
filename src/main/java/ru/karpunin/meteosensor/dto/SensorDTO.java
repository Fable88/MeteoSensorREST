package ru.karpunin.meteosensor.dto;

import jakarta.validation.constraints.NotEmpty;
import ru.karpunin.meteosensor.models.Measurement;

import java.util.List;

public class SensorDTO {

    @NotEmpty
    private String name;
    private List<Measurement> measurements;

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
