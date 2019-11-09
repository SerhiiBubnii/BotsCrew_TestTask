package com.bubnii.model.entity;

import lombok.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Indexed
@Table(name = "lector")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "first_name")
    private String firstName;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "degree")
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @ManyToMany(mappedBy = "lectors", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Department> departments = new HashSet<>();

    @Override
    public String toString() {
        return "Lector{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", degree=" + degree +
                '}';
    }
}
