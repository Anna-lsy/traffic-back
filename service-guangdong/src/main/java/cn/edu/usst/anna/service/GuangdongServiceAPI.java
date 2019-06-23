package cn.edu.usst.anna.service;

import cn.edu.usst.anna.web.controller.*;
import cn.edu.usst.anna.web.entity.Ownersban;
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
public class GuangdongServiceAPI {

    String portB = "SERVICE-BEIJING";
    String portS = "SERVICE-SHANGHAI";
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/guangdong/getLoginResult")
    public Map getLoginResult(@RequestBody Map message){

        Map result =  UserController.getLoginResult(message);
        if(result.get("result").equals("2")){
            ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/getLoginResult", message, Map.class);
            result = (Map)responseEntity1.getBody();

            if(result.get("result").equals("2")){
                ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portB+"/getLoginResult", message, Map.class);
                result = (Map)responseEntity2.getBody();
            }
        }
//        System.out.println("getLoginResult result:"+result.get("result"));
        return result;
    }

    @PostMapping("/guangdong/getCars")
    public List getCars(@RequestBody Map message){

        String city_id = (String)message.get("city_id");
        List result = new ArrayList();

        if(city_id.equals("1")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portB+"/getCars", message, List.class);
            result = (List)responseEntity.getBody();
        }else if(city_id.equals("2")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portS+"/getCars", message, List.class);
            result = (List)responseEntity.getBody();
        }else if(city_id.equals("3")){
            result =  CarController.getCars(message);
        }
        return result;
    }

    @PostMapping("/guangdong/getBans0")
    public List<Ownersban> getBans0(@RequestBody Map message){

        List result =  OwnersbanController.getBans0(message);
        ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/getBans0", message, List.class);
        result.addAll((List)responseEntity1.getBody());
        ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portB+"/getBans0", message, List.class);
        result.addAll((List)responseEntity2.getBody());

        return result;
    }

    @PostMapping("/guangdong/getBans1")
    public List getBans1(@RequestBody Map message){

        List result =  OwnersbanController.getBans1(message);
        ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/getBans1", message, List.class);
        result.addAll((List)responseEntity1.getBody());
        ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portB+"/getBans1", message, List.class);
        result.addAll((List)responseEntity2.getBody());

        return result;
    }
    @PostMapping("/guangdong/charge")
    public Map charge(@RequestBody Map message){

        Map result = BanController.charge(message);

        if(result.get("result").equals("2")){
            ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/charge", message, Map.class);
            result = (Map)responseEntity1.getBody();
            if(result.get("result").equals("2")){
                ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portB+"/charge", message, Map.class);
                result = (Map)responseEntity2.getBody();
            }
        }
        return result;
    }

    @PostMapping("/guangdong/ban")
    public Map ban(@RequestBody Map message){

        String city_id = (String)message.get("city_id");
        Map result = new HashMap();

        if(city_id.equals("1")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portB+"/ban", message, Map.class);
            result = (Map)responseEntity.getBody();
        }else if(city_id.equals("2")){
            ResponseEntity responseEntity = restTemplate.postForEntity("http://"+portS+"/ban", message, Map.class);
            result = (Map)responseEntity.getBody();
        }else if(city_id.equals("3")){
            result =  BanController.ban(message);
        }
        return result;
    }

    @GetMapping("/guangdong/getCops")
    public List getCops(){

        List result =  CopController.getCops();

        ResponseEntity responseEntity1 = restTemplate.getForEntity("http://"+portS+"/getCops", List.class);
        result.addAll((List)responseEntity1.getBody());

        ResponseEntity responseEntity2 = restTemplate.getForEntity("http://"+portB+"/getCops", List.class);
        result.addAll((List)responseEntity2.getBody());
        return result;
    }

    @GetMapping("/guangdong/getOwners")
    public List getOwners(){

        List result =  OwnerController.getOwners();

        ResponseEntity responseEntity1 = restTemplate.getForEntity("http://"+portS+"/getOwners", List.class);
        result.addAll((List)responseEntity1.getBody());

        ResponseEntity responseEntity2 = restTemplate.getForEntity("http://"+portB+"/getOwners", List.class);
        result.addAll((List)responseEntity2.getBody());

        return result;
    }

    @PostMapping("/guangdong/edit")
    public Map edit(@RequestBody Map message){

        Map result =  UserController.edit(message);
        if(result.get("result").equals("2")){
            ResponseEntity responseEntity1 = restTemplate.postForEntity("http://"+portS+"/edit", message, Map.class);
            result = (Map)responseEntity1.getBody();
            if(result.get("result").equals("2")){
                ResponseEntity responseEntity2 = restTemplate.postForEntity("http://"+portB+"/edit", message, Map.class);
                result = (Map)responseEntity2.getBody();
            }
        }
        return result;
    }

    @PostMapping("/guangdong/addOwner")
    public Map addOwner(@RequestBody Map message){
        String username = (String) message.get("userName");
        System.out.println("laile"+username);
        Map result =  UserController.addOwner(message);

        return result;
    }

    @PostMapping("/guangdong/addCop")
    public Map addCop(@RequestBody Map message){

        Map result =  UserController.addCop(message);

        return result;
    }

    @PostMapping("/guangdong/deleteUser")
    public Map deleteUser(@RequestBody Map message){

        Map result =  UserController.deleteUser(message);

        return result;
    }

    @PostMapping("/guangdong/deleteCar")
    public Map deleteCar(@RequestBody Map message){

        Map result =  CarController.deleteCar(message);

        return result;
    }

    @PostMapping("/guangdong/addCar")
    public Map addCar(@RequestBody Map message){

        Map result =  CarController.addCar(message);

        return result;
    }

    @PostMapping("/guangdong/getCars1")
    public List getCars1(@RequestBody Map message){
        List result = new ArrayList();
        result =  CarController.getCars1(message);

        return result;
    }

}
