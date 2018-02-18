package ru.gitstats.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ALIASES", joinColumns = {
            @JoinColumn(name = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "USER_ID")})
    @JsonBackReference
    private Set<Alias> aliases;

    public void addAlias(Alias alias) {
        aliases.add(alias);
        alias.setUser(this);
    }

    public void removeComment(Alias alias) {
        aliases.remove(alias);
        alias.setUser(null);
    }
}
