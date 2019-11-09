package com.bubnii.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "department_name")
    private String name;
    @Column(name = "head_of_department")
    private String headOfDepartment;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "department_lector",
            joinColumns = @JoinColumn(
                    name = "department_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "lector_id", referencedColumnName = "id"))
    private Set<Lector> lectors  = new HashSet<>();
}
