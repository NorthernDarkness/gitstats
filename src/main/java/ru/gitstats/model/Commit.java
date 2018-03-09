package ru.gitstats.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commits")
@Getter
@Setter
@EqualsAndHashCode
public class Commit {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "DATE", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


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
