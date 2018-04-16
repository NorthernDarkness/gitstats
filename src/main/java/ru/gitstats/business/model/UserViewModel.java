package ru.gitstats.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserViewModel {

    private long id;
    private String name;
    private String email;
    private long commitNumber;
    private double averageCommitNumberPerMonth;
    private String mostChangableFile;
    private long numberOfAddedLines;
    private long numberOfRemovedLines;


}
