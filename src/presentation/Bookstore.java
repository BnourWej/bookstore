package presentation;

import dao.ManipulateDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Order;

public class Bookstore {

    public static void main(String[] args) throws ParseException {
        ManipulateDB mDB = new ManipulateDB();

        int a = 0;
        do {
            System.out.println("****   Bookstore  ****");
            System.out.println("Choose from the following options");
            System.out.println("1: Add book ");
            System.out.println("2: Update title book ");
            System.out.println("3: Delete an existing book ");
            System.out.println("4: Display books ");
            System.out.println("5: find book by ID");
            System.out.println("6: calculate total price ");
            System.out.println("7: logout ");

            Scanner sc = new Scanner(System.in);
            Scanner scI = new Scanner(System.in);
            Scanner scD = new Scanner(System.in);

            a = scI.nextInt();
            System.out.println("you have chosen " + a);
            switch (a) {

                case 1: {
                    String title, author;
                    double price;
                    Date m;

                    System.out.println("Enter the title of the book");
                    title = sc.nextLine();
                    System.out.println("Enter the author book");
                    author = sc.nextLine();
                    System.out.println("Enter the price of the book");
                    price = scD.nextDouble();
                    System.out.println("Enter the date of release of the book in this format: yyyy-MM-dd");

                    // Formating the date
                    String date = sc.nextLine();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    m = format.parse(date);
                    mDB.insert(author, title, price, m);

                    break;
                }

                case 2: {

                    System.out.println("type the id of the book you want to update ");
                    Scanner sc9 = new Scanner(System.in);
                    int id = sc9.nextInt();
                    System.out.println(" title ");
                    String Title = sc.nextLine();
                    System.out.println(" author ");
                    String Author = sc.nextLine();
                    System.out.println(" price ");
                    Double Price = scD.nextDouble();
                    System.out.println("Date of release in format (yyyy-mm-dd)");
                    String date = sc.next();
                    mDB.update(id, Title, Author, Price, date);

                    break;
                }

                case 3: {
                    System.out.println("type the id of the book that you want to delete ");
                    int id = scI.nextInt();
                    mDB.delete(id);
                    break;
                }

                case 4: {

                    mDB.displayBooks();

                    break;
                }

                case 5: {
                    System.out.println("type the id of the book that you want to show ");
                    int id = scI.nextInt();

                    System.out.println(mDB.find(id));
                    break;
                }

                case 6: {
                    boolean ValidInputs = false;
                    int quantity;
                    double price;
                    //while inputs are not valid, the user have to reenter another values
                    while (ValidInputs == false) {
                        try {
                            System.out.print("**************    Calculating total price of a new order   **************\n  \n");

                            System.out.print("Enter the quantity  \n");
                            quantity = scI.nextInt();

                            System.out.print("Enter the price of the unit \n ");
                            price = scD.nextDouble();
                            //values must be greater then 0
                            if (quantity > 0 && price > 0) {
                                System.out.println(" Total price = quantity * price = " + Order.calculateTotalPrice(quantity, price));
                                ValidInputs = true;
                            } else {
                                System.out.println("Quantity and price must be greater than 0 !");
                            }
                        } //raise exception if the inputs are not numbers
                        catch (InputMismatchException e) {

                            System.out.println("You must enter a valid number !");
                        }
                    }
                    break;
                }

                case 7: {
                    mDB.logout();
                    break;
                }
            }

        } while (a != 7);
    }
}
