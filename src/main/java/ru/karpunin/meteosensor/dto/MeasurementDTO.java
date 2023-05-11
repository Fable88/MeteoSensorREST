package ru.karpunin.meteosensor.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.karpunin.meteosensor.models.Sensor;

import java.util.Date;

public class MeasurementDTO {

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Min(-100)
    @Max(100)
    private double value;

    @NotNull
    private boolean isRaining;
    private Sensor sensor;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
