/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.library.dao;

import com.sg.library.model.Author;
import com.sg.library.model.Book;
import com.sg.library.model.Publisher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alex
 */
public class LibraryDaoJdbcTemplateImpl implements LibraryDao {

    private static final String SQL_INSERT_AUTHOR = "insert into authors (firstName, lastName, street, city, state, zip, phone)"
            + "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_AUTHOR = "delete from authors where authorId = ?";
    private static final String SQL_UPDATE_AUTHOR = "update authors set firstName = ?, lastName = ?, "
            + "street = ?, city = ?, state = ?, zip = ?, phone = ? "
            + "where authorId = ?";
    private static final String SQL_SELECT_AUTHOR = "select * from authors where authorId = ?";
    private static final String SQL_SELECT_AUTHORS_BY_BOOK_ID = "select au.authorID, au.firstName, "
            + "au.lastName, au.street, au.city, au.state, au.zip, "
            + "au.phone from authors au "
            + "join booksAuthors ba on au.authorId = ba.authorId where ba.bookId = ?";
    private static final String SQL_SELECT_ALL_AUTHORS = "select * from authors";

    // BOOKS AND BOOKS_AUTHORS
    private static final String SQL_INSERT_BOOK
            = "insert into books "
            + "(isbn, title, publisherId, price, publishDate) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_BOOKS_AUTHORS
            = "insert into booksAuthors (bookId, authorId) values(?, ?)";
    private static final String SQL_DELETE_BOOK
            = "delete from books where bookId = ?";
    private static final String SQL_DELETE_BOOKS_AUTHORS
            = "delete from booksAuthors where bookId = ?";
    private static final String SQL_UPDATE_BOOK
            = "update books set isbn = ?, title = ?, publisherId = ?, "
            + "price = ?, publishDate = ? "
            + "where bookId = ?";
    private static final String SQL_SELECT_BOOK
            = "select * from books where bookId = ?";
    private static final String SQL_SELECT_BOOKS_AUTHORS_AUTHOR_ID_BY_BOOK_ID
            = "select authorId from booksAuthors where bookId = ?";
    private static final String SQL_SELECT_ALL_BOOKS
            = "select * from books";
    private static final String SQL_SELECT_BOOKS_BY_AUTHOR_ID
            = "select b.bookId, b.isbn, b.title, b.publisherId, b.price, "
            + "b.publishDate from books b join booksAuthors ba on b.bookId = ba.bookId "
            + "where ba.authorId  =  ?";

    //PUBLISHERS
    private static final String SQL_SELECT_BOOKS_BY_PUBLISHER_ID
            = "select * from books where publisherId = ?";
    private static final String SQL_INSERT_PUBLISHER
            = "insert into publishers (name, street, city, state, zip, phone) "
            + "values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_PUBLISHER
            = "delete from publishers where publisherId = ?";
    private static final String SQL_UPDATE_PUBLISHER
            = "update publishers set name = ?, street = ?, city = ?, "
            + "state = ?, zip = ?, phone = ? where publisherId  =  ?";
    private static final String SQL_SELECT_PUBLISHER
            = "select * from publishers where publisherId = ?";
    private static final String SQL_SELECT_PUBLISHER_BY_BOOK_ID
            = "select pub.publisherId, pub.name, pub.street, pub.city, "
            + "pub.state, pub.zip, pub.phone from publishers pub "
            + "join books on pub.publisherId = books.publisherId where "
            + "books.bookId = ?";
    private static final String SQL_SELECT_ALL_PUBLISHERS
            = "select * from publishers";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void addAuthor(Author author) {
        jdbcTemplate.update(SQL_INSERT_AUTHOR,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getZip(),
                author.getPhone());

        int authorId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        author.setAuthorId(authorId);
    }

    @Override
    public void deleteAuthor(int authorId) {
        jdbcTemplate.update(SQL_DELETE_AUTHOR, authorId);
    }

    @Override
    public void updateAuthor(Author author) {
        jdbcTemplate.update(SQL_UPDATE_AUTHOR,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getZip(),
                author.getPhone(),
                author.getAuthorId());
    }

    @Override
    public Author getAuthorById(int authorId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_AUTHOR, new AuthorMapper(), authorId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AUTHORS, new AuthorMapper());
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        jdbcTemplate.update(SQL_INSERT_BOOK,
                book.getIsbn(),
                book.getTitle(),
                book.getPublisher().getPublisherId(),
                book.getPrice(),
                book.getPublishDate().toString());
        book.setBookId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    @Transactional
    public void deleteBook(int bookId) {
        //delete booksAuthors relationship for this book
        jdbcTemplate.update(SQL_DELETE_BOOKS_AUTHORS, bookId);
        //delete book
        jdbcTemplate.update(SQL_DELETE_BOOK, bookId);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        //update books table
        jdbcTemplate.update(SQL_UPDATE_BOOK,
                book.getIsbn(),
                book.getTitle(),
                book.getPublisher().getPublisherId(),
                book.getPrice(),
                book.getPublishDate().toString(),
                book.getBookId()
        );
        //delete booksAuthors relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_BOOKS_AUTHORS, book.getBookId());
        insertBooksAuthors(book);
    }

    @Override
    public Book getBookById(int id) {
        try {
            //get the properties from the books table
            Book book = jdbcTemplate.queryForObject(SQL_SELECT_BOOK, new BookMapper(), id);
            //get the authors for this book and set the list on the book
            book.setAuthors(findAuthorsForBook(book));
            //get the publisher for this book
            book.setPublisher(findPublisherForBook(book));
            return book;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Book> getBooksByAuthorId(int authorId) {
        //get books written/cowritten by this author
        List<Book> bookList = jdbcTemplate.query(SQL_SELECT_BOOKS_BY_AUTHOR_ID, new BookMapper(), authorId);
        //set the publisher and list of authors for each book
        return associatePublisherAndAuthorsWithBooks(bookList);
    }

    @Override
    public List<Book> getBooksByPublisherId(int publisherId) {
        //get the books published by this publisher
        List<Book> bookList = jdbcTemplate.query(SQL_SELECT_BOOKS_BY_PUBLISHER_ID, new BookMapper(), publisherId);
        //set the publisher and list of Authors for each book
        return associatePublisherAndAuthorsWithBooks(bookList);
    }

    @Override
    public List<Book> getAllBooks() {
        //get all the books
        List<Book> bookList = jdbcTemplate.query(SQL_SELECT_ALL_BOOKS, new BookMapper());
        //set the publisher and list of authors for each book
        return associatePublisherAndAuthorsWithBooks(bookList);
    }

    @Override
    @Transactional
    public void addPublisher(Publisher publisher) {
        jdbcTemplate.update(SQL_INSERT_PUBLISHER,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getZip(),
                publisher.getPhone());

        int publisherId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        publisher.setPublisherId(publisherId);
    }

    @Override
    public void deletePublisher(int publisherId) {
        jdbcTemplate.update(SQL_DELETE_PUBLISHER, publisherId);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(SQL_UPDATE_PUBLISHER,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getZip(),
                publisher.getPhone(),
                publisher.getPublisherId());
    }

    @Override
    public Publisher getPublisherById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PUBLISHER, new PublisherMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PUBLISHERS, new PublisherMapper());
    }

    private void insertBooksAuthors(Book book) {
        final int bookId = book.getBookId();
        final List<Author> authors = book.getAuthors();

        //update the BooksAuthors bridge table with an entry for each author of this book
        for (Author currentAuthor : authors) {
            jdbcTemplate.update(SQL_INSERT_BOOKS_AUTHORS, bookId, currentAuthor.getAuthorId());
        }
    }

    private List<Author> findAuthorsForBook(Book book) {
        return jdbcTemplate.query(SQL_SELECT_AUTHORS_BY_BOOK_ID, new AuthorMapper(), book.getBookId());
    }

    private Publisher findPublisherForBook(Book book) {
        return jdbcTemplate.queryForObject(SQL_SELECT_PUBLISHER_BY_BOOK_ID, new PublisherMapper(), book.getBookId());
    }

    private List<Book> associatePublisherAndAuthorsWithBooks(List<Book> bookList) {
        //set complete list of author IDs for each book
        for (Book currentBook : bookList) {
            //add Authors to current book
            currentBook.setAuthors(findAuthorsForBook(currentBook));
            //add Publisher to current book
            currentBook.setPublisher(findPublisherForBook(currentBook));
        }
        return bookList;
    }

    private static final class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            Author au = new Author();
            au.setFirstName(rs.getString("firstName"));
            au.setLastName(rs.getString("lastName"));
            au.setStreet(rs.getString("street"));
            au.setCity(rs.getString("city"));
            au.setState(rs.getString("state"));
            au.setZip(rs.getString("zip"));
            au.setPhone(rs.getString("phone"));
            au.setAuthorId(rs.getInt("authorId"));
            return au;
        }
    }

    private static final class PublisherMapper implements RowMapper<Publisher> {

        @Override
        public Publisher mapRow(ResultSet rs, int i) throws SQLException {
            Publisher pub = new Publisher();
            pub.setPublisherId(rs.getInt("publisherId"));
            pub.setName(rs.getString("name"));
            pub.setStreet(rs.getString("street"));
            pub.setCity(rs.getString("city"));
            pub.setState(rs.getString("state"));
            pub.setZip(rs.getString("zip"));
            pub.setPhone(rs.getString("phone"));
            return pub;
        }
    }

    private static final class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            Book b = new Book();
            b.setBookId(rs.getInt("bookID"));
            b.setIsbn(rs.getString("isbn"));
            b.setTitle(rs.getString("title"));
            b.setPrice(rs.getBigDecimal("price"));
            b.setPublishDate(rs.getTimestamp("publishDate").toLocalDateTime().toLocalDate());
            return b;
        }
    }
}
