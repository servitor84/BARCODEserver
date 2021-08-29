package de.di.barcode.gui;

import de.di.barcode.gui.ConfigView;
import de.di.barcode.gui.GeneralTab;
import de.di.barcode.gui.ConfigApp;
import de.di.barcode.gui.SwingUtils;
import de.di.barcode.gui.FilesTab_Ext;
import de.elo.ix.client.DocMask;
import de.elo.ix.client.DocMaskLine;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Samir
 */
public class QRCodeReader extends javax.swing.JPanel {

    private FilesTab_Ext types = new FilesTab_Ext();
    private Properties proproperties = new Properties();
    public boolean fieldSet = true;
    private String globalErrorMessage = "";
    private ResourceBundle bundle;

    /**
     * Creates new form BarcodeReader
     */
    public QRCodeReader(Properties prop) {
        proproperties = prop;
        initComponents();
        setListener();
        loadResources();
        setProfileProperties(proproperties);
    }

    public void loadResources() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        SwingUtilities.updateComponentTreeUI(this);
        Locale locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle("de/di/barcode/gui/resources/QRCodeReader", locale);
        jLabel1.setText(bundle.getString("jLabel1.text"));
        jLabel6.setText(bundle.getString("jLabel6.text"));
        jLabel7.setText(bundle.getString("jLabel7.text"));
        jLabel8.setText(bundle.getString("jLabel8.text"));        
        jLabel13.setText(bundle.getString("jLabel13.text"));
        jLabel14.setText(bundle.getString("jLabel14.text"));
        jLabel15.setText(bundle.getString("jLabel15.text"));
        jLabel16.setText(bundle.getString("jLabel16.text"));
        jLabel17.setText(bundle.getString("jLabel17.text"));
        jLabel18.setText(bundle.getString("jLabel18.text"));
        jLabel19.setText(bundle.getString("jLabel19.text"));
        jLabel20.setText(bundle.getString("jLabel20.text"));
        jLabel21.setText(bundle.getString("jLabel21.text"));
        jLabel22.setText(bundle.getString("jLabel22.text"));
        jLabelMask.setText(bundle.getString("jLabel23.text"));
        jLabelBarcodes.setText(bundle.getString("jLabel24.text"));
        jLabelCount.setText(bundle.getString("jLabel25.text"));
        jLabelStatus.setText(bundle.getString("jLabel26.text"));
        jLabel27.setText(bundle.getString("jLabel27.text"));
        jLabel30.setText(bundle.getString("jLabel30.text"));
        jLabel33.setText(bundle.getString("jLabel33.text"));
		jLabel34.setText("RegEx");
		jLabel35.setText("Wortposition");
        jPanel2.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel2.title")));
        jPanel3.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel3.title")));
        jPanel4.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel4.title")));
        jPanel6.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel6.title")));
        jPanel7.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel7.title")));
        jPanel8.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel8.title")));
        //jPanel9.setBorder(BorderFactory.createTitledBorder(bundle.getString("jPanel9.title")));
        jRadioButton1.setText(bundle.getString("jRadioButton1.text"));
        jRadioButton2.setText(bundle.getString("jRadioButton2.text"));
        jRadioButton3.setText(bundle.getString("jRadioButton3.text"));
        jRadioButton4.setText(bundle.getString("jRadioButton4.text"));
        jRadioButton6.setText(bundle.getString("jRadioButton6.text"));
        jRadioButton7.setText(bundle.getString("jRadioButton7.text"));
        jCheckBox1.setText(bundle.getString("jCheckBox1.text"));
        jButton1.setText(bundle.getString("jButton1.text"));
        // +++ SL 
        jCheckBox6.setText(bundle.getString("jCheckBox6.text"));
        // +++
    }

    public void setListener() {
        jTextField1.addKeyListener(new KeyAdapterStringOnly());
        jTextField2.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField3.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField4.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField5.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField7.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField8.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField9.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField10.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField11.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField12.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField13.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField14.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField15.addKeyListener(new KeyAdapterNumbersOnly());
        jTextField16.addKeyListener(new KeyAdapterNumbersOnly());
		
		//jTextField25.addKeyListener(new KeyAdapterStringOnly());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
		jCheckBox6 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabelMask = new javax.swing.JLabel();
        jLabelBarcodes = new javax.swing.JLabel();
        jLabelCount = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();		
        jComboBoxMasks = new javax.swing.JComboBox();
        jComboBoxBarcode = new javax.swing.JComboBox();
        jComboBoxCount = new javax.swing.JComboBox();
        jComboBoxStatus = new javax.swing.JComboBox();
        jLabelInvalidMask = new javax.swing.JLabel();
        jLabelInvalidFieldBarcodes = new javax.swing.JLabel();
        jLabelInvalidFieldCount = new javax.swing.JLabel();
        jLabelInvalidFieldStatus = new javax.swing.JLabel();
        jComboBoxFielfPageCount = new javax.swing.JComboBox();
        jLabelInvalidFieldPage = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        jRadioButton8 = new javax.swing.JRadioButton();
        jTextField22 = new javax.swing.JTextField();
		
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
		jLabel23 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
		jTextField25 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
		jLabel34 = new javax.swing.JLabel();
		jLabel35 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        jPanel1.setName("jPanel1"); // NOI18N

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
		
		jTextField25.setName("jTextField25"); // NOI18N
        jTextField25.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField25FocusGained(evt);
            }
        });

        jLabel8.setText("Action");
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField21.setName("jTextField21"); // NOI18N
		
		// NEW
		//jCheckBox6.setText("Seiten nicht anzeigen");
        jCheckBox6.setName("jCheckBox6"); // NOI18N
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });						
		// END

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
		
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(21, 21, 21)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField21))//)
                            // NEW
                            .addGroup(jPanel6Layout.createSequentialGroup()						
                        .addComponent(jCheckBox6))//)
                        
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jRadioButton6)
                            //.addGap(30, 30, 30)
                            .addComponent(jRadioButton7))
                        
                       .addGroup(jPanel6Layout.createSequentialGroup()                                                
                               .addComponent(jLabel34)
                               .addGap(18, 18, 18)
                               .addComponent(jTextField25))
                )
					// END
                .addGap(39, 39, 39))
        );
		
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				// NEW
				//.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGap(5, 5, 5)
				.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCheckBox6))	
				.addGap(5, 5, 5)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jRadioButton6)
                                        //.addGap(30, 30, 30)
                                        .addComponent(jRadioButton7))
				.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)                                        
					.addComponent(jLabel34)
					.addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				// END
                .addContainerGap(92, Short.MAX_VALUE))
        );

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/di/barcode/gui/resources/FilesTab"); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("jpanel1.border.title"))); // NOI18N
        jPanel2.setDoubleBuffered(false);
        jPanel2.setEnabled(false);
        jPanel2.setFocusable(false);
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setName("jLabel5"); // NOI18N

        jLabel9.setText("X1: ");
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText("X2: ");
        jLabel10.setName("jLabel10"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        jTextField3.setName("jTextField3"); // NOI18N

        jTextField4.setName("jTextField4"); // NOI18N

        jTextField5.setName("jTextField5"); // NOI18N

        jLabel11.setText("Y1:");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText("Y2:");
        jLabel12.setName("jLabel12"); // NOI18N

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("All Pages");
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("First page only");
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        buttonGroup7.add(jRadioButton6);
        jRadioButton6.setText("Enthalten");
        jRadioButton6.setName("jRadioButton6"); // NOI18N
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        buttonGroup7.add(jRadioButton7);
        jRadioButton7.setText("Nicht enthalten");
        jRadioButton7.setName("jRadioButton7"); // NOI18N
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setText("Full Page");
        jRadioButton3.setName("jRadioButton3"); // NOI18N
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("Areas (coordinates)");
        jRadioButton4.setName("jRadioButton4"); // NOI18N
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton4))
                        .addGap(495, 495, 495)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel9)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("jPanel2.border.title"))); // NOI18N
        jPanel3.setDoubleBuffered(false);
        jPanel3.setEnabled(false);
        jPanel3.setFocusable(false);
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        jCheckBox2.setText("0°");
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("90°");
        jCheckBox3.setName("jCheckBox3"); // NOI18N
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setText("180°");
        jCheckBox4.setName("jCheckBox4"); // NOI18N
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.setText("270°");
        jCheckBox5.setName("jCheckBox5"); // NOI18N
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox2)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox3)
                .addGap(11, 11, 11)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox5)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Skew"));
        jPanel4.setEnabled(false);
        jPanel4.setFocusable(false);
        jPanel4.setName("jPanel4"); // NOI18N

        buttonGroup6.add(jRadioButton11);
        jRadioButton11.setText("8°");
        jRadioButton11.setName("jRadioButton11"); // NOI18N
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });

        buttonGroup6.add(jRadioButton12);
        jRadioButton12.setText("21°");
        jRadioButton12.setName("jRadioButton12"); // NOI18N
        jRadioButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton12ActionPerformed(evt);
            }
        });

        buttonGroup6.add(jRadioButton13);
        jRadioButton13.setText("34°");
        jRadioButton13.setName("jRadioButton13"); // NOI18N
        jRadioButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton13ActionPerformed(evt);
            }
        });

        buttonGroup6.add(jRadioButton14);
        jRadioButton14.setText("46°");
        jRadioButton14.setName("jRadioButton14"); // NOI18N
        jRadioButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton11)
                .addGap(16, 16, 16)
                .addComponent(jRadioButton12)
                .addGap(10, 10, 10)
                .addComponent(jRadioButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jRadioButton14)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton11)
                    .addComponent(jRadioButton12)
                    .addComponent(jRadioButton14)
                    .addComponent(jRadioButton13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("ELO"));
        jPanel7.setDoubleBuffered(false);
        jPanel7.setEnabled(false);
        jPanel7.setFocusable(false);
        jPanel7.setMaximumSize(new java.awt.Dimension(390, 252));
        jPanel7.setMinimumSize(new java.awt.Dimension(390, 252));
        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(390, 252));
        jPanel7.setRequestFocusEnabled(false);
        jPanel7.setVerifyInputWhenFocusTarget(false);

        jLabelMask.setText("Mask");
        jLabelMask.setName("jLabelMask"); // NOI18N

        jLabelBarcodes.setText("Barcodes");
        jLabelBarcodes.setName("jLabelBarcodes"); // NOI18N

        jLabelCount.setText("Count");
        jLabelCount.setName("jLabelCount"); // NOI18N

        jLabelStatus.setText("Status");
        jLabelStatus.setName("jLabelStatus"); // NOI18N

        jLabel13.setText("Page number");
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel1.setText("Status OK");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel6.setText("Status Error");
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField23.setName("jTextField23"); // NOI18N
        jTextField23.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField23FocusGained(evt);
            }
        });

        jTextField24.setName("jTextField24"); // NOI18N
        jTextField24.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField24FocusGained(evt);
            }
        });			

        jComboBoxMasks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMasks.setMaximumSize(new java.awt.Dimension(80, 20));
        jComboBoxMasks.setMinimumSize(new java.awt.Dimension(80, 20));
        jComboBoxMasks.setName("jComboBoxMasks"); // NOI18N
        jComboBoxMasks.setPreferredSize(new java.awt.Dimension(80, 20));
        jComboBoxMasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMasksActionPerformed(evt);
            }
        });

        jComboBoxBarcode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBarcode.setName("jComboBoxBarcode"); // NOI18N
        jComboBoxBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBarcodeActionPerformed(evt);
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
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("de/di/barcode/gui/resources/SplitSortMerge"); // NOI18N
        jLabelInvalidMask.setToolTipText(bundle1.getString("errorMessage.invalidMask")); // NOI18N
        jLabelInvalidMask.setName("jLabelInvalidMask"); // NOI18N

        jLabelInvalidFieldBarcodes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldBarcodes.setToolTipText(bundle1.getString("errorMessage.invalidField")); // NOI18N
        jLabelInvalidFieldBarcodes.setName("jLabelInvalidFieldBarcodes"); // NOI18N

        jLabelInvalidFieldCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldCount.setToolTipText(bundle1.getString("errorMessage.invalidField")); // NOI18N
        jLabelInvalidFieldCount.setName("jLabelInvalidFieldCount"); // NOI18N

        jLabelInvalidFieldStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldStatus.setToolTipText(bundle1.getString("errorMessage.invalidField")); // NOI18N
        jLabelInvalidFieldStatus.setName("jLabelInvalidFieldStatus"); // NOI18N

        jComboBoxFielfPageCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxFielfPageCount.setName("jComboBoxFielfPageCount"); // NOI18N
        jComboBoxFielfPageCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFielfPageCountActionPerformed(evt);
            }
        });

        jLabelInvalidFieldPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/di/barcode/gui/resources/test.png"))); // NOI18N
        jLabelInvalidFieldPage.setToolTipText(bundle1.getString("errorMessage.invalidField")); // NOI18N
        jLabelInvalidFieldPage.setName("jLabelInvalidFieldPage"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMask)
                            .addComponent(jLabelBarcodes)
                            .addComponent(jLabelCount)
                            .addComponent(jLabelStatus)
                            .addComponent(jLabel1)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField23)
                            .addComponent(jTextField24)
                            .addComponent(jComboBoxMasks, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxBarcode, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxFielfPageCount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInvalidFieldBarcodes)
                            .addComponent(jLabelInvalidMask)
                            .addComponent(jLabelInvalidFieldStatus)
                            .addComponent(jLabelInvalidFieldCount)
                            .addComponent(jLabelInvalidFieldPage))
                        .addGap(34, 34, 34))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelInvalidMask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMask)
                    .addComponent(jComboBoxMasks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBarcodes)
                        .addComponent(jComboBoxBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInvalidFieldBarcodes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelCount)
                        .addComponent(jComboBoxCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInvalidFieldCount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelStatus)
                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInvalidFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInvalidFieldPage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxFielfPageCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Individual configuration"));
        jPanel8.setDoubleBuffered(false);
        jPanel8.setEnabled(false);
        jPanel8.setFocusable(false);
        jPanel8.setMaximumSize(new java.awt.Dimension(390, 59));
        jPanel8.setMinimumSize(new java.awt.Dimension(390, 59));
        jPanel8.setName("jPanel8"); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(390, 59));

        jLabel27.setText("Configuration 1");
        jLabel27.setName("jLabel27"); // NOI18N

        buttonGroup3.add(jRadioButton5);
        jRadioButton5.setName("jRadioButton5"); // NOI18N
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jLabel30.setText("Standard");
        jLabel30.setName("jLabel30"); // NOI18N

        buttonGroup3.add(jRadioButton8);
        jRadioButton8.setName("jRadioButton8"); // NOI18N
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        jTextField22.setName("jTextField22"); // NOI18N
        jTextField22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField22FocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel27)
                    .addComponent(jRadioButton5)
                    .addComponent(jLabel30)
                    .addComponent(jRadioButton8)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

//        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Searchparameter"));
//        jPanel9.setDoubleBuffered(false);
//        jPanel9.setEnabled(false);
//        jPanel9.setFocusable(false);
//        jPanel9.setMaximumSize(new java.awt.Dimension(390, 210));
//        jPanel9.setMinimumSize(new java.awt.Dimension(390, 210));
//        jPanel9.setName("jPanel9"); // NOI18N
//        jPanel9.setOpaque(false);
//        jPanel9.setPreferredSize(new java.awt.Dimension(390, 210));
//        jPanel9.setRequestFocusEnabled(false);
//        jPanel9.setVerifyInputWhenFocusTarget(false);

        jLabel15.setText("Rest area");
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText("Percent");
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel17.setText("Scan distance");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText("Search distance");
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setText("Offset");
        jLabel19.setFocusable(false);
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setRequestFocusEnabled(false);
        jLabel19.setVerifyInputWhenFocusTarget(false);

        jLabel20.setText("max. Gap");
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel21.setText("min. Height");
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel22.setText("max. Height");
        jLabel22.setName("jLabel22"); // NOI18N

        jTextField9.setName("jTextField9"); // NOI18N
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });

        jTextField10.setName("jTextField10"); // NOI18N
        jTextField10.setVerifyInputWhenFocusTarget(false);
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField10FocusLost(evt);
            }
        });

        jTextField11.setName("jTextField11"); // NOI18N
        jTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField11FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField11FocusLost(evt);
            }
        });

        jTextField12.setName("jTextField12"); // NOI18N
        jTextField12.setVerifyInputWhenFocusTarget(false);
        jTextField12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField12FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField12FocusLost(evt);
            }
        });

        jTextField13.setName("jTextField13"); // NOI18N
        jTextField13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField13FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField13FocusLost(evt);
            }
        });

        jTextField14.setName("jTextField14"); // NOI18N
        jTextField14.setVerifyInputWhenFocusTarget(false);
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField14FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField14FocusLost(evt);
            }
        });

        jTextField15.setName("jTextField15"); // NOI18N
        jTextField15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField15FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField15FocusLost(evt);
            }
        });

        jTextField16.setName("jTextField16"); // NOI18N
        jTextField16.setVerifyInputWhenFocusTarget(false);
        jTextField16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField16FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField16FocusLost(evt);
            }
        });

        jLabel14.setText("Min Length");
        jLabel14.setName("jLabel14"); // NOI18N

        jTextField7.setName("jTextField7"); // NOI18N
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });				

        jLabel33.setText("Max Length");
        jLabel33.setName("jLabel33"); // NOI18N
		
		//jLabel34.setText("RegEx");
        jLabel34.setName("jLabel34"); // NOI18N
		
		//jLabel35.setText("Wortposition");
		jLabel35.setName("jLabel35");

        jTextField8.setName("jTextField8"); // NOI18N
        jTextField8.setVerifyInputWhenFocusTarget(false);
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });

        jButton1.setText("Types");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Dense Search");
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

//        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
//        jPanel9.setLayout(jPanel9Layout);
//        jPanel9Layout.setHorizontalGroup(
//            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(jPanel9Layout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(jPanel9Layout.createSequentialGroup()
//                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addGroup(jPanel9Layout.createSequentialGroup()
//                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
//                            .addGroup(jPanel9Layout.createSequentialGroup()
//                                .addComponent(jLabel19)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
//                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
//                                .addComponent(jLabel17)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
//                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
//                                .addComponent(jLabel15)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
//                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
//                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(jLabel16)
//                            .addComponent(jLabel18)
//                            .addComponent(jLabel20)
//                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addComponent(jLabel33))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
//                    .addGroup(jPanel9Layout.createSequentialGroup()
//                        .addComponent(jButton1)
//                        .addGap(87, 87, 87)))
//                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(jCheckBox1)
//                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGap(46, 46, 46))
//        );
//        jPanel9Layout.setVerticalGroup(
//            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(jPanel9Layout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
//                    .addComponent(jLabel15)
//                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jLabel16)
//                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
//                    .addComponent(jLabel17)
//                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jLabel18))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
//                    .addComponent(jLabel19)
//                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jLabel20))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
//                    .addComponent(jLabel21)
//                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jLabel22)
//                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
//                    .addComponent(jLabel14)
//                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jLabel33)
//                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGap(18, 18, 18)
//                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(jButton1)
//                    .addComponent(jCheckBox1))
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

        private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
            GeneralTab.config.setProperty("", "");
        // TODO add your handling code here:}                                             
    }        
        private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                              
            GeneralTab.config.setProperty("", "");
        // TODO add your handling code here:}                                             
    }
        
        private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                              
            GeneralTab.config.setProperty("", "");
        // TODO add your handling code here:}                                             
    }
        
        private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                              
            GeneralTab.config.setProperty("", "");
        // TODO add your handling code here:}                                             
    }
        private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                              
            GeneralTab.config.setProperty("", "");
            jTextField2.setEnabled(true);
            jTextField3.setEnabled(true);
            jTextField4.setEnabled(true);
            jTextField5.setEnabled(true);
        // TODO add your handling code here:}                                             
    }

        private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
            GeneralTab.config.setProperty("", "");
        // TODO add your handling code here:}                                          
    }
        private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
            GeneralTab.config.setProperty("", "");
        // TODO add your handling code here:}                                          
    }
        private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
            GeneralTab.config.setProperty("", "");
        // TODO add your handling code here:}                                          
    }
        private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {                                           
            GeneralTab.config.setProperty("", "");
        // TODO add your handling code here:}                                          
    }
	
		// NEW
		private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {
            GeneralTab.config.setProperty("", "");        
		}
		// END
	
        private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                              
            GeneralTab.config.setProperty("", "");
            jTextField2.setEnabled(false);
            jTextField3.setEnabled(false);
            jTextField4.setEnabled(false);
            jTextField5.setEnabled(false);
    }                                             

    private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        GeneralTab.config.setProperty("", "");
    }                                              

    private void jRadioButton12ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        GeneralTab.config.setProperty("", "");
    }                                              

    private void jRadioButton13ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        GeneralTab.config.setProperty("", "");
    }                                              

    private void jRadioButton14ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        GeneralTab.config.setProperty("", "");
    }                                              

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        GeneralTab.config.setProperty("", "");
    }                                             

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        GeneralTab.config.setProperty("", "");
    }                                             

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {                                        
        GeneralTab.config.setProperty("", "");
    }                                       
	
	private void jTextField25FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField22FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField23FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField24FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        
	

    private void jComboBoxMasksActionPerformed(java.awt.event.ActionEvent evt) {                                               
        boolean hasValue = SwingUtils.setItems(jComboBoxMasks, ConfigApp.client.getMasks(), (String) jComboBoxMasks.getSelectedItem());
        jLabelInvalidMask.setVisible(!hasValue);
        hasValue = SwingUtils.setItems(jComboBoxBarcode, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxBarcode.getSelectedItem());
        jLabelInvalidFieldBarcodes.setVisible(!hasValue);
        hasValue = SwingUtils.setItems(jComboBoxCount, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxCount.getSelectedItem());
        jLabelInvalidFieldCount.setVisible(!hasValue);
        hasValue = SwingUtils.setItems(jComboBoxStatus, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxStatus.getSelectedItem());
        jLabelInvalidFieldStatus.setVisible(!hasValue);
        hasValue = SwingUtils.setItems(jComboBoxFielfPageCount, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxFielfPageCount.getSelectedItem());
        jLabelInvalidFieldPage.setVisible(!hasValue);
    }                                              

    private void jComboBoxBarcodeActionPerformed(java.awt.event.ActionEvent evt) {                                                 
//        System.out.println("label visible: " + "value" + (String) jComboBoxBarcode.getSelectedItem());
    }                                                

    private void jComboBoxCountActionPerformed(java.awt.event.ActionEvent evt) {                                               

        boolean hasValue = SwingUtils.setItems(jComboBoxCount, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxCount.getSelectedItem());
        jLabelInvalidFieldCount.setVisible(!hasValue);    }                                              

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {                                                
        boolean hasValue = SwingUtils.setItems(jComboBoxStatus, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxStatus.getSelectedItem());
        jLabelInvalidFieldStatus.setVisible(!hasValue);
    }                                               

    private void jComboBoxFielfPageCountActionPerformed(java.awt.event.ActionEvent evt) {                                                        
        boolean hasValue = SwingUtils.setItems(jComboBoxFielfPageCount, ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()), (String) jComboBoxFielfPageCount.getSelectedItem());
        jLabelInvalidFieldPage.setVisible(!hasValue);
    }                                                       

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        GeneralTab.config.setProperty("", "");
    }                                          

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        types.setProfileProperties(proproperties);
        types.setLocation(200, 200);
        types.setAlwaysOnTop(true);
        types.setVisible(true);
    }                                        

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {                                      
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField8.getText());
        } catch (NumberFormatException ex) {
        }
        if (value > 64 || jTextField8.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Die Wertebereich von max. Länge liegt in der Menge {0,...,64}";
            } else {
                errorMessage = "The value-range from max. length is between {0,...,64}";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            globalErrorMessage = errorMessage;
            //jTextField8.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                     

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {                                        
        GeneralTab.config.setProperty("", "");
    }                                       

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {                                      
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField7.getText());
        } catch (NumberFormatException ex) {
        }
        if (value > 64 || jTextField7.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Die Wertebereich von min. Länge liegt in der Menge {0,...,64}";
            } else {
                errorMessage = "The value-range of min. length is between {0,...,64}";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            globalErrorMessage = errorMessage;
            //jTextField7.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                       

    private void jTextField7FocusGained(java.awt.event.FocusEvent evt) {                                        
        GeneralTab.config.setProperty("", "");
    }                                         

    private void jTextField16FocusLost(java.awt.event.FocusEvent evt) {                                       
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField16.getText());
        } catch (NumberFormatException ex) {
        }
        if (value < 0 || jTextField16.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Der Feld Max. Höhe kann nur Zahlen enthalten";
            } else {
                errorMessage = "The field Max. Heigth can only contained number";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            globalErrorMessage = errorMessage;
            //            jTextField16.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                      

    private void jTextField16FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField15FocusLost(java.awt.event.FocusEvent evt) {                                       
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField15.getText());
        } catch (NumberFormatException ex) {
        }
        if (value < 0 || jTextField15.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Der Feld Min. Höhe kann nur Zahlen enthalten";
            } else {
                errorMessage = "The field Min. Heigth can only contained number";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            globalErrorMessage = errorMessage;
            //            jTextField15.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                      

    private void jTextField15FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField14FocusLost(java.awt.event.FocusEvent evt) {                                       
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField14.getText());
        } catch (NumberFormatException ex) {
        }
        if (value < 0 || jTextField14.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Der Feld Max. Lücke kann nur Zahlen enthalten";
            } else {
                errorMessage = "The Max. Gap can only contained number";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            globalErrorMessage = errorMessage;
            //            jTextField14.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                      

    private void jTextField14FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField13FocusLost(java.awt.event.FocusEvent evt) {                                       
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField13.getText());
        } catch (NumberFormatException ex) {
        }
        if (value < 0 || jTextField13.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Der Feld Versatz kann nur Zahlen enthalten";
            } else {
                errorMessage = "The field Offset can only contained number";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            //                jTextField13.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                      

    private void jTextField13FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField12FocusLost(java.awt.event.FocusEvent evt) {                                       
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField12.getText());
        } catch (NumberFormatException ex) {
        }
        if (value < 0 || jTextField12.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Der Feld Suchabstand kann nur Zahlen enthalten";
            } else {
                errorMessage = "The field Search distance can only contained number";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            globalErrorMessage = errorMessage;
            //            jTextField12.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                      

    private void jTextField12FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField11FocusLost(java.awt.event.FocusEvent evt) {                                       
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField11.getText());
        } catch (NumberFormatException ex) {
        }
        if (value < 0 || jTextField11.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Der Feld Scanabstand kann nur Zahlen enthalten";
            } else {
                errorMessage = "The field Scan distance can only contained number";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            globalErrorMessage = errorMessage;
            //            jTextField11.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                      

    private void jTextField11FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField10FocusLost(java.awt.event.FocusEvent evt) {                                       
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField10.getText());
        } catch (NumberFormatException ex) {
        }
        if (value > 100 || jTextField10.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Die Wertebereich vom Feld Prozent liegt in der Menge {0,...,100}";
            } else {
                errorMessage = "The value-range from field Percent is between {0,...,100}";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            globalErrorMessage = errorMessage;
            //            jTextField10.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                      

    private void jTextField10FocusGained(java.awt.event.FocusEvent evt) {                                         
        GeneralTab.config.setProperty("", "");
    }                                        

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {                                      
        fieldSet = false;
        String errorMessage = "";
        int value = 0;
        try {
            value = Integer.valueOf(jTextField9.getText());
        } catch (NumberFormatException ex) {
        }
        if (value < 18 || jTextField9.getText().equalsIgnoreCase("")) {
            if (System.getProperty("user.language").equalsIgnoreCase("de")) {
                errorMessage = "Der Feld Ruhezone kann Werte >= 18 haben";
            } else {
                errorMessage = "The field rest area can assume values >= 18";
            }
            JOptionPane.showMessageDialog(this, errorMessage);
            globalErrorMessage = errorMessage;
            //            jTextField9.requestFocus();
        } else {
            fieldSet = true;
        }
    }                                     

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {                                        
        GeneralTab.config.setProperty("", "");
    }                                       
    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
	private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JComboBox jComboBoxBarcode;
    private javax.swing.JComboBox jComboBoxCount;
    private javax.swing.JComboBox jComboBoxFielfPageCount;
    private javax.swing.JComboBox jComboBoxMasks;
    private javax.swing.JComboBox jComboBoxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
	private javax.swing.JLabel jLabel34;
	private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBarcodes;
    private javax.swing.JLabel jLabelCount;
    private javax.swing.JLabel jLabelInvalidFieldBarcodes;
    private javax.swing.JLabel jLabelInvalidFieldCount;
    private javax.swing.JLabel jLabelInvalidFieldPage;
    private javax.swing.JLabel jLabelInvalidFieldStatus;
    private javax.swing.JLabel jLabelInvalidMask;
    private javax.swing.JLabel jLabelMask;
    private javax.swing.JLabel jLabelStatus;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    
    private javax.swing.JRadioButton jRadioButton8;
    public javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
	private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration                   

    private void setProfileProperties(Properties prop) {
        if (prop.containsKey("ProfileName")) {
            jTextField1.setText(prop.getProperty("ProfileName"));
        }
        if (prop.containsKey("ChangeMask")) {
//            jTextField17.setText(prop.getProperty("ChangeMask"));
            boolean hasValue = SwingUtils.setItems(jComboBoxMasks, ConfigApp.client.getMasks(), prop.getProperty("ChangeMask"));
            jLabelInvalidMask.setVisible(!hasValue);
        }
        if (prop.containsKey("ResultFieldData")) {
            //jTextField18.setText(prop.getProperty("ResultFieldData"));
            String selectedMask = (String) jComboBoxMasks.getSelectedItem();
            boolean hasValue = SwingUtils.setItems(jComboBoxBarcode, ConfigApp.client.getMasksAsMap().get(selectedMask), prop.getProperty("ResultFieldData"));
//            System.out.println("label visible: " + !hasValue);
            jLabelInvalidFieldBarcodes.setVisible(!hasValue);
            jComboBoxBarcode.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
//                    System.out.println("dasdadsad");
                    boolean hasValue = false;
                    String value = (String) jComboBoxBarcode.getSelectedItem();
                    for (DocMaskLine dml : ConfigApp.client.getMasksAsMap().get((String) jComboBoxMasks.getSelectedItem()).getLines()) {
                        if (dml.getKey().equals(value)) {
                            hasValue = true;
                            break;
                        }
                    }

                    jLabelInvalidFieldBarcodes.setVisible(!hasValue);
                }
            });
        }
        if (prop.containsKey("ResultFieldBCcount")) {
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
        if (prop.containsKey("Action")) {
            jTextField21.setText(prop.getProperty("Action"));
        }
		if (prop.containsKey("BC_Character")) {
            jTextField25.setText(prop.getProperty("BC_Character"));
        }
//        if (prop.containsKey("ResultStatusOk")) {
        jTextField23.setText(prop.getProperty("ResultStatusOk", "20 BCfinished"));
//        }
//        if (prop.containsKey("ResultStatusError")) {
        jTextField24.setText(prop.getProperty("ResultStatusError", "21 – BCvalidateError"));
//        }
        jTextField21.setEditable(false);
        if (prop.containsKey("SearchArea") && prop.getProperty("SearchArea").equalsIgnoreCase("Page")) {
            jRadioButton3.setSelected(true);
            jRadioButton4.setSelected(false);
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField2.setEnabled(false);
            jTextField3.setEnabled(false);
            jTextField4.setEnabled(false);
            jTextField5.setEnabled(false);
        } else {
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(true);
            jTextField2.setEnabled(true);
            jTextField3.setEnabled(true);
            jTextField4.setEnabled(true);
            jTextField5.setEnabled(true);
            if (prop.containsKey("iBC_SizeX")) {
                jTextField2.setText(prop.getProperty("iBC_SizeX"));
            }
            if (prop.containsKey("iBC_StartX")) {
                jTextField3.setText(prop.getProperty("iBC_StartX"));
            }
            if (prop.containsKey("iBC_SizeY")) {
                jTextField4.setText(prop.getProperty("iBC_SizeY"));
            }
            if (prop.containsKey("iBC_StartY")) {
                jTextField5.setText(prop.getProperty("iBC_StartY"));
            }
        }
//        if (prop.containsKey("PageNumber") && !prop.getProperty("PageNumber").isEmpty()) {
//            jTextField6.setText(prop.getProperty("PageNumber"));
// 
//        }
        if (prop.containsKey("BC_SKEW_NONE") && prop.getProperty("BC_SKEW_NONE").equalsIgnoreCase("1")) {
            jRadioButton11.setSelected(true);
        }
        if (prop.containsKey("BC_SKEW_LIGHT") && prop.getProperty("BC_SKEW_LIGHT").equalsIgnoreCase("1")) {
            jRadioButton12.setSelected(true);
        }
        if (prop.containsKey("BC_SKEW_MEDIUM") && prop.getProperty("BC_SKEW_MEDIUM").equalsIgnoreCase("1")) {
            jRadioButton13.setSelected(true);
        }
        if (prop.containsKey("BC_SKEW_HEAVY") && prop.getProperty("BC_SKEW_HEAVY").equalsIgnoreCase("1")) {
            jRadioButton14.setSelected(true);
        }
        if (prop.containsKey("BC_SKEW_DENSE_SEARCH") && prop.getProperty("BC_SKEW_DENSE_SEARCH").equalsIgnoreCase("1")) {
            jCheckBox1.setSelected(true);
        }
        if (prop.containsKey("SearchArea") && prop.getProperty("SearchPages").equalsIgnoreCase("All")) {
            jRadioButton1.setSelected(true);
        } else {
            jRadioButton2.setSelected(true);
        }
        // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if (prop.containsKey("BC_included") && prop.getProperty("BC_included").equalsIgnoreCase("TRUE")) {
            jRadioButton6.setSelected(true);
        } else {
            jRadioButton7.setSelected(true);
        }
        // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if (prop.containsKey("BC_0") && prop.getProperty("BC_0").equals("1")) {
            jCheckBox2.setSelected(true);
        } else {
            jCheckBox2.setSelected(false);
        }
        if (prop.containsKey("BC_90") && prop.getProperty("BC_90").equals("1")) {
            jCheckBox3.setSelected(true);
        } else {
            jCheckBox3.setSelected(false);
        }
        if (prop.containsKey("BC_180") && prop.getProperty("BC_180").equals("1")) {
            jCheckBox4.setSelected(true);
        } else {
            jCheckBox4.setSelected(false);
        }
        if (prop.containsKey("BC_270") && prop.getProperty("BC_270").equals("1")) {
            jCheckBox5.setSelected(true);
        } else {
            jCheckBox5.setSelected(false);
        }
		// NEW
		if (prop.getProperty("HidePagesNrByKeywording").equalsIgnoreCase("TRUE")) {
            jCheckBox6.setSelected(true);
        } else {
            jCheckBox6.setSelected(false);
        }
		// END
        if (prop.containsKey("iLaengeVon") && !prop.getProperty("iLaengeVon").isEmpty()) {
            jTextField7.setText(prop.getProperty("iLaengeVon"));
        } else {
            jTextField7.setText("");
        }
        if (prop.containsKey("iLaengeBis") && !prop.getProperty("iLaengeBis").isEmpty()) {
            jTextField8.setText(prop.getProperty("iLaengeBis"));
        } else {
            jTextField8.setText("");
        }
        if (prop.containsKey("BC_LIGHTMARGIN") && !prop.getProperty("BC_LIGHTMARGIN").isEmpty()) {
            jTextField9.setText(prop.getProperty("BC_LIGHTMARGIN"));
        } else {
            jTextField9.setText("");
        }
        if (prop.containsKey("BC_PERCENT") && !prop.getProperty("BC_PERCENT").isEmpty()) {
            jTextField10.setText(prop.getProperty("BC_PERCENT"));
        } else {
            jTextField10.setText("");
        }
        if (prop.containsKey("BC_SCANDISTBAR") && !prop.getProperty("BC_SCANDISTBAR").isEmpty()) {
            jTextField11.setText(prop.getProperty("BC_SCANDISTBAR"));
        } else {
            jTextField11.setText("");
        }
        if (prop.containsKey("BC_SCANDISTANCE") && !prop.getProperty("BC_SCANDISTANCE").isEmpty()) {
            jTextField12.setText(prop.getProperty("BC_SCANDISTANCE"));
        } else {
            jTextField12.setText("");
        }
        if (prop.containsKey("BC_TOLERANCE") && !prop.getProperty("BC_TOLERANCE").isEmpty()) {
            jTextField13.setText(prop.getProperty("BC_TOLERANCE"));
        } else {
            jTextField13.setText("");
        }
        if (prop.containsKey("BC_MAX_NO_VAL") && !prop.getProperty("BC_MAX_NO_VAL").isEmpty()) {
            jTextField14.setText(prop.getProperty("BC_MAX_NO_VAL"));
        } else {
            jTextField14.setText("");
        }
        if (prop.containsKey("BC_MINHEIGHT") && !prop.getProperty("BC_MINHEIGHT").isEmpty()) {
            jTextField15.setText(prop.getProperty("BC_MINHEIGHT"));
        } else {
            jTextField15.setText("");
        }
        if (prop.containsKey("BC_MAXHEIGHT") && !prop.getProperty("BC_MAXHEIGHT").isEmpty()) {
            jTextField16.setText(prop.getProperty("BC_MAXHEIGHT"));
        } else {
            jTextField16.setText("");
        }
        if (prop.containsKey("Configuration1_ISSELECTED") && prop.getProperty("Configuration1_ISSELECTED").equalsIgnoreCase("true")) {
            jRadioButton5.setSelected(true);
        } else {
            jRadioButton5.setSelected(false);
        }
        if (prop.containsKey("ResultFieldPagecount") && !prop.getProperty("ResultFieldPagecount").isEmpty()) {
            String selectedMask = (String) jComboBoxMasks.getSelectedItem();
            boolean hasValue = SwingUtils.setItems(jComboBoxFielfPageCount, ConfigApp.client.getMasksAsMap().get(selectedMask), prop.getProperty("ResultFieldPagecount"));
            jLabelInvalidFieldPage.setVisible(!hasValue);

        }
    }

    public void showErrorMessage() throws Exception {
        String errorMessage;
        if (System.getProperty("user.language").equalsIgnoreCase("de")) {
            errorMessage = "Ein Profil mit identischem Namen existiert bereits!";
        } else {
            errorMessage = "A profile with the same name already existing!";
        }
        JOptionPane.showMessageDialog(this, errorMessage);
        throw new Exception();
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
            JOptionPane.showMessageDialog(this, bundle.getString("errorMessage.enumeration"));
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
        return true;
    }

    public void save(Properties profile, String currentProfileNumber) {
        if (fieldSet) {
            if (jTextField1.getText().isEmpty()) {
                profile.setProperty("ProfileName", "");
            } else {
                profile.setProperty("ProfileName", jTextField1.getText());
            }
			if (jTextField25.getText().isEmpty()) {
                profile.setProperty("BC_Character", "");
            } else {
                profile.setProperty("BC_Character", jTextField25.getText());
            }
//            if (jTextField17.getText().isEmpty()) {
//                profile.setProperty("ChangeMask", "");
//            } else {
//                profile.setProperty("ChangeMask", jTextField17.getText());
//            }
            profile.setProperty("ChangeMask", (String) jComboBoxMasks.getSelectedItem());
//            if (jTextField18.getText().isEmpty()) {
//                profile.setProperty("ResultFieldData", "");
//            } else {
//                profile.setProperty("ResultFieldData", jTextField18.getText());
//            }
            profile.setProperty("BarcodeContent", (String) jComboBoxBarcode.getSelectedItem());
//            if (jTextField19.getText().isEmpty()) {
//                profile.setProperty("ResultFieldBCcount", "");
//            } else {
//                profile.setProperty("ResultFieldBCcount", jTextField19.getText());
//            }
            profile.setProperty("ResultFieldBCcount", (String) jComboBoxCount.getSelectedItem());
//            if (jTextField20.getText().isEmpty()) {
//                profile.setProperty("ResultFieldStatus", "");
//            } else {
//                profile.setProperty("ResultFieldStatus", jTextField20.getText());
//            }
            profile.setProperty("ResultFieldStatus", (String) jComboBoxStatus.getSelectedItem());
            if (jTextField23.getText().isEmpty()) {
                profile.setProperty("ResultStatusOk", "");
            } else {
                profile.setProperty("ResultStatusOk", jTextField23.getText());
            }
            if (jTextField24.getText().isEmpty()) {
                profile.setProperty("ResultStatusError", "");
            } else {
                profile.setProperty("ResultStatusError", jTextField24.getText());
            }
            if (jRadioButton3.isSelected()) {
                profile.setProperty("SearchArea", "Page");
                profile.setProperty("iBC_SizeX", "0");
                profile.setProperty("iBC_StartX", "0");
                profile.setProperty("iBC_SizeY", "0");
                profile.setProperty("iBC_StartY", "0");
            } else {
                profile.setProperty("SearchArea", "");
                if (jTextField2.getText().isEmpty()) {
                    profile.setProperty("iBC_SizeX", "0");
                } else {
                    profile.setProperty("iBC_SizeX", jTextField2.getText());
                }
                if (jTextField3.getText().isEmpty()) {
                    profile.setProperty("iBC_StartX", "0");
                } else {
                    profile.setProperty("iBC_StartX", jTextField3.getText());
                }
                if (jTextField4.getText().isEmpty()) {
                    profile.setProperty("iBC_SizeY", "0");
                } else {
                    profile.setProperty("iBC_SizeY", jTextField4.getText());
                }
                if (jTextField5.getText().isEmpty()) {
                    profile.setProperty("iBC_StartY", "0");
                } else {
                    profile.setProperty("iBC_StartY", jTextField5.getText());
                }
            }
            if (jRadioButton1.isSelected()) {
                profile.setProperty("SearchPages", "ALL");
            } else {
                profile.setProperty("SearchPages", "First");
            }
            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            if (jRadioButton6.isSelected()) {
                profile.setProperty("BC_included", "TRUE");
            } else {
                profile.setProperty("BC_included", "FALSE");
            }
            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            if (jRadioButton3.isSelected()) {
                profile.setProperty("SearchArea", "Page");
            } else {
                profile.setProperty("iBC_StartX", jTextField2.getText());
                profile.setProperty("iBC_StartY", jTextField4.getText());
                profile.setProperty("iBC_SizeX", jTextField3.getText());
                profile.setProperty("iBC_SizeY", jTextField5.getText());
            }
            if (jCheckBox2.isSelected()) {
                profile.setProperty("BC_0", "1");
            } else {
                profile.setProperty("BC_0", "0");
            }
            if (jCheckBox3.isSelected()) {
                profile.setProperty("BC_90", "1");
            } else {
                profile.setProperty("BC_90", "0");
            }
            if (jCheckBox4.isSelected()) {
                profile.setProperty("BC_180", "1");
            } else {
                profile.setProperty("BC_180", "0");
            }
            if (jCheckBox5.isSelected()) {
                profile.setProperty("BC_270", "1");
            } else {
                profile.setProperty("BC_270", "0");
            }
			// NEW
			if (jCheckBox6.isSelected()) {
                profile.setProperty("HidePagesNrByKeywording", "TRUE");
            } else {
                profile.setProperty("HidePagesNrByKeywording", "FALSE");
            }
			// END
            if (!jCheckBox2.isSelected()
                    && !jCheckBox3.isSelected()
                    && !jCheckBox4.isSelected()
                    && !jCheckBox5.isSelected()) {
                profile.setProperty("BC_0", "1");
                profile.setProperty("BC_90", "1");
                profile.setProperty("BC_180", "1");
                profile.setProperty("BC_270", "1");
            }
            if (jTextField7.getText().isEmpty()) {
                profile.setProperty("iLaengeVon", "4");
            } else {
                profile.setProperty("iLaengeVon", jTextField7.getText());
            }
            if (jTextField8.getText().isEmpty()) {
                profile.setProperty("iLaengeBis", "64");
            } else {
                profile.setProperty("iLaengeBis", jTextField8.getText());
            }
            if (jTextField9.getText().isEmpty()) {
                profile.setProperty("BC_LIGHTMARGIN", "");
            } else {
                profile.setProperty("BC_LIGHTMARGIN", jTextField9.getText());
            }
            if (jTextField10.getText().isEmpty()) {
                profile.setProperty("BC_PERCENT", "");
            } else {
                profile.setProperty("BC_PERCENT", jTextField10.getText());
            }
            if (jTextField11.getText().isEmpty()) {
                profile.setProperty("BC_SCANDISTBAR", "");
            } else {
                profile.setProperty("BC_SCANDISTBAR", jTextField11.getText());
            }
            if (jTextField12.getText().isEmpty()) {
                profile.setProperty("BC_SCANDISTANCE", "");
            } else {
                profile.setProperty("BC_SCANDISTANCE", jTextField12.getText());
            }
            if (jTextField13.getText().isEmpty()) {
                profile.setProperty("BC_TOLERANCE", "");
            } else {
                profile.setProperty("BC_TOLERANCE", jTextField13.getText());
            }
            if (jTextField14.getText().isEmpty()) {
                profile.setProperty("BC_MAX_NO_VAL", "");
            } else {
                profile.setProperty("BC_MAX_NO_VAL", jTextField14.getText());
            }
            if (jTextField15.getText().isEmpty()) {
                profile.setProperty("BC_MINHEIGHT", "");
            } else {
                profile.setProperty("BC_MINHEIGHT", jTextField15.getText());
            }
            if (jTextField16.getText().isEmpty()) {
                profile.setProperty("BC_MAXHEIGHT", "");
            } else {
                profile.setProperty("BC_MAXHEIGHT", jTextField16.getText());
            }
            if (jTextField9.getText().isEmpty()
                    && jTextField10.getText().isEmpty()
                    && jTextField11.getText().isEmpty()
                    && jTextField12.getText().isEmpty()
                    && jTextField13.getText().isEmpty()
                    && jTextField14.getText().isEmpty()
                    && jTextField15.getText().isEmpty()
                    && jTextField16.getText().isEmpty()) {
                profile.setProperty("BC_LIGHTMARGIN", "19");
                profile.setProperty("BC_PERCENT", "100");
                profile.setProperty("BC_SCANDISTBAR", "3");
                profile.setProperty("BC_SCANDISTANCE", "15");
                profile.setProperty("BC_TOLERANCE", "20");
                profile.setProperty("BC_MAX_NO_VAL", "10");
                profile.setProperty("BC_MINHEIGHT", "15");
                profile.setProperty("BC_MAXHEIGHT", "400");
            }
            if (FilesTab_Ext.map.get("BC_INTERLEAVED25").equals("1")) {
                profile.setProperty("BC_INTERLEAVED25", "1");
            } else {
                profile.setProperty("BC_INTERLEAVED25", "0");
            }
            if (FilesTab_Ext.map.get("BC_INDUSTRIE25").equals("1")) {
                profile.setProperty("BC_INDUSTRIE25", "1");
            } else {
                profile.setProperty("BC_INDUSTRIE25", "0");
            }
            if (FilesTab_Ext.map.get("BC_25_IATA").equals("1")) {
                profile.setProperty("BC_25_IATA", "1");
            } else {
                profile.setProperty("BC_25_IATA", "0");
            }
            if (FilesTab_Ext.map.get("BC_25_3MATRIX").equals("1")) {
                profile.setProperty("BC_25_3MATRIX", "1");
            } else {
                profile.setProperty("BC_25_3MATRIX", "0");
            }
            if (FilesTab_Ext.map.get("BC_25_3DATALOGIC").equals("1")) {
                profile.setProperty("BC_25_3DATALOGIC", "1");
            } else {
                profile.setProperty("BC_25_3DATALOGIC", "0");
            }
            if (FilesTab_Ext.map.get("BC_25_BCDMATRIX").equals("1")) {
                profile.setProperty("BC_25_BCDMATRIX", "1");
            } else {
                profile.setProperty("BC_25_BCDMATRIX", "0");
            }
            if (FilesTab_Ext.map.get("BC_25_INVERTIERT").equals("1")) {
                profile.setProperty("BC_25_INVERTIERT", "1");
            } else {
                profile.setProperty("BC_25_INVERTIERT", "0");
            }
            if (FilesTab_Ext.map.get("BC_EAN13").equals("1")) {
                profile.setProperty("BC_EAN13", "1");
            } else {
                profile.setProperty("BC_EAN13", "0");
            }
            if (FilesTab_Ext.map.get("BC_EAN8").equals("1")) {
                profile.setProperty("BC_EAN8", "1");
            } else {
                profile.setProperty("BC_EAN8", "0");
            }
            if (FilesTab_Ext.map.get("BC_UPC_A").equals("1")) {
                profile.setProperty("BC_UPC_A", "1");
            } else {
                profile.setProperty("BC_UPC_A", "0");
            }
            if (FilesTab_Ext.map.get("BC_UPC_E").equals("1")) {
                profile.setProperty("BC_UPC_E", "1");
            } else {
                profile.setProperty("BC_UPC_E", "0");
            }
            if (FilesTab_Ext.map.get("BC_CODE39").equals("1")) {
                profile.setProperty("BC_CODE39", "1");
            } else {
                profile.setProperty("BC_CODE39", "0");
            }
            if (FilesTab_Ext.map.get("BC_CODE39EXT").equals("1")) {
                profile.setProperty("BC_CODE39EXT", "1");
            } else {
                profile.setProperty("BC_CODE39EXT", "0");
            }
            if (FilesTab_Ext.map.get("BC_CODE32").equals("1")) {
                profile.setProperty("BC_CODE32", "1");
            } else {
                profile.setProperty("BC_CODE32", "0");
            }
            if (FilesTab_Ext.map.get("BC_CODE93").equals("1")) {
                profile.setProperty("BC_CODE93", "1");
            } else {
                profile.setProperty("BC_CODE93", "0");
            }
            if (FilesTab_Ext.map.get("BC_CODE93EXT").equals("1")) {
                profile.setProperty("BC_CODE93EXT", "1");
            } else {
                profile.setProperty("BC_CODE93EXT", "0");
            }
            if (FilesTab_Ext.map.get("BC_CODABAR").equals("1")) {
                profile.setProperty("BC_CODE128", "1");
            } else {
                profile.setProperty("BC_CODE128", "0");
            }
            if (FilesTab_Ext.map.get("BC_CODE128").equals("1")) {
                profile.setProperty("BC_CODE128", "1");
            } else {
                profile.setProperty("BC_CODE128", "0");
            }
            if (FilesTab_Ext.map.get("BC_EAN128").equals("1")) {
                profile.setProperty("BC_EAN128", "1");
            } else {
                profile.setProperty("BC_EAN128", "0");
            }
            if (FilesTab_Ext.map.get("BC_CODE11").equals("1")) {
                profile.setProperty("BC_CODE11", "1");
            } else {
                profile.setProperty("BC_CODE11", "0");
            }
            if (FilesTab_Ext.map.get("BC_EAN13").equals("1")) {
                profile.setProperty("BC_EAN13", "1");
            } else {
                profile.setProperty("BC_EAN13", "0");
            }
            if (FilesTab_Ext.map.get("BC_UPC_A").equals("1")) {
                profile.setProperty("BC_UPC_A", "1");
            } else {
                profile.setProperty("BC_UPC_A", "0");
            }
            if (!FilesTab_Ext.map.get("BC_INTERLEAVED25").equals("1")
                    && !FilesTab_Ext.map.get("BC_INDUSTRIE25").equals("1")
                    && !FilesTab_Ext.map.get("BC_25_IATA").equals("1")
                    && !FilesTab_Ext.map.get("BC_25_3MATRIX").equals("1")
                    && !FilesTab_Ext.map.get("BC_25_3DATALOGIC").equals("1")
                    && !FilesTab_Ext.map.get("BC_25_BCDMATRIX").equals("1")
                    && !FilesTab_Ext.map.get("BC_25_INVERTIERT").equals("1")
                    && !FilesTab_Ext.map.get("BC_EAN13").equals("1")
                    && !FilesTab_Ext.map.get("BC_EAN8").equals("1")
                    && !FilesTab_Ext.map.get("BC_UPC_A").equals("1")
                    && !FilesTab_Ext.map.get("BC_UPC_E").equals("1")
                    && !FilesTab_Ext.map.get("BC_CODE39").equals("1")
                    && !FilesTab_Ext.map.get("BC_CODE39EXT").equals("1")
                    && !FilesTab_Ext.map.get("BC_CODE32").equals("1")
                    && !FilesTab_Ext.map.get("BC_CODE93").equals("1")
                    && !FilesTab_Ext.map.get("BC_CODE93EXT").equals("1")
                    && !FilesTab_Ext.map.get("BC_CODABAR").equals("1")
                    && !FilesTab_Ext.map.get("BC_CODE128").equals("1")
                    && !FilesTab_Ext.map.get("BC_EAN128").equals("1")
                    && !FilesTab_Ext.map.get("BC_CODE11").equals("1")
                    && !FilesTab_Ext.map.get("BC_EAN13").equals("1")
                    && !FilesTab_Ext.map.get("BC_UPC_A").equals("1")) {
                profile.setProperty("BC_CODE39", "1");
            }
            if (jRadioButton11.isSelected()) {
                profile.setProperty("BC_SKEW_NONE", "1");
            } else {
                profile.setProperty("BC_SKEW_NONE", "0");
            }
            if (jRadioButton12.isSelected()) {
                profile.setProperty("BC_SKEW_LIGHT", "1");
            } else {
                profile.setProperty("BC_SKEW_LIGHT", "0");
            }
            if (jRadioButton13.isSelected()) {
                profile.setProperty("BC_SKEW_MEDIUM", "1");
            } else {
                profile.setProperty("BC_SKEW_MEDIUM", "0");
            }
            if (jRadioButton14.isSelected()) {
                profile.setProperty("BC_SKEW_HEAVY", "1");
            } else {
                profile.setProperty("BC_SKEW_HEAVY", "0");
            }
            if (jCheckBox1.isSelected()) {
                profile.setProperty("BC_SKEW_DENSE_SEARCH", "1");
            } else {
                profile.setProperty("BC_SKEW_DENSE_SEARCH", "0");
            }
//            if (jTextField6.getText().isEmpty()) {
//                profile.setProperty("ResultFieldPagecount", "");
//            } else {
//                profile.setProperty("ResultFieldPagecount", jTextField6.getText());
//            }
            profile.setProperty("ResultFieldPagecount", (String) jComboBoxFielfPageCount.getSelectedItem());
            profile.setProperty("Action", "ReadBarcode");
            profile.setProperty("BC_NONE", "");
            profile.setProperty("BC_MULTI", "1");
            profile.setProperty("iBC_Checksum", "0");
            profile.setProperty("iBC_Length", "");
            try {
                profile.store(new FileOutputStream("../profiles/profile_" + currentProfileNumber + ".properties"), null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Can not write in folder profiles" + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, globalErrorMessage);
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

    public class KeyAdapterStringOnly extends KeyAdapter {

        private String allowedRegex = "_";

        public void keyReleased(KeyEvent e) {
            String curText = ((JTextComponent) e.getSource()).getText();
            curText = curText.replaceAll(allowedRegex, "");
            ((JTextComponent) e.getSource()).setText(curText);
        }
    }
}
