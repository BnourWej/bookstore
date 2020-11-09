package bookstore;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bookstore
{
public static void main(String[] args)  
 {
    boolean ValidInputs = false;
    Scanner sc = new Scanner( System.in );
    int quantity;
    double price;
                //while inputs are not valid, the user have to reenter another values 
    while(ValidInputs== false)
    {
	try 
            {
		System.out.print( "Enter the quantity  \n");
		quantity = sc.nextInt();

                System.out.print( "Enter the price of the unit \n " );
                price = sc.nextDouble();
                //values must be greater then 0
                if (quantity>0 && price > 0)
                    {
                        System.out.println(" Total price = quantity * price = "+Order.calculateTotalPrice(quantity,price));
                        ValidInputs = true;
                    }
                            
                else 
                        System.out.println("Quantity and price must be greater than 0 !");
	    }
            //raise exception if the inputs are not numbers
	catch(InputMismatchException e) 
            {
		System.out.println("You must enter a valid number !");
            }
    }
 }
}

