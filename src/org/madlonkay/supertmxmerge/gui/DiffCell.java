/*
 * Copyright (C) 2013 Aaron Madlon-Kay <aaron@madlon-kay.com>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.madlonkay.supertmxmerge.gui;

import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import org.madlonkay.supertmxmerge.DiffController.DiffInfo;
import org.madlonkay.supertmxmerge.util.GuiUtil;
import org.madlonkay.supertmxmerge.util.LocString;

/**
 *
 * @author Aaron Madlon-Kay <aaron@madlon-kay.com>
 */
public class DiffCell extends javax.swing.JPanel {

     private final static MapToTextConverter CONVERTER = new MapToTextConverter();
    
     private final JScrollPane scrollTarget;
     
    /**
     * Creates new form TUDiffCell
     * @param itemNumber
     * @param info
     * @param scrollTarget
     */
    public DiffCell(int itemNumber, DiffInfo info, JScrollPane scrollTarget) {
        initComponents();
        
        this.scrollTarget = scrollTarget;
        
        itemNumberLabel.setText(String.valueOf(itemNumber));
        if (info.key.props != null) {
            setToolTipText((String) CONVERTER.convertForward(info.key.props));
        }
        setSourceLanguage(info.sourceLanguage);
        setTargetLanguage(info.targetLanguage);
        setTextWithFallback(sourceText, info.key.sourceText, "STM_TUV_NOT_PRESENT", null);
        setTextWithFallback(tuvText1, info.tuv1Text, "STM_TUV_NOT_PRESENT", info.tuv1Props);
        setTextWithFallback(tuvText2, info.tuv2Text, "STM_TUV_DELETED", info.tuv2Props);
        info.diff.applyStyling(tuvText1, tuvText2);
    }
    
    private void setSourceLanguage(String language) {
        TitledBorder sourceBorder = (TitledBorder) sourceTextPanel.getBorder();
        sourceBorder.setTitle(language);
    }
    
    private void setTargetLanguage(String language) {
        TitledBorder targetBorder = (TitledBorder) targetTextsPanel.getBorder();
        targetBorder.setTitle(language);
    }
    
    private void setTextWithFallback(JTextComponent field, String text,
            String fallbackKey, Map<String, String> props) {
        if (text == null) {
            field.setBackground(getBackground());
            text = LocString.get(fallbackKey);
        }
        if (props != null) {
            field.setToolTipText((String) CONVERTER.convertForward(props));
        }
        field.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        itemNumberLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        sourceTextPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        sourceText = new javax.swing.JTextArea();
        targetTextsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tuvText1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tuvText2 = new javax.swing.JTextPane();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setLayout(new java.awt.BorderLayout());

        itemNumberLabel.setText("n");
        itemNumberLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        add(itemNumberLabel, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        sourceTextPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Source Language"));
        sourceTextPanel.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                forwardScrollEvent(evt);
            }
        });

        sourceText.setEditable(false);
        sourceText.setColumns(72);
        sourceText.setFont(UIManager.getDefaults().getFont("Label.font"));
        sourceText.setLineWrap(true);
        sourceText.setText("Source text");
        sourceText.setWrapStyleWord(true);
        jScrollPane3.setViewportView(sourceText);

        sourceTextPanel.add(jScrollPane3);

        jPanel2.add(sourceTextPanel, java.awt.BorderLayout.NORTH);

        targetTextsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Target Language"));
        targetTextsPanel.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                forwardScrollEvent(evt);
            }
        });

        tuvText1.setEditable(false);
        tuvText1.setFont(UIManager.getDefaults().getFont("Label.font"));
        tuvText1.setText("Target text 1");
        jScrollPane1.setViewportView(tuvText1);

        targetTextsPanel.add(jScrollPane1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                forwardScrollEvent(evt);
            }
        });

        tuvText2.setEditable(false);
        tuvText2.setFont(UIManager.getDefaults().getFont("Label.font"));
        tuvText2.setText("Target text 2");
        jScrollPane2.setViewportView(tuvText2);

        targetTextsPanel.add(jScrollPane2);

        jPanel2.add(targetTextsPanel, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void forwardScrollEvent(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_forwardScrollEvent
        GuiUtil.forwardMouseWheelEvent(scrollTarget, evt);
    }//GEN-LAST:event_forwardScrollEvent

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel itemNumberLabel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea sourceText;
    private javax.swing.JPanel sourceTextPanel;
    private javax.swing.JPanel targetTextsPanel;
    private javax.swing.JTextPane tuvText1;
    private javax.swing.JTextPane tuvText2;
    // End of variables declaration//GEN-END:variables

}
