package ru.netology;

import ru.netology.domain.Issue;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Status;

import java.util.ArrayList;
import java.util.List;

public class IssuesRepository {
    private List<Issue> issueList = new ArrayList<>();

    public void save(Issue issue) {
        issueList.add(issue);
    }

    public List<Issue> findAll() {
        return issueList;
    }

    public void OpenIssuesById(int id) {
        for (Issue issue : issueList) {
            if (issue.getId() == id) {
                issue.setStatus(Status.Open);
            }
        }
    }

    public void ClosedIssuesById(int id) {
        for (Issue issue : issueList) {
            if (issue.getId() == id) {
                issue.setStatus(Status.Closed);
            }
        }
    }
    public Issue findById(int id) {
        for (Issue issue : issueList) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        throw new NotFoundException(id + " not found");
    }
}
