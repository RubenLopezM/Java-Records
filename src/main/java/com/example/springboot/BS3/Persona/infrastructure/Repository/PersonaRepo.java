package com.example.springboot.BS3.Persona.infrastructure.Repository;

import com.example.springboot.BS3.Persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepo extends JpaRepository<Persona, String> {
}
