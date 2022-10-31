package com.ideaco.dia.backendproject;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public PersonModel getPerson(int personId){
        //validation
        Optional<PersonModel> personOpt = personRepository.findById(personId);
        if (personOpt.isEmpty()){
            return null;
        }else {
            return personOpt.get();
        }
    }

    public PersonModel getByAddress(String address){
        Optional<PersonModel> personOpt = personRepository.findByAddress(address);
        if (personOpt.isEmpty()){
            return null;
        }else {
            return personOpt.get();
        }
    }

    public List<PersonModel> getPersonByAge(int age){
        List<PersonModel> persons = personRepository.findByAgeGreaterThan(age);
        return persons;
    }

    //native query
    public List<PersonModel> getPersons(String name){
        return personRepository.getPersons(name);
    }

    //jpql
    public List<PersonModel> getPersonsByAge(int age){
        return personRepository.getPersonsByAgeJpql(age);
    }

    //update
    public PersonModel updatePerson(int personId, PersonModel personModel){
        //find data by id
        Optional<PersonModel> personOpt = personRepository.findById(personId);
        //validasi apakah data ada di database
        if (personOpt.isEmpty()){
            return null;
        }else {
            return personRepository.save(personModel);
        }
    }

    //PATCH
    public PersonModel updateJob(int personId, String job){
        //validation
        Optional<PersonModel> personOpt = personRepository.findById(personId);
        if (personOpt.isEmpty()){
            return null;
        }else {
            PersonModel personModel = personOpt.get();
            personModel.setJob(job);
            return personRepository.save(personModel);
        }
    }

    //DELETE
    public boolean deletePerson(int personId){
        //validation
        Optional<PersonModel> personOpt = personRepository.findById(personId);
        if (personOpt.isEmpty()){
            return false;
        }else {
            personRepository.delete(personOpt.get());

            return true;
        }
    }
}
