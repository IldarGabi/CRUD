package ru.netology.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Issue {
    private int id;
    private String name;
    private Author author;
    private Status status;
    private Set<Tag> tagsSet = new HashSet<>();
    private Assignee assignee;
    private Label label;
    private LocalDate date;

    public Issue(int id, String name, Author author, Status status, Tag tag, Assignee assignee, Label label, LocalDate date) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.status = status;
        this.tagsSet.add(tag);
        this.assignee = assignee;
        this.label = label;
        this.date = date;
    }
}
