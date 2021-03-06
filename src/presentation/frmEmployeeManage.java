/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import databaseaccess.EmployeeDAOImp;
import databaseaccess.DBConnect;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Employee;

/**
 *
 * @author EnablePassword
 */
public class frmEmployeeManage extends javax.swing.JDialog {

    /**
     * Creates new form frmEmployeeManage
     */
    Vector data = new Vector();

    public frmEmployeeManage(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initTable();
        getData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cbxId = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        chkNet = new javax.swing.JCheckBox();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        chkJava = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        chkHtml5 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        chkSql = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        cbxPosition = new javax.swing.JComboBox<>();
        lblCurrentStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Commands", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add List-24.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Save-24.png"))); // NOI18N
        btnSave.setText("Save Edit");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Delete-24.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbxId.setEditable(true);
        cbxId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxId, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N

        jLabel7.setText("Phone:");

        jLabel8.setText("Address:");

        chkNet.setText(".NET");

        jLabel9.setText("Current status:");

        jLabel3.setText("Name*:");

        chkJava.setText("Java");

        jLabel4.setText("Email*:");

        chkHtml5.setText("HTML5");

        jLabel5.setText("Skill:");

        chkSql.setText("SQL");

        jLabel6.setText("Position:");

        cbxPosition.setEditable(true);
        cbxPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Accounting", "Secretary", "Developer", "Designer", "Marketing", "System Analize" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkJava)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkNet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkHtml5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSql))
                    .addComponent(lblCurrentStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPhone)
                    .addComponent(txtEmail)
                    .addComponent(txtName)
                    .addComponent(cbxPosition, 0, 340, Short.MAX_VALUE)
                    .addComponent(txtAddress))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(chkJava)
                    .addComponent(chkHtml5)
                    .addComponent(chkSql)
                    .addComponent(chkNet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblCurrentStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel2, jPanel3, jScrollPane1});

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void initTable() {
        try {
            DBConnect dbc = new DBConnect();
            Connection conn = dbc.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id as \"ID\", name as \"Name\", position as \"Position\", skill as \"Skill\" FROM ETM.dbo.Employees WHERE isDeleted='0'");
            int len = rs.getMetaData().getColumnCount();
            Vector cols = new Vector(len);
            for (int i = 1; i <= len; i++) {
                cols.add(rs.getMetaData().getColumnName(i));
            }

            TableModel tm = new DefaultTableModel(new Vector(), cols);
            tblData.setModel(tm);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getData() {
        try {
            DBConnect dbc = new DBConnect();
            Connection conn = dbc.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM ETM.dbo.Employees WHERE isDeleted=0");
            Vector v = new Vector();
            while (rs.next()) {
                String id = rs.getString("id");
                v.add(id);
            }
            EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
            DefaultComboBoxModel cbmModel = new DefaultComboBoxModel(v);
            cbxId.setModel(cbmModel);
            cbxId.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent event) {
                    txtName.setText(null);
                    txtEmail.setText(null);
                    txtPhone.setText(null);
                    txtAddress.setText(null);
                    chkHtml5.setSelected(false);
                    chkJava.setSelected(false);
                    chkNet.setSelected(false);
                    chkSql.setSelected(false);
                    lblCurrentStatus.setText(null);
                    cbxPosition.setSelectedIndex(0);

                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    Employee employee = employeeDAOImp.search(String.valueOf(selected));
                    txtName.setText(employee.getName());
                    txtEmail.setText(employee.getEmail());
                    txtPhone.setText(employee.getPhone());
                    txtAddress.setText(employee.getAddress());
                    String[] skill = employee.getSkill().split("\\s+");
                    for (int i = 0; i < skill.length; i++) {
                        if ("Java".equals(skill[i])) {
                            chkJava.setSelected(true);
                        }
                        if (".Net".equals(skill[i])) {
                            chkNet.setSelected(true);
                        }
                        if ("HTML5".equals(skill[i])) {
                            chkHtml5.setSelected(true);
                        }
                        if ("SQL".equals(skill[i])) {
                            chkSql.setSelected(true);
                        }
                    }
                    lblCurrentStatus.setText(employeeDAOImp.getCurrentStatus(employee.getId()));
                    int position = 5;
                    switch (employee.getPosition()) {
                        case "Accounting":
                            position = 0;
                            break;
                        case "Secretary":
                            position = 1;
                            break;
                        case "Developer":
                            position = 2;
                            break;
                        case "Designer":
                            position = 3;
                            break;
                        case "Marketing":
                            position = 4;
                            break;
                        case "System Analize":
                            position = 5;
                            break;
                        default:
                            break;
                    }
                    cbxPosition.setSelectedIndex(position);
                }
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            data.clear();
            DBConnect dbc = new DBConnect();
            Connection conn = dbc.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id as \"ID\", name as \"Name\", position as \"Position\", skill as \"Skill\" FROM ETM.dbo.Employees WHERE isDeleted='0'");
            int len = rs.getMetaData().getColumnCount();
            Vector cols = new Vector(len);
            for (int i = 1; i <= len; i++) {
                cols.add(rs.getMetaData().getColumnName(i));
            }
            while (rs.next()) {
                Vector row = new Vector(len);
                for (int i = 1; i <= len; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            TableModel tm = new DefaultTableModel(data, cols);
            tblData.setModel(tm);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    //Đặt sự kiện cho nút Add
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!"".equals(txtName.getText()) && !"".equals(txtEmail.getText())) {
            String skill = "";
            if (chkJava.isSelected()) {
                skill += "Java ";
            }
            if (chkNet.isSelected()) {
                skill += ".Net ";
            }
            if (chkHtml5.isSelected()) {
                skill += "HTML5 ";
            }
            if (chkSql.isSelected()) {
                skill += "SQL ";
            }
            Employee employee = new Employee(String.valueOf(cbxId.getSelectedItem()).toUpperCase(), txtName.getText(), txtEmail.getText(), txtPhone.getText(), txtAddress.getText(), skill, String.valueOf(cbxPosition.getSelectedItem()), false);
            EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
            employeeDAOImp.add(employee);
            JOptionPane.showMessageDialog(this, DBConnect.message);
            cbxId.setSelectedIndex(0);
            txtName.setText(null);
            txtEmail.setText(null);
            txtPhone.setText(null);
            txtAddress.setText(null);
            chkHtml5.setSelected(false);
            chkJava.setSelected(false);
            chkNet.setSelected(false);
            chkSql.setSelected(false);
            cbxPosition.setSelectedIndex(0);
            txtName.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
            txtEmail.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
            jLabel3.setForeground(Color.black);
            jLabel4.setForeground(Color.black);
            getData();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter required field(s).");
            txtName.setBorder(BorderFactory.createLineBorder(Color.decode("#ff0000")));
            txtEmail.setBorder(BorderFactory.createLineBorder(Color.decode("#ff0000")));
            jLabel3.setForeground(Color.red);
            jLabel4.setForeground(Color.red);
        }
    }//GEN-LAST:event_btnAddActionPerformed
    //Đặt sự kiện cho nút Save
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String skill = "";
        if (chkJava.isSelected()) {
            skill += "Java ";
        }
        if (chkNet.isSelected()) {
            skill += ".Net ";
        }
        if (chkHtml5.isSelected()) {
            skill += "HTML5 ";
        }
        if (chkSql.isSelected()) {
            skill += "SQL ";
        }
        Employee employee = new Employee(String.valueOf(cbxId.getSelectedItem()).toUpperCase(), txtName.getText(), txtEmail.getText(), txtPhone.getText(), txtAddress.getText(), skill, String.valueOf(cbxPosition.getSelectedItem()), false);
        EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
        employeeDAOImp.update(employee);
        JOptionPane.showMessageDialog(this, "Saved!");
        cbxId.setSelectedIndex(0);
        txtName.setText(null);
        txtEmail.setText(null);
        txtPhone.setText(null);
        txtAddress.setText(null);
        chkHtml5.setSelected(false);
        chkJava.setSelected(false);
        chkNet.setSelected(false);
        chkSql.setSelected(false);
        cbxPosition.setSelectedIndex(0);
        getData();
    }//GEN-LAST:event_btnSaveActionPerformed
    //Đặt sự kiện cho nút Delete
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure deleting this employee?");
        if (dialogResult == JOptionPane.YES_OPTION) {
            EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
            employeeDAOImp.delete(String.valueOf(cbxId.getSelectedItem()));
            JOptionPane.showMessageDialog(this, "Deleted!");
            cbxId.setSelectedIndex(0);
            txtName.setText(null);
            txtEmail.setText(null);
            txtPhone.setText(null);
            txtAddress.setText(null);
            chkHtml5.setSelected(false);
            chkJava.setSelected(false);
            chkNet.setSelected(false);
            chkSql.setSelected(false);
            cbxPosition.setSelectedIndex(0);
            getData();
        } else;


    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmEmployeeManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEmployeeManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEmployeeManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEmployeeManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmEmployeeManage dialog = new frmEmployeeManage(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbxId;
    private javax.swing.JComboBox<String> cbxPosition;
    private javax.swing.JCheckBox chkHtml5;
    private javax.swing.JCheckBox chkJava;
    private javax.swing.JCheckBox chkNet;
    private javax.swing.JCheckBox chkSql;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurrentStatus;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
