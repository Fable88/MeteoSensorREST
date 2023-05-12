package ru.karpunin.meteosensor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karpunin.meteosensor.models.Measurement;
import ru.karpunin.meteosensor.repositories.MeasurementRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    public int getRainyDaysCount() {
        return measurementRepository.countByIsRainingTrue();
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }
}
