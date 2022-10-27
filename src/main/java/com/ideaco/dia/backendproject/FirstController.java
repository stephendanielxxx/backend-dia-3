package com.ideaco.dia.backendproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/first")
public class FirstController {
    private FirstService firstService;

    @Autowired
    public FirstController(FirstService firstService){
        this.firstService = firstService;
    }

    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return firstService.manipulateString("Welcome");
    }

    @GetMapping("/persons")
    public List<PersonModel> getAllPerson(){
        return firstService.getAllPerson();
    }

    @PostMapping("/person")
    public String createPerson(@RequestParam("name") String name,
                               @RequestParam("age") int age,
                               @RequestParam("job") String job,
                               @RequestParam("address") String address){
        if(firstService.createPerson(name, age, job, address)){
            return "Sukses insert person";
        }else {
            return "Failed insert person";
        }

    }

}
