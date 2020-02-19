package com.quizilla.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAnyAttribute;
import java.io.Serializable;

@Entity
@Data
@Table(name = "languages")
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String code;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;
}
