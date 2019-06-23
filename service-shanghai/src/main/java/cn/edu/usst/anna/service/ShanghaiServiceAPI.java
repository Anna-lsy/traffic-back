package cn.edu.usst.anna.service;

import cn.edu.usst.anna.web.controller.*;
import cn.edu.usst.anna.web.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class ShanghaiServiceAPI {

    String portB = "SERVICE-BEIJING";
    String portG = "SERVICE-GUANGDONG";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/shanghai/getLoginResult")
    public Map getLoginResult(@RequestBody Map message){

        Map result =  UserController.getLoginResult(message);
        if(result.get("result").equals("2")){
            ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portG+"/getLoginResult", message, Map.class);
            result = (Map)responseEntity1.getBody();

            if(result.get("result").equals("2")){
                ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portB+"/getLoginResult", message, Map.class);
                result = (Map)responseEntity2.getBody();
            }
        }
//        System.out.println("getLoginResult result:"+result.get("result"));
        return result;
    }

    @PostMapping("/shanghai/getCars")
    public List getCars(@RequestBody Map message){

        String city_id = (String)message.get("city_id");
        List result = new ArrayList();

        if(city_id.equals("1")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portB+"/getCars", message, List.class);
            result = (List)responseEntity.getBody();
        }else if(city_id.equals("2")){
            result =  CarController.getCars(message);
        }else if(city_id.equals("3")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portG+"/getCars", message, List.class);
            result = (List)responseEntity.getBody();
        }
        return result;
    }

    @PostMapping("/shanghai/getBans0")
    public List getBans0(@RequestBody Map message){

        List result =  OwnersbanController.getBans0(message);
        ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portB+"/getBans0", message, List.class);
        result.addAll((List)responseEntity1.getBody());
        ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portG+"/getBans0", message, List.class);
        result.addAll((List)responseEntity2.getBody());

        return result;
    }

    @PostMapping("/shanghai/getBans1")
    public List getBans1(@RequestBody Map message){

        List result =  OwnersbanController.getBans1(message);
        ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portB+"/getBans1", message, List.class);
        result.addAll((List)responseEntity1.getBody());
        ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portG+"/getBans1", message, List.class);
        result.addAll((List)responseEntity2.getBody());

        return result;
    }

    @PostMapping("/shanghai/charge")
    public Map charge(@RequestBody Map message){

        Map result = BanController.charge(message);

        if(result.get("result").equals("2")){
            ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portB+"/charge", message, Map.class);
            result = (Map)responseEntity1.getBody();
            if(result.get("result").equals("2")){
                ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portG+"/charge", message, Map.class);
                result = (Map)responseEntity2.getBody();
            }
        }
        return result;
    }

    @PostMapping("/shanghai/ban")
    public Map ban(@RequestBody Map message){

        String city_id = (String)message.get("city_id");
        Map result = new HashMap();

        if(city_id.equals("1")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portB+"/ban", message, Map.class);
            result = (Map)responseEntity.getBody();
        }else if(city_id.equals("2")){
            result =  BanController.ban(message);
        }else if(city_id.equals("3")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portG+"/ban", message, Map.class);
            result = (Map)responseEntity.getBody();
        }
        return result;
    }

    @GetMapping("/shanghai/getCops")
    public List getCops(){

        List result =  CopController.getCops();

        ResponseEntity responseEntity1 = restTemplate.getForEntity("http://"+portB+"/getCops", List.class);
        result.addAll((List)responseEntity1.getBody());

        ResponseEntity responseEntity2 = restTemplate.getForEntity("http://"+portG+"/getCops", List.class);
        result.addAll((List)responseEntity2.getBody());
        return result;
    }

    @GetMapping("/shanghai/getOwners")
    public List getOwners(){

        List result =  OwnerController.getOwners();

        ResponseEntity responseEntity1 = restTemplate.getForEntity("http://"+portB+"/getOwners", List.class);
        result.addAll((List)responseEntity1.getBody());

        ResponseEntity responseEntity2 = restTemplate.getForEntity("http://"+portG+"/getOwners", List.class);
        result.addAll((List)responseEntity2.getBody());

        return result;
    }

    @PostMapping("/shanghai/edit")
    public Map edit(@RequestBody Map message){

        Map result =  UserController.edit(message);
        if(result.get("result").equals("2")){
            ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portB+"/edit", message, Map.class);
            result = (Map)responseEntity1.getBody();
            if(result.get("result").equals("2")){
                ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portG+"/edit", message, Map.class);
                result = (Map)responseEntity2.getBody();
            }
        }
        return result;
    }
}
