package ru.karpunin.meteosensor.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karpunin.meteosensor.dto.MeasurementDTO;
import ru.karpunin.meteosensor.models.Measurement;
import ru.karpunin.meteosensor.repositories.MeasurementRepository;

import java.util.Date;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, ModelMapper modelMapper) {
        this.measurementRepository = measurementRepository;
        this.modelMapper = modelMapper;
    }

    public Measurement mapToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }
}
