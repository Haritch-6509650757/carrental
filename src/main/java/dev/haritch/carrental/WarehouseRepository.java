package dev.haritch.carrental;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface WarehouseRepository extends JpaRepository<CarStorage, Long>  {
    List<CarStorage> findByCarType(String carType);
    List<CarStorage> findByCarBrand(String CarBrand);
    Optional<CarStorage> findByLicensePlate(String licensePlate);
    List<CarStorage> findByCarStatus(String carStatus);
}
