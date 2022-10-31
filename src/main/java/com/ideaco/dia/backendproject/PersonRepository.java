package com.ideaco.dia.backendproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonModel, Integer> {
    Optional<PersonModel> findByAddress(String address);

    List<PersonModel> findByAgeGreaterThan(int age);

    @Query(value = "select * from dia_batch3.tab_stephen ts WHERE ts.name LIKE %:name%", nativeQuery = true)
    List<PersonModel> getPersons(@Param("name") String name);

    @Query(value = "select p from PersonModel p " +
            "where p.age >= :age")
    List<PersonModel> getPersonsByAgeJpql(@Param("age")int age);
}
