package com.ideaco.dia.backendproject;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstService {
    private PersonRepository personRepository;

    public FirstService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public String manipulateString(String value){
        return "Manipulated String = "+value;
    }

    public List<PersonModel> getAllPerson(){
        return personRepository.findAll();
    }

    public boolean createPerson(String name, int age, String job, String address){
        PersonModel personModel = new PersonModel();
        personModel.setName(name);
        personModel.setAge(age);
        personModel.setAddress(address);
        personModel.setJob(job);

        personRepository.save(personModel);

        return true;
    }
}
