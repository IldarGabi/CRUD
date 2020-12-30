package ru.netology.domain;

import java.util.Comparator;

public class IssuesComparator implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
}
