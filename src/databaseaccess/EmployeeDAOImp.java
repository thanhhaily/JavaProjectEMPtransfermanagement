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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.sql.rowset.JdbcRowSet;
import model.Employee;
import model.Transfer;

/**
 *
 * @author EnablePassword
 */
public class EmployeeDAOImp implements EmployeeDAO {

    @Override
    public Employee search(String id) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        Employee employee;
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_SearchEmployee(?,?,?,?,?,?,?)}");
            cstmt.setString(1, id);
            cstmt.registerOutParameter(2, java.sql.Types.NVARCHAR);
            cstmt.registerOutParameter(3, java.sql.Types.NVARCHAR);
            cstmt.registerOutParameter(4, java.sql.Types.NVARCHAR);
            cstmt.registerOutParameter(5, java.sql.Types.NVARCHAR);
            cstmt.registerOutParameter(6, java.sql.Types.NVARCHAR);
            cstmt.registerOutParameter(7, java.sql.Types.NVARCHAR);
            cstmt.execute();
            employee = new Employee(id, cstmt.getNString(2), cstmt.getNString(3), cstmt.getNString(4), cstmt.getNString(5), cstmt.getNString(6), cstmt.getString(7), false);
            if (cstmt.getNString(2) == null && cstmt.getNString(3) == null && cstmt.getNString(4) == null && cstmt.getNString(5) == null && cstmt.getNString(6) == null && cstmt.getString(7) == null) {
                employee = new Employee(id, "", "", "", "", "", "", true);
            } else {
                employee = new Employee(id, cstmt.getNString(2), cstmt.getNString(3), cstmt.getNString(4), cstmt.getNString(5), cstmt.getNString(6), cstmt.getString(7), false);
            }
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void add(Employee employee) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_AddEmployee(?,?,?,?,?,?,?)}");
            cstmt.setString(1, employee.getId());
            cstmt.setString(2, employee.getName());
            cstmt.setString(3, employee.getEmail());
            cstmt.setString(4, employee.getPhone());
            cstmt.setString(5, employee.getAddress());
            cstmt.setString(6, employee.getSkill());
            cstmt.setString(7, employee.getPosition());
            cstmt.executeUpdate();
            DBConnect.message = "Employee added!";
        } catch (Exception e) {
            DBConnect.message = "Please choose another ID.";
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Employee employee) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_UpdateEmployee(?,?,?,?,?,?,?)}");
            cstmt.setString(1, employee.getId());
            cstmt.setString(2, employee.getName());
            cstmt.setString(3, employee.getEmail());
            cstmt.setString(4, employee.getPhone());
            cstmt.setString(5, employee.getAddress());
            cstmt.setString(6, employee.getSkill());
            cstmt.setString(7, employee.getPosition());
            cstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_DeleteEmployee(?)}");
            cstmt.setString(1, id);
            cstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public String getCurrentStatus(String id) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
        String currentStatus = "";

        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_GetCurrentProject(?,?,?)}");
            cstmt.setString(1, id);
            cstmt.setDate(2, currentDate);
            cstmt.registerOutParameter(3, java.sql.Types.CHAR);
            cstmt.execute();
            if (cstmt.getString(3) != null) {
                currentStatus = cstmt.getString(3);
            } else {
                currentStatus = "Free";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return currentStatus;
    }

    public boolean isFree(String id) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_GetCurrentProject(?,?,?)}");
            cstmt.setString(1, id);
            cstmt.setDate(2, currentDate);
            cstmt.registerOutParameter(3, java.sql.Types.CHAR);
            cstmt.execute();
            if (cstmt.getString(3) != null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isFreeOn(String id, java.sql.Date date) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_GetCurrentProject(?,?,?)}");
            cstmt.setString(1, id);
            cstmt.setDate(2, date);
            cstmt.registerOutParameter(3, java.sql.Types.CHAR);
            cstmt.execute();
            if (cstmt.getString(3) != null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }

    //Hai's code: Search records
    public List<Transfer> searchRecordByID(String id) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<Transfer> transferObjList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from Transfers where employeeId = " + "'" + id + "'");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                Transfer transferObj = new Transfer();
                transferObj.setId(jr.getString("id"));
                transferObj.setEmployeeId(jr.getString("employeeId"));
                transferObj.setFromProjectId(jr.getString("fromProjectId"));
                transferObj.setToProjectId(jr.getString("toProjectId"));
                transferObj.setStatus(jr.getString("status"));
                transferObj.setApprovedBy(jr.getString("approvedBy"));
                transferObj.setStartDate(jr.getDate("startDate"));
                transferObj.setEndDate(jr.getDate("endDate"));
                transferObjList.add(transferObj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transferObjList;
    }

    public List<Transfer> searchRecordByFromProject(String fromProject) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<Transfer> transferObjList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from Transfers where fromProjectId = " + "'" + fromProject + "'");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                Transfer transferObj = new Transfer();
                transferObj.setId(jr.getString("id"));
                transferObj.setEmployeeId(jr.getString("employeeId"));
                transferObj.setFromProjectId(jr.getString("fromProjectId"));
                transferObj.setToProjectId(jr.getString("toProjectId"));
                transferObj.setStatus(jr.getString("status"));
                transferObj.setApprovedBy(jr.getString("approvedBy"));
                transferObj.setStartDate(jr.getDate("startDate"));
                transferObj.setEndDate(jr.getDate("endDate"));
                transferObjList.add(transferObj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transferObjList;
    }

    public List<Transfer> searchRecordByToProject(String toProject) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<Transfer> transferObjList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from Transfers where toProjectId = " + "'" + toProject + "'");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                Transfer transferObj = new Transfer();
                transferObj.setId(jr.getString("id"));
                transferObj.setEmployeeId(jr.getString("employeeId"));
                transferObj.setFromProjectId(jr.getString("fromProjectId"));
                transferObj.setToProjectId(jr.getString("toProjectId"));
                transferObj.setStatus(jr.getString("status"));
                transferObj.setApprovedBy(jr.getString("approvedBy"));
                transferObj.setStartDate(jr.getDate("startDate"));
                transferObj.setEndDate(jr.getDate("endDate"));
                transferObjList.add(transferObj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transferObjList;
    }

    public List<Transfer> searchRecordByDate(Date date1, Date date2) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<Transfer> transferObjList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from Transfers where startDate between " + "'" + date1 + "'"
                    + " and '" + date2 + "'" + " or endDate between '" + date1 + "' and '" + date2 + "'"
                    + " or '" + date1 + "' between startDate and endDate "
                    + "or '" + date2 + "' between startDate and endDate ");

            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                Transfer transferObj = new Transfer();
                transferObj.setId(jr.getString("id"));
                transferObj.setEmployeeId(jr.getString("employeeId"));
                transferObj.setFromProjectId(jr.getString("fromProjectId"));
                transferObj.setToProjectId(jr.getString("toProjectId"));
                transferObj.setStatus(jr.getString("status"));
                transferObj.setApprovedBy(jr.getString("approvedBy"));
                transferObj.setStartDate(jr.getDate("startDate"));
                transferObj.setEndDate(jr.getDate("endDate"));
                transferObjList.add(transferObj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transferObjList;
    }
    
    public List<Transfer> searchAllRecord() {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<Transfer> transferObjList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from Transfers");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                Transfer transferObj = new Transfer();
                transferObj.setId(jr.getString("id"));
                transferObj.setEmployeeId(jr.getString("employeeId"));
                transferObj.setFromProjectId(jr.getString("fromProjectId"));
                transferObj.setToProjectId(jr.getString("toProjectId"));
                transferObj.setStatus(jr.getString("status"));
                transferObj.setApprovedBy(jr.getString("approvedBy"));
                transferObj.setStartDate(jr.getDate("startDate"));
                transferObj.setEndDate(jr.getDate("endDate"));
                transferObjList.add(transferObj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transferObjList;
    }
    
    public List<Transfer> searchRecordByMonth(int month, int year) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<Transfer> transferObjList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select id, employeeId, fromProjectId, toProjectId, status"
                    + " from Transfers where MONTH(startDate) = " + month + "and YEAR(startDate) = " + year);
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                Transfer transferObj = new Transfer();
                transferObj.setId(jr.getString("id"));
                transferObj.setEmployeeId(jr.getString("employeeId"));
                transferObj.setFromProjectId(jr.getString("fromProjectId"));
                transferObj.setToProjectId(jr.getString("toProjectId"));
                transferObj.setStatus(jr.getString("status"));
                transferObjList.add(transferObj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transferObjList;
    }
    
    public List<Transfer> searchRecordByFromAndToProject(String projectFrom, String projectTo, String andOr) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<Transfer> transferObjList = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select id, employeeId, fromProjectId, toProjectId, status"
                    + " from Transfers where fromProjectId = '" + projectFrom + "' " + andOr 
                    + " toProjectId = '" + projectTo + "'");
            JdbcRowSet jr = new JdbcRowSetImpl(rs);
            while (jr.next()) {
                Transfer transferObj = new Transfer();
                transferObj.setId(jr.getString("id"));
                transferObj.setEmployeeId(jr.getString("employeeId"));
                transferObj.setFromProjectId(jr.getString("fromProjectId"));
                transferObj.setToProjectId(jr.getString("toProjectId"));
                transferObj.setStatus(jr.getString("status"));
                transferObjList.add(transferObj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transferObjList;
    }

    //End search record
    
    @Override
    public void updateCurrentProjectId(String id, String currentProjectId) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        try {
            CallableStatement cstmt = conn.prepareCall("{call usp_UpdateCurrentProject(?,?)}");
            cstmt.setString(1, id);
            cstmt.setString(2, currentProjectId);
            cstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public List<String> getEmpBySkill(StringBuilder skillList) {
        DBConnect dbc = new DBConnect();
        Connection conn = dbc.getConnection();
        List<String> listHasSkill = new ArrayList<>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery("select id from Employees where skill like '%" + skillList + "%' and isDeleted = '0'");

            JdbcRowSet jr = new JdbcRowSetImpl(rs);

            while (jr.next()) {
                listHasSkill.add(jr.getString("id"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listHasSkill;
    }
}
