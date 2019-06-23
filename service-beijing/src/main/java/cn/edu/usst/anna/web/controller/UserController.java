package cn.edu.usst.anna.web.controller;

import cn.edu.usst.anna.web.entity.User;
import cn.edu.usst.anna.web.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    private static UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/getLoginResult")
    public static Map getLoginResult(@RequestBody Map message){
        String username = (String)message.get("userName");
        String password = (String)message.get("password");

//        System.out.println("username:"+username);
//        System.out.println("password:"+password);

        Optional<User> find_result = userRepository.findById(username);

        Map result = new HashMap();
        if(find_result.isPresent()){
            User user = find_result.get();
            if(user.getCity()==null || user.getCity().equals("1")){
                if(user.getPassword().equals(password)){
                    result.put("result","1");
                    result.put("username",user.getUsername());
                    result.put("password",user.getPassword());
                    result.put("who",user.getWho());
                    result.put("name",user.getName());
                    result.put("phoneNumber",user.getPhone());
                    result.put("city_id",user.getCity());
                }
                else
                    result.put("result","3");
            }else
                result.put("result","2");
        }else
            result.put("result","2");

        return result;
    }

    @PostMapping(value = "/getResult")
    public static Map getResult(@RequestBody Map message){
        String username = (String)message.get("userName");

        Optional<User> find_result = userRepository.findById(username);

        Map result = new HashMap();
        if(find_result.isPresent()){
            User user = find_result.get();
            if(user.getCity()==null || user.getCity().equals("1")){
                result.put("result","1");
                result.put("username",user.getUsername());
                result.put("password",user.getPassword());
                result.put("who",user.getWho());
                result.put("name",user.getName());
                result.put("phoneNumber",user.getPhone());
                result.put("city_id",user.getCity());
            }else
                result.put("result","2");
        }else
            result.put("result","2");

        return result;
    }

    @PostMapping(value = "/edit")
    public static Map edit(@RequestBody Map message) {
        String username = (String) message.get("userName");
        String password = (String) message.get("password");

        Optional<User> find_result = userRepository.findById(username);

        Map result = new HashMap();
        if (find_result.isPresent()) {
            User user = find_result.get();
            if(user.getCity().equals("1")){
                user.setPassword(password);
                userRepository.save(user);
                result.put("result", "1");
            } else
                result.put("result", "2");
        } else
            result.put("result", "2");

        return result;
    }

    @PostMapping(value = "/addOwner")
    public static Map addOwner(@RequestBody Map message) {
        String username = (String) message.get("userName");
        String password = (String) message.get("password");
        String city = (String) message.get("local");
        String name = (String) message.get("name");
        String phone = (String) message.get("phone");
        User user=new User();
        user.setCity("1");
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUsername(username);
        user.setWho("owner");
        System.out.println("username:"+username);
        userRepository.save(user);
        Map result = new HashMap();
        result.put("result", "1");

        return result;
    }

    @PostMapping(value = "/addCop")
    public static Map addCop(@RequestBody Map message) {
        String username = (String) message.get("userName");
        String password = (String) message.get("password");
        String name = (String) message.get("name");
        String phone = (String) message.get("phone");
        User user=new User();
        user.setCity("1");
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUsername(username);
        user.setWho("cop");
        userRepository.save(user);
        Map result = new HashMap();
        result.put("result", "1");

        return result;
    }

    @PostMapping(value = "/deleteUser")
    public static Map deleteUser(@RequestBody Map message) {
        String username = (String) message.get("userName");

        Optional<User> find_result = userRepository.findById(username);

        Map result = new HashMap();
        if (find_result.isPresent()) {
            User user = find_result.get();
            userRepository.delete(user);
            result.put("result", "1");
        } else
            result.put("result", "2");

        return result;
    }

}
