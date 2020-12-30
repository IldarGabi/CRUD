package ru.netology;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import lombok.Data;
import ru.netology.domain.*;

@Data
public class IssuesManager {
    private IssuesRepository repository;

    public IssuesManager(IssuesRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public List<Issue> findOpenStatus() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getStatus().equals(Status.Open)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    public List<Issue> findCloseStatus() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getStatus().equals(Status.Closed)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    private List<Issue> filterBy(Predicate<Issue> predicate, Comparator<Issue> issueComparator) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue)) {
                temp.add(issue);
            }
        }
        temp.sort(issueComparator);
        return temp;
    }

    public List<Issue> filterByAuthor(Author author, Comparator<Issue> issueComparator) {
        Predicate<Issue> authorPredicate = issue -> issue.getAuthor().equals(author);
        return filterBy(authorPredicate, issueComparator);
    }

    public List<Issue> filterByAssignee(Assignee assignee, Comparator<Issue> issueComparator) {
        Predicate<Issue> assigneePredicate = issue -> issue.getAssignee().equals(assignee);
        return filterBy(assigneePredicate, issueComparator);
    }

    public List<Issue> filterByLabel(Label label, Comparator<Issue> issueComparator) {
        Predicate<Issue> labelPredicate = issue -> issue.getLabel().equals(label);
        return filterBy(labelPredicate, issueComparator);
    }
}
