package model;

import java.util.Date;

public class Book {

    private int id;
    private String title, author;
    private double price;
    private Date releaseD;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReleaseD() {
        return releaseD;
    }

    public void setReleaseD(Date releaseD) {
        this.releaseD = releaseD;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", releaseD=" + releaseD + '}';
    }

}
