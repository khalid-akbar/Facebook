package main.java.net.therap.Dao;

import java.sql.SQLException;

/**
 * @author babar
 * @since 11/1/15
 */
public interface UserDao {
    void registerUser(User user) throws SQLException;
    void updateUserInformation(User user) throws SQLException;
    void deleteUser(long id) throws SQLException;
    User getUserById(long id) throws SQLException;
    boolean logInUser(String email, String password) throws SQLException;
    long findUserByEmail(String email) throws SQLException;
}
