package com.example.springboot.BS3.Persona.infrastructure.Controller;

import com.example.springboot.BS3.Errores.PersonNotFoundException;
import com.example.springboot.BS3.Persona.application.IPersona;
import com.example.springboot.BS3.Persona.domain.Persona;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.input.PersonainputDTO;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.output.ListPersonaOutputDTO;
import com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.output.PersonaOutputDTO;
import com.example.springboot.BS3.Persona.infrastructure.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    IPersona personaService;   //Se hace un autowire de la interfaz, no de la clase!!





    public static final String GREATER_THAN="greater";
    public static final String LESS_THAN="less";
    public static final String EQUAL="equal";



    @PostMapping
    public ResponseEntity<PersonaOutputDTO>  addPerson(@RequestBody PersonainputDTO personainputDTO) throws Exception{
        return new ResponseEntity<PersonaOutputDTO>(personaService.addPerson(personainputDTO), HttpStatus.CREATED) ;
    }

    @GetMapping("querypersonas")
    public ResponseEntity <ListPersonaOutputDTO> searchPersons(@RequestParam(required = false,value = "usuario")String usuarioPersona,
                                                               @RequestParam(required=false,value="namePersona") String namePersona,
                                                               @RequestParam(required=false, value = "surname") String surnamePersona,
                                                               @RequestParam(required=false,value = "created_date") @DateTimeFormat(pattern="dd-MM-yyyy") Date createdDate,
                                                               @RequestParam(required=false, value = "dateCondition") String dateCondition,
                                                               @RequestParam(required = false, value = "sorted") String sorted,
                                                               @RequestParam(required = true, value = "page", defaultValue = "0") int page)
            {
                HashMap<String,Object> data= new HashMap<>();
                if (usuarioPersona!=null)
                    data.put("usuario",usuarioPersona);
                if (namePersona!=null)
                    data.put("name",namePersona);
                if(sorted!=null)
                    data.put("sorted",sorted);
                if (surnamePersona!=null)
                    data.put("surname",surnamePersona);
                if (dateCondition==null)
                    dateCondition=GREATER_THAN;
                if (!dateCondition.equals(GREATER_THAN) && 	!dateCondition.equals(LESS_THAN) && !dateCondition.equals(EQUAL))
                    dateCondition=GREATER_THAN;
                if (createdDate!=null)
                {
                    data.put("created_date",createdDate);
                    data.put("dateCondition",dateCondition);
                }

                return new ResponseEntity<ListPersonaOutputDTO> (personaService.searchPerson(data,page), HttpStatus.OK);

    }

    @GetMapping("personas")
    public ResponseEntity <List<PersonaOutputDTO>> getPersons(){
        return new ResponseEntity<>(personaService.getPersons(),HttpStatus.OK);
    }

    @GetMapping("personas/{id}")
    public ResponseEntity <PersonaOutputDTO> findPerson(@PathVariable String id){
        return new ResponseEntity<>(personaService.findbyId(id),HttpStatus.OK);
    }

    @GetMapping("persona/{usuario}")
    public ResponseEntity<List<PersonaOutputDTO>> findUsuario(@PathVariable String usuario) throws Exception {
        return new ResponseEntity<>(personaService.findUsuario(usuario),HttpStatus.OK);
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
