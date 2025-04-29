package dev.haritch.carrental;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    public static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner intiDatabase(WarehouseRepository repository){
        return args -> {
            log.info("Loading "+repository.save(new CarStorage("Sedan", "Toyota", "AB1000", "1st Floor", "Camry", "White", true, "not Rental", "15000KM", null, null, 50)));
            log.info("Loading "+repository.save(new CarStorage("Suv", "Toyota", "CX8943", "2st floor", "Corolla Cross", "Grey", true, "not Rental", "5000km", null, null, 60)));
            log.info("Loading"+repository.save(new CarStorage("Suv", "Porsche", "PS1234", "3rd Floor", "Cayenne", "White", true, "not Rental", "1000km", null, null, 100)));
            log.info("Loading"+repository.save(new CarStorage("Sedan", "BMW", "KS9099", "3rd Floor", "M340i", "Black", true, "not Rental", "4700km", null, null, 80)));
        };
    }
}
