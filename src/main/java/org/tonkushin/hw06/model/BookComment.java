package org.tonkushin.hw06.model;

import javax.persistence.*;

/**
 * Комментарий к книге
 */
@Entity
@Table(name = "BOOK_COMMENTS")
public class BookComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id =-1L;     //код в БД
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;        //книга
    @Column(name = "COMMENT_TEXT", length = 4000)
    private String commentText;   //комментарий к книге

    public BookComment() {
    }

    public BookComment(Book book, String commentText) {
        this.book = book;
        this.commentText = commentText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
