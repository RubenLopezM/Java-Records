package com.example.springboot.BS3.Persona.domain;

import com.example.springboot.BS3.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personas_seq")
    @GenericGenerator(
            name = "personas_seq",
            strategy = "com.example.springboot.BS3.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            })
    @Column(name = "id_persona",updatable = false)
    private String id;
    @Column(nullable = false)
    private String usuario;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String surname;
    @Column(nullable = false)
    private String company_email;
    @Column(nullable = false)
    private String personal_email;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private Date created_date;
    private String imagen_url;
    private Date termination_date;




}
