package ru.karpunin.meteosensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karpunin.meteosensor.models.Measurement;
import java.util.Date;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Date> {
    int countByIsRainingTrue();
}
