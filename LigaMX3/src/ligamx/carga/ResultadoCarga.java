package ligamx.carga;

import javax.swing.JTextArea;

/**
 *
 * @author hecto
 */
public class ResultadoCarga extends javax.swing.JPanel {

    /**
     * Creates new form ResultadoCarga
     */
    public ResultadoCarga() {
        initComponents();
    }

    public JTextArea getTxtCorrectos() {
        return txtCorrectos;
    }

    public JTextArea getTxtIncorrectosPrev() {
        return txtIncorrectosPrev;
    }

    public JTextArea getTxtIncorrectosVal() {
        return txtIncorrectosVal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panResultados = new javax.swing.JTabbedPane();
        panIncorrectosPrev = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtIncorrectosPrev = new javax.swing.JTextArea();
        panIncorrectosVal = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtIncorrectosVal = new javax.swing.JTextArea();
        panCorrectos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCorrectos = new javax.swing.JTextArea();

        txtIncorrectosPrev.setEditable(false);
        txtIncorrectosPrev.setColumns(20);
        txtIncorrectosPrev.setRows(5);
        jScrollPane1.setViewportView(txtIncorrectosPrev);

        javax.swing.GroupLayout panIncorrectosPrevLayout = new javax.swing.GroupLayout(panIncorrectosPrev);
        panIncorrectosPrev.setLayout(panIncorrectosPrevLayout);
        panIncorrectosPrevLayout.setHorizontalGroup(
            panIncorrectosPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panIncorrectosPrevLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        panIncorrectosPrevLayout.setVerticalGroup(
            panIncorrectosPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panIncorrectosPrevLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panResultados.addTab("Incorrectos Prevalidación", panIncorrectosPrev);

        txtIncorrectosVal.setEditable(false);
        txtIncorrectosVal.setColumns(20);
        txtIncorrectosVal.setRows(5);
        jScrollPane2.setViewportView(txtIncorrectosVal);

        javax.swing.GroupLayout panIncorrectosValLayout = new javax.swing.GroupLayout(panIncorrectosVal);
        panIncorrectosVal.setLayout(panIncorrectosValLayout);
        panIncorrectosValLayout.setHorizontalGroup(
            panIncorrectosValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panIncorrectosValLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        panIncorrectosValLayout.setVerticalGroup(
            panIncorrectosValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panIncorrectosValLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panResultados.addTab("Incorrectos Validación", panIncorrectosVal);

        txtCorrectos.setEditable(false);
        txtCorrectos.setColumns(20);
        txtCorrectos.setRows(5);
        jScrollPane3.setViewportView(txtCorrectos);

        javax.swing.GroupLayout panCorrectosLayout = new javax.swing.GroupLayout(panCorrectos);
        panCorrectos.setLayout(panCorrectosLayout);
        panCorrectosLayout.setHorizontalGroup(
            panCorrectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCorrectosLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        panCorrectosLayout.setVerticalGroup(
            panCorrectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCorrectosLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panResultados.addTab("Correctos", panCorrectos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panResultados)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panResultados)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panCorrectos;
    private javax.swing.JPanel panIncorrectosPrev;
    private javax.swing.JPanel panIncorrectosVal;
    private javax.swing.JTabbedPane panResultados;
    private javax.swing.JTextArea txtCorrectos;
    private javax.swing.JTextArea txtIncorrectosPrev;
    private javax.swing.JTextArea txtIncorrectosVal;
    // End of variables declaration//GEN-END:variables
}
