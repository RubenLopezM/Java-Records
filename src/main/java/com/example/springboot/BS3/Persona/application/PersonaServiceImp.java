package com.example.springboot.BS3.Persona.application;

import com.example.springboot.BS3.Errores.PersonNotFoundException;
import com.example.springboot.BS3.Errores.UnprocesableException;
import com.example.springboot.BS3.Persona.domain.Persona;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.input.PersonainputDTO;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.output.PersonaOutputDTO;
import com.example.springboot.BS3.Persona.infrastructure.Repository.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImp implements IPersona {

    @Autowired
    PersonaRepo personaRepo;

    @Override
    public PersonaOutputDTO addPerson(PersonainputDTO personainputDTO) throws Exception {
            this.validar(personainputDTO);
            Persona persona= convertInputtoEntity(personainputDTO);
            personaRepo.save(persona);
            return convertToDTO(persona);
    }

    @Override
    public PersonaOutputDTO setPerson(String id, PersonainputDTO personainputDTO) throws PersonNotFoundException {
        Persona persona= personaRepo.findById(id).orElseThrow(()-> new PersonNotFoundException("No hay ninguna persona con este ID"));
        persona.setUsuario(personainputDTO.usuario());
        persona.setPassword(personainputDTO.password());
        persona.setCity(personainputDTO.city());
        persona.setCompany_email(personainputDTO.company_email());
        persona.setPersonal_email(personainputDTO.personal_email());
        persona.setName(personainputDTO.name());
        persona.setSurname(personainputDTO.surname());
        persona.setImagen_url(personainputDTO.imagen_url());
        persona.setCreated_date(personainputDTO.created_date());
        persona.setTermination_date(personainputDTO.termination_date());
        personaRepo.save(persona);
        return convertToDTO(persona);
    }

    @Override
    public void deletePerson(String id) throws PersonNotFoundException {
        Persona persona= personaRepo.findById(id).orElseThrow(()-> new PersonNotFoundException("No hay ninguna persona con este ID"));
        personaRepo.delete(persona);

    }

    @Override
    public List<PersonaOutputDTO> getPersons() {
        Pageable firstPage = PageRequest.of(0, 5);
        List<PersonaOutputDTO> personaOutputDTOList= new ArrayList<>();
        Slice<Persona> personas= personaRepo.findAll(firstPage);
        for (Persona persona: personas){
           personaOutputDTOList.add(this.convertToDTO(persona));
        }
        return personaOutputDTOList;
    }

    @Override
    public PersonaOutputDTO findbyId(String id) throws PersonNotFoundException {
        Persona persona= personaRepo.findById(id).orElseThrow(()-> new PersonNotFoundException("No hay ninguna persona con este ID"));
        return convertToDTO(persona);
    }

    private void validar(PersonainputDTO personainputDTO) throws UnprocesableException{
        String usuario= personainputDTO.usuario();

        if (usuario==null) throw new UnprocesableException("Error: Usuario no puede ser nulo");
        if (usuario.length()>10 || usuario.length()<6) throw new UnprocesableException("Error: El usuario debe tener entre 6 y 10 caracteres");;
        if (personainputDTO.password()==null) throw new UnprocesableException("Error: Se debe introducir una contraseña");
        if (personainputDTO.created_date()==null) throw new UnprocesableException("Debe introducir una fecha");
    }

    private Persona convertInputtoEntity(PersonainputDTO personainputDTO){
        Persona persona= new Persona();
        persona.setUsuario(personainputDTO.usuario());
        persona.setPassword(personainputDTO.password());
        persona.setCity(personainputDTO.city());
        persona.setPersonal_email(personainputDTO.personal_email());
        persona.setActive(personainputDTO.active());
        persona.setCreated_date(personainputDTO.created_date());
        persona.setSurname(personainputDTO.surname());
        persona.setName(personainputDTO.name());
        persona.setCompany_email(personainputDTO.company_email());
        persona.setImagen_url(personainputDTO.imagen_url());
        persona.setTermination_date(personainputDTO.termination_date());
        return  persona;
    }

    private PersonaOutputDTO convertToDTO(Persona persona){
        PersonaOutputDTO personaOutputDTO= new PersonaOutputDTO(
                persona.getId()
                ,persona.getUsuario()
                ,persona.getName(),
                persona.getSurname(),
                persona.getCompany_email(),
                persona.getPersonal_email(),
                persona.getCity(),
                persona.isActive(),
                persona.getCreated_date(),
                persona.getImagen_url(),
                persona.getTermination_date());
        return personaOutputDTO;
    }
}
