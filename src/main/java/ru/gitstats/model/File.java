//package ru.gitstats.model;
//
//
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.Set;
//
//@Entity
//@Table(name = "files")
//@Getter
//@Setter
//public class File {
//
//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "path", nullable = false)
//    private String path;
//
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "files")
//    private Set<Change> changes;
//
//
//}
