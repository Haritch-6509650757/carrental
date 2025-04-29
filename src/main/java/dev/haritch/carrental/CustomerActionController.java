package dev.haritch.carrental;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class CustomerActionController {

    private final WarehouseRepository repository;
    private final RestTemplate restTemplate;
    private final StorefrontUrlConfig storefrontUrl;
     
    public CustomerActionController(WarehouseRepository repository, RestTemplate restTemplate, StorefrontUrlConfig storefrontUrl){
        this.repository = repository; //ใช้เข้าถึงฐานข้อมูล
        this.restTemplate = restTemplate;
        this.storefrontUrl = storefrontUrl;
    }

    @PutMapping("/sendcar/{rentalId}/requestcustomeraction")
    public ResponseEntity<String> requestCustomerAction(){
        List<CarStorage> carlist = repository.findByCarStatus("customer not show up");

        if(carlist.isEmpty()){
            return ResponseEntity.ok("No cars with status 'customer not show up'");
        }

        for(CarStorage car : carlist){
            String url = storefrontUrl.getOrderCar() + "/ordercar/" + car.getLicensePlate();

            Map<String, String> payload = new HashMap<>();
            payload.put("licensePlate", car.getLicensePlate());
            payload.put("carStatus", car.getCarStatus());

            try{
                restTemplate.put(url, payload);
            } catch (Exception ex){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send to storefront for licenplate: " + car.getLicensePlate());
            }
        }
        return ResponseEntity.ok("All eligible car data sent to storefront");
    }
}
