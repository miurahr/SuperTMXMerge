/*
 * Copyright (C) 2013 Aaron Madlon-Kay <aaron@madlon-kay.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.madlonkay.supertmxmerge.gui;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import org.madlonkay.supertmxmerge.data.MergeInfo;
import org.madlonkay.supertmxmerge.util.LocString;

/**
 *
 * @author Aaron Madlon-Kay <aaron@madlon-kay.com>
 */
public class MergeCell extends javax.swing.JPanel {

    private static Border BORDER_DEFAULT;
    private static Border BORDER_SELECTED;
    
    static {
        JScrollPane sp = new JScrollPane();
        BORDER_DEFAULT = sp.getBorder();
        BORDER_SELECTED = new MatteBorder(2, 2, 2, 2, Color.BLUE);
    }
    
    /**
     * Creates new form TUDiffCell
     */
    public MergeCell(int itemNumber, MergeInfo info) {
        initComponents();
        
        itemNumberLabel.setText(String.valueOf(itemNumber));
        sourceText.setText(info.sourceText);
        sourceText.setRows(sourceText.getLineCount());
        setSourceLanguage(info.sourceLanguage);
        setTargetLanguage(info.targetLanguage);
        setBaseText(info.baseTuvText);
        setLeftText(info.leftTuvText, info.baseTuvText != null);
        setRightText(info.rightTuvText, info.baseTuvText != null);
        tuvTextLeft.addMouseListener(new ClickForwarder(leftButton));
        tuvTextRight.addMouseListener(new ClickForwarder(rightButton));
        tuvTextCenter.addMouseListener(new ClickForwarder(centerButton));
    }
    
    private void setSourceLanguage(String language) {
        TitledBorder sourceBorder = (TitledBorder) jPanel2.getBorder();
        sourceBorder.setTitle(language);
    }
    
    private void setTargetLanguage(String language) {
        TitledBorder targetBorder = (TitledBorder) jPanel1.getBorder();
        targetBorder.setTitle(language);
    }
    
    private void setBaseText(String text) {
        if (text == null) {
            tuvTextCenter.setBackground(getBackground());
            text = LocString.get("tuv_not_present");
        }
        tuvTextCenter.setText(text);
    }
    
    private void setLeftText(String text, boolean presentInBase) {
        if (text == null) {
            tuvTextLeft.setBackground(getBackground());
            text = presentInBase? LocString.get("tuv_deleted") : LocString.get("tuv_not_present");
        }
        tuvTextLeft.setText(text);
    }
    
    private void setRightText(String text, boolean presentInBase) {
        if (text == null) {
            tuvTextRight.setBackground(getBackground());
            text = presentInBase? LocString.get("tuv_deleted") : LocString.get("tuv_not_present");
        }
        tuvTextRight.setText(text);
    }
    
    public JRadioButton getLeftButton() {
        return leftButton;
    }
    
    public JRadioButton getRightButton() {
        return rightButton;
    }
    
    public JRadioButton getCenterButton() {
        return centerButton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        buttonGroup2 = new javax.swing.ButtonGroup();
        borderConverter1 = new BorderConverter(BORDER_SELECTED, BORDER_DEFAULT);
        leftButton = new javax.swing.JRadioButton();
        centerButton = new javax.swing.JRadioButton();
        rightButton = new javax.swing.JRadioButton();
        itemNumberLabel = new javax.swing.JLabel();
        sourceTargetPane = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        sourceScrollPane = new javax.swing.JScrollPane();
        sourceText = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        targetTargetPane = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tuvTextLeft = new javax.swing.JTextArea();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tuvTextCenter = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        tuvTextRight = new javax.swing.JTextArea();

        buttonGroup2.add(leftButton);
        leftButton.setContentAreaFilled(false);
        leftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftButtonActionPerformed(evt);
            }
        });

        buttonGroup2.add(centerButton);
        centerButton.setContentAreaFilled(false);
        centerButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonGroup2.add(rightButton);
        rightButton.setContentAreaFilled(false);

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setLayout(new java.awt.BorderLayout());

        itemNumberLabel.setText("n");
        itemNumberLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        add(itemNumberLabel, java.awt.BorderLayout.WEST);

        sourceTargetPane.setBorder(null);
        sourceTargetPane.setDividerSize(0);
        sourceTargetPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        sourceTargetPane.setResizeWeight(0.5);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Source Language"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        sourceScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        sourceText.setEditable(false);
        sourceText.setColumns(40);
        sourceText.setLineWrap(true);
        sourceText.setRows(2);
        sourceText.setText("Source text");
        sourceText.setWrapStyleWord(true);
        sourceScrollPane.setViewportView(sourceText);

        jPanel2.add(sourceScrollPane, java.awt.BorderLayout.CENTER);

        sourceTargetPane.setTopComponent(jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Target Language"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        targetTargetPane.setBorder(null);
        targetTargetPane.setDividerSize(0);
        targetTargetPane.setResizeWeight(0.33);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, leftButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), jScrollPane1, org.jdesktop.beansbinding.BeanProperty.create("border"), "tuvTextLeftBorder");
        binding.setConverter(borderConverter1);
        bindingGroup.addBinding(binding);

        tuvTextLeft.setEditable(false);
        tuvTextLeft.setLineWrap(true);
        tuvTextLeft.setText("Target text 1");
        tuvTextLeft.setWrapStyleWord(true);
        jScrollPane1.setViewportView(tuvTextLeft);

        targetTargetPane.setLeftComponent(jScrollPane1);

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerSize(0);
        jSplitPane1.setResizeWeight(0.5);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, centerButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), jScrollPane2, org.jdesktop.beansbinding.BeanProperty.create("border"), "tuvTextCenterBorder");
        binding.setConverter(borderConverter1);
        bindingGroup.addBinding(binding);

        tuvTextCenter.setEditable(false);
        tuvTextCenter.setLineWrap(true);
        tuvTextCenter.setText("Target text 2");
        tuvTextCenter.setWrapStyleWord(true);
        jScrollPane2.setViewportView(tuvTextCenter);

        jSplitPane1.setLeftComponent(jScrollPane2);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, rightButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), jScrollPane5, org.jdesktop.beansbinding.BeanProperty.create("border"), "tuvTextRightBorder");
        binding.setConverter(borderConverter1);
        bindingGroup.addBinding(binding);

        tuvTextRight.setEditable(false);
        tuvTextRight.setLineWrap(true);
        tuvTextRight.setText("Target text 2");
        tuvTextRight.setWrapStyleWord(true);
        jScrollPane5.setViewportView(tuvTextRight);

        jSplitPane1.setRightComponent(jScrollPane5);

        targetTargetPane.setRightComponent(jSplitPane1);

        jPanel1.add(targetTargetPane, java.awt.BorderLayout.CENTER);

        sourceTargetPane.setRightComponent(jPanel1);

        add(sourceTargetPane, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void leftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leftButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.madlonkay.supertmxmerge.gui.BorderConverter borderConverter1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton centerButton;
    private javax.swing.JLabel itemNumberLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JRadioButton leftButton;
    private javax.swing.JRadioButton rightButton;
    private javax.swing.JScrollPane sourceScrollPane;
    private javax.swing.JSplitPane sourceTargetPane;
    private javax.swing.JTextArea sourceText;
    private javax.swing.JSplitPane targetTargetPane;
    private javax.swing.JTextArea tuvTextCenter;
    private javax.swing.JTextArea tuvTextLeft;
    private javax.swing.JTextArea tuvTextRight;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
