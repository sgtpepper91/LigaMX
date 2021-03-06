package ligamx.carga;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author hecto
 */
public class SeleccionArchivoCarga extends javax.swing.JPanel {
    private File archivo;
    /**
     * Creates new form SeleccionArchivoCarga
     */
    public SeleccionArchivoCarga() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcArchivoCarga = new javax.swing.JFileChooser();
        lblNombreArchivo = new javax.swing.JLabel();
        txtNombreArchivo = new javax.swing.JTextField();
        btnExaminar = new javax.swing.JButton();

        lblNombreArchivo.setText("Nombre:");

        txtNombreArchivo.setEditable(false);

        btnExaminar.setText("Examinar");
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombreArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnExaminar))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreArchivo)
                    .addComponent(txtNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExaminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarActionPerformed
        int returnVal = fcArchivoCarga.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
        archivo = fcArchivoCarga.getSelectedFile();
        }
        txtNombreArchivo.setText(archivo.getName());
    }//GEN-LAST:event_btnExaminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExaminar;
    private javax.swing.JFileChooser fcArchivoCarga;
    private javax.swing.JLabel lblNombreArchivo;
    private javax.swing.JTextField txtNombreArchivo;
    // End of variables declaration//GEN-END:variables

    public File getArchivo() {
        return archivo;
    }
}
