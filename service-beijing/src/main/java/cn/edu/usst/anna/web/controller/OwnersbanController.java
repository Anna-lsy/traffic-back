package cn.edu.usst.anna.web.controller;

import cn.edu.usst.anna.web.entity.Ownersban;
import cn.edu.usst.anna.web.repository.OwnersbanRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OwnersbanController {
    private static OwnersbanRepository ownersbanRepository;
    public OwnersbanController(OwnersbanRepository ownersbanRepository){
        this.ownersbanRepository = ownersbanRepository;
    }


    @PostMapping(value = "/getBans0")
    public static List<Ownersban> getBans0(@RequestBody Map message) {
        String id = (String)message.get("id");
        List<Ownersban> ownersbans = ownersbanRepository.findAll();
        List<Ownersban> ban0 = new ArrayList<>();
        for(Ownersban ownersban : ownersbans){
            if(ownersban.getOid().equals(id) && ownersban.getStatus().equals("0") && ownersban.getCity().equals("1")){
//                System.out.println("ban0:"+ ownersban.getId());
                ban0.add(ownersban);
            }
        }
        return ban0;
    }

    @PostMapping(value = "/getBans1")
    public static List<Ownersban> getBans1(@RequestBody Map message) {
        String id = (String)message.get("id");
        List<Ownersban> ownersbans = ownersbanRepository.findAll();
        List<Ownersban> ban1 = new ArrayList<>();
        for(Ownersban ownersban : ownersbans){
            if(ownersban.getOid().equals(id) && ownersban.getStatus().equals("1") && ownersban.getCity().equals("1")){
//                System.out.println("ban1:"+ ownersban.getId());
                ban1.add(ownersban);
            }
        }
        return ban1;
    }
}