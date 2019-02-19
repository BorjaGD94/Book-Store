/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

/**
 *
 * @author Borja
 */
public class Book {
    
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String genre;
    @PastOrPresent
    private Date yearPublished;

 
    public Book() {
    }

    public Book(String title, String author, String genre, Date yearPublished) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearPublished = yearPublished;
    }
    

    /**
     * Get the value of genre
     *
     * @return the value of genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set the value of genre
     *
     * @param genre new value of genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }


    /**
     * Get the value of author
     *
     * @return the value of author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the value of author
     *
     * @param author new value of author
     */
    public void setAuthor(String author) {
        this.author = author;
    }


    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
        /**
     * Get the value of yearPublished
     *
     * @return the value of yearPublished
     */
    public Date getYearPublished() {
        return yearPublished;
    }

    /**
     * Set the value of yearPublished
     *
     * @param yearPublished new value of yearPublished
     */
    public void setYearPublished(Date yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", author=" + author + ", genre=" + genre + ", yearPublished=" + yearPublished + '}';
    }

}
