/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package databaseaccess;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;
import model.User;

/**
 *
 * @author Hai
 */
public class UserDAOImp implements UserDAO {

    @Override
    public User getUser(String username) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        User returnedUser = new User();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from Users where username = " + "'" + username + "'");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while(jr.next()) {
                returnedUser.setPassword(jr.getString("password"));
                returnedUser.setRole(jr.getString("role"));
                returnedUser.setId(jr.getString("id"));
                returnedUser.setSalt(jr.getString("salt"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return returnedUser;
    }

    public int addUser(User user) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        int check = 0;
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from Users");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            jr.moveToInsertRow();
            jr.updateString(1, user.getId());
            jr.updateString(2, user.getUsername());
            jr.updateString(3, user.getPassword());
            jr.updateString(4, user.getRole());
            jr.updateString(5, user.getSalt());
            jr.insertRow();
            jr.moveToCurrentRow();
            check++;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }
    
    public ArrayList findUsername() {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        ArrayList<String> usernameList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select username from Users");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while(jr.next()) {
                usernameList.add(jr.getString(1));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usernameList;
    }
}
