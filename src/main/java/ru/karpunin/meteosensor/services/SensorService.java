package ru.karpunin.meteosensor.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karpunin.meteosensor.dto.SensorDTO;
import ru.karpunin.meteosensor.models.Sensor;
import ru.karpunin.meteosensor.repositories.SensorRepository;
import ru.karpunin.meteosensor.utils.SensorNotFoundException;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public Sensor findByName(String name) {
        return sensorRepository.findById(name).orElseThrow(SensorNotFoundException::new);
    }

    public boolean isRegistered(String name) {
        return sensorRepository.findById(name).isPresent();
    }

    public Sensor mapToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
