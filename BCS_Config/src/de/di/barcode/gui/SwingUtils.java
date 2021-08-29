package de.di.barcode.gui;

import de.elo.ix.client.DocMask;
import de.elo.ix.client.DocMaskLine;
import java.util.List;
import javax.swing.JComboBox;

public class SwingUtils {

    public static boolean setItems(JComboBox comboBox, List<DocMask> masks, String selectedValue) {
        if (comboBox == null || masks == null) {
            if (comboBox != null) {
                comboBox.removeAllItems();

            }
            return false;
        }
        comboBox.removeAllItems();
        boolean found = false;
        for (DocMask docMask : masks) {
            comboBox.addItem(docMask.getName());
            if (selectedValue != null && docMask.getName().equals(selectedValue)) {
                comboBox.setSelectedItem(selectedValue);
                found = true;
            }
        }
        if (!found) {
            comboBox.addItem(selectedValue);
            comboBox.setSelectedItem(selectedValue);
            return false;
        }
        return true;
    }

    public static boolean setItems(JComboBox comboBox, DocMask mask, String selectedValue) {
        if (comboBox == null || mask == null) {
            if (comboBox != null) {
                comboBox.removeAllItems();
                comboBox.addItem(selectedValue);
                comboBox.setSelectedItem(selectedValue);
            }
            return false;
        }
        boolean found = false;
        comboBox.removeAllItems();
        for (DocMaskLine docMaskLine : mask.getLines()) {
            comboBox.addItem(docMaskLine.getKey());
            if (selectedValue != null && docMaskLine.getKey().equals(selectedValue)) {
                comboBox.setSelectedItem(selectedValue);
                found = true;
            }
        }
        if (!found) {
            comboBox.addItem(selectedValue);
            comboBox.setSelectedItem(selectedValue);
            return false;
        }
        return true;
    }
    
    // SL
    public static boolean setItems_2(JComboBox comboBox, String[] masks, String selectedValue) {
        if (comboBox == null || masks == null) {
            if (comboBox != null) {
                comboBox.removeAllItems();

            }
            return false;
        }
        comboBox.removeAllItems();
        boolean found = false;
        for (String mask : masks) {
            comboBox.addItem(mask);
            if (selectedValue != null && mask.equals(selectedValue)) {
                comboBox.setSelectedItem(selectedValue);
                found = true;
            }
        }
        if (!found) {
            comboBox.addItem(selectedValue);
            comboBox.setSelectedItem(selectedValue);
            return false;
        }
        return true;
    }
    
    // SL
    public static boolean setItems_3(JComboBox comboBox, String groupNames, String selectedValue) {
        if (comboBox == null || groupNames == null) {
            if (comboBox != null) {
                comboBox.removeAllItems();
                comboBox.addItem(selectedValue);
                comboBox.setSelectedItem(selectedValue);
            }
            return false;
        }
        boolean found = false;
        comboBox.removeAllItems();
        String[] lines = groupNames.split(",");
        for (String docMaskLine : lines) {
            comboBox.addItem(docMaskLine);
            if (selectedValue != null && docMaskLine.equals(selectedValue)) {
                comboBox.setSelectedItem(selectedValue);
                found = true;
            }
        }
        if (!found) {
            comboBox.addItem(selectedValue);
            comboBox.setSelectedItem(selectedValue);
            return false;
        }
        return true;
    }
}
