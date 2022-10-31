package com.ideaco.dia.backendproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/first")
public class FirstController {
    private FirstService firstService;

    @Autowired
    public FirstController(FirstService firstService) {
        this.firstService = firstService;
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return firstService.manipulateString("Welcome");
    }

    @GetMapping("/persons")
    public List<PersonModel> getAllPerson() {
        return firstService.getAllPerson();
    }

    @PostMapping("/person")
    public String createPerson(@RequestParam("name") String name,
                               @RequestParam("age") int age,
                               @RequestParam("job") String job,
                               @RequestParam("address") String address) {
        if (firstService.createPerson(name, age, job, address)) {
            return "Sukses insert person";
        } else {
            return "Failed insert person";
        }

    }

    @PostMapping("/person/body")
    public String createPersonWithBody(@RequestBody PersonRequest personRequest) {
        if (firstService.createPerson(personRequest.getName(),
                personRequest.getAge(), personRequest.getJob(),
                personRequest.getAddress())) {
            return "Sukses insert person";
        } else {
            return "Failed insert person";
        }
    }

    @PostMapping("/persons/body")
    public String createPersonsWithBody(@RequestBody List<PersonRequest> personRequests) {
        for (PersonRequest person : personRequests) {
            firstService.createPerson(person.getName(),
                    person.getAge(), person.getJob(),
                    person.getAddress());
        }

        return "Sukses insert person";

    }

    @GetMapping("/person/{personId}")
    public PersonModel getPerson(@PathVariable int personId){
        PersonModel person = firstService.getPerson(personId);
        if (person == null){
            return new PersonModel();
        }else {
            return person;
        }
    }

    @GetMapping("/person/param")
    public PersonModel getPersonParam(@RequestParam("personId")int personId){
        PersonModel person = firstService.getPerson(personId);
        if (person == null){
            return new PersonModel();
        }else {
            return person;
        }
    }

    @GetMapping("/person/body")
    public PersonModel getPersonBody(@RequestBody PersonIdRequest personIdRequest){
        PersonModel person = firstService.getPerson(personIdRequest.getPersonId());
        if (person == null){
            return new PersonModel();
        }else {
            return person;
        }
    }

    @GetMapping("/person/address/{address}")
    public PersonModel getByAddress(@PathVariable("address")String address){
        PersonModel person = firstService.getByAddress(address);
        if (person == null){
            return new PersonModel();
        }else {
            return person;
        }

    }

    @GetMapping("/person/age/{age}")
    public List<PersonModel> getPersonByAge(@PathVariable("age")int age){
        return firstService.getPersonByAge(age);
    }

    @GetMapping("/person/get/{name}")
    public List<PersonModel> getPersons(@PathVariable("name") String name){
        return firstService.getPersons(name);
    }

    @GetMapping("/person/age/jpql/{age}")
    public List<PersonModel> getPersonsByAge(@PathVariable("age") int age){
        return firstService.getPersonsByAge(age);
    }

    @PutMapping("/person/update/{personId}")
    public PersonModel updatePerson(@PathVariable("personId")int personId, @RequestBody PersonModel personModel){
        PersonModel person = firstService.updatePerson(personId, personModel);

        if (person == null){
            return null;
        }else {
            return person;
        }
    }

    @PatchMapping("/person/patch/{personId}")
    public PersonModel updateJob(@PathVariable("personId")int personId, @RequestParam("job")String job){
        PersonModel personModel = firstService.updateJob(personId, job);

        if (personModel == null){
            return null;
        }else {
            return personModel;
        }
    }

    @DeleteMapping("/person/delete/{personId}")
    public String deletePerson(@PathVariable("personId")int personId){

        if (firstService.deletePerson(personId)){
            return "Success";
        }else {
            return "Failed";
        }
    }

}
