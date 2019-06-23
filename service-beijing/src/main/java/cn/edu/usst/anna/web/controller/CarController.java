package cn.edu.usst.anna.web.controller;

import cn.edu.usst.anna.web.entity.Ban;
import cn.edu.usst.anna.web.entity.Car;
import cn.edu.usst.anna.web.entity.User;
import cn.edu.usst.anna.web.repository.BanRepository;
import cn.edu.usst.anna.web.repository.CarRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CarController {
    private static CarRepository carRepository;
    private static BanRepository banRepository;
    public CarController(BanRepository banRepository,CarRepository carRepository){
        this.carRepository = carRepository;
        this.banRepository = banRepository;
    }

    @PostMapping(value = "/getCars")
    public static List<String> getCars(@RequestBody Map message) {
        String id = (String)message.get("id");
        List<Car> cars = carRepository.findAll();
        List<String> result = new ArrayList<>();
        for(Car car : cars){
            if(car.getOid().equals(id) && car.getCid().equals("1")){
                result.add(car.getId());
            }
        }
        return result;
    }

    @PostMapping(value = "/getCars1")
    public static List<String> getCars1(@RequestBody Map message) {
        String id = (String)message.get("id");
        List<Car> cars = carRepository.findAll();
        List<String> result = new ArrayList<>();
        for(Car car : cars){
            if(car.getOid().equals(id)){
                result.add(car.getId());
            }
        }
        return result;
    }

    @PostMapping(value = "/deleteCar")
    public static Map deleteCar(@RequestBody Map message) {
        String car1 = (String) message.get("car");
        System.out.println("car:"+car1);
        List<Ban> find_ban = banRepository.findAll();
        Optional<Car> find_result = carRepository.findById(car1);
        Iterator iterator=find_ban.iterator();
        while (iterator.hasNext()){
            Ban ban=(Ban) iterator.next();
            if (ban.getCarID().equals(car1))banRepository.delete(ban);
        }

        Map result = new HashMap();
        if (find_result.isPresent()) {
            Car car = find_result.get();
            carRepository.delete(car);
            result.put("result", "1");
        } else
            result.put("result", "2");

        return result;
    }

    @PostMapping(value = "/addCar")
    public static Map addCar(@RequestBody Map message) {
        String id=(String)message.get("id");
        String car1 = (String) message.get("car");
        Car car=new Car();
        car.setCid(car1);
        car.setId(id);
        car.setOid("1");
        carRepository.save(car);

        Map result = new HashMap();
        result.put("result","1");

        return result;
    }

}
