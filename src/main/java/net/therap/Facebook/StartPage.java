package main.java.net.therap.Facebook;

import main.java.net.therap.Dao.User;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author babar
 * @since 11/2/15
 */
public class StartPage {
    private Scanner in = new Scanner(System.in);
    public void view(){
        System.out.println("Choose From An Option Below : ");
        System.out.println("1. Log In");
        System.out.println("2. Sign Up");

        int choice = in.nextInt();
        in.nextLine();
        switch(choice){
            case 1:
                processLogIn();
                break;
            case 2:
                processSignUp();
                break;
        }
    }
    private void processLogIn(){
        System.out.println("Enter email address :");
        String email = in.nextLine();
        System.out.println("Enter Password :");
        String password = in.nextLine();
        User user = new User();
        boolean success = false;
        try {
            success = user.logInUser(email, password);
        } catch (SQLException e) {
            System.out.println("Server problem , please try again later.");
        }
        if(success){
            SESSION.LOGGED_IN = true;
            try {
                SESSION.LOGGED_USER_ID = user.findUserByEmail(email);
                System.out.println(SESSION.LOGGED_USER_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void processSignUp(){
        System.out.println("Enter First Name :");
        String firstName = in.nextLine();
        System.out.println("Enter Last Name :");
        String lastName = in.nextLine();
        System.out.println("Enter Email Address :");
        String email = in.nextLine();
        System.out.println("Enter Password :");
        String password = in.nextLine();
        System.out.println("Enter Gender :");
        String gender = in.nextLine();
        System.out.println("Enter Date Of Birth (dd-mm-yy) :");
        String dateOfBirth = in.nextLine();
        long birthDay = 0;
        DateFormat format = new SimpleDateFormat("dd-mm-YY", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date != null){
            birthDay = date.getTime();
        }
        User user = new User(firstName,lastName,email,password,gender,birthDay);
        try {
            user.registerUser(user);
        } catch (SQLException e) {
            System.out.println("Server problem , please try again later.");
        }
    }
}
