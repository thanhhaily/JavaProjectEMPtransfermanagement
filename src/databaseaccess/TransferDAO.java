/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseaccess;

import model.Transfer;

/**
 *
 * @author EnablePassword
 */
public interface TransferDAO {
    public Transfer search(String id);
    public void add(Transfer transfer);
    public void update(Transfer transfer);
}
