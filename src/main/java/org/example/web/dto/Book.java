package org.example.web.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class Book {

    private Integer id;
    @NotEmpty(message = "title is empty")
    @Pattern(regexp = "^[a-zA-ZА-Яа-яЁё\\s]+$", message = "title is String")
    private String title;
    @NotEmpty(message = "author is empty")
    @Pattern(regexp = "^[a-zA-ZА-Яа-яЁё\\s]+$", message = "author is String")
    private String author;
    @Digits(integer = 4, fraction = 0)
    private Integer size;

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", size=" + size +
                '}';
    }
}
