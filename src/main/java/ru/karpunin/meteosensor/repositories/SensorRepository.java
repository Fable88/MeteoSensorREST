package ru.karpunin.meteosensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karpunin.meteosensor.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, String> {
}
