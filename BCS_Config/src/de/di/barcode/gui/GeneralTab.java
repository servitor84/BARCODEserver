package de.di.barcode.gui;

import de.di.barcode.gui.Config.Property;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import org.jdesktop.application.Action;

/**
 *
 * @author  E. Rahman
 * @Mitautor S. Lebaal - Siehe Zeilen, die mit SL vermerk sind
 *      20.01.2021  -   Auf den Zugriff auf IX, um die Masken zu lesen, verzichten. Der Vorgang wird damit ersetzt, dass die Masken in einer lokalen Datei manuel gespeichert werden.
 *      21.01.2021  -   ´´
 */
public class GeneralTab extends javax.swing.JPanel implements ConfigTab {

    public static Config config = null;
    private boolean setup = false;
    private String title = "General";
    private File lastDirectory = null;
    private static final String bundleName = "de/di/barcode/gui/resources/GeneralTab";
    private org.jdesktop.application.ResourceMap resourceMap;
    private static String basicNewInstall = "FALSE";
    private static String applicationPollTime = "";
    private static String agentWorkflowsPerRun = "";
    private static String directoriesLogging = "";
    private static String indexServerPassword = "";
    private static String indexServerUser = "";
    private static String indexServer = "";
    private static String tempDirectory = "";
    private Properties oldProperties = new Properties();
    private Properties actualProperties = new Properties();
    
    // SL - Hinzugefügt
    public String allMasks;
    public List<String> allGroupNames = new ArrayList<String>();

    /** Creates new form GeneralTab */
    public GeneralTab() {
        try {
            oldProperties.load(new FileInputStream("../conf/config.properties"));            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "config.properties can not be found");
        }
        // SL
        try {
            FileReader file = new FileReader("../conf/maskFromIX.txt");            
            BufferedReader br = new BufferedReader(file);
            int linesInTxtFile = 0;
            String st;
            while ((st = br.readLine()) != null) {
                if(linesInTxtFile == 0) {
                    allMasks = st.substring(st.indexOf("=")+1);
                } else {
                    allGroupNames.add(st);
                }
                linesInTxtFile++;
            } 
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(this, "maskFromIX.properties can not be found");
        }
        
        resourceMap = org.jdesktop.application.Application.getInstance(de.di.barcode.gui.ConfigApp.class).getContext().getResourceMap(GeneralTab.class);
        title = resourceMap.getString("tabTitle.text");
        initComponents();
        jTextField2.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField3.addKeyListener(new KeyAdapterNumbersOnly());
        createLogLevelCombobox();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        serviceLabel = new javax.swing.JLabel();
        activateButton = new javax.swing.JToggleButton();
        statusLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tempDirectoryField = new javax.swing.JTextField();
        tempSelectButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        indexServerTextField = new javax.swing.JTextField();
        userTextField = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/di/barcode/gui/resources/GeneralTab"); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(GeneralTab.class);
        serviceLabel.setText(resourceMap.getString("serviceLabel.text")); // NOI18N
        serviceLabel.setName("serviceLabel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(GeneralTab.class, this);
        activateButton.setAction(actionMap.get("toggleService")); // NOI18N
        activateButton.setText(resourceMap.getString("activateButton.text")); // NOI18N
        activateButton.setName("activateButton"); // NOI18N

        statusLabel.setText(resourceMap.getString("statusLabel.text")); // NOI18N
        statusLabel.setName("statusLabel"); // NOI18N

        jButton1.setAction(actionMap.get("showStatus")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel)
                    .addComponent(serviceLabel))
                .addGap(97, 97, 97)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(activateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addGap(584, 584, 584))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {activateButton, jButton1});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activateButton)
                    .addComponent(serviceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(statusLabel))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setVerifyInputWhenFocusTarget(false);

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField1.setText(oldProperties.getProperty("Directories.Logging"));
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setVerifyInputWhenFocusTarget(false);
        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        tempDirectoryField.setText(oldProperties.getProperty("TemporaryDirectory"));
        tempDirectoryField.setName("tempDirectoryField");
        tempDirectoryField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tempDirectoryFieldCaretUpdate(evt);
            }
        });

        tempSelectButton.setText(resourceMap.getString("tempSelectButton.text")); // NOI18N
        tempSelectButton.setName("tempSelectButton"); // NOI18N
        tempSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempSelectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tempDirectoryField, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tempSelectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tempDirectoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tempSelectButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        indexServerTextField.setText(oldProperties.getProperty("IndexServer.URL"));
        indexServerTextField.setName("indexServerTextField"); // NOI18N
        indexServerTextField.setVerifyInputWhenFocusTarget(false);
        indexServerTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                indexServerTextFieldCaretUpdate(evt);
            }
        });

        userTextField.setText(oldProperties.getProperty("IndexServer.User"));
        userTextField.setName("userTextField"); // NOI18N
        userTextField.setVerifyInputWhenFocusTarget(false);
        userTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                userTextFieldCaretUpdate(evt);
            }
        });

        jPasswordField1.setText(resourceMap.getString("")); // NOI18N
        jPasswordField1.setName("jPasswordField1"); // NOI18N
        jPasswordField1.setVerifyInputWhenFocusTarget(false);
        jPasswordField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jPasswordField1CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(userTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(indexServerTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addContainerGap(443, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indexServerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField2.setText(getPollTime());
        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.setVerifyInputWhenFocusTarget(false);
        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });

        jTextField3.setText(oldProperties.getProperty("Agent.workflowsPerRun"));
        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.setVerifyInputWhenFocusTarget(false);
        jTextField3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField3CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(583, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(63, 63, 63))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(jPanel1);
        File f;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            directoriesLogging = f.getPath();
            jTextField1.setText(f.getPath());
            jTextField1.setName(f.getPath());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        if (setup) {
            return;
        }
        applicationPollTime = jTextField2.getText();
        config.setProperty(Config.Property.ApplicationPollTime, jTextField2.getText());
    }//GEN-LAST:event_jTextField2CaretUpdate

    private void indexServerTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_indexServerTextFieldCaretUpdate
        if (setup) {
            return;
        }
        indexServer = indexServerTextField.getText();
        config.setProperty(Config.Property.IndexServerURL, indexServerTextField.getText());
    }//GEN-LAST:event_indexServerTextFieldCaretUpdate

    private void jTextField3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField3CaretUpdate
        if (setup) {
            return;
        }
        agentWorkflowsPerRun = jTextField3.getText();
        config.setProperty(Config.Property.AgentWorkflowsPerRun, jTextField3.getText());
    }//GEN-LAST:event_jTextField3CaretUpdate

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (setup) {
            return;
        }
        directoriesLogging = jTextField1.getText();
        config.setProperty(Config.Property.DirectoriesLogging, jTextField1.getText());
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void userTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_userTextFieldCaretUpdate
        if (setup) {
            return;
        }
        indexServerUser = userTextField.getText();
        config.setProperty(Config.Property.IndexServerUser, userTextField.getText());
    }//GEN-LAST:event_userTextFieldCaretUpdate

    private void jPasswordField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jPasswordField1CaretUpdate
        if (setup) {
            return;
        }
        try {            
            // Encode ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//            de.elo.utils.sec.DesEncryption des = new de.elo.utils.sec.DesEncryption();
//            indexServerPassword = des.encrypt(String.copyValueOf(jPasswordField1.getPassword()));
            indexServerPassword = String.copyValueOf(jPasswordField1.getPassword());
        } catch (Exception ex) {}                
    }//GEN-LAST:event_jPasswordField1CaretUpdate

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        ConfigView.saveButton.setEnabled(true);
    }//GEN-LAST:event_jComboBox1ItemStateChanged

private void tempDirectoryFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tempDirectoryFieldCaretUpdate
    if (setup) {
        return;
    }
    tempDirectory = tempDirectoryField.getText();
    config.setProperty(Config.Property.TemporaryDirectory, tempDirectoryField.getText());
}//GEN-LAST:event_tempDirectoryFieldCaretUpdate

private void tempSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempSelectButtonActionPerformed
    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = fc.showOpenDialog(jPanel1);
    File f;
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        f = fc.getSelectedFile();
        tempDirectory = f.getPath();
        tempDirectoryField.setText(f.getPath());
        tempDirectoryField.setName(f.getPath());
    }
}//GEN-LAST:event_tempSelectButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton activateButton;
    private javax.swing.JTextField indexServerTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel serviceLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField tempDirectoryField;
    private javax.swing.JButton tempSelectButton;
    private javax.swing.JTextField userTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public JPanel getJPanel() {
        return this;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setConfig(Config c) {
        config = c;
        setup = true;

        int queueSize = 0;
        try {
            queueSize = Integer.parseInt(config.getProperty(
                    Config.Property.BasicQueueSize, "30"));
        } catch (Exception e) {
        }
        boolean newInstall = Boolean.parseBoolean(
                config.getProperty(Config.Property.BasicNewInstall, "TRUE"));

        activateButton.setSelected(!newInstall);
        if (newInstall) {
            activateButton.setText(resourceMap.getString("toggleService.Action.text"));
        } else {
            activateButton.setText(resourceMap.getString("toggleService.Action.Inactive.text"));
        }
        int i = 10485760;
        try {
            i = Integer.parseInt(config.getProperty(
                    Property.BasicMaxRecoveryLogSize));
        } catch (Exception e) {
        }
        i = i / (1024 * 1024);
        setup = false;
        jPasswordField1.setText("**********");
    }

    private File getSelectedDirectory(String filename) {
        File f = null;

        if (filename != null) {
            f = new File(filename);
        }
        JFileChooser fileChooser = new JFileChooser();

        if (f != null && f.exists()) {
            fileChooser.setCurrentDirectory(f);
        } else if (lastDirectory != null) {
            fileChooser.setCurrentDirectory(lastDirectory);
        }
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    @Action
    public void toggleService() {
        boolean newInstall = !activateButton.isSelected();
        if (newInstall) {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    resourceMap.getString("toggleService.Deactivate.Confirm.text"),
                    resourceMap.getString("toggleService.Deactivate.Confirm.title"),
                    JOptionPane.WARNING_MESSAGE);

            if (result != JOptionPane.YES_OPTION) {
                basicNewInstall = "FALSE";
                activateButton.setSelected(true); //reset state
                return;
            }
            basicNewInstall = "TRUE";
            activateButton.setText(resourceMap.getString("toggleService.Action.text"));
        } else {
            basicNewInstall = "FALSE";
            activateButton.setText(resourceMap.getString("toggleService.Action.Inactive.text"));
        }
        ConfigView.saveButton.setEnabled(true);
    }

    @Action
    public void showStatus() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("../conf/config.properties"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "config.properties can not be found");
        }
        String uri = prop.getProperty("Basic.TomcatURL") + prop.getProperty("Basic.ServiceName") + "/";
        java.net.URI serviceURI;

        try {
            serviceURI = new java.net.URI(uri);
            java.awt.Desktop.getDesktop().browse(serviceURI);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    resourceMap.getString("showStatus.Error.text"),
                    resourceMap.getString("showStatus.Error.title"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void writeConfigProperties() {
        actualProperties.setProperty("Basic.LogLevel", jComboBox1.getSelectedItem().toString());
        actualProperties.setProperty("Basic.NewInstall", basicNewInstall);
        if (oldProperties.getProperty("Basic.MaxRecoveryLogSize") != null) {
            actualProperties.setProperty("Basic.MaxRecoveryLogSize", oldProperties.getProperty("Basic.MaxRecoveryLogSize"));
        } else {
            actualProperties.setProperty("Basic.MaxRecoveryLogSize", "10485760");
        }
        if (oldProperties.getProperty("Basic.LogPattern") != null) {
            actualProperties.setProperty("Basic.LogPattern", oldProperties.getProperty("Basic.LogPattern"));
        } else {
            actualProperties.setProperty("Basic.LogPattern", "%d{dd.MM.yyyy HH:mm:ss} %-5p [%t]: %m%n");
        }
        if (oldProperties.getProperty("Basic.ServiceName") != null) {
            actualProperties.setProperty("Basic.ServiceName", oldProperties.getProperty("Basic.ServiceName"));
        } else {
            actualProperties.setProperty("Basic.ServiceName", "BARCODEserver");
        }
        if (oldProperties.getProperty("Basic.TomcatURL") != null) {
            actualProperties.setProperty("Basic.TomcatURL", oldProperties.getProperty("Basic.TomcatURL"));
        } else {
            actualProperties.setProperty("Basic.TomcatURL", "http://localhost:8080/");
        }
        if (directoriesLogging.isEmpty()) {
            actualProperties.setProperty("Directories.Logging", jTextField1.getText());
        } else {
            actualProperties.setProperty("Directories.Logging", directoriesLogging);
        }
        if (indexServerPassword.equals("**********")) {
            if (oldProperties.getProperty("IndexServer.Password") == null) {
                actualProperties.setProperty("IndexServer.Password", "");
            } else {
                actualProperties.setProperty("IndexServer.Password", oldProperties.getProperty("IndexServer.Password"));
            }
        } else {
            actualProperties.setProperty("IndexServer.Password", indexServerPassword);
        }
        if (indexServerUser.isEmpty()) {
            actualProperties.setProperty("IndexServer.User", userTextField.getText());
        } else {
            actualProperties.setProperty("IndexServer.User", indexServerUser);
        }
        if (indexServer.isEmpty()) {
            actualProperties.setProperty("IndexServer.URL", indexServerTextField.getText());
        } else {
            actualProperties.setProperty("IndexServer.URL", indexServer);
        }
        if (applicationPollTime.isEmpty()) {
            actualProperties.setProperty("Application.PollTime", jTextField2.getText() + "000");
        } else {
            actualProperties.setProperty("Application.PollTime", applicationPollTime + "000");
        }
        if (agentWorkflowsPerRun.isEmpty()) {
            actualProperties.setProperty("Agent.workflowsPerRun", jTextField3.getText());
        } else {
            actualProperties.setProperty("Agent.workflowsPerRun", agentWorkflowsPerRun);
        }
        if (tempDirectory.isEmpty()) {
            actualProperties.setProperty("TemporaryDirectory", tempDirectoryField.getText());
        } else {
            actualProperties.setProperty("TemporaryDirectory", tempDirectory);
        }
        try {
            // PWD: Decode ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            String password = actualProperties.getProperty("IndexServer.Password");
//            String pattern = "^((\\d){1,})([-]{1}(\\d){1,}){1,}";
//            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
//            java.util.regex.Matcher matcher = p.matcher(password);
//            if(!matcher.matches()) {
//                de.elo.utils.sec.DesEncryption des = new de.elo.utils.sec.DesEncryption();
//                password = des.encrypt(password);
//            }
            actualProperties.setProperty("IndexServer.Password", password); 
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  
            actualProperties.store(new FileOutputStream("../conf/config.properties"), null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "can not write in config.properties" + ex.getMessage());
        }
    }

    public String getPollTime() {
        if (oldProperties.containsKey("Application.PollTime") && !oldProperties.getProperty("Application.PollTime").isEmpty()) {
            return oldProperties.getProperty("Application.PollTime").substring(0, oldProperties.getProperty("Application.PollTime").length() - 3);
        } else {
            return "";
        }
    }

    private void createLogLevelCombobox() {
        jComboBox1.addItem((Object) "ALL");
        jComboBox1.addItem((Object) "TRACE");
        jComboBox1.addItem((Object) "DEBUG");
        jComboBox1.addItem((Object) "INFO");
        jComboBox1.addItem((Object) "WARN");
        jComboBox1.addItem((Object) "ERROR");
        jComboBox1.addItem((Object) "FATAL");
        jComboBox1.addItem((Object) "OFF");
        if (oldProperties.getProperty("Basic.LogLevel") == null) {
            jComboBox1.setSelectedItem((Object) "ALL");
        } else {
            jComboBox1.setSelectedItem((Object) oldProperties.getProperty("Basic.LogLevel"));
        }
    }

    public class KeyAdapterNumbersOnly extends KeyAdapter {

        private String allowedRegex = "[^0-9]";

        public void keyReleased(KeyEvent e) {
            String curText = ((JTextComponent) e.getSource()).getText();
            curText = curText.replaceAll(allowedRegex, "");
            ((JTextComponent) e.getSource()).setText(curText);
        }
    }
    
    // LS - 25.01.2021

    // LS - 25.01.2021
    public String getAllMasks() {
        return allMasks;
    }

    public List<String> getAllGroupNames() {
        return allGroupNames;
    }

    public void setAllMasks(String allMasks) {
        this.allMasks = allMasks;
    }

    public void setAllGroupNames(List<String> allGroupNames) {
        this.allGroupNames = allGroupNames;
    }
    
}
