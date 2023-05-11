package ru.karpunin.meteosensor.utils;

public class SensorNotRegisteredException extends RuntimeException {

    public SensorNotRegisteredException(String message) {
        super(message);
    }
}
