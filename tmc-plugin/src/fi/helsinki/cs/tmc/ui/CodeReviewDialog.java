package fi.helsinki.cs.tmc.ui;

import fi.helsinki.cs.tmc.core.domain.Review;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.openide.awt.HtmlBrowser;

public class CodeReviewDialog extends javax.swing.JDialog {
    private static final Logger log = Logger.getLogger(CodeReviewDialog.class.getName());
    
    private Review review;
    private ActionListener okListener;
    
    public CodeReviewDialog(Review review) {
        this.review = review;
        this.okListener = null;
        
        initComponents();
        
        // Set location according to screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - (this.getWidth() / 2), screenSize.height / 2 - (this.getHeight() / 2));
        
        
        this.setLocationByPlatform(true);
        
        this.setTitle("Code review - " + review.getExerciseName());
        titleLabel.setText("Code review - " + review.getExerciseName());
        reviewedByLabel.setText("Reviewed by " + review.getReviewerName());
        reviewArea.setText(review.getReviewBody());
        markAsReadCheckBox.setSelected(true);
        pointsLabel.setText(getPointsAwardedText(review));
    }
    
    public void setOkListener(ActionListener okListener) {
        this.okListener = okListener;
    }
    
    public boolean getMarkAsRead() {
        return markAsReadCheckBox.isSelected();
    }
    
    private String getPointsAwardedText(Review review) {
        StringBuilder sb = new StringBuilder();
        if (!review.getPoints().isEmpty()) {
            sb.append("Points awarded: ").append(StringUtils.join(review.getPoints(), ", "));
            if (!review.getPointsNotAwarded().isEmpty()) {
                sb.append(";  not awarded: ").append(StringUtils.join(review.getPointsNotAwarded(), ", "));
            }
        }
        return sb.toString();
    }
    
    private void openInBrowser() {
        try {
            HtmlBrowser.URLDisplayer.getDefault().showURLExternal(review.getUrl().toURL());
        } catch (MalformedURLException ex) {
            log.log(Level.WARNING, "Malformed URL: " + ex.getMessage(), ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pointsLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        openInBrowserButton = new javax.swing.JButton();
        reviewedByLabel = new javax.swing.JLabel();
        markAsReadCheckBox = new javax.swing.JCheckBox();
        reviewScrollPane = new javax.swing.JScrollPane();
        reviewArea = new javax.swing.JTextArea();
        titleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);

        org.openide.awt.Mnemonics.setLocalizedText(pointsLabel, org.openide.util.NbBundle.getMessage(CodeReviewDialog.class, "CodeReviewDialog.pointsLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(okButton, org.openide.util.NbBundle.getMessage(CodeReviewDialog.class, "CodeReviewDialog.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(openInBrowserButton, org.openide.util.NbBundle.getMessage(CodeReviewDialog.class, "CodeReviewDialog.openInBrowserButton.text")); // NOI18N
        openInBrowserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openInBrowserButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(reviewedByLabel, org.openide.util.NbBundle.getMessage(CodeReviewDialog.class, "CodeReviewDialog.reviewedByLabel.text")); // NOI18N

        markAsReadCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(markAsReadCheckBox, org.openide.util.NbBundle.getMessage(CodeReviewDialog.class, "CodeReviewDialog.markAsReadCheckBox.text")); // NOI18N

        reviewArea.setEditable(false);
        reviewArea.setColumns(20);
        reviewArea.setRows(5);
        reviewScrollPane.setViewportView(reviewArea);

        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getStyle() | java.awt.Font.BOLD, titleLabel.getFont().getSize()+1));
        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(CodeReviewDialog.class, "CodeReviewDialog.titleLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reviewScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(reviewedByLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                        .addComponent(markAsReadCheckBox))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pointsLabel)
                            .addComponent(titleLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(openInBrowserButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reviewedByLabel)
                    .addComponent(markAsReadCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reviewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pointsLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openInBrowserButton)
                    .addComponent(okButton))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (okListener != null) {
            okListener.actionPerformed(evt);
        }
        this.setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void openInBrowserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openInBrowserButtonActionPerformed
        openInBrowser();
    }//GEN-LAST:event_openInBrowserButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox markAsReadCheckBox;
    private javax.swing.JButton okButton;
    private javax.swing.JButton openInBrowserButton;
    private javax.swing.JLabel pointsLabel;
    private javax.swing.JTextArea reviewArea;
    private javax.swing.JScrollPane reviewScrollPane;
    private javax.swing.JLabel reviewedByLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

}
