package main.java.net.therap.Facebook;

/**
 * @author babar
 * @since 11/1/15
 */
public class DatabaseConstants {
    public static final String HOST_NAME = "localhost";
    public static final int PORT = 3306;
    public static final String DB_NAME = "facebook";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "therap";
    public static final String CON_URL = String.format("jdbc:mysql://%s:%d/%s",HOST_NAME,PORT,DB_NAME);

    public static void main(String[] args) {
        System.out.println(CON_URL);
    }
}
