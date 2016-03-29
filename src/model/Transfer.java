/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author EnablePassword
 */
public class Transfer {

    String id, employeeId, fromProjectId, toProjectId, status, approvedBy;
    Date startDate, endDate;

    public Transfer() {
    }

    public Transfer(String id, String employeeId, String fromProjectId, String toProjectId, String status, String approvedBy, Date startDate, Date endDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.fromProjectId = fromProjectId;
        this.toProjectId = toProjectId;
        this.status = status;
        this.approvedBy = approvedBy;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFromProjectId() {
        return fromProjectId;
    }

    public void setFromProjectId(String fromProjectId) {
        this.fromProjectId = fromProjectId;
    }

    public String getToProjectId() {
        return toProjectId;
    }

    public void setToProjectId(String toProjectId) {
        this.toProjectId = toProjectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
