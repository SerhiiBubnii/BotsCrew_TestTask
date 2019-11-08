package com.bubnii.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "lector")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "degree")
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Setter(AccessLevel.PRIVATE)
    private Set<Department> departments = new HashSet<>();
}
