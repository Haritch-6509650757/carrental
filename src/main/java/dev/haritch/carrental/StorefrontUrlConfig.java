package dev.haritch.carrental;

import org.springframework.stereotype.Component;

@Component
public class StorefrontUrlConfig {
    private String searchCar;
    private String returnCar;
    private String orderCar;
   
    public String getSearchCar() {
        return searchCar;
    }
    
    public String getReturnCar() {
        return returnCar;
    }

    public String getOrderCar() {
        return orderCar;
    }

    public void setSearchCar(String searchCar) {
        this.searchCar = searchCar;
    }

    public void setReturnCar(String returnCar) {
        this.returnCar = returnCar;
    }

    public void setOrderCar(String orderCar) {
        this.orderCar = orderCar;
    }    
}
