package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Repository
public class BookRepository<T> implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(context.getBean(IdProvider.class).provideId(book));
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public void removeItemById(String bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (Objects.equals(book.getId(), bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeItemByRegex(String regex) {
        for (Book book : retreiveAll()) {
            Integer size = book.getSize();
            String author = book.getAuthor();
            String title = book.getTitle();

            Pattern pattern = Pattern.compile(regex);
            if (pattern.matcher(title).find()
            || pattern.matcher(author).find()
            || pattern.matcher("" + size).find()){
                logger.info("remove book by regex " + book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

//    private void defaultInit() {
//        logger.info("default INIT in book repo bean");
//    }
//
//    private void defaultDestroy() {
//        logger.info("default DESTROY in book repo bean");
//    }
}

