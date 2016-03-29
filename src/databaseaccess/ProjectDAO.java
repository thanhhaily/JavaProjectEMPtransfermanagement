/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseaccess;

import model.Project;

/**
 *
 * @author EnablePassword
 */
public interface ProjectDAO {
    public Project search(String id);
}
