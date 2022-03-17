package com.example.springboot.BS3.Persona.infrastructure.Controller;

import com.example.springboot.BS3.Errores.PersonNotFoundException;
import com.example.springboot.BS3.Persona.application.IPersona;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.input.PersonainputDTO;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    IPersona personaService;   //Se hace un autowire de la interfaz, no de la clase!!

    @PostMapping
    public ResponseEntity<PersonaOutputDTO>  addPerson(@RequestBody PersonainputDTO personainputDTO) throws Exception{
        return new ResponseEntity<PersonaOutputDTO>(personaService.addPerson(personainputDTO), HttpStatus.CREATED) ;
    }

    @GetMapping("personas")
    public ResponseEntity <List<PersonaOutputDTO>> getPersons(){
        return new ResponseEntity<>(personaService.getPersons(),HttpStatus.OK);
    }

    @GetMapping("personas/{id}")
    public ResponseEntity <PersonaOutputDTO> findPerson(@PathVariable String id){
        return new ResponseEntity<>(personaService.findbyId(id),HttpStatus.OK);
    }

    @PutMapping("personas/{id}")
    public ResponseEntity<PersonaOutputDTO> setPerson(@PathVariable String id, @RequestBody PersonainputDTO personainputDTO) throws PersonNotFoundException{
        return new ResponseEntity<PersonaOutputDTO>(personaService.setPerson(id,personainputDTO),HttpStatus.OK);
    }

    @DeleteMapping("personas/{id}")
    public void deletePerson(@PathVariable String id) throws PersonNotFoundException{
           personaService.deletePerson(id);
    }
}
