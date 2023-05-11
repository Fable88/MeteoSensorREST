package ru.karpunin.meteosensor.utils;

public class MeasurementNotAcceptedException extends RuntimeException {
    public MeasurementNotAcceptedException(String message) {
        super(message);
    }
}
