package dev.haritch.carrental;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/warehouse")
public class ReturnCarController {

    private final WarehouseRepository repository;
    private final RestTemplate restTemplate;
    private final StorefrontUrlConfig storefrontUrl;
     
    public ReturnCarController(WarehouseRepository repository, RestTemplate restTemplate, StorefrontUrlConfig storefrontUrl){
        this.repository = repository; //ใช้เข้าถึงฐานข้อมูล
        this.restTemplate = restTemplate;
        this.storefrontUrl = storefrontUrl;
    }

    @GetMapping("/returncar")
    public String searchCar() {
        String returnCarInfo = restTemplate.getForObject(storefrontUrl.getSearchCar(), String.class);
        return "Search completed and storefront responded: " + returnCarInfo;
    }
    
}
