/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseaccess;

import model.Employee;

/**
 *
 * @author EnablePassword
 */
public interface EmployeeDAO {
    public Employee search(String id);
    public void add(Employee employee);
    public void update(Employee employee);
    public void updateCurrentProjectId(String id, String currentProjectId);
    public void delete(String id);
}
