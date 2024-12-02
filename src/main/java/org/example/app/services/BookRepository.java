package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Repository
public class BookRepository<T> implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private ApplicationContext context;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Book> retreiveAll() {
        String sql = "select * from books";
        List<Book> books = jdbcTemplate.query(sql, (ResultSet rs, int row) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });
        return new ArrayList<>(books);
    }

    @Override
    public void store(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", book.getAuthor());
        params.addValue("title", book.getTitle());
        params.addValue("size", book.getSize());
        String sql = """
                insert into books (author, title, size)
                values (:author, :title, :size)
                """;
        jdbcTemplate.update(sql, params);
        logger.info("store new book: " + book);
    }

    @Override
    public void removeItemById(Integer bookIdToRemove) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookIdToRemove);
        String sql = "delete from books where id = :id";
        jdbcTemplate.update(sql, params);
        logger.info("removeItemById: " + bookIdToRemove);
    }

    @Override
    public void removeItemByRegex(String regex) {
        String sql = """
                delete from books
                where id in(
                select id from books
                where author REGEXP :regex
                or title REGEXP :regex)
                --or size::varchar REGEXP :regex)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("regex", regex);
        jdbcTemplate.update(sql, params);
        logger.info("remove book by regex " + regex);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}

