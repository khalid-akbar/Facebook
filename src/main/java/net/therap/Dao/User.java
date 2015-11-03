package main.java.net.therap.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author babar
 * @since 11/1/15
 */
public class User implements UserDao {

    private long   ID;
    private String Firstname;
    private String LastName;
    private String Email;
    private String Password;
    private String Gender;
    private long   BirthDay;

    public User(){

    }

    public User(String f,String l,String e,String p,String g,long b){
        ID = -1;
        Firstname = f;
        LastName = l;
        Email = e;
        Password = p;
        Gender = g;
        BirthDay = b;
    }

    @Override
    public void registerUser(User user) throws SQLException {
        String userInsert = String.format("INSERT INTO user VALUES" +
                "(NULL,'%s','%s','%s','%s','%s',%d)",user.Firstname,user.LastName,
                user.Email,user.Password,user.Gender,user.BirthDay);
        //System.out.println(userInsert);
        try(
            Connection connection = MyConnectionPool.getConnection();
            Statement statement = connection.createStatement();
        ){
            statement.executeUpdate(userInsert);
        }

    }

    @Override
    public void updateUserInformation(User user) throws SQLException{
        String userUpdate = String.format("UPDATE user SET FirstName='%s'," +
                "LastName='%s',Email='%s',PassWord='%s',Gender='%s',Birthday=%d" +
                " WHERE ID=%d",user.Firstname,user.LastName,user.Email,
                user.Password,user.Gender,user.BirthDay,user.ID);

        try(
            Connection connection = MyConnectionPool.getConnection();
            Statement statement = connection.createStatement();
        ){
            statement.executeUpdate(userUpdate);
        }
    }

    @Override
    public void deleteUser(long id) throws SQLException {
        String userDelete = String.format("DELETE FROM user WHERE ID=%d",id);
        try(
            Connection connection = MyConnectionPool.getConnection();
            Statement statement = connection.createStatement();
        )
        {
            statement.executeUpdate(userDelete);
        }
    }

    @Override
    public User getUserById(long id) throws SQLException {
        User user = new User();
        String getUser = String.format("SELECT * FROM user WHERE ID=%d",id);

        try(
            Connection connection = MyConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet userInfo = statement.executeQuery(getUser);
        )
        {
            userInfo.next();
            user.setID(userInfo.getInt("ID"));
            user.setFirstname(userInfo.getString("FirstName"));
            user.setLastName(userInfo.getString("LastName"));
            user.setEmail(userInfo.getString("Email"));
            user.setPassword(userInfo.getString("Password"));
            user.setGender(userInfo.getString("Gender"));
            user.setBirthDay(userInfo.getLong("BirthDay"));
        }
        return user;
    }

    @Override
    public boolean logInUser(String email, String password) throws SQLException {
        String checkLogin = String.format("SELECT ID FROM user WHERE Email='%s' AND" +
                " Password='%s'",email,password);
        try(
            Connection connection = MyConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(checkLogin);
        ){
            if(rs.next()){
                return true;
            }
        }
        return false;
    }

    @Override
    public long findUserByEmail(String email) throws SQLException {
        long id=-1;
        String query = String.format("SELECT ID FROM user WHERE Email='%s'",email);
        try(
            Connection connection = MyConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
        ){
            rs.next();
            id = rs.getLong("ID");
        }
        return id;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public long getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(long birthDay) {
        BirthDay = birthDay;
    }

    public static void main(String[] args) {
        long birthDay = 0;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse("8-6-1990");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date != null){
            birthDay = date.getTime();
        }
        User user = new User("Khalid","Akbar","babar@therap.net","asd",
                "male",birthDay);
        //user.setID(1);
        try {
            //user.registerUser(user);
            //user.deleteUser(1);
            User u = new User();
            //System.out.println(u.getEmail());
            u.updateUserInformation(user);
            u.logInUser("babar@therap","asd");
            User u1 =u.getUserById(3);
            System.out.println(u1.getEmail());
        } catch (SQLException e) {
            System.out.println("There was a error inserting the user to Database");
            e.printStackTrace();
        }
    }

}
