/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import databaseaccess.DBConnect;
import databaseaccess.EmployeeDAOImp;
import databaseaccess.ProjectDAOImp;
import databaseaccess.TransferDAOImp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Employee;
import model.Project;
import model.Transfer;

/**
 *
 * @author EnablePassword
 */
public class frmSearchEmployee extends javax.swing.JDialog {

    int currentRole = frmMain.roleNumber;
    /**
     * Creates new form frmSearchEmployeeNew
     */
    Vector dataSearch = new Vector();
    Vector dataHistory = new Vector();
    private String query;
    private Employee employee;
    ProjectDAOImp projectDAOImp = new ProjectDAOImp();
    EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
    public frmSearchEmployee(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        if(currentRole==12){
            chkConfirm.setEnabled(true);
        }
        ListSelectionModel listSelectionModel = tblSearch.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!listSelectionModel.isSelectionEmpty()) {
                    lblId.setText(null);
                    lblName.setText(null);
                    lblCurrentProject.setText(null);
                    lblEmail.setText(null);
                    lblPhone.setText(null);
                    lblFromDate.setText(null);
                    lblToDate.setText(null);
                    chkJava.setSelected(false);
                    chkNet.setSelected(false);
                    chkHtml5.setSelected(false);
                    chkSql.setSelected(false);
                    employee = employeeDAOImp.search((String) tblSearch.getValueAt(tblSearch.getSelectedRow(), 0));
                    lblId.setText(employee.getId());
                    lblName.setText(employee.getName());
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
                    lblCurrentProject.setText((String) tblSearch.getValueAt(tblSearch.getSelectedRow(), 4));
                    lblFromDate.setText((String) tblSearch.getValueAt(tblSearch.getSelectedRow(), 5));
                    lblToDate.setText((String) tblSearch.getValueAt(tblSearch.getSelectedRow(), 6));
                    lblEmail.setText(employee.getEmail());
                    lblPhone.setText(employee.getPhone());

                    try {
                        dataHistory.clear();
                        DBConnect dbc = new DBConnect();
                        Connection conn = dbc.getConnection();
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT id, employeeId, fromProjectId, toProjectId, status, approvedBy, startDate, endDate FROM ETM.dbo.Transfers WHERE employeeId = '" + (String) tblSearch.getValueAt(tblSearch.getSelectedRow(), 0) + "'");
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
                            dataHistory.add(row);
                        }
                        TableModel tm = new DefaultTableModel(dataHistory, cols);
                        tblHistory.setModel(tm);

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
        chkConfirm.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                if (chkConfirm.isSelected()) {
                    btnSubmit.setEnabled(rootPaneCheckingEnabled);
                } else {
                    btnSubmit.setEnabled(false);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        radId = new javax.swing.JRadioButton();
        radName = new javax.swing.JRadioButton();
        radProject = new javax.swing.JRadioButton();
        radFreeOn = new javax.swing.JRadioButton();
        radAll = new javax.swing.JRadioButton();
        dtcSearcDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblFromDate = new javax.swing.JLabel();
        lblCurrentProject = new javax.swing.JLabel();
        chkJava = new javax.swing.JCheckBox();
        chkNet = new javax.swing.JCheckBox();
        chkSql = new javax.swing.JCheckBox();
        chkHtml5 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblToDate = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        chkConfirm = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        txtProjectId = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dtcStartDate = new com.toedter.calendar.JDateChooser();
        dtcEndDate = new com.toedter.calendar.JDateChooser();
        lblName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N

        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblSearch);

        jLabel2.setText("Search by:");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search-24.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        buttonGroup1.add(radId);
        radId.setText("EMPID");
        radId.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radIdStateChanged(evt);
            }
        });

        buttonGroup1.add(radName);
        radName.setText("Name");
        radName.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radNameStateChanged(evt);
            }
        });

        buttonGroup1.add(radProject);
        radProject.setText("Project ID");
        radProject.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radProjectStateChanged(evt);
            }
        });

        buttonGroup1.add(radFreeOn);
        radFreeOn.setText("Free on");
        radFreeOn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radFreeOnStateChanged(evt);
            }
        });

        buttonGroup1.add(radAll);
        radAll.setText("All");
        radAll.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radAllStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(radAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radProject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radFreeOn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtcSearcDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearch))
                    .addComponent(dtcSearcDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radId)
                    .addComponent(radName)
                    .addComponent(radProject)
                    .addComponent(jLabel2)
                    .addComponent(radFreeOn)
                    .addComponent(radAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N

        jLabel1.setText("ID:");

        jLabel3.setText("Skill:");

        jLabel4.setText("Current Project:");

        jLabel9.setText("From date:");

        jLabel6.setText("Email:");

        chkJava.setText("Java");

        chkNet.setText(".NET");

        chkSql.setText("SQL");

        chkHtml5.setText("HTML 5");

        jLabel5.setText("Name:");

        jLabel10.setText("To date:");

        jLabel8.setText("Phone:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transfer History", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblHistory);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transfer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N

        chkConfirm.setEnabled(false);
        chkConfirm.setText("I've read these information carefully and want to recruit this employee to my project.");

        jLabel11.setText("Project ID:");

        btnSubmit.setEnabled(false);
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Invite-24.png"))); // NOI18N
        btnSubmit.setText("Request");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel12.setText("Start Date:");

        jLabel13.setText("End Date:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProjectId)
                            .addComponent(dtcStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtcEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnSubmit)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(chkConfirm)
                        .addGap(11, 11, 11))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtProjectId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12))
                            .addComponent(dtcStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dtcEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addComponent(chkConfirm))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCurrentProject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblName))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(chkJava)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkNet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkHtml5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkSql))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblId)
                    .addComponent(jLabel5)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(chkJava)
                    .addComponent(chkNet)
                    .addComponent(chkHtml5)
                    .addComponent(chkSql))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblCurrentProject))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblFromDate)
                    .addComponent(jLabel10)
                    .addComponent(lblToDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblEmail)
                    .addComponent(jLabel8)
                    .addComponent(lblPhone))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search() {
        dataSearch.clear();
        try {
            DBConnect dbc = new DBConnect();
            Connection conn = dbc.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
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
                dataSearch.add(row);
            }
            TableModel tm = new DefaultTableModel(dataSearch, cols);
            tblSearch.setModel(tm);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchData = txtSearch.getText();
        if (radAll.isSelected()) {
            query = "SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', c.name as 'Current Project', b.startDate as 'Start Date', b.endDate as 'End Date' FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' UNION SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', NULL as 'Current Project', NULL as 'Start Date', NULL as 'End Date' FROM ETM.dbo.Employees a FULL OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId FULL OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id WHERE a.id NOT IN ( 	SELECT a.id 	FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id 	WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' ) AND a.isDeleted='0' ";
            search();
        }
        if (radId.isSelected()) {
            query = "SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', c.name as 'Current Project', b.startDate as 'Start Date', b.endDate as 'End Date' FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' AND a.id='" + searchData + "'" + " UNION SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', NULL as 'Current Project', NULL as 'Start Date', NULL as 'End Date' FROM ETM.dbo.Employees a FULL OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId FULL OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id WHERE a.id NOT IN ( 	SELECT a.id 	FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id 	WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' ) AND a.isDeleted='0' AND a.id='" + searchData + "'";
            search();
        }
        if (radName.isSelected()) {
            query = "SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', c.name as 'Current Project', b.startDate as 'Start Date', b.endDate as 'End Date' FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' AND a.name='" + searchData + "'" + " UNION SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', NULL as 'Current Project', NULL as 'Start Date', NULL as 'End Date' FROM ETM.dbo.Employees a FULL OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId FULL OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id WHERE a.id NOT IN ( 	SELECT a.id 	FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id 	WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' ) AND a.isDeleted='0' AND a.name='" + searchData + "'";
            search();
        }
        if (radProject.isSelected()) {
            query = "SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', c.name as 'Current Project', b.startDate as 'Start Date', b.endDate as 'End Date' FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' AND c.id='" + searchData + "'";
            search();
        }
        if (radFreeOn.isSelected()) {
            java.util.Date utilDate = dtcSearcDate.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            query = "SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', c.name as 'Current Project', b.startDate as 'Start Date', b.endDate as 'End Date' FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' AND a.id NOT IN ( 	SELECT a.id 	FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id 	WHERE ('" + sqlDate + "' BETWEEN b.startDate AND b.endDate) AND a.isDeleted='0' AND b.status ='Approved') UNION SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', NULL as 'Current Project', NULL as 'Start Date', NULL as 'End Date' FROM ETM.dbo.Employees a FULL OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId FULL OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id WHERE a.id NOT IN ( 	SELECT a.id 	FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id 	WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' ) AND a.isDeleted='0'";
            search();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        TransferDAOImp transferDAOImp = new TransferDAOImp();
        URL url = getClass().getResource("passphrase.txt");
        File tempFile = new File(url.getPath());
        File file = new File(tempFile.getParentFile().getParentFile().getParentFile().getParentFile(), "passphrase.txt");
        String id = "";
        try {
            Scanner fileScanner = new Scanner(file);
            id = "TRF" + fileScanner.nextLine();
            FileWriter fileStream = new FileWriter(url.getPath());
            BufferedWriter out = new BufferedWriter(fileStream);
            while (fileScanner.hasNextLine()) {
                String next = fileScanner.nextLine();
                if (next.equals("\n")) {
                    out.newLine();
                } else {
                    out.write(next);
                }
                out.newLine();
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmSearchEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frmSearchEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(id);
        java.util.Date startDate = dtcStartDate.getDate();
        java.util.Date endDate = dtcEndDate.getDate();
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        String dateBusy = "";
        int count = 0;
        for (LocalDate date = sqlStartDate.toLocalDate(); date.isBefore(sqlEndDate.toLocalDate()); date = date.plusDays(1)) {
            if (!employeeDAOImp.isFreeOn(employee.getId(), java.sql.Date.valueOf(date))) {
                dateBusy += java.sql.Date.valueOf(date) + "; ";
                count++;
            }
        }
        Project project = projectDAOImp.search(txtProjectId.getText());
        //khi có ngày bận
        if (count > 0) {
            JOptionPane.showMessageDialog(this, "Employee busy on " + dateBusy);
        } //khi ngày bắt đầu > ngày kết thúc
        else if (startDate.after(endDate)) {
            JOptionPane.showMessageDialog(this, "Start date is after end date!");
        } else if (sqlStartDate.before(project.getStartDate()) || sqlEndDate.after(project.getEndDate())) {
            JOptionPane.showMessageDialog(this, "Transfer's dates are out of Project's dates!");
        } else {
            String currentProject = null;
            if (!employeeDAOImp.isFree(employee.getId())) {
                currentProject = employeeDAOImp.getCurrentStatus(employee.getId());
            }
            Transfer transfer = new Transfer(id, employee.getId(), currentProject, txtProjectId.getText(), "Pending "+frmMain.currentPMLoggedIn, "", sqlStartDate, sqlEndDate);
            transferDAOImp.add(transfer);
            dataHistory.clear();
            try {
                dataHistory.clear();
                DBConnect dbc = new DBConnect();
                Connection conn = dbc.getConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT id, employeeId, fromProjectId, toProjectId, status, approvedBy, startDate, endDate FROM ETM.dbo.Transfers WHERE employeeId = '" + (String) tblSearch.getValueAt(tblSearch.getSelectedRow(), 0) + "'");
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
                    dataHistory.add(row);
                }
                TableModel tm = new DefaultTableModel(dataHistory, cols);
                tblHistory.setModel(tm);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Transfer request sent!");
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        btnSearchActionPerformed(evt);
    }//GEN-LAST:event_txtSearchActionPerformed

    private void radAllStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radAllStateChanged
        if (radAll.isSelected()) {
            txtSearch.setVisible(true);
            txtSearch.setEnabled(false);
            dtcSearcDate.setVisible(false);
        }
    }//GEN-LAST:event_radAllStateChanged

    private void radIdStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radIdStateChanged
        if (radId.isSelected()) {
            txtSearch.setVisible(true);
            txtSearch.setEnabled(true);
            dtcSearcDate.setVisible(false);
        }
    }//GEN-LAST:event_radIdStateChanged

    private void radNameStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radNameStateChanged
        if (radName.isSelected()) {
            txtSearch.setVisible(true);
            txtSearch.setEnabled(true);
            dtcSearcDate.setVisible(false);
        }
    }//GEN-LAST:event_radNameStateChanged

    private void radProjectStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radProjectStateChanged
        if (radProject.isSelected()) {
            txtSearch.setVisible(true);
            txtSearch.setEnabled(true);
            dtcSearcDate.setVisible(false);
        }
    }//GEN-LAST:event_radProjectStateChanged

    private void radFreeOnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radFreeOnStateChanged
        if (radFreeOn.isSelected()) {
            txtSearch.setVisible(false);
            txtSearch.setEnabled(false);
            dtcSearcDate.setVisible(true);
        } else {
            txtSearch.setVisible(true);
            dtcSearcDate.setVisible(false);
        }
    }//GEN-LAST:event_radFreeOnStateChanged

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
            java.util.logging.Logger.getLogger(frmSearchEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSearchEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSearchEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSearchEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmSearchEmployee dialog = new frmSearchEmployee(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSubmit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkConfirm;
    private javax.swing.JCheckBox chkHtml5;
    private javax.swing.JCheckBox chkJava;
    private javax.swing.JCheckBox chkNet;
    private javax.swing.JCheckBox chkSql;
    private com.toedter.calendar.JDateChooser dtcEndDate;
    private com.toedter.calendar.JDateChooser dtcSearcDate;
    private com.toedter.calendar.JDateChooser dtcStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCurrentProject;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFromDate;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblToDate;
    private javax.swing.JRadioButton radAll;
    private javax.swing.JRadioButton radFreeOn;
    private javax.swing.JRadioButton radId;
    private javax.swing.JRadioButton radName;
    private javax.swing.JRadioButton radProject;
    private javax.swing.JTable tblHistory;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtProjectId;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
