package ru.gitstats.business.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectViewModel {

    private long id;
    private String name;
    private long numberOfCommits;
    private long numberOfUsers;
    private long numberOfFiles;
    private double averageCommitNumberPerMonth;
    private String mostChangableFile;
    private long numberOfAddedLines;
    private long numberOfRemovedLines;


}
