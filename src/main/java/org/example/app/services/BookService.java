package org.example.app.services;

import org.example.app.services.Utils.Utils;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.app.services.Utils.Utils.isStringEmpty;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }


    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public void removeBookById(Integer bookIdToRemove) {
        bookRepo.removeItemById(bookIdToRemove);
    }

    public void removeBookByRegex(String regex) {
        bookRepo.removeItemByRegex(regex);
    }

    //Проверка на пустоту заполненных полей
    public boolean isBookEmpty(Book book) {
        String bookTitle = book.getTitle();
        String bookAuthor = book.getAuthor();
        if (isStringEmpty(bookTitle) && isStringEmpty(bookAuthor)) {
            return true;
        }
        return false;
    }
}
