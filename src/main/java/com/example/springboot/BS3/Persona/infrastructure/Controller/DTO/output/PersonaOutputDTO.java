package com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.output;

import java.util.Date;

public record PersonaOutputDTO(String id_persona, String usuario, String name, String surname, String company_email, String personal_email, String city, boolean active,
                               Date created_date,String imagen_url,Date termination_date) {
}
