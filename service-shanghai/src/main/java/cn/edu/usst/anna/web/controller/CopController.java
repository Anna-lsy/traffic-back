package cn.edu.usst.anna.web.controller;

import cn.edu.usst.anna.web.entity.Cop;
import cn.edu.usst.anna.web.repository.CopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CopController {
    private static CopRepository copRepository;
    public CopController(CopRepository copRepository){
        this.copRepository = copRepository;
    }

    @GetMapping(value = "/getCops")
    public static List<Cop> getCops(){
        List<Cop> list = copRepository.findAll();
        List<Cop> result = new ArrayList<>();
        for (Cop cop : list) {
            if(cop.getCity().equals("2"))
                result.add(cop);
        }

        return result;
    }
}
