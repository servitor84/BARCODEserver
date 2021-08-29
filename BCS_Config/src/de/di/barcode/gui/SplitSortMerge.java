package de.di.barcode.gui;

import de.elo.ix.client.DocMaskLine;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * @author Emal Rahman
 */
public class SplitSortMerge extends javax.swing.JPanel {

    Properties properties = new Properties();
    private ResourceBundle bundle;

    /**
     * Creates new form SplitSortMerge
     */
    public SplitSortMerge(Properties prop) {
        properties = prop;
        initComponents();
        loadResources();
        setProfileProperties(properties);
    }

    private void setProfileProperties(Properties prop) {
        if (prop.containsKey("ProfileName")) {
            jTextField1.setText(prop.getProperty("ProfileName"));
        }
        if (prop.containsKey("Action")) {
            jTextField21.setText(prop.getProperty("Action"));
        }
        jTextField21.setEditable(false);
        if (prop.containsKey("ChangeMask")) {
            boolean hasValue = SwingUtils.setItems(jComboBoxMasks, ConfigApp.client.getMasks(), prop.getProperty("ChangeMask"));
            jLabelInvalidMask.setVisible(!hasValue);
        }
        if (prop.containsKey("BarcodeContent")) {
//            jTextField18.setText(prop.getProperty("BarcodeContent"));
            String selectedMask = (String) jComboBoxMasks.getSelectedItem();
            boolean hasValue = SwingUtils.setItems(jComboBoxBarcode, ConfigApp.client.getMasksAsMap().get(selectedMask), prop.getProperty("BarcodeContent"));
            jLabelInvalidFieldBarcodes.setVisible(!hasValue);
            jComboBoxBarcode.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                   
                    boolean hasValue = false;
                    String value = (String) jComboBoxBarcode.getSelectedItem();
                    for (DocMaskLine dml : ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()).getLines()) {
                        if (dml.getKey().equals(value)) {
                            hasValue = true;
                            break;
                        }
                    }
//                     System.out.println("visible: " + !hasValue);
                    jLabelInvalidFieldBarcodes.setVisible(!hasValue);
                }
            });
        }
        if (prop.containsKey("ResultFieldBCcount")) {
//            jTextField19.setText(prop.getProperty("ResultFieldBCcount"));
            String selectedMask = (String) jComboBoxMasks.getSelectedItem();
            boolean hasValue = SwingUtils.setItems(jComboBoxCount, ConfigApp.client.getMasksAsMap().get(selectedMask), prop.getProperty("ResultFieldBCcount"));
            jLabelInvalidFieldCount.setVisible(!hasValue);
            jComboBoxCount.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {

                    boolean hasValue = false;
                    String value = (String) jComboBoxCount.getSelectedItem();
                    for (DocMaskLine dml : ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()).getLines()) {
                        if (dml.getKey().equals(value)) {
                            hasValue = true;
                            break;
                        }
                    }
                    jLabelInvalidFieldCount.setVisible(!hasValue);
                }
            });
        }
        if (prop.containsKey("ResultFieldStatus")) {
//            jTextField20.setText(prop.getProperty("ResultFieldStatus"));
            String selectedMask = (String) jComboBoxMasks.getSelectedItem();
            boolean hasValue = SwingUtils.setItems(jComboBoxStatus, ConfigApp.client.getMasksAsMap().get(selectedMask), prop.getProperty("ResultFieldStatus"));
            jLabelInvalidFieldStatus.setVisible(!hasValue);
            jComboBoxStatus.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    boolean hasValue = false;
                    String value = (String) jComboBoxStatus.getSelectedItem();
                    for (DocMaskLine dml : ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()).getLines()) {
                        if (dml.getKey().equals(value)) {
                            hasValue = true;
                            break;
                        }
                    }
                    jLabelInvalidFieldStatus.setVisible(!hasValue);
                }
            });
        }
        if (prop.containsKey("ResultStatusOk")) {
            jTextField2.setText(prop.getProperty("ResultStatusOk"));
        }
        if (prop.containsKey("ResultStatusError")) {
            jTextField3.setText(prop.getProperty("ResultStatusError"));
        }
        if (prop.containsKey("CreateTXT.FieldsForMetaFile")) {
            jTextField4.setText(prop.getProperty("CreateTXT.FieldsForMetaFile"));
        }
        if (prop.containsKey("CreateTXT.XMLtemplate")) {
            jTextField5.setText(prop.getProperty("CreateTXT.XMLtemplate"));
        }
        if (prop.containsKey("SplitFilePrefix")) {
            jTextField7.setText(prop.getProperty("SplitFilePrefix"));
        }
        if (prop.containsKey("TempDirectory")) {
            jTextField8.setText(prop.getProperty("TempDirectory"));
        }
        if (prop.containsKey("WorkingDirectory")) {
            jTextField9.setText(prop.getProperty("WorkingDirectory"));
        }
        if (prop.containsKey("OutputDirectory")) {
            jTextField10.setText(prop.getProperty("OutputDirectory"));
        }
        if (prop.containsKey("CreateTXT") && prop.getProperty("CreateTXT").equalsIgnoreCase("true")) {
            jCheckBox2.setSelected(true);
        }
        if (prop.containsKey("BarcodeMode") && prop.getProperty("BarcodeMode").equalsIgnoreCase("complex")) {
            jRadioButton1.setSelected(true);
            jSpinner1.setEnabled(true);
            jSpinner1.setVisible(true);
            jSpinner2.setEnabled(true);
            jSpinner2.setVisible(true);
            jSpinner3.setEnabled(true);
            jSpinner3.setVisible(true);
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            jLabel14.setVisible(true);
            if (prop.containsKey("DropLastCharactersCount") && !prop.getProperty("DropLastCharactersCount").isEmpty()) {
                jSpinner1.setValue(new Integer(Integer.valueOf(prop.getProperty("DropLastCharactersCount"))));
            }
            if (prop.containsKey("DropFirstCharactersCount") && !prop.getProperty("DropFirstCharactersCount").isEmpty()) {
                jSpinner2.setValue(new Integer(Integer.valueOf(prop.getProperty("DropFirstCharactersCount"))));
            }
            if (prop.containsKey("SortLastCharactersCount") && !prop.getProperty("SortLastCharactersCount").isEmpty()) {
                jSpinner3.setValue(new Integer(Integer.valueOf(prop.getProperty("SortLastCharactersCount"))));
            }
        } else {
            jRadioButton2.setSelected(true);
            jSpinner1.setEnabled(false);
            jSpinner1.setVisible(false);
            jSpinner2.setEnabled(false);
            jSpinner2.setVisible(false);
            jSpinner3.setEnabled(false);
            jSpinner3.setVisible(false);
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
            jLabel14.setVisible(false);
        }
        String selectedMask = (String) jComboBoxMasks.getSelectedItem();
        boolean hasValue = SwingUtils.setItems(jComboBoxPageCount, ConfigApp.client.getMasksAsMap().get(selectedMask), prop.getProperty("ResultFieldPagecount", " "));
        jLabelInvalidFieldPageCount.setVisible(!hasValue);

        if (prop.containsKey("CreateTXT") && prop.getProperty("CreateTXT").equalsIgnoreCase("true")) {
            jCheckBox2.setSelected(true);
        }
        if (prop.containsKey("CreateSIG") && prop.getProperty("CreateSIG").equalsIgnoreCase("true")) {
            jCheckBox3.setSelected(true);
        }
    }

    public boolean validationOk() {
        if (jLabelInvalidMask.isVisible()) {
            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.mask"));
            ConfigView.saveButton.setEnabled(false);
            return false;
        }
        if (jLabelInvalidFieldBarcodes.isVisible()) {
            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.barcodes"));
            ConfigView.saveButton.setEnabled(false);
            return false;
        }
        if (jLabelInvalidFieldCount.isVisible()) {
            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.count"));
            ConfigView.saveButton.setEnabled(false);
            return false;
        }
        if (jLabelInvalidFieldStatus.isVisible()) {
            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.status"));
            ConfigView.saveButton.setEnabled(false);
            return false;
        }
//        if (jTextField6.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.numberOfPage"));
//            ConfigView.saveButton.setEnabled(false);
//            return false;
//        }
        if (jTextField4.getText().equals("")) {
            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.mtafields"));
            ConfigView.saveButton.setEnabled(false);
            return false;
        }
        return true;
    }

    public void save(Properties prop, String currentProfileNumber) {
        if (jTextField1.getText().isEmpty()) {
            prop.setProperty("ProfileName", "");
        } else {
            prop.setProperty("ProfileName", jTextField1.getText());
        }
        if (jTextField2.getText().isEmpty()) {
            prop.setProperty("ResultStatusOk", "");
        } else {
            prop.setProperty("ResultStatusOk", jTextField2.getText());
        }
        if (jTextField3.getText().isEmpty()) {
            prop.setProperty("ResultStatusError", "");
        } else {
            prop.setProperty("ResultStatusError", jTextField3.getText());
        }
        if (jTextField4.getText().isEmpty()) {
            prop.setProperty("CreateTXT.FieldsForMetaFile", "");
        } else {
            prop.setProperty("CreateTXT.FieldsForMetaFile", jTextField4.getText());
        }
        if (jTextField5.getText().isEmpty()) {
            prop.setProperty("CreateTXT.XMLtemplate", "");
        } else {
            prop.setProperty("CreateTXT.XMLtemplate", jTextField5.getText());
        }
//        if (jTextField6.getText().isEmpty()) {
//            prop.setProperty("ResultFieldPagecount", "");
//        } else {
        prop.setProperty("ResultFieldPagecount", (String) jComboBoxPageCount.getSelectedItem());
//        }
        if (jTextField7.getText().isEmpty()) {
            prop.setProperty("SplitFilePrefix", "");
        } else {
            prop.setProperty("SplitFilePrefix", jTextField7.getText());
        }
        if (jTextField8.getText().isEmpty()) {
            prop.setProperty("TempDirectory", "");
        } else {
            prop.setProperty("TempDirectory", jTextField8.getText());
        }
        if (jTextField9.getText().isEmpty()) {
            prop.setProperty("WorkingDirectory", "");
        } else {
            prop.setProperty("WorkingDirectory", jTextField9.getText());
        }
        if (jTextField10.getText().isEmpty()) {
            prop.setProperty("OutputDirectory", "");
        } else {
            prop.setProperty("OutputDirectory", jTextField10.getText());
        }
//        if (jTextField17.getText().isEmpty()) {
//            prop.setProperty("ChangeMask", "");
//        } else {
//            prop.setProperty("ChangeMask", jTextField17.getText());
//        }
        prop.setProperty("ChangeMask", (String) jComboBoxMasks.getSelectedItem());
//        if (jTextField18.getText().isEmpty()) {
//            prop.setProperty("BarcodeContent", "");
//        } else {
//            prop.setProperty("BarcodeContent", jTextField18.getText());
//        }
        prop.setProperty("BarcodeContent", (String) jComboBoxBarcode.getSelectedItem());
//        if (jTextField19.getText().isEmpty()) {
//            prop.setProperty("ResultFieldBCcount", "");
//        } else {
//            prop.setProperty("ResultFieldBCcount", jTextField19.getText());
//        }
        prop.setProperty("ResultFieldBCcount", (String) jComboBoxCount.getSelectedItem());
//        if (jTextField20.getText().isEmpty()) {
//            prop.setProperty("ResultFieldStatus", "");
//        } else {
//            prop.setProperty("ResultFieldStatus", jTextField20.getText());
//        }
        prop.setProperty("ResultFieldStatus", (String) jComboBoxStatus.getSelectedItem());
        if (jTextField21.getText().isEmpty()) {
            prop.setProperty("Action", "");
        } else {
            prop.setProperty("Action", jTextField21.getText());
        }
        if (jRadioButton1.isSelected()) {
            prop.setProperty("BarcodeMode", "Complex");
            prop.setProperty("DropLastCharactersCount", jSpinner1.getValue().toString());
            prop.setProperty("DropFirstCharactersCount", jSpinner2.getValue().toString());
            prop.setProperty("SortLastCharactersCount", jSpinner3.getValue().toString());
        } else {
            prop.setProperty("BarcodeMode", "Simple");
            prop.setProperty("DropLastCharactersCount", "0");
            prop.setProperty("DropFirstCharactersCount", "0");
            prop.setProperty("SortLastCharactersCount", "0");
        }
        if (jCheckBox2.isSelected()) {
            prop.setProperty("CreateTXT", "TRUE");
        } else {
            prop.setProperty("CreateTXT", "FALSE");
        }
        if (jCheckBox3.isSelected()) {
            prop.setProperty("CreateSIG", "TRUE");
        } else {
            prop.setProperty("CreateSIG", "FALSE");
        }
        try {
            prop.store(new FileOutputStream("../profiles/profile_" + currentProfileNumber + ".properties"), null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "can not write in folder profiles" + ex.getMessage());
        }
    }

    public void loadResources() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        SwingUtilities.updateComponentTreeUI(this);
        Locale locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle("de/di/barcode/gui/resources/SplitSortMerge", locale);
        jLabel1.setText(bundle.getString("jLabel1.text"));
        jLabel2.setText(bundle.getString("jLabel2.text"));
        jLabel3.setText(bundle.getString("jLabel3.text"));
        jLabel4.setText(bundle.getString("jLabel4.text"));
        jLabel5.setText(bundle.getString("jLabel5.text"));
        jLabel6.setText(bundle.getString("jLabel6.text"));
        jLabel7.setText(bundle.getString("jLabel7.text"));
        jLabel8.setText(bundle.getString("jLabel8.text"));
        jLabel9.setText(bundle.getString("jLabel9.text"));
        jLabel10.setText(bundle.getString("jLabel10.text"));
        jLabel11.setText(bundle.getString("jLabel11.text"));
        jLabel12.setText(bundle.getString("jLabel12.text"));
        jLabel13.setText(bundle.getString("jLabel13.text"));
        jLabel14.setText(bundle.getString("jLabel14.text"));
        jLabel23.setText(bundle.getString("jLabel23.text"));
        jLabel24.setText(bundle.getString("jLabel24.text"));
        jLabel25.setText(bundle.getString("jLabel25.text"));
        jLabel26.setText(bundle.getString("jLabel26.text"));
        jButton1.setText(bundle.getString("jButton1.text"));
        jButton2.setText(bundle.getString("jButton2.text"));
        jButton3.setText(bundle.getString("jButton3.text"));
        jPanel2.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel2.title")));
        jPanel3.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel3.title")));
        jPanel4.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel4.title")));
        jPanel6.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel6.title")));
        jPanel7.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel7.title")));
        jRadioButton1.setText(bundle.getString("jRadioButton1.text"));
        jRadioButton2.setText(bundle.getString("jRadioButton2.text"));
        jCheckBox2.setText(bundle.getString("jCheckBox2.text"));
        jCheckBox3.setText(bundle.getString("jCheckBox3.text"));
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
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jSpinner1 = new javax.swing.JSpinner(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jLabel3 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jLabel4 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jComboBoxMasks = new javax.swing.JComboBox();
        jComboBoxCount = new javax.swing.JComboBox();
        jComboBoxStatus = new javax.swing.JComboBox();
        jLabelInvalidMask = new javax.swing.JLabel();
        jLabelInvalidFieldCount = new javax.swing.JLabel();
        jLabelInvalidFieldStatus = new javax.swing.JLabel();
        jComboBoxBarcode = new javax.swing.JComboBox();
        jLabelInvalidFieldBarcodes = new javax.swing.JLabel();
        jComboBoxPageCount = new javax.swing.JComboBox();
        jLabelInvalidFieldPageCount = new javax.swing.JLabel();

        jPanel1.setName("jPanel1"); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Metadata"));
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel5.setText("Split file prefix");
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField4.setName("jTextField4"); // NOI18N
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
        });

        jLabel6.setText("XML template");
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
        });

        jLabel9.setText("Metafields");
        jLabel9.setName("jLabel9"); // NOI18N

        jTextField7.setName("jTextField7"); // NOI18N
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField7FocusGained(evt);
            }
        });

        jCheckBox2.setText("Create txt file");
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("Create sig file");
        jCheckBox3.setName("jCheckBox3"); // NOI18N
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addGap(30, 30, 30)
                        .addComponent(jCheckBox3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jTextField5)
                            .addComponent(jTextField4))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(jTextField7))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Directories"));
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel10.setText("Temporary directory");
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText("Working directory");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText("Output Directory");
        jLabel12.setName("jLabel12"); // NOI18N

        jTextField8.setName("jTextField8"); // NOI18N
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
        });

        jTextField9.setName("jTextField9"); // NOI18N
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
        });

        jTextField10.setName("jTextField10"); // NOI18N
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField10FocusGained(evt);
            }
        });

        jButton1.setText("Select");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Select");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Select");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(69, 69, 69))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Profile"));
        jPanel6.setDoubleBuffered(false);
        jPanel6.setEnabled(false);
        jPanel6.setFocusable(false);
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel7.setText("Name");
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jLabel8.setText("Action");
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField21.setName("jTextField21"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21)))
                .addGap(14, 14, 14))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(218, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sorting parameter"));
        jPanel2.setName("jPanel2"); // NOI18N

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Complex mode");
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Simple mode");
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jSpinner1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSpinner1.setName("jSpinner1"); // NOI18N
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel3.setText("Drop last chareckter");
        jLabel3.setName("jLabel3"); // NOI18N

        jSpinner2.setName("jSpinner2"); // NOI18N
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        jLabel4.setText("Drop first chareckter");
        jLabel4.setName("jLabel4"); // NOI18N

        jSpinner3.setName("jSpinner3"); // NOI18N

        jLabel14.setText("Char insert for the sorting");
        jLabel14.setName("jLabel14"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jRadioButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton2)))
                .addGap(70, 70, 70))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("ELO"));
        jPanel7.setDoubleBuffered(false);
        jPanel7.setEnabled(false);
        jPanel7.setFocusable(false);
        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setRequestFocusEnabled(false);
        jPanel7.setVerifyInputWhenFocusTarget(false);

        jLabel23.setText("Mask");
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel24.setText("Barcodes");
        jLabel24.setName("jLabel24"); // NOI18N

        jLabel25.setText("Count");
        jLabel25.setName("jLabel25"); // NOI18N

        jLabel26.setText("Status");
        jLabel26.setName("jLabel26"); // NOI18N

        jLabel13.setText("Page number");
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel1.setText("Result ok");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Result error");
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
        });

        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
        });

        jComboBoxMasks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMasks.setName("jComboBoxMasks"); // NOI18N
        jComboBoxMasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMasksActionPerformed(evt);
            }
        });

        jComboBoxCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCount.setName("jComboBoxCount"); // NOI18N
        jComboBoxCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCountActionPerformed(evt);
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

        jLabelInvalidFieldCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldCount.setToolTipText(bundle.getString("errorMessage.invalidField")); // NOI18N
        jLabelInvalidFieldCount.setName("jLabelInvalidFieldCount"); // NOI18N

        jLabelInvalidFieldStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldStatus.setToolTipText(bundle.getString("errorMessage.invalidField")); // NOI18N
        jLabelInvalidFieldStatus.setName("jLabelInvalidFieldStatus"); // NOI18N

        jComboBoxBarcode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBarcode.setName("jComboBoxBarcode"); // NOI18N
        jComboBoxBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBarcodeActionPerformed(evt);
            }
        });

        jLabelInvalidFieldBarcodes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldBarcodes.setToolTipText(bundle.getString("errorMessage.invalidMask")); // NOI18N
        jLabelInvalidFieldBarcodes.setName("jLabelInvalidFieldBarcodes"); // NOI18N

        jComboBoxPageCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxPageCount.setName("jComboBoxPageCount"); // NOI18N
        jComboBoxPageCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPageCountActionPerformed(evt);
            }
        });

        jLabelInvalidFieldPageCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldPageCount.setToolTipText(bundle.getString("errorMessage.invalidField")); // NOI18N
        jLabelInvalidFieldPageCount.setName("jLabelInvalidFieldPageCount"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxMasks, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxBarcode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInvalidMask)
                            .addComponent(jLabelInvalidFieldCount)
                            .addComponent(jLabelInvalidFieldBarcodes)
                            .addComponent(jLabelInvalidFieldStatus)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jComboBoxPageCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelInvalidFieldPageCount)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jComboBoxMasks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInvalidMask, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jComboBoxBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInvalidFieldBarcodes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jComboBoxCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInvalidFieldCount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInvalidFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jComboBoxPageCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInvalidFieldPageCount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        GeneralTab.config.setProperty("", "");     }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField4FocusGained

    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField5FocusGained

    private void jTextField7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField7FocusGained

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField8FocusGained

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField10FocusGained

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        GeneralTab.config.setProperty("", "");
        Properties lockalProperty = new Properties();
        try {
            String profilePath = getProfilePath();
            lockalProperty.load(new FileInputStream(getProfilePath()));
        } catch (IOException ex) {
        }
        if (jRadioButton1.isSelected()) {
            jSpinner1.setEnabled(true);
            jSpinner1.setVisible(true);
            if (lockalProperty.getProperty("DropLastCharactersCount") != null) {
                jSpinner1.setValue(Integer.valueOf(lockalProperty.getProperty("DropLastCharactersCount")));
            } else {
                jSpinner1.setToolTipText("0");
            }
            jSpinner2.setEnabled(true);
            jSpinner2.setVisible(true);
            if (lockalProperty.getProperty("DropFirstCharactersCount") != null) {
                jSpinner2.setValue(Integer.valueOf(lockalProperty.getProperty("DropFirstCharactersCount")));
            } else {
                jSpinner2.setToolTipText("0");
            }
            jSpinner3.setEnabled(true);
            jSpinner3.setVisible(true);
            if (lockalProperty.getProperty("SortLastCharactersCount") != null) {
                jSpinner3.setValue(Integer.valueOf(lockalProperty.getProperty("SortLastCharactersCount")));
            } else {
                jSpinner3.setToolTipText("0");
            }
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            jLabel14.setVisible(true);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        GeneralTab.config.setProperty("", "");
        if (jRadioButton2.isSelected()) {
            jSpinner1.setEnabled(false);
            jSpinner1.setVisible(false);
            jSpinner2.setEnabled(false);
            jSpinner2.setVisible(false);
            jSpinner3.setEnabled(false);
            jSpinner3.setVisible(false);
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
            jLabel14.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jCheckBox3ActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        GeneralTab.config.setProperty("", "");
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(jPanel1);
        File f;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            jTextField9.setText(f.getPath());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        GeneralTab.config.setProperty("", "");
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(jPanel1);
        File f;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            jTextField10.setText(f.getPath());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField2FocusGained

    private String getProfilePath() {
        String number = FilesTab.seclectedProfileNumber;
        String profilePath = "";
        if (number.length() == 1) {
            profilePath = "../profiles/profile_00" + number + ".properties";
        } else if (number.length() == 2) {
            profilePath = "../profiles/profile_0" + number + ".properties";
        } else {
            profilePath = "../profiles/profile_" + number + ".properties";
        }
        return profilePath;
    }

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        GeneralTab.config.setProperty("", "");
    }//GEN-LAST:event_jTextField3FocusGained

    private void jComboBoxMasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMasksActionPerformed
        boolean hasValue = SwingUtils.setItems(jComboBoxMasks, ConfigApp.client.getMasks(), (String) jComboBoxMasks.getSelectedItem());
        jLabelInvalidMask.setVisible(!hasValue);
        hasValue = SwingUtils.setItems(jComboBoxBarcode, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxBarcode.getSelectedItem());
        jLabelInvalidFieldBarcodes.setVisible(!hasValue);
        hasValue = SwingUtils.setItems(jComboBoxCount, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxCount.getSelectedItem());
        jLabelInvalidFieldCount.setVisible(!hasValue);
        hasValue = SwingUtils.setItems(jComboBoxStatus, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxStatus.getSelectedItem());
        jLabelInvalidFieldStatus.setVisible(!hasValue);
    }//GEN-LAST:event_jComboBoxMasksActionPerformed

    private void jComboBoxBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBarcodeActionPerformed
        boolean hasValue = SwingUtils.setItems(jComboBoxBarcode, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxBarcode.getSelectedItem());
        jLabelInvalidFieldBarcodes.setVisible(!hasValue);
    }//GEN-LAST:event_jComboBoxBarcodeActionPerformed

    private void jComboBoxCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCountActionPerformed
        boolean hasValue = SwingUtils.setItems(jComboBoxCount, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxCount.getSelectedItem());
        jLabelInvalidFieldCount.setVisible(!hasValue);

    }//GEN-LAST:event_jComboBoxCountActionPerformed

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStatusActionPerformed
        boolean hasValue = SwingUtils.setItems(jComboBoxStatus, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxStatus.getSelectedItem());
        jLabelInvalidFieldStatus.setVisible(!hasValue);
    }//GEN-LAST:event_jComboBoxStatusActionPerformed

    private void jComboBoxPageCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPageCountActionPerformed
        boolean hasValue = SwingUtils.setItems(jComboBoxPageCount, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxPageCount.getSelectedItem());
        jLabelInvalidFieldPageCount.setVisible(!hasValue);
    }//GEN-LAST:event_jComboBoxPageCountActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox jComboBoxBarcode;
    private javax.swing.JComboBox jComboBoxCount;
    private javax.swing.JComboBox jComboBoxMasks;
    private javax.swing.JComboBox jComboBoxPageCount;
    private javax.swing.JComboBox jComboBoxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelInvalidFieldBarcodes;
    private javax.swing.JLabel jLabelInvalidFieldCount;
    private javax.swing.JLabel jLabelInvalidFieldPageCount;
    private javax.swing.JLabel jLabelInvalidFieldStatus;
    private javax.swing.JLabel jLabelInvalidMask;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
