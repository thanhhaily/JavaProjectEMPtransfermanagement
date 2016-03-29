/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseaccess;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JdbcRowSetImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import model.Project;

/**
 *
 * @author EnablePassword
 */
public class ProjectDAOImp implements ProjectDAO {

    public static Date startDate, endDate;

    public Vector getProjectId() {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        Vector v = new Vector();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id from Projects");

            while (rs.next()) {
                v.add(rs.getString("id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return v;
    }

    @Override
    public Project search(String id) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        Project project;
        //Date startDate, endDate;
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_SearchProject(?,?,?,?,?)}");
            cstmt.setString(1, id);
            cstmt.registerOutParameter(2, java.sql.Types.NVARCHAR);
            cstmt.registerOutParameter(3, java.sql.Types.DATE);
            cstmt.registerOutParameter(4, java.sql.Types.DATE);
            cstmt.registerOutParameter(5, java.sql.Types.BIT);
            cstmt.execute();
            project = new Project(id, cstmt.getNString(2), cstmt.getDate(3), cstmt.getDate(4), cstmt.getBoolean(5));
            return project;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public int addNewProject(Project newProject) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        int check = 0;
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from Projects");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            jr.moveToInsertRow();
            jr.updateString(1, newProject.getId());
            jr.updateString(2, newProject.getName());
            jr.updateDate(3, newProject.getStartDate());
            jr.updateDate(4, newProject.getEndDate());
            jr.insertRow();
            jr.moveToCurrentRow();
            check++;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }
    
    public Project selectProject(String id) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        Project returnedProject = new Project();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from Projects where id = '" + id + "'");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while(jr.next()) {
                returnedProject.setName(jr.getString("name"));
                returnedProject.setStartDate(jr.getDate("startDate"));
                returnedProject.setEndDate(jr.getDate("endDate"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return returnedProject;
    }
    
    public void updateProject(Project projectUpdate) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            CachedRowSet cache = new CachedRowSetImpl();
            cache.setUrl("jdbc:sqlserver://localhost:1433;databaseName=ETM");
            cache.setUsername("sa");
            cache.setPassword("123456");
            cache.setCommand("select * from Projects where id = '" + projectUpdate.getId() + "'");
            cache.execute();
            cache.absolute(1);
            System.out.println(cache.getString(1));
            cache.updateString("name", projectUpdate.getName());
            cache.updateDate("startDate", projectUpdate.getStartDate());
            cache.updateDate("endDate", projectUpdate.getEndDate());
            cache.updateRow();
            cache.acceptChanges();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int deleteProject(String id) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        int resultUpdate = 0;
        Project returnedProject = new Project();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultUpdate = st.executeUpdate("update Projects set isDeleted = 1 where id = '" + id + "'");
                        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultUpdate;
    }
}
