package ru.gitstats.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user")
    private Collection<Commit> commits;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "stats", joinColumns = {
//            @JoinColumn(name = "ID")}, inverseJoinColumns = {
//            @JoinColumn(name = "USER_ID")})
//    @JsonBackReference
//    private Set<Stats> stats;
//
//    public void addStat(Stats stats) {
//        this.stats.add(stats);
//        stats.setUser(this);
//    }
//
//    public void removeStat(Stats stats) {
//        this.stats.remove(stats);
//        stats.setUser(null);
//    }
}
