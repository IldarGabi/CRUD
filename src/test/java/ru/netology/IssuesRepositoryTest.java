package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;
import ru.netology.domain.Label;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

class IssuesRepositoryTest {
    private IssuesRepository repository = new IssuesRepository();

    private Issue issue1 = new Issue(1, "Name1", new Author("Author1"), Status.Open, new Tag(1, "One"), new Assignee("First"), Label.Bug, LocalDate.of(2020, 1, 1));
    private Issue issue2 = new Issue(2, "Name2", new Author("Author2"), Status.Open, new Tag(2, "Two"), new Assignee("Second"), Label.Question, LocalDate.of(2020, 2, 2));
    private Issue issue3 = new Issue(3, "Name3", new Author("Author3"), Status.Closed, new Tag(3, "Second"), new Assignee("Third"), Label.Task, LocalDate.of(2020, 3, 3));
    private Issue issue4 = new Issue(4, "Name4", new Author("Author4"), Status.Closed, new Tag(4, "Four"), new Assignee("Fourth"), Label.Bug, LocalDate.of(2020, 4, 4));

    @BeforeEach
    void setUp() {
        repository.save(issue1);
        repository.save(issue2);
        repository.save(issue3);
        repository.save(issue4);
    }

    @Test
    public void CloseById() {
        repository.ClosedIssuesById(2);
        Issue byId = repository.findById(2);
        assertSame(byId.getStatus(), Status.Closed);
    }

    @Test
    public void OpenById() {
        repository.OpenIssuesById(2);
        Issue byId = repository.findById(2);
        assertSame(byId.getStatus(), Status.Open);
    }

    @Test
    public void SearchById() {
        Issue actual = repository.findById(4);
        Issue expected = issue4;
        assertEquals(actual, expected);
    }

    @Test
    public void SearchByNotFoundId() {
        assertThrows(NotFoundException.class, () -> repository.findById(5));
    }

    @Test
    public void ShowAllIssues() {
        List<Issue> actual = repository.findAll();
        List<Issue> expected = Arrays.asList(issue1, issue2, issue3, issue4);
        assertEquals(actual, expected);
    }
}
