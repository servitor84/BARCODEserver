package de.di.barcode.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Rahman
 */
public class ConvertTiff2Pdf extends javax.swing.JPanel {

    Properties properties = new Properties();
    private ResourceBundle bundle;

    public ConvertTiff2Pdf(Properties prop) {
        properties = prop;
        initComponents();
        loadResources();
        setProfileProperties(properties);
    }

    public void loadResources() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        SwingUtilities.updateComponentTreeUI(this);
        Locale locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle("de/di/barcode/gui/resources/ConvertTiff2Pdf", locale);
        jLabel1.setText(bundle.getString("jLabel1.text"));
        jLabel2.setText(bundle.getString("jLabel2.text"));
        jLabel3.setText(bundle.getString("jLabel3.text"));
        jLabel4.setText(bundle.getString("jLabel4.text"));
        jLabel5.setText(bundle.getString("jLabel5.text"));
        jLabel6.setText(bundle.getString("jLabel6.text"));
        jLabel7.setText(bundle.getString("jLabel7.text"));
        jLabel8.setText(bundle.getString("jLabel8.text"));
        jLabel9.setText(bundle.getString("jLabel9.text"));
        jButton1.setText(bundle.getString("jButton1.text"));
        jPanel2.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel2.title")));
        jPanel3.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel3.title")));
        jPanel4.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel4.title")));
    }

    private void setProfileProperties(Properties prop) {
        if (prop.containsKey("ProfileName")) {
            jTextField1.setText(prop.getProperty("ProfileName"));
        }
        if (prop.containsKey("Action")) {
            jTextField2.setText(prop.getProperty("Action"));
        }
        jTextField2.setEnabled(false);
        if (prop.containsKey("ChangeMask")) {
//            jTextField3.setText(prop.getProperty("ChangeMask"));
            boolean hasValue = SwingUtils.setItems(jComboBoxMasks, ConfigApp.client.getMasks(), prop.getProperty("ChangeMask"));
            jLabelInvalidMask.setVisible(!hasValue);
        }
        if (prop.containsKey("ResultFieldStatus")) {
            String selectedMask = (String) jComboBoxMasks.getSelectedItem();
            boolean hasValue = SwingUtils.setItems(jComboBoxStatus, ConfigApp.client.getMasksAsMap().get(selectedMask), prop.getProperty("ResultFieldData"));
            jLabelInvalidFieldStatus.setVisible(!hasValue);
        }
//        if (prop.containsKey("ResultStatusOk")) {
            jTextField5.setText(prop.getProperty("ResultStatusOk", "24 - ConvertOK"));
//        }
//        if (prop.containsKey("ResultStatusError")) {
            jTextField6.setText(prop.getProperty("ResultStatusError", "25 - ConvertError"));
//        }
        if (prop.containsKey("ResultFieldPagecount")) {
            String selectedMask = (String) jComboBoxMasks.getSelectedItem();
            boolean hasValue = SwingUtils.setItems(jComboBoxPageCount, ConfigApp.client.getMasksAsMap().get(selectedMask), prop.getProperty("ResultFieldPagecount"));
            jLabelInvalidFieldPageCount.setVisible(!hasValue);
        }
        if (prop.containsKey("TempDirectory")) {
            jTextField8.setText(prop.getProperty("TempDirectory"));
        }
        if (prop.containsKey("PDFasNewVersion") && prop.getProperty("PDFasNewVersion").equalsIgnoreCase("true")) {
            jCheckBox1.setSelected(true);
        }
    }

    public boolean validationOk() {

        if (jLabelInvalidMask.isVisible()) {
            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.mask"));
            ConfigView.saveButton.setEnabled(false);
            return false;
        }
        if (jLabelInvalidFieldStatus.isVisible()) {
            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.status"));
            ConfigView.saveButton.setEnabled(false);
            return false;
        }
//        if (jTextField7.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.pdf"));
//            ConfigView.saveButton.setEnabled(false);
//            return false;
//        }
        return true;
    }

    public void save(Properties prop, String currentProfileNumber) {
        if (jTextField1.getText().isEmpty()) {
            prop.setProperty("ProfileName", "");
        } else {
            prop.setProperty("ProfileName", jTextField1.getText());
        }
        if (jTextField2.getText().isEmpty()) {
            prop.setProperty("Action", "");
        } else {
            prop.setProperty("Action", jTextField2.getText());
        }
//        if (jTextField3.getText().isEmpty()) {
//            prop.setProperty("ChangeMask", "");
//        } else {
//            prop.setProperty("ChangeMask", jTextField3.getText());
//        }
        prop.setProperty("ChangeMask", (String) jComboBoxMasks.getSelectedItem());
//        if (jTextField4.getText().isEmpty()) {
//            prop.setProperty("ResultFieldStatus", "");
//        } else {
//            prop.setProperty("ResultFieldStatus", jTextField4.getText());
//        }
        prop.setProperty("ResultFieldStatus", (String) jComboBoxStatus.getSelectedItem());
        if (jTextField5.getText().isEmpty()) {
            prop.setProperty("ResultStatusOk", "");
        } else {
            prop.setProperty("ResultStatusOk", jTextField5.getText());
        }
        if (jTextField6.getText().isEmpty()) {
            prop.setProperty("ResultStatusError", "");
        } else {
            prop.setProperty("ResultStatusError", jTextField6.getText());
        }
//        if (jTextField7.getText().isEmpty()) {
//            prop.setProperty("ResultFieldPagecount", "");
//        } else {
        prop.setProperty("ResultFieldPagecount", (String) jComboBoxPageCount.getSelectedItem());
//        }
        if (jTextField8.getText().isEmpty()) {
            prop.setProperty("TempDirectory", "");
        } else {
            prop.setProperty("TempDirectory", jTextField8.getText());
        }
        if (jCheckBox1.isSelected()) {
            prop.setProperty("PDFasNewVersion", "TRUE");
        } else {
            prop.setProperty("PDFasNewVersion", "");
        }
        try {
            prop.store(new FileOutputStream("../profiles/profile_" + currentProfileNumber + ".properties"), null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "can not write in folder profiles" + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBoxMasks = new javax.swing.JComboBox();
        jComboBoxStatus = new javax.swing.JComboBox();
        jLabelInvalidMask = new javax.swing.JLabel();
        jLabelInvalidFieldStatus = new javax.swing.JLabel();
        jComboBoxPageCount = new javax.swing.JComboBox();
        jLabelInvalidFieldPageCount = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(727, 332));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Profile"));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setText("Name");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Aktion");
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jTextField2.setName("jTextField2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("ELO"));
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel3.setText("Maske");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Status");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Status OK");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("Status Error");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("Page number of PDF");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText("PDF as new version");
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
        });

        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
        });

        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jComboBoxMasks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMasks.setName("jComboBoxMasks"); // NOI18N
        jComboBoxMasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMasksActionPerformed(evt);
            }
        });

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxStatus.setName("jComboBoxStatus"); // NOI18N
        jComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStatusActionPerformed(evt);
            }
        });

        jLabelInvalidMask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/di/barcode/gui/resources/SplitSortMerge"); // NOI18N
        jLabelInvalidMask.setToolTipText(bundle.getString("errorMessage.invalidMask")); // NOI18N
        jLabelInvalidMask.setName("jLabelInvalidMask"); // NOI18N

        jLabelInvalidFieldStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldStatus.setToolTipText(bundle.getString("errorMessage.invalidField")); // NOI18N
        jLabelInvalidFieldStatus.setName("jLabelInvalidFieldStatus"); // NOI18N

        jComboBoxPageCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxPageCount.setName("jComboBoxPageCount"); // NOI18N
        jComboBoxPageCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPageCountActionPerformed(evt);
            }
        });

        jLabelInvalidFieldPageCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldPageCount.setToolTipText(bundle.getString("errorMessage.invalidMask")); // NOI18N
        jLabelInvalidFieldPageCount.setName("jLabelInvalidFieldPageCount"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, 186, Short.MAX_VALUE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxPageCount, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxMasks, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInvalidFieldStatus)
                            .addComponent(jLabelInvalidMask)
                            .addComponent(jLabelInvalidFieldPageCount)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jCheckBox1)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jComboBoxMasks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInvalidMask, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelInvalidFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxPageCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel8)
                            .addComponent(jCheckBox1)))
                    .addComponent(jLabelInvalidFieldPageCount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Path"));
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel9.setText("Temp path");
        jLabel9.setName("jLabel9"); // NOI18N

        jTextField8.setName("jTextField8"); // NOI18N
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
        });

        jButton1.setText("Select");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GeneralTab.config.setProperty("", "");
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(jPanel1);
        File f;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            jTextField8.setText(f.getPath());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField5FocusGained

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField6FocusGained

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField8FocusGained

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBoxMasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMasksActionPerformed
        boolean hasValue = SwingUtils.setItems(jComboBoxMasks, ConfigApp.client.getMasks(), (String) jComboBoxMasks.getSelectedItem());
        jLabelInvalidMask.setVisible(!hasValue);
        hasValue = SwingUtils.setItems(jComboBoxPageCount, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxPageCount.getSelectedItem());
        jLabelInvalidFieldPageCount.setVisible(!hasValue);
        hasValue = SwingUtils.setItems(jComboBoxStatus, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxStatus.getSelectedItem());
        jComboBoxStatus.setVisible(!hasValue);
    }//GEN-LAST:event_jComboBoxMasksActionPerformed

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStatusActionPerformed
        boolean hasValue = SwingUtils.setItems(jComboBoxStatus, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxStatus.getSelectedItem());
        jComboBoxStatus.setVisible(!hasValue);
    }//GEN-LAST:event_jComboBoxStatusActionPerformed

    private void jComboBoxPageCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPageCountActionPerformed
        boolean hasValue = SwingUtils.setItems(jComboBoxPageCount, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxPageCount.getSelectedItem());
        jLabelInvalidFieldPageCount.setVisible(!hasValue);
    }//GEN-LAST:event_jComboBoxPageCountActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBoxMasks;
    private javax.swing.JComboBox jComboBoxPageCount;
    private javax.swing.JComboBox jComboBoxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelInvalidFieldPageCount;
    private javax.swing.JLabel jLabelInvalidFieldStatus;
    private javax.swing.JLabel jLabelInvalidMask;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
