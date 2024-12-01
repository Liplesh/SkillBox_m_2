package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    void removeItemById(String bookIdToRemove);
    void removeItemByRegex(String regex);
}
