/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

/**
 *
 * @author Borja
 */
@Entity
@NamedQuery(name = "Book.findAll", query = "select b from Book b")
@NamedQuery(name = "Book.findByTitle", query = "select b from Book b where b.title = :title")
public class Book extends AbstractIdentifiedEntity{
    
    /**
     *
     */
    @NotBlank(message = "This field can't be empty")
    @Column(nullable = false, unique = true)
    protected String title;

    @OneToOne(mappedBy = "book")
    private PurchaseHistory purchase;
    
    @ManyToOne
    private Client client;
    
    @ManyToOne
    private Retailer retailer;

    @NotBlank(message = "This field can't be empty")
    @Column(nullable = false)
    private String author;
    @NotBlank(message = "This field can't be empty")
    @Column(nullable = false)
    private String genre;
    @PastOrPresent(message = "Books can only have a publishing date in the past or the present, not in the future")
    private LocalDate yearPublished;

    /**
     *
     */
    public Book() {
    }
    
    /**
     * Full args constructor for a Book
     * 
     * @param title
     * @param author
     * @param genre
     * @param yearPublished 
     */
    public Book(String title, String author, String genre, LocalDate yearPublished) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearPublished = yearPublished;
    }

    /**
     *
     * @return
     */
    public PurchaseHistory getPurchase() {
        return purchase;
    }

    /**
     *
     * @param purchase
     */
    public void setPurchase(PurchaseHistory purchase) {
        this.purchase = purchase;
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
     * Get the value of yearPublished
     *
     * @return the value of yearPublished
     */
    public LocalDate getYearPublished() {
        return yearPublished;
    }

    /**
     * Set the value of yearPublished
     *
     * @param yearPublished new value of yearPublished
     */
    public void setYearPublished(LocalDate yearPublished) {
        this.yearPublished = yearPublished;
    }

    /**
     *
     * @return
     */
    public Client getClient() {
        return client;
    }

    /**
     *
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public Retailer getRetailer() {
        return retailer;
    }

    /**
     *
     * @param retailer
     */
    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    
    

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", genre=" + genre + ", yearPublished=" + yearPublished + '}';
    }

}
