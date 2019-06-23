package cn.edu.usst.anna.web.controller;

import cn.edu.usst.anna.web.entity.Ban;
import cn.edu.usst.anna.web.repository.BanRepository;
import cn.edu.usst.anna.web.repository.CarRepository;
import cn.edu.usst.anna.web.entity.Car;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class BanController {

    private static BanRepository banRepository;
    private static CarRepository carRepository;
    public BanController(BanRepository banRepository, CarRepository carRepository){
        this.banRepository = banRepository;
        this.carRepository = carRepository;
    }

    @PostMapping(value = "/charge")
    public static Map charge(@RequestBody Map message){
        Integer id = Integer.parseInt((String)message.get("ban_id"));

        Optional<Ban> find_result = banRepository.findById(id);

        Map result = new HashMap();
        if(find_result.isPresent()){
            Ban ban = find_result.get();
            if(ban.getCity().equals("2")){
                if(ban.getStatus().equals("0")){
                    ban.setStatus("1");
                    banRepository.save(ban);
                    result.put("result","1");
                }
                else
                    result.put("result","3");
            }else
                result.put("result","2");
        }else
            result.put("result","2");

        return result;
    }

    @PostMapping(value = "/ban")
    public static Map ban(@RequestBody Map postmessage){
        String copID = (String)postmessage.get("cop_id");
        String carID = (String)postmessage.get("car_id");
        String message = (String)postmessage.get("message");
        String fine = (String)postmessage.get("fine");
        String time = (String)postmessage.get("time");
        String city = (String)postmessage.get("city_id");

        Optional<Car> find_result = carRepository.findById(carID);

        Map result = new HashMap();
        if(find_result.isPresent()){
            Ban ban = new Ban(carID, time, fine, message, "0", copID, city);
            banRepository.save(ban);
            result.put("result","1");
        }else
            result.put("result","2");

        return result;
    }

}
