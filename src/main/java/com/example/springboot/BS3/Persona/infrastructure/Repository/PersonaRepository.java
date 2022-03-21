package com.example.springboot.BS3.Persona.infrastructure.Repository;

import com.example.springboot.BS3.Persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


public interface PersonaRepository extends JpaRepository<Persona, String> {


    List<Persona> findByUsuario(String usuario);
    List<Persona> getData(HashMap<String, Object> conditions, int page);

}
