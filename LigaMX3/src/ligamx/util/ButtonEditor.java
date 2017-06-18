package ligamx.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import ligamx.controller.LigaMXController;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PartidoDTO;
import ligamx.service.EquipoService;
import ligamx.service.PartidoService;
import ligamx.service.impl.EquipoServiceImpl;
import ligamx.service.impl.PartidoServiceImpl;

/**
 *
 * @author hector.lopez
 */
public class ButtonEditor extends DefaultCellEditor {

    private static final long serialVersionUID = 1L;

    protected JButton button;
    private String label;
    private boolean isPushed;
    private int row, column;
    private JTable table;
    private final LigaMXController ligaMXController;
    private final PartidoService partidoService;
    private final EquipoService equipoService;

    public ButtonEditor(JCheckBox checkBox, LigaMXController ligaMXController) {
        super(checkBox);
        this.ligaMXController = ligaMXController;
        partidoService = new PartidoServiceImpl();
        equipoService = new EquipoServiceImpl();
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener((ActionEvent e) -> {
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "+" : value.toString();
        button.setText(label);
        isPushed = true;
        this.row = row;
        this.column = column;
        this.table = table;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            try {
                String equipo1 = table.getValueAt(row, 1).toString();
                String equipo2 = table.getValueAt(row, 4).toString();
                EquipoDTO equipoDTO1 = equipoService.buscarEquipoporNombre(equipo1);
                EquipoDTO equipoDTO2 = equipoService.buscarEquipoporNombre(equipo2);
                PartidoDTO partidoDTO = partidoService.buscarPartidoporEquipos(equipoDTO1.getIdEquipo(), equipoDTO2.getIdEquipo());
                if (column == 8) {
                    Object ml = table.getValueAt(row, 2);
                    
                    if (ml == null) {
                        int opc = JOptionPane.showConfirmDialog(null, equipo1 + "-" + equipo2, "¿Desea iniciar este partido?", JOptionPane.OK_CANCEL_OPTION);
                        if (opc == JOptionPane.OK_OPTION) {
                            table.setValueAt(0, row, 2);
                            table.setValueAt(0, row, 3);
                            partidoDTO.setMl(0);
                            partidoDTO.setMv(0);
                            partidoService.actualizarPartido(partidoDTO);
                            equipoService.actualizarEquipo(partidoDTO.getIdPartido());
                            ligaMXController.ActualizarTabla();
                            ligaMXController.BuscarEquipo();
                            ligaMXController.tablaGoleadores();
                            ligaMXController.detalles(partidoDTO);
                        }
                    } else {
                            ligaMXController.nuevoGol(equipo1, equipo2, partidoDTO.getIdPartido(), row);
                            ligaMXController.getjGol().pack();
                            ligaMXController.getjGol().setVisible(true);
                            ligaMXController.setEnabled(false);
                    }
                } else {
                    ligaMXController.detalles(partidoDTO);
                }
            } catch (ExcepcionAplicacion ex) {
                Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
