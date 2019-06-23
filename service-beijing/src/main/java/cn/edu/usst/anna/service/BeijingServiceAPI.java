package cn.edu.usst.anna.service;

import cn.edu.usst.anna.web.controller.*;
import cn.edu.usst.anna.web.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class BeijingServiceAPI {

    String portS = "SERVICE-SHANGHAI";
    String portG = "SERVICE-GUANGDONG";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/beijing/getLoginResult")
    public Map getLoginResult(@RequestBody Map message){

        Map result =  UserController.getLoginResult(message);
        if(result.get("result").equals("2")){
            ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/getLoginResult", message, Map.class);
            result = (Map)responseEntity1.getBody();

            if(result.get("result").equals("2")){
                ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portG+"/getLoginResult", message, Map.class);
                result = (Map)responseEntity2.getBody();
            }
        }
        System.out.println("getLoginResult result:"+result.get("result"));
        return result;
    }

    @PostMapping("/beijing/getCars")
    public List getCars(@RequestBody Map message){

        String city_id = (String)message.get("city_id");
        List result = new ArrayList();
        System.out.println("city"+city_id);
        if(city_id.equals("1")){
            result =  CarController.getCars(message);
        }else if(city_id.equals("2")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portS+"/getCars", message, List.class);
            result = (List)responseEntity.getBody();
        }else if(city_id.equals("3")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portG+"/getCars", message, List.class);
            result = (List)responseEntity.getBody();
        }
        System.out.println("result.size:"+result.size());
        return result;
    }

    @PostMapping("/beijing/getCars1")
    public List getCars1(@RequestBody Map message){
        List result = new ArrayList();
        result =  CarController.getCars1(message);

        return result;
    }

    @PostMapping("/beijing/getBans0")
    public List getBans0(@RequestBody Map message){

        List result =  OwnersbanController.getBans0(message);
        ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/getBans0", message, List.class);

        result.addAll((List)responseEntity1.getBody());
        ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portG+"/getBans0", message, List.class);
        result.addAll((List)responseEntity2.getBody());

        return result;
    }

    @PostMapping("/beijing/getBans1")
    public List getBans1(@RequestBody Map message){

        List result =  OwnersbanController.getBans1(message);
        ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/getBans1", message, List.class);
        result.addAll((List)responseEntity1.getBody());
        ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portG+"/getBans1", message, List.class);
        result.addAll((List)responseEntity2.getBody());

        return result;
    }

    @PostMapping("/beijing/charge")
    public Map charge(@RequestBody Map message){

        Map result = BanController.charge(message);

        if(result.get("result").equals("2")){
            ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/charge", message, Map.class);
            result = (Map)responseEntity1.getBody();
            if(result.get("result").equals("2")){
                ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portG+"/charge", message, Map.class);
                result = (Map)responseEntity2.getBody();
            }
        }
        return result;
    }

    @PostMapping("/beijing/ban")
    public Map ban(@RequestBody Map message){

        String city_id = (String)message.get("city_id");
        Map result = new HashMap();

        if(city_id.equals("1")){
            result =  BanController.ban(message);
        }else if(city_id.equals("2")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portS+"/ban", message, Map.class);
            result = (Map)responseEntity.getBody();
        }else if(city_id.equals("3")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portG+"/ban", message, Map.class);
            result = (Map)responseEntity.getBody();
        }
        return result;
    }

    @GetMapping("/beijing/getCops")
    public List getCops(){

        List result =  CopController.getCops();

        ResponseEntity responseEntity1 = restTemplate.getForEntity("http://"+portS+"/getCops", List.class);
        result.addAll((List)responseEntity1.getBody());

        ResponseEntity responseEntity2 = restTemplate.getForEntity("http://"+portG+"/getCops", List.class);
        result.addAll((List)responseEntity2.getBody());
        return result;
    }

    @GetMapping("/beijing/getOwners")
    public List getOwners(){

        List result =  OwnerController.getOwners();

        ResponseEntity responseEntity1 = restTemplate.getForEntity("http://"+portS+"/getOwners", List.class);
        result.addAll((List)responseEntity1.getBody());

        ResponseEntity responseEntity2 = restTemplate.getForEntity("http://"+portG+"/getOwners", List.class);
        result.addAll((List)responseEntity2.getBody());

        return result;
    }

    @PostMapping("/beijing/edit")
    public Map edit(@RequestBody Map message){

        Map result =  UserController.edit(message);
        if(result.get("result").equals("2")){
            ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/edit", message, Map.class);
            result = (Map)responseEntity1.getBody();
            if(result.get("result").equals("2")){
                ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portG+"/edit", message, Map.class);
                result = (Map)responseEntity2.getBody();
            }
        }
        return result;
    }

    @PostMapping("/beijing/addOwner")
    public Map addOwner(@RequestBody Map message){
        String username = (String) message.get("userName");
        System.out.println("laile"+username);
        Map result =  UserController.addOwner(message);

        return result;
    }

    @PostMapping("/beijing/addCop")
    public Map addCop(@RequestBody Map message){

        Map result =  UserController.addCop(message);

        return result;
    }

    @PostMapping("/beijing/deleteUser")
    public Map deleteUser(@RequestBody Map message){

        Map result =  UserController.deleteUser(message);

        return result;
    }

    @PostMapping("/beijing/deleteCar")
    public Map deleteCar(@RequestBody Map message){

        Map result =  CarController.deleteCar(message);

        return result;
    }

    @PostMapping("/beijing/addCar")
    public Map addCar(@RequestBody Map message){

        Map result =  CarController.addCar(message);

        return result;
    }
}
