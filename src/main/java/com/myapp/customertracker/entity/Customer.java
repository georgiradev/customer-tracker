package com.myapp.customertracker.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "is required")
    @Size(min = 3, max = 45, message = "First name size must be between 3 and 45 characters long")
    @Pattern(regexp = "[a-zA-Z]+", message = "Only letters is allowed")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "is required")
    @Size(min = 3, max = 45, message = "Last name size must be between 3 and 45 characters long")
    @Pattern(regexp = "[a-zA-Z]+", message = "Only letters is allowed")
    private String lastName;

    @Column(name = "email", unique = true)
    @NotEmpty(message = "is required")
    @Email(message = "Not a valid email")
    private String email;
}
