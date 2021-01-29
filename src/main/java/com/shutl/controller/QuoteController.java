package com.shutl.controller;

import com.shutl.model.Quote;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class QuoteController {

    Map<String, Integer> myMap = new HashMap<String, Integer>(){{
        put("bicycle", 10);
        put("motorbike", 15);
        put("parcel_car", 20);
        put("small_van", 30);
        put("large_van", 40);

    }};


    @RequestMapping(value = "/quote", method = POST)
    public @ResponseBody Quote quote(@RequestBody Quote quote) {

        Long price = Math.abs((Long.valueOf(quote.getDeliveryPostcode(), 36) -
                Long.valueOf(quote.getPickupPostcode(), 36))/100000000);
//        System.out.println(price);
//        System.out.println(quote.getVehicleType());
        price+= Double.valueOf(calculatePercentage(myMap.get(quote.getVehicleType()),price)).longValue();

try {
    Thread.sleep(3000);
}
catch (Exception e){

}
        System.out.println(price);
        return new Quote(quote.getPickupPostcode(), quote.getDeliveryPostcode(),
                quote.getVehicleType(),price);
    }

    public double calculatePercentage(double percentage, double total) {
        return (percentage * total) / 100;
    }
}
