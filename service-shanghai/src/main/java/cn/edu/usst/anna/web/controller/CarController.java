package cn.edu.usst.anna.web.controller;

import cn.edu.usst.anna.web.repository.CarRepository;
import cn.edu.usst.anna.web.entity.Car;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CarController {
    private static CarRepository carRepository;
    public CarController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @PostMapping(value = "/getCars")
    public static List getCars(@RequestBody Map message) {
        String id = (String)message.get("id");
        System.out.println("id:"+id);
        List<Car> cars = carRepository.findAll();
        List<String> result = new ArrayList<>();
        for(Car car : cars){
            if(car.getOid().equals(id) && car.getCid().equals("2")){
                result.add(car.getId());
                System.out.println("car.getId():"+car.getId());
            }
        }
        return result;
    }

}
