package cn.edu.usst.anna.web.controller;

import cn.edu.usst.anna.web.entity.Owner;
import cn.edu.usst.anna.web.repository.OwnerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OwnerController {
    private static OwnerRepository ownerRepository;
    public OwnerController(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }

    @GetMapping(value = "/getOwners")
    public static List<Owner> getOwners(){

        List<Owner> list = ownerRepository.findAll();
        List<Owner> result = new ArrayList<>();
        for (Owner owner : list) {
            if(owner.getCity().equals("3"))
                result.add(owner);
        }

        return result;
    }
}
