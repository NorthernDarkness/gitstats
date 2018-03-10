package ru.gitstats.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
public class CommitsModel {

    private String email;
    private Long count;

}
