package dev.haritch.carrental;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
public class CarrentalController {
    private final StorageRepository repository;
     
    CarrentalController(StorageRepository repository){
        this.repository = repository; //ใช้เข้าถึงฐานข้อมูล
    }

    @GetMapping("/carlists")
    List<Storage> findAll() {
        return repository.findAll();
    }

    @GetMapping("/carlists/id/{id}")
    Storage findOne(@PathVariable Long id) {
        Optional<Storage> carlists = repository.findById(id);
        if(carlists.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no car lists by given id");
        }
        return carlists.get();
    }

    @GetMapping("/carlists/licenseplate/{licensePlate}")
    Storage findOne(@PathVariable String licensePlate) {
        Optional<Storage> carlists = repository.findByLicensePlate(licensePlate);
        if(carlists.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no license plate by given");
        }
        return carlists.get();
    }

    @GetMapping("/carlists/type/{carType}")
    List<Storage> findcarByType(@PathVariable String carType) {
        List<Storage> carlists = repository.findByCarType(carType);
        if(carlists.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no car type by given");
        }
        return carlists;
    }
    
    @GetMapping("/carlists/brand/{carBrand}")
    List<Storage> findcarByBrand(@PathVariable String carBrand) {
        List<Storage> carlists = repository.findByCarBrand(carBrand);
        if(carlists.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no car brand by given");
        }
        return carlists;
    }

    @PutMapping("/carlists/{id}")
    Storage saveCarLists(@RequestBody Storage newCarLists, @PathVariable Long id) {
        return repository.findById(id).map(carlists -> {
            carlists.setCarBrand(newCarLists.getCarBrand());
            carlists.setCarColor(newCarLists.getCarColor());
            carlists.setCarInsurance(newCarLists.isCarInsurance());
            carlists.setCarLocation(newCarLists.getCarLocation());
            carlists.setCarModel(newCarLists.getCarModel());
            carlists.setCarStatus(newCarLists.getCarStatus());
            carlists.setCarType(newCarLists.getCarType());
            carlists.setLicensePlate(newCarLists.getLicensePlate());
            carlists.setMilege(newCarLists.getMilege());
            carlists.setPrice(newCarLists.getPrice());
            carlists.setRentalStartDate(newCarLists.getRentalStartDate());
            carlists.setRentalEndDate(newCarLists.getRentalEndDate());
            return repository.save(carlists);
        }).orElseGet(() -> {
            return repository.save(newCarLists);
        });
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/carlists/{id}")
    void deleteCarLists(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PostMapping("/carlists")
    Storage newCarLists(@RequestBody Storage carlists) {
        return repository.save(carlists);
    }

    @GetMapping("/carlists/price/lowtohigh")
    List<Storage> findcarByLowtoHighPrice() {
        return repository.findAll(Sort.by(Sort.Order.asc("price")));
    }
    
    @GetMapping("/carlists/price/hightolow")
    List<Storage> findcarByHightoLowPrice() {
        return repository.findAll(Sort.by(Sort.Order.desc("price")));
    }

    @PostMapping("/carlists/rentcar/{carId}")
    public String rentCar(@PathVariable Long carId, @RequestParam LocalDate rentalStartDate, @RequestParam int rentalDays) {
        return "Car rented successfully from " + rentalStartDate + " for " + rentalDays + " days.";
    }
    
    
}
