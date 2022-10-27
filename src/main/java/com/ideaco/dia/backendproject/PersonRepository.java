package com.ideaco.dia.backendproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonModel, Integer> {
}
