/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseaccess;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.JdbcRowSet;
import model.Transfer;

/**
 *
 * @author EnablePassword
 */
public class TransferDAOImp implements TransferDAO {

    @Override
    public Transfer search(String id) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        Transfer transfer = null;
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_SearchTransfer(?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, id);
            cstmt.registerOutParameter(2, java.sql.Types.CHAR);
            cstmt.registerOutParameter(3, java.sql.Types.CHAR);
            cstmt.registerOutParameter(4, java.sql.Types.CHAR);
            cstmt.registerOutParameter(5, java.sql.Types.NVARCHAR);
            cstmt.registerOutParameter(6, java.sql.Types.NVARCHAR);
            cstmt.registerOutParameter(7, java.sql.Types.DATE);
            cstmt.registerOutParameter(8, java.sql.Types.DATE);
            cstmt.executeUpdate();
            transfer = new Transfer(id, cstmt.getString(2), cstmt.getString(3), cstmt.getString(4), cstmt.getNString(5), cstmt.getNString(6), cstmt.getDate(7), cstmt.getDate(8));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return transfer;
    }

    @Override
    public void add(Transfer transfer) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_AddTransfer(?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, transfer.getId());
            cstmt.setString(2, transfer.getEmployeeId());
            cstmt.setString(3, transfer.getFromProjectId());
            cstmt.setString(4, transfer.getToProjectId());
            cstmt.setString(5, transfer.getStatus());
            cstmt.setString(6, transfer.getApprovedBy());
            cstmt.setDate(7, transfer.getStartDate());
            cstmt.setDate(8, transfer.getEndDate());
            cstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Transfer transfer) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_UpdateTransfer(?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, transfer.getId());
            cstmt.setString(2, transfer.getEmployeeId());
            cstmt.setString(3, transfer.getFromProjectId());
            cstmt.setString(4, transfer.getToProjectId());
            cstmt.setString(5, transfer.getStatus());
            cstmt.setString(6, transfer.getApprovedBy());
            cstmt.setDate(7, transfer.getStartDate());
            cstmt.setDate(8, transfer.getEndDate());
            cstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //Hai
    public List<String> getEmpFromDateToDate(Date date1, Date date2) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<String> nameList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "select id from Employees "
                    + "where id not in("
                    + "select employeeId from Transfers where ('" + date1 + "' between startDate and endDate "
                    + "or '" + date2 + "' between startDate and endDate "
                    + " or startDate between '" + date1 + "'"
                    + " and '" + date2 + "'" + " or endDate between '" + date1 + "' and '" + date2 + "')"
                    + "and status like 'Approved%') and isDeleted = '0'";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);

            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                nameList.add(jr.getString("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransferDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nameList;
    }

    public List<String> getEmpByCurrentProject(String projectId) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<String> nameList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery("select Employees.id from Employees join Transfers on Employees.id = Transfers.employeeId"
                    + " where isDeleted = '0' and ((SELECT GETDATE()) BETWEEN startDate AND endDate) and toProjectId = '" + projectId + "'");

            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                nameList.add(jr.getString("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransferDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nameList;
    }
    
    public List<String> getAllEmp() {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<String> nameList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery("select * from Employees where isDeleted = '0'");

            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                nameList.add(jr.getString("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransferDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nameList;
    }

    public boolean checkEmpFreeFromDateToDate(String id, java.sql.Date dateFrom, java.sql.Date dateTo) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        boolean freeOrNot = false;
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select id from Employees "
                    + "where id not in("
                    + "select employeeId from Transfers where ('" + dateFrom + "' between startDate and endDate "
                    + "or '" + dateTo + "' between startDate and endDate "
                    + " or startDate between '" + dateFrom + "'"
                    + " and '" + dateTo + "'" + " or endDate between '" + dateFrom + "' and '" + dateTo + "')"
                    + "and status like 'Approved%') and isDeleted = '0'";
            ResultSet rs = st.executeQuery(query);

            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                if (id.equals(jr.getString("id"))) {
                    freeOrNot = true;
                    break;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransferDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return freeOrNot;
    }

    public String getCurrentProjectIdOfEmp(String empId) {
        String currentProjId = null;
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery("select toProjectId from Transfers"
                    + " where ((SELECT GETDATE()) BETWEEN startDate AND endDate) and employeeId = '" + empId + "'");

            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                if (jr.getString("toProjectId") != null) {
                    currentProjId = jr.getString("toProjectId");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransferDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return currentProjId;
    }
}
