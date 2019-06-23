package cn.edu.usst.anna.web.controller;

import cn.edu.usst.anna.web.repository.UserRepository;
import cn.edu.usst.anna.web.entity.User;
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
            if(user.getCity()==null || user.getCity().equals("2")){
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

    @PostMapping(value = "/edit")
    public static Map edit(@RequestBody Map message) {
        String username = (String) message.get("userName");
        String password = (String) message.get("password");

        Optional<User> find_result = userRepository.findById(username);

        Map result = new HashMap();
        if (find_result.isPresent()) {
            User user = find_result.get();
            if(user.getCity().equals("2")){
                user.setPassword(password);
                userRepository.save(user);
                result.put("result", "1");
            } else
                result.put("result", "2");
        } else
            result.put("result", "2");

        return result;
    }

}
