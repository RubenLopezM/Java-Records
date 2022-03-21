package com.example.springboot.BS3.Persona.application;

import com.example.springboot.BS3.Errores.PersonNotFoundException;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.input.PersonainputDTO;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.output.ListPersonaOutputDTO;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.output.PersonaOutputDTO;

import java.util.HashMap;
import java.util.List;

public interface IPersona {
    public PersonaOutputDTO addPerson(PersonainputDTO persona) throws Exception;
    public PersonaOutputDTO setPerson(String id, PersonainputDTO personainputDTO) throws PersonNotFoundException;
    public void deletePerson(String id) throws PersonNotFoundException;
    public List <PersonaOutputDTO> getPersons();
    public PersonaOutputDTO findbyId(String id) throws PersonNotFoundException;
    public List<PersonaOutputDTO> findUsuario(String usuario) throws Exception;
    public ListPersonaOutputDTO searchPerson(HashMap<String,Object> conditions, int page);
}
