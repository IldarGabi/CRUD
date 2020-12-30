package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ru.netology.domain.Author;

import static org.junit.jupiter.api.Assertions.*;

class IssuesManagerTest {
    private IssuesRepository repository = new IssuesRepository();
    private IssuesManager manager = new IssuesManager(repository);
    private IssuesComparator comparator = new IssuesComparator();

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
    public void showCloseStatus() {
        List<Issue> actual = manager.findCloseStatus();
        List<Issue> expected = Arrays.asList(issue3, issue4);
        assertEquals(expected, actual);
    }
    @Test
    void showOpenStatus() {
        List<Issue> actual = manager.findOpenStatus();
        List<Issue> expected = Arrays.asList(issue1, issue2);
        assertEquals(expected, actual);
    }
    @Test
    void showByAuthor() {
        List<Issue> actual = manager.filterByAuthor(new Author("Author1"), comparator);
        List<Issue> expected = Collections.singletonList(issue1);
        assertEquals(actual, expected);
    }
    @Test
    void showByAssignee() {
        List<Issue> actual = manager.filterByAssignee(new Assignee("Second"), comparator);
        List<Issue> expected = Collections.singletonList(issue2);
        assertEquals(expected, actual);
    }
    @Test
    void showByLabel() {
        List<Issue> actual = manager.filterByLabel(Label.Bug, comparator);
        List<Issue> expected = Arrays.asList(issue1,issue4);
        assertEquals(expected, actual);
    }
}