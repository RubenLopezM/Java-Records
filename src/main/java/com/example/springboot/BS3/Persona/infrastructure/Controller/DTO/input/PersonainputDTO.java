package com.example.springboot.BS3.Persona.infrastructure.Controller.DTO.input;

import java.util.Date;

public record PersonainputDTO(String usuario, String password, String name, String surname, String company_email, String personal_email, String city, boolean active,
                              Date created_date,String imagen_url,Date termination_date) {
}