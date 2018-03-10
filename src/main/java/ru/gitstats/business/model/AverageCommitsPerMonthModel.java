package ru.gitstats.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;
import org.joda.time.Months;

import java.time.Month;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AverageCommitsPerMonthModel {

    private String email;
    private Double averageCommitsPerMonth;

}
