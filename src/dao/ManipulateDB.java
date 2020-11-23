/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;

/**
 *
 * @author bnwej
 */
public class ManipulateDB {

    Connexion cnx = new Connexion();

//Insert Book method
    public void insert(String author, String title, double price, Date releaseD) {
        try {
            //id auto increment in the DB
            if (cnx.con == null) {
                System.out.println("NOT CONNECTED !");
            } else {
                //id auto increment in the DB
                PreparedStatement pstmt = cnx.con.prepareStatement("INSERT INTO `book`(author,title,price,releaseD) VALUES (?, ?, ?, ?)");
                pstmt.setString(1, author);
                pstmt.setString(2, title);
                pstmt.setDouble(3, price);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                pstmt.setString(4, format.format(releaseD));

                pstmt.executeUpdate();

                System.out.println("BOOK ADDED SUCCESSFULLY");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void displayBooks() {
        try {
            PreparedStatement pstmt = cnx.con.prepareStatement("SELECT * FROM Book ");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                System.out.println(" Book n° " + rs.getString("id")
                        + " Author : " + rs.getString("author")
                        + " Title : " + rs.getString("title")
                        + " Price : " + rs.getString("price")
                        + " ReleaseDate : " + rs.getString("releaseD"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, String title, String author, double price, String releaseD) {
        try {
            if (cnx.con == null) {
                System.out.println("NOT CONNECTED !");
            } else {

                if (find(id) == null) {
                    System.out.println("there is no such book with that id");
                } else {

                    PreparedStatement pstmt = cnx.con.prepareStatement("UPDATE `book` SET VALUES title=?,author=?, price=? , releaseD=? where id=?");
                    pstmt.setString(1, find(id).getTitle());
                    pstmt.setString(2, find(id).getAuthor());
                    pstmt.setDouble(3, find(id).getPrice());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    pstmt.setString(3, format.format(find(id).getReleaseD()));
                    pstmt.setInt(4, id);

                    pstmt.executeUpdate();

                    System.out.println("BOOK UPDATED SUCCESSFULLY");

                }
            }
        } catch (SQLException e) {
        }
    }

    public void delete(int id) {
        try {
            if (cnx.con == null) {
                System.out.println("NOT CONNECTED !");
            } else {

                if (find(id) == null) {
                    System.out.println("there is no such book with that id");
                } else {

                    System.out.println("YOU'RE GOING TO DELETE THE BOOK N° " + id + "!");

                    PreparedStatement pstmt = cnx.con.prepareStatement("DELETE FROM `book` WHERE id=?");
                    pstmt.setInt(1, id);

                    pstmt.executeUpdate();

                    System.out.println("BOOK DELETED SUCCESSFULLY");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book find(int id) {

        try {
            Book book = new Book();

            PreparedStatement ps = cnx.con.prepareStatement("SELECT * FROM book WHERE id= ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            boolean check = false; // verify if the book instance is empty or not.

            while (rs.next()) {
                check = true;
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                book.setReleaseD(format.parse(rs.getString("releaseD")));
            }
            if (check) {
                return book;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void logout() {
        try {
            cnx.con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
