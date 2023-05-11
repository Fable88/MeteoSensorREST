package ru.karpunin.meteosensor.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.karpunin.meteosensor.models.Measurement;
import ru.karpunin.meteosensor.models.Sensor;
import ru.karpunin.meteosensor.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        Sensor sensor = measurement.getSensor();
        if (!sensorService.isRegistered(sensor.getName())) errors.rejectValue(
                "sensor", "", "Sensor is not registered");
    }
}
