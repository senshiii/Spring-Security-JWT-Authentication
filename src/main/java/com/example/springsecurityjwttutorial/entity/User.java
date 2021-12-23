package com.example.springsecurityjwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Marks this class as an Entity
@Getter // Defines the getter methods of the member fields
@Setter // Defines the setter methods of the member fields
@ToString // Defines a meaningful toString implementation of this class
@NoArgsConstructor // Defines the default constructor
public class User {

    @Id // Marks the "id" field as the identifier of this entity
    @GeneratedValue(strategy = GenerationType.AUTO) // The "id" field is to be generated automatically
    private Long id;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
