package ru.karpunin.meteosensor.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.karpunin.meteosensor.dto.MeasurementDTO;
import ru.karpunin.meteosensor.models.Measurement;
import ru.karpunin.meteosensor.services.MeasurementService;
import ru.karpunin.meteosensor.utils.MeasurementErrorResponse;
import ru.karpunin.meteosensor.utils.MeasurementNotAcceptedException;
import ru.karpunin.meteosensor.validators.MeasurementValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public HttpEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                 BindingResult bindingResult) {
        Measurement measurement = mapToMeasurement(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errorMsg.append(fieldError.getField()).append(" - ")
                        .append(fieldError.getDefaultMessage());
            }
            throw new MeasurementNotAcceptedException(errorMsg.toString());
        }
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.getAllMeasurements().stream()
                .map(this::mapToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotAcceptedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public Measurement mapToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO mapToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
