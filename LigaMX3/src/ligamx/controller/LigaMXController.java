package ligamx.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import ligamx.dto.CocienteDTO;
import ligamx.dto.EquipoDTO;
import ligamx.dto.GolDTO;
import ligamx.dto.GoleadorDTO;
import ligamx.dto.JugadorDTO;
import ligamx.dto.PartidoDTO;
import ligamx.dto.PosicionDTO;
import ligamx.service.CocienteService;
import ligamx.service.EquipoService;
import ligamx.service.GolService;
import ligamx.service.JugadorService;
import ligamx.service.PartidoService;
import ligamx.service.PosicionService;
import ligamx.service.impl.CocienteServiceImpl;
import ligamx.service.impl.EquipoServiceImpl;
import ligamx.service.impl.GolServiceImpl;
import ligamx.service.impl.JugadorServiceImpl;
import ligamx.service.impl.PartidoServiceImpl;
import ligamx.service.impl.PosicionServiceImpl;
import ligamx.util.BaseController;
import ligamx.util.ButtonEditor;
import ligamx.util.ButtonRenderer;
import ligamx.util.Constantes;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author Administrator
 */
public class LigaMXController extends BaseController {
    private PantallaCarga pantallaCarga;
    private final EquipoService equipoService;
    private final PartidoService partidoService;
    private final GolService golService;
    private final JugadorService jugadorService;
    private final CocienteService cocienteService;
    private final PosicionService posicionService;
    private final JugadorController jugadorController;
    private int maximo = 0;
    private boolean cambio = false;
    private String equipo1, equipo2;
    private int idpartido, row;

    /**
     * Creates new form LIGAMX
     */
    public LigaMXController() {
        initComponents();
        setIcono();
        equipoService = new EquipoServiceImpl();
        partidoService = new PartidoServiceImpl();
        golService = new GolServiceImpl();
        jugadorService = new JugadorServiceImpl();
        cocienteService = new CocienteServiceImpl();
        posicionService = new PosicionServiceImpl();
        jugadorController = new JugadorController();
        try {
            ActualizarGeneral();
            cJornada1.setSelectedIndex(maximo - 1);
            BuscarActualiza();
            BuscarEquipo();
            tablaGoleadores();
            /*Cuartos();
            CalcularCuartos();
            Semi();
            CalcularSemi();
            
            Final();
            CalcularFinal();*/

 /*tablaCuartos.getModel().addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e){
            System.out.println("cuartos 1");
            CalcularCuartos();
            System.out.println("cuartos 2");
            }
            });*/
        } catch (ExcepcionAplicacion ex) {
            lanzarExcepcion(ex);
        }
    }

    public final void BuscarEquipo() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar equipo");
        }
        String equipoString = cLocal1.getSelectedItem().toString();
        String url = "ligamx/resources/images/" + equipoString + "1.png";
        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(url)));
        icon.setImage(icon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
        lblImagen.setIcon(icon);
        int fila = 0;
        for (int f = 0; f < 17; f++) {
            for (int columna = 0; columna < 5; columna++) {
                tablaEquipo.setValueAt(null, f, columna);
            }
        }
        EquipoDTO equipoDTO = equipoService.buscarEquipoporNombre(equipoString);
        List<PartidoDTO> partidoDTOs = partidoService.buscarPartidosporEquipo(equipoDTO);
        for (PartidoDTO partidoDTO : partidoDTOs) {
            tablaEquipo.setValueAt(partidoDTO.getJornada(), fila, 0);
            EquipoDTO local = equipoService.buscarEquipoporId(partidoDTO.getIdLocal());
            tablaEquipo.setValueAt(local.getNombre(), fila, 1);
            tablaEquipo.setValueAt(partidoDTO.getMl(), fila, 2);
            tablaEquipo.setValueAt(partidoDTO.getMv(), fila, 3);
            EquipoDTO visitante = equipoService.buscarEquipoporId(partidoDTO.getIdVisitante());
            tablaEquipo.setValueAt(visitante.getNombre(), fila, 4);
            fila++;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jGol = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        jrEquipo1 = new javax.swing.JRadioButton();
        jrEquipo2 = new javax.swing.JRadioButton();
        cAutogol = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        lJugadores = new javax.swing.JList<>();
        sMinuto = new javax.swing.JSlider();
        jLabel12 = new javax.swing.JLabel();
        jtMinuto = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        nomJugBuscar = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        panGlobal = new javax.swing.JPanel();
        panPrincipal = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaGeneral = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaGeneral1 = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tGoleadores = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEquipo = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cLocal1 = new javax.swing.JComboBox();
        lblImagen = new javax.swing.JLabel();
        panMarcador = new javax.swing.JPanel();
        cJornada1 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tablaJornada1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        panSecundario = new javax.swing.JTabbedPane();
        panDescenso = new javax.swing.JScrollPane();
        tablaCociente = new javax.swing.JTable();
        panLiguilla = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaCuartos = new javax.swing.JTable();
        btnCalcularCuartos = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaSemi = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaPosCuartos = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaPosSemi = new javax.swing.JTable();
        btnCalcularSemi = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaFinal = new javax.swing.JTable();
        btnCalcularFinal = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Penales = new javax.swing.JPanel();
        lblLocal = new javax.swing.JLabel();
        lblVisit = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        menuBar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        menuAgrega = new javax.swing.JMenuItem();
        jmGrafica = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        ACuartos = new javax.swing.JMenuItem();
        aSemi = new javax.swing.JMenuItem();
        aFinal = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();

        jGol.setTitle("Gol ");
        jGol.setIconImage(getIconImage());
        jGol.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jGolWindowClosing(evt);
            }
        });
        jGol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jGolKeyTyped(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(51, 255, 255));
        jPanel9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel9KeyTyped(evt);
            }
        });

        buttonGroup1.add(jrEquipo1);
        jrEquipo1.setText("Equipo 1");
        jrEquipo1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jrEquipo1StateChanged(evt);
            }
        });

        buttonGroup1.add(jrEquipo2);
        jrEquipo2.setText("Equipo 2");
        jrEquipo2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jrEquipo2StateChanged(evt);
            }
        });

        cAutogol.setText("Autogol");
        cAutogol.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cAutogolStateChanged(evt);
            }
        });

        jLabel11.setText("Jugador");

        lJugadores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lJugadores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lJugadoresKeyTyped(evt);
            }
        });
        jScrollPane12.setViewportView(lJugadores);

        sMinuto.setMajorTickSpacing(10);
        sMinuto.setMaximum(90);
        sMinuto.setMinorTickSpacing(5);
        sMinuto.setPaintLabels(true);
        sMinuto.setPaintTicks(true);
        sMinuto.setValue(1);
        sMinuto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sMinutoStateChanged(evt);
            }
        });

        jLabel12.setText("Minuto");

        jtMinuto.setEditable(false);
        jtMinuto.setText("1");

        jButton2.setText("Nuevo Jugador");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        nomJugBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomJugBuscarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrEquipo2)
                                    .addComponent(jrEquipo1)
                                    .addComponent(cAutogol)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton2)
                                .addGap(26, 26, 26)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sMinuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(nomJugBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jrEquipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrEquipo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cAutogol)
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(sMinuto, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(nomJugBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)))
        );

        javax.swing.GroupLayout jGolLayout = new javax.swing.GroupLayout(jGol.getContentPane());
        jGol.getContentPane().setLayout(jGolLayout);
        jGolLayout.setHorizontalGroup(
            jGolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jGolLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jGolLayout.setVerticalGroup(
            jGolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jGolLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LIGA MX C17");
        setExtendedState(6);
        setFocusCycleRoot(false);
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane5.setAutoscrolls(true);
        jScrollPane5.setPreferredSize(new java.awt.Dimension(1900, 990));

        panGlobal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panGlobal.setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        tablaGeneral.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        tablaGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Pos", "", "Equipo", "JJ", "JG", "JE", "JP", "GF", "GC", "DG", "PTS", "Clas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, javax.swing.ImageIcon.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaGeneral.setRowHeight(17);
        tablaGeneral.setName("tablaGeneral"); // NOI18N
        tablaGeneral.getTableHeader().setReorderingAllowed(false);
        tablaGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaGeneralMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaGeneral);
        tablaGeneral.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablaGeneral.getColumnModel().getColumn(0).setPreferredWidth(35);
        tablaGeneral.getColumnModel().getColumn(1).setPreferredWidth(17);
        tablaGeneral.getColumnModel().getColumn(2).setPreferredWidth(203);
        tablaGeneral.getColumnModel().getColumn(3).setPreferredWidth(53);
        tablaGeneral.getColumnModel().getColumn(4).setPreferredWidth(53);
        tablaGeneral.getColumnModel().getColumn(5).setPreferredWidth(53);
        tablaGeneral.getColumnModel().getColumn(6).setPreferredWidth(53);
        tablaGeneral.getColumnModel().getColumn(7).setPreferredWidth(53);
        tablaGeneral.getColumnModel().getColumn(8).setPreferredWidth(53);
        tablaGeneral.getColumnModel().getColumn(9).setPreferredWidth(53);
        tablaGeneral.getColumnModel().getColumn(10).setPreferredWidth(53);
        tablaGeneral.getColumnModel().getColumn(11).setPreferredWidth(53);
        tablaGeneral.getColumnModel().getColumn(0).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(1).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(2).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(3).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(4).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(5).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(6).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(7).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(8).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(9).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(10).setResizable(false);
        tablaGeneral.getColumnModel().getColumn(11).setResizable(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tablaGeneral.setDefaultRenderer(Integer.class, centerRenderer);
        tablaGeneral.setDefaultRenderer(String.class, centerRenderer);

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tablaGeneral.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tablaGeneral.getColumnModel().getColumn(11).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                //Cells are by default rendered as a JLabel.
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                if (value.toString().equals("☑")) {
                    l.setForeground(Color.GREEN);
                } else {
                    l.setForeground(Color.RED);
                }
                //Return the JLabel which renders the cell.
                return l;
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
        );

        panPrincipal.addTab("Tabla General", jPanel1);

        tablaGeneral1.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        tablaGeneral1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Posición", "Equipo", "JJ", "JG", "JE", "JP", "GF", "GC", "DG", "PTS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaGeneral1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaGeneral1.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tablaGeneral1);
        if (tablaGeneral1.getColumnModel().getColumnCount() > 0) {
            tablaGeneral1.getColumnModel().getColumn(0).setResizable(false);
            tablaGeneral1.getColumnModel().getColumn(1).setResizable(false);
            tablaGeneral1.getColumnModel().getColumn(2).setResizable(false);
            tablaGeneral1.getColumnModel().getColumn(3).setResizable(false);
            tablaGeneral1.getColumnModel().getColumn(4).setResizable(false);
            tablaGeneral1.getColumnModel().getColumn(5).setResizable(false);
            tablaGeneral1.getColumnModel().getColumn(6).setResizable(false);
            tablaGeneral1.getColumnModel().getColumn(7).setResizable(false);
            tablaGeneral1.getColumnModel().getColumn(8).setResizable(false);
            tablaGeneral1.getColumnModel().getColumn(9).setResizable(false);
        }

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Local");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Visitante");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panPrincipal.addTab("Estadisticas", jPanel4);

        tGoleadores.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        tGoleadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Equipo", "Jugador", "Goles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tGoleadores.getTableHeader().setReorderingAllowed(false);
        jScrollPane14.setViewportView(tGoleadores);
        tGoleadores.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (tGoleadores.getColumnModel().getColumnCount() > 0) {
            tGoleadores.getColumnModel().getColumn(0).setResizable(false);
            tGoleadores.getColumnModel().getColumn(0).setPreferredWidth(200);
            tGoleadores.getColumnModel().getColumn(1).setResizable(false);
            tGoleadores.getColumnModel().getColumn(1).setPreferredWidth(400);
            tGoleadores.getColumnModel().getColumn(2).setResizable(false);
            tGoleadores.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panPrincipal.addTab("Goleadores", jPanel5);

        tablaEquipo.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        tablaEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Jornada", "Local", "ML", "MV", "Visitante"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEquipo.setPreferredSize(new java.awt.Dimension(625, 272));
        jScrollPane2.setViewportView(tablaEquipo);
        if (tablaEquipo.getColumnModel().getColumnCount() > 0) {
            tablaEquipo.getColumnModel().getColumn(0).setResizable(false);
            tablaEquipo.getColumnModel().getColumn(1).setResizable(false);
            tablaEquipo.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablaEquipo.getColumnModel().getColumn(3).setResizable(false);
            tablaEquipo.getColumnModel().getColumn(4).setResizable(false);
            tablaEquipo.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        jLabel2.setText("Equipo");

        cLocal1.setModel(new javax.swing.DefaultComboBoxModel(Constantes.EQUIPOS));
        cLocal1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cLocal1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cLocal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cLocal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panPrincipal.addTab("Calendario por Equipos", jPanel2);

        cJornada1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17" }));
        cJornada1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cJornada1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Jornada");

        tablaJornada1.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        tablaJornada1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, "+", "+"},
                {null, null, null, null, null, null, "+", "+"},
                {null, null, null, null, null, null, "+", "+"},
                {null, null, null, null, null, null, "+", "+"},
                {null, null, null, null, null, null, "+", "+"},
                {null, null, null, null, null, null, "+", "+"},
                {null, null, null, null, null, null, "+", "+"},
                {null, null, null, null, null, null, "+", "+"},
                {null, null, null, null, null, null, "+", "+"}
            },
            new String [] {
                "", "Local", "GL", "GV", "Visitante", "", "Detalles", "Actualizar"
            }
        ) {
            Class[] types = new Class [] {
                javax.swing.ImageIcon.class

                , java.lang.String.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , javax.swing.ImageIcon.class

                , java.lang.Object.class

                , java.lang.Object.class

            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaJornada1.setColumnSelectionAllowed(true);
        tablaJornada1.setPreferredSize(new java.awt.Dimension(684, 144));
        tablaJornada1.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(tablaJornada1);
        tablaJornada1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablaJornada1.getColumn ("Detalles").setCellRenderer(new ButtonRenderer());
        tablaJornada1.getColumn ("Detalles").setCellEditor(new ButtonEditor(new JCheckBox(), this));
        tablaJornada1.getColumn ("Actualizar").setCellRenderer(new ButtonRenderer());
        tablaJornada1.getColumn ("Actualizar").setCellEditor(new ButtonEditor(new JCheckBox(), this));
        tablaJornada1.getColumnModel().getColumn(0).setResizable(false);
        tablaJornada1.getColumnModel().getColumn(0).setPreferredWidth(17);
        tablaJornada1.getColumnModel().getColumn(1).setResizable(false);
        tablaJornada1.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaJornada1.getColumnModel().getColumn(2).setResizable(false);
        tablaJornada1.getColumnModel().getColumn(3).setResizable(false);
        tablaJornada1.getColumnModel().getColumn(4).setResizable(false);
        tablaJornada1.getColumnModel().getColumn(4).setPreferredWidth(200);
        tablaJornada1.getColumnModel().getColumn(5).setResizable(false);
        tablaJornada1.getColumnModel().getColumn(5).setPreferredWidth(17);
        tablaJornada1.getColumnModel().getColumn(6).setResizable(false);
        tablaJornada1.getColumnModel().getColumn(6).setPreferredWidth(100);
        tablaJornada1.getColumnModel().getColumn(7).setResizable(false);
        tablaJornada1.getColumnModel().getColumn(7).setPreferredWidth(110);

        jPanel8.setPreferredSize(new java.awt.Dimension(746, 346));

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Local", "Visitante"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalles.setColumnSelectionAllowed(true);
        tablaDetalles.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tablaDetalles);
        tablaDetalles.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (tablaDetalles.getColumnModel().getColumnCount() > 0) {
            tablaDetalles.getColumnModel().getColumn(0).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panMarcadorLayout = new javax.swing.GroupLayout(panMarcador);
        panMarcador.setLayout(panMarcadorLayout);
        panMarcadorLayout.setHorizontalGroup(
            panMarcadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMarcadorLayout.createSequentialGroup()
                .addGroup(panMarcadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panMarcadorLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cJornada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(jScrollPane11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panMarcadorLayout.setVerticalGroup(
            panMarcadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMarcadorLayout.createSequentialGroup()
                .addGroup(panMarcadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cJornada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        panSecundario.setPreferredSize(new java.awt.Dimension(920, 288));

        panDescenso.setBorder(null);

        tablaCociente.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        tablaCociente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(2), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(3), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(4), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(5), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(6), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(7), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(8), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(9), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(10), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(11), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(12), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(13), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(14), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(15), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(16), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(17), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                { new Integer(18), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "POS", "", "EQUIPO", "PA13", "PC14", "PA14", "PC15", "PA15", "PC16", "TPTS", "", "JA13", "JC14", "JA14", "JC15", "JA15", "JC16", "TJ", "COCIENTE", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class

                , javax.swing.ImageIcon.class

                , java.lang.String.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.String.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.Integer.class

                , java.lang.String.class

                , java.lang.String.class

            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCociente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaCociente.setMinimumSize(new java.awt.Dimension(920, 288));
        tablaCociente.setName("tablaCociente"); // NOI18N
        tablaCociente.setRowHeight(17);
        tablaCociente.getTableHeader().setResizingAllowed(false);
        tablaCociente.getTableHeader().setReorderingAllowed(false);
        tablaCociente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaGeneralMouseClicked(evt);
            }
        });
        panDescenso.setViewportView(tablaCociente);
        tablaCociente.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablaCociente.getColumnModel().getColumn(0).setPreferredWidth(35);
        tablaCociente.getColumnModel().getColumn(1).setPreferredWidth(17);
        tablaCociente.getColumnModel().getColumn(2).setPreferredWidth(203);
        tablaCociente.getColumnModel().getColumn(3).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(4).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(5).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(6).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(7).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(8).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(9).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(10).setPreferredWidth(26);
        tablaCociente.getColumnModel().getColumn(11).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(12).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(13).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(14).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(15).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(16).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(17).setPreferredWidth(41);
        tablaCociente.getColumnModel().getColumn(18).setPreferredWidth(77);
        tablaCociente.getColumnModel().getColumn(19).setPreferredWidth(21);

        tablaCociente.setDefaultRenderer(Integer.class, centerRenderer);
        tablaCociente.setDefaultRenderer(String.class, centerRenderer);

        DefaultTableCellRenderer rendererTablaCociente = (DefaultTableCellRenderer) tablaCociente.getTableHeader().getDefaultRenderer();
        rendererTablaCociente.setHorizontalAlignment(SwingConstants.CENTER);

        tablaCociente.getColumnModel().getColumn(19).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                //Cells are by default rendered as a JLabel.
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (value.toString().equals("☑")) {
                    l.setForeground(Color.GREEN);
                } else {
                    l.setForeground(Color.RED);
                }
                //Return the JLabel which renders the cell.
                return l;
            }
        });

        panSecundario.addTab("Descenso", panDescenso);

        panLiguilla.setEnabled(false);

        jLabel1.setText("Cuartos de Final");

        tablaCuartos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null, null, null, null},
                { new Integer(8), null, null, null, null},
                { new Integer(2), null, null, null, null},
                { new Integer(7), null, null, null, null},
                { new Integer(3), null, null, null, null},
                { new Integer(6), null, null, null, null},
                { new Integer(4), null, null, null, null},
                { new Integer(5), null, null, null, null}
            },
            new String [] {
                "Posición", "Equipo", "Ida", "Vuelta", "Global"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCuartos.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tablaCuartos);

        btnCalcularCuartos.setText("Calcular");
        btnCalcularCuartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularCuartosActionPerformed(evt);
            }
        });

        jLabel4.setText("Semifinal de Final");

        tablaSemi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null,  new Integer(0),  new Integer(0), null},
                { new Integer(4), null,  new Integer(0),  new Integer(0), null},
                { new Integer(2), null,  new Integer(0),  new Integer(0), null},
                { new Integer(3), null,  new Integer(0),  new Integer(0), null}
            },
            new String [] {
                "Posición", "Equipo", "Ida", "Vuelta", "Global"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaSemi.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tablaSemi);

        tablaPosCuartos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null},
                {"2", null},
                {"3", null},
                {"4", null}
            },
            new String [] {
                "Posición", "Equipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPosCuartos.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(tablaPosCuartos);

        tablaPosSemi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null},
                {"2", null}
            },
            new String [] {
                "Posición", "Equipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPosSemi.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(tablaPosSemi);

        btnCalcularSemi.setText("Calcular");
        btnCalcularSemi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularSemiActionPerformed(evt);
            }
        });

        tablaFinal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null,  new Integer(0),  new Integer(0), null},
                { new Integer(2), null,  new Integer(0),  new Integer(0), null}
            },
            new String [] {
                "Posición", "Equipo", "Ida", "Vuelta", "Global"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFinal.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(tablaFinal);

        btnCalcularFinal.setText("Calcular");
        btnCalcularFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularFinalActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("CAMPEÓN");

        lblLocal.setText("jLabel3");

        lblVisit.setText("jLabel4");

        jLabel8.setText("0");

        jLabel9.setText("0");

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });

        jCheckBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PenalesLayout = new javax.swing.GroupLayout(Penales);
        Penales.setLayout(PenalesLayout);
        PenalesLayout.setHorizontalGroup(
            PenalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PenalesLayout.createSequentialGroup()
                .addGroup(PenalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLocal, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(lblVisit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PenalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PenalesLayout.createSequentialGroup()
                        .addComponent(jCheckBox6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox7))
                    .addGroup(PenalesLayout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PenalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PenalesLayout.createSequentialGroup()
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(PenalesLayout.createSequentialGroup()
                        .addComponent(jCheckBox8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        PenalesLayout.setVerticalGroup(
            PenalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PenalesLayout.createSequentialGroup()
                .addGroup(PenalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addComponent(lblLocal)
                    .addComponent(jLabel8)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PenalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVisit)
                    .addGroup(PenalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox7, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jCheckBox8, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jCheckBox9, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jCheckBox10, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9))
                    .addComponent(jCheckBox6))
                .addContainerGap())
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panLiguillaLayout = new javax.swing.GroupLayout(panLiguilla);
        panLiguilla.setLayout(panLiguillaLayout);
        panLiguillaLayout.setHorizontalGroup(
            panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panLiguillaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panLiguillaLayout.createSequentialGroup()
                        .addComponent(Penales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panLiguillaLayout.createSequentialGroup()
                        .addGroup(panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panLiguillaLayout.createSequentialGroup()
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(btnCalcularFinal))
                            .addGroup(panLiguillaLayout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(jLabel1))
                            .addGroup(panLiguillaLayout.createSequentialGroup()
                                .addGroup(panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panLiguillaLayout.createSequentialGroup()
                                        .addGap(185, 185, 185)
                                        .addComponent(btnCalcularSemi)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panLiguillaLayout.createSequentialGroup()
                        .addGroup(panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panLiguillaLayout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(jLabel4))
                            .addGroup(panLiguillaLayout.createSequentialGroup()
                                .addGap(186, 186, 186)
                                .addComponent(btnCalcularCuartos)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panLiguillaLayout.setVerticalGroup(
            panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panLiguillaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panLiguillaLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCalcularCuartos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panLiguillaLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panLiguillaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addComponent(btnCalcularSemi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panLiguillaLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(panLiguillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Penales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panLiguillaLayout.createSequentialGroup()
                        .addComponent(btnCalcularFinal)
                        .addGap(134, 134, 134)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panSecundario.addTab("Liguilla", panLiguilla);

        javax.swing.GroupLayout panGlobalLayout = new javax.swing.GroupLayout(panGlobal);
        panGlobal.setLayout(panGlobalLayout);
        panGlobalLayout.setHorizontalGroup(
            panGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panGlobalLayout.createSequentialGroup()
                .addGroup(panGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panGlobalLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(panMarcador, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panGlobalLayout.setVerticalGroup(
            panGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panGlobalLayout.createSequentialGroup()
                .addGroup(panGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panGlobalLayout.createSequentialGroup()
                        .addComponent(panPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panMarcador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        panPrincipal.getAccessibleContext().setAccessibleName("Tabla General");
        panPrincipal.getAccessibleContext().setAccessibleDescription("");

        jScrollPane5.setViewportView(panGlobal);

        menuBar.setAutoscrolls(true);

        jMenu2.setText("Archivo");

        menuAgrega.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuAgrega.setText("Agregar Partido");
        menuAgrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAgregaActionPerformed(evt);
            }
        });
        jMenu2.add(menuAgrega);

        jmGrafica.setText("Gráfica");
        jmGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmGraficaActionPerformed(evt);
            }
        });
        jMenu2.add(jmGrafica);

        jMenuItem1.setText("Transferencias");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        ACuartos.setText("Agregar Cuartos");
        ACuartos.setEnabled(false);
        ACuartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACuartosActionPerformed(evt);
            }
        });
        jMenu2.add(ACuartos);

        aSemi.setText("Agregar Semifinal");
        aSemi.setEnabled(false);
        aSemi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aSemiActionPerformed(evt);
            }
        });
        jMenu2.add(aSemi);

        aFinal.setText("Agregar Final");
        aFinal.setEnabled(false);
        aFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aFinalActionPerformed(evt);
            }
        });
        jMenu2.add(aFinal);

        menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        jMenu2.add(menuSalir);

        menuBar.add(jMenu2);

        jMenu3.setText("Ayuda");

        jMenuItem10.setText("Acerca  de");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1666, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarActualiza() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar actualiza");
        }
        cambio = false;
        Integer jornada = Integer.parseInt(cJornada1.getSelectedItem().toString());//Integer.parseInt(cJornada.getSelectedItem().toString());
        int fila = 0;
        for (int f = 0; f < tablaJornada1.getRowCount(); f++) {
            for (int columna = 0; columna < tablaJornada1.getColumnCount(); columna++) {
                tablaJornada1.setValueAt(null, f, columna);
            }
        }
        List<PartidoDTO> partidoDTOs = partidoService.buscarPartidosporJornada(jornada);
        for (PartidoDTO partidoDTO : partidoDTOs) {
            EquipoDTO localEquipoDTO = equipoService.buscarEquipoporId(partidoDTO.getIdLocal());
            tablaJornada1.setValueAt(localEquipoDTO.getNombre(), fila, 1);
            EquipoDTO visitanteEquipoDTO = equipoService.buscarEquipoporId(partidoDTO.getIdVisitante());
            tablaJornada1.setValueAt(visitanteEquipoDTO.getNombre(), fila, 4);
            if (null != partidoDTO.getMl()) {
                tablaJornada1.setValueAt(partidoDTO.getMl(), fila, 2);
                tablaJornada1.setValueAt(partidoDTO.getMv(), fila, 3);
            }
            String urll = "ligamx/resources/images/" + localEquipoDTO.getNombre() + ".png";
            String urlv = "ligamx/resources/images/" + visitanteEquipoDTO.getNombre() + ".png";
            ImageIcon iconl = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(urll)));
            ImageIcon iconv = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(urlv)));
            iconl.setImage(iconl.getImage().getScaledInstance(17, 17, Image.SCALE_DEFAULT));
            iconv.setImage(iconv.getImage().getScaledInstance(17, 17, Image.SCALE_DEFAULT));
            tablaJornada1.setValueAt(iconl, fila, 0);
            tablaJornada1.setValueAt(iconv, fila, 5);
            fila++;
        }
        cambio = true;
    }

    public void ActualizarTabla() throws ExcepcionAplicacion {
        LOGGER.info("Entró a actualizar tabla");
        int jornada = Integer.parseInt(cJornada1.getSelectedItem().toString());
        for (int fila = 0; fila < 9; fila++) {
            if (tablaJornada1.getValueAt(fila, 2) != null && tablaJornada1.getValueAt(fila, 3) != null) {
                EquipoDTO equipoDTO = equipoService.buscarEquipoporNombre(tablaJornada1.getValueAt(fila, 1).toString());
                PartidoDTO partidoDTO = partidoService.buscarPartidoporJornadayLocal(jornada, equipoDTO);
                partidoDTO.setMl(Integer.parseInt(tablaJornada1.getValueAt(fila, 2).toString()));
                partidoDTO.setMv(Integer.parseInt(tablaJornada1.getValueAt(fila, 3).toString()));
                partidoService.actualizarPartido(partidoDTO);
                equipoService.actualizarEquipo(partidoDTO.getIdPartido());
            }
        }
        ActualizarGeneral();
    }

    public final void tablaGoleadores() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a tabla goleadores");
        }
        int fila = 0;
        List<GoleadorDTO> goleadorDTOs = golService.buscarGoleadores();
        if (!goleadorDTOs.isEmpty()) {
            while (fila < 10 && goleadorDTOs.size() >= fila) {
                GoleadorDTO goleadorDTO = goleadorDTOs.get(fila);
                tGoleadores.setValueAt(goleadorDTO.getEquipo(), fila, 0);
                tGoleadores.setValueAt(goleadorDTO.getNombre(), fila, 1);
                tGoleadores.setValueAt(goleadorDTO.getGoles(), fila, 2);
                fila++;
            }
        }
    }

    private void menuAgregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAgregaActionPerformed
        PartidoController partidoController = new PartidoController();
        partidoController.setVisible(true);
    }//GEN-LAST:event_menuAgregaActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuSalirActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        JOptionPane.showMessageDialog(this, "LIGA MX C17 \n"
                + "Héctor López V4.2\n"
                //                + "*V4.0(21-01-17)\n     -C17\n"
                + "*V4.2(29-04-17)\n     -Correción en tabla cociente \n"
                + "     -Selección automática de equipos en las tablas", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void cJornada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cJornada1ActionPerformed
        try {
            BuscarActualiza();
        } catch (ExcepcionAplicacion ex) {
            lanzarExcepcion(ex);
        }
    }//GEN-LAST:event_cJornada1ActionPerformed

    private void jrEquipo1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jrEquipo1StateChanged
        try {
            llenarJugadores();
        } catch (ExcepcionAplicacion ex) {
            lanzarExcepcion(ex);
        }
    }//GEN-LAST:event_jrEquipo1StateChanged

    private void jrEquipo2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jrEquipo2StateChanged
        try {
            llenarJugadores();
        } catch (ExcepcionAplicacion ex) {
            lanzarExcepcion(ex);
        }
    }//GEN-LAST:event_jrEquipo2StateChanged

    private void cAutogolStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cAutogolStateChanged
        try {
            llenarJugadores();
        } catch (ExcepcionAplicacion ex) {
            lanzarExcepcion(ex);
        }
    }//GEN-LAST:event_cAutogolStateChanged

    private void sMinutoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sMinutoStateChanged
        jtMinuto.setText(String.valueOf(sMinuto.getValue()));
    }//GEN-LAST:event_sMinutoStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jugadorController.setLigaMXController(this);
        jugadorController.equipo.setSelectedItem(jrEquipo1.isSelected() ? equipo1 : equipo2);
        jugadorController.setVisible(true);
        this.getjGol().setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.agregarGol();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nomJugBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomJugBuscarKeyPressed
        try {
            llenarJugadores();
        } catch (ExcepcionAplicacion ex) {
            lanzarExcepcion(ex);
        }
    }//GEN-LAST:event_nomJugBuscarKeyPressed

    private void jmGraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGraficaActionPerformed
//        grafica.setVisible(true);
    }//GEN-LAST:event_jmGraficaActionPerformed

    private void ACuartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACuartosActionPerformed
//        String sql, local, visitante, str = "";
//        int idpart = 154;
//        for (int i = 0; i < 8; i += 2) {
//            local = tablaCuartos.getValueAt(i, 1).toString();
//            visitante = tablaCuartos.getValueAt(i + 1, 1).toString();
//            try {
//                sql = "Insert into C16 values (" + (idpart + 4) + ",18,'" + local + "',0,'" + visitante + "',0)";
//                stmn.executeUpdate(sql);
//                sql = "Insert into C16 values (" + (idpart) + ",18,'" + visitante + "',0,'" + local + "',0)";
//                stmn.executeUpdate(sql);
//                str += "" + local + "-" + visitante + "\n";
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Error en tabla C16 " + e);
//            }
//            idpart++;
//        }
//        JOptionPane.showMessageDialog(null, "Partidos agregados \n" + str);
    }//GEN-LAST:event_ACuartosActionPerformed
    public void Cuartos() {
//        try {
//            conectarBase();
//        } catch (SQLException ex) {
//            Logger.getLogger(LigaMXController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        String local, visitante;
//        for (int fila = 0; fila < 8; fila += 2) {
//            local = tablaCuartos.getValueAt(fila, 1).toString();
//            visitante = tablaCuartos.getValueAt(fila + 1, 1).toString();
//
//            try {
//                rset = stmn.executeQuery("Select * from C16 where JORNADA=18 and LOCAL='" + visitante + "'");
//                while (rset.next()) {
//                    tablaCuartos.setValueAt(rset.getInt("ML"), fila + 1, 2);
//                    tablaCuartos.setValueAt(rset.getInt("MV"), fila, 2);
//
//                }//while
//                //conn.close();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 1");
//            }
//            try {
//                rset = stmn.executeQuery("Select * from C16 where JORNADA=18 and LOCAL='" + local + "'");
//                while (rset.next()) {
//
//                    tablaCuartos.setValueAt(rset.getInt("ML"), fila, 3);
//                    tablaCuartos.setValueAt(rset.getInt("MV"), fila + 1, 3);
//
//                }
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 213");
//            }
//        }
    }

    public void Semi() {
//        tablaSemi.setValueAt(tablaPosCuartos.getValueAt(0, 1), 0, 1);
//        tablaSemi.setValueAt(tablaPosCuartos.getValueAt(3, 1), 1, 1);
//        tablaSemi.setValueAt(tablaPosCuartos.getValueAt(1, 1), 2, 1);
//        tablaSemi.setValueAt(tablaPosCuartos.getValueAt(2, 1), 3, 1);
//        try {
//            conectarBase();
//        } catch (SQLException ex) {
//            Logger.getLogger(LigaMXController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        String local, visitante;
//        for (int fila = 0; fila < 4; fila += 2) {
//            local = tablaSemi.getValueAt(fila, 1).toString();
//            visitante = tablaSemi.getValueAt(fila + 1, 1).toString();
//            System.out.println("local= " + local + " visitante= " + visitante);
//
//            try {
//                rset = stmn.executeQuery("Select * from C16 where JORNADA=19 and LOCAL='" + visitante + "'");
//                while (rset.next()) {
//                    tablaSemi.setValueAt(rset.getInt("ML"), fila + 1, 2);
//                    tablaSemi.setValueAt(rset.getInt("MV"), fila, 2);
//
//                }//while
//                // conn.close();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 1");
//            }
//            try {
//                rset = stmn.executeQuery("Select * from C16 where JORNADA=19 and LOCAL='" + local + "'");
//                while (rset.next()) {
//
//                    tablaSemi.setValueAt(rset.getInt("ML"), fila, 3);
//                    tablaSemi.setValueAt(rset.getInt("MV"), fila + 1, 3);
//
//                }
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 213");
//            }
//        }
    }

    public void CalcularSemi() {
//        int ida, vuelta, global, fila, global1, global2, vis1, vis2;
//        String local, sql = "";
//        int ml, mv;
//        int[] pos = new int[2];
//        String[] equipos = new String[2];
//
//        for (fila = 0; fila < 4; fila++) {
//            ida = Integer.parseInt(tablaSemi.getValueAt(fila, 2).toString());
//            vuelta = Integer.parseInt(tablaSemi.getValueAt(fila, 3).toString());
//
//            global = ida + vuelta;
//            tablaSemi.setValueAt(global, fila, 4);
//
//        }
//        for (fila = 0; fila < 4; fila += 2) {
//            global1 = Integer.parseInt(tablaSemi.getValueAt(fila, 4).toString());
//            global2 = Integer.parseInt(tablaSemi.getValueAt(fila + 1, 4).toString());
//            if (global1 > global2) {
//                pos[fila / 2] = Integer.parseInt(tablaSemi.getValueAt(fila, 0).toString());
//            } else if (global2 > global1) {
//                pos[fila / 2] = Integer.parseInt(tablaSemi.getValueAt(fila + 1, 0).toString());
//            } else if (global1 == global2) {
//                vis1 = Integer.parseInt(tablaSemi.getValueAt(fila, 2).toString());
//                vis2 = Integer.parseInt(tablaSemi.getValueAt(fila + 1, 3).toString());
//                if (vis1 > vis2 || vis1 == vis2) {
//                    pos[fila / 2] = Integer.parseInt(tablaSemi.getValueAt(fila, 0).toString());
//                } else if (vis2 > vis1) {
//                    pos[fila / 2] = Integer.parseInt(tablaSemi.getValueAt(fila + 1, 0).toString());
//                }
//            }
//
//        }
//        Arrays.sort(pos);
//        for (int i = 0; i < 2; i++) {
//            for (fila = 0; fila < 4; fila++) {
//                if (pos[i] == Integer.parseInt(tablaSemi.getValueAt(fila, 0).toString())) {
//                    equipos[i] = tablaSemi.getValueAt(fila, 1).toString();
//                }
//            }
//        }
//        for (fila = 1; fila >= 0; fila--) {
//            tablaPosSemi.setValueAt(equipos[fila], fila, 1);
//        }
//        try {
//            conectarBase();
//        } catch (SQLException ex) {
//            Logger.getLogger(LigaMXController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        for (int j = 0; j < 4; j += 2) {
//            try {
//                local = tablaSemi.getValueAt(j + 1, 1).toString();
//                ml = Integer.parseInt(tablaSemi.getValueAt((j + 1), 2).toString());
//                mv = Integer.parseInt(tablaSemi.getValueAt(j, 2).toString());
//                sql = "Update C16 set ML = " + ml + ", MV=" + mv + " where JORNADA=19 and LOCAL='" + local + "'";
//                stmn.executeUpdate(sql);
//                // conn.close();
//            } catch (NumberFormatException | SQLException e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 2 aqui1");
//            }
//            try {
//
//                local = tablaSemi.getValueAt(j, 1).toString();
//                ml = Integer.parseInt(tablaSemi.getValueAt(j, 3).toString());
//                mv = Integer.parseInt(tablaSemi.getValueAt(j + 1, 3).toString());
//                sql = "Update C16 set ML = " + ml + ", MV=" + mv + " where JORNADA=19 and LOCAL='" + local + "'";
//                stmn.executeUpdate(sql);
//
//            } catch (NumberFormatException | SQLException e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 2 aqui2");
//            }
//        }
//        Final();
    }

    public void Final() {
//        tablaFinal.setValueAt(tablaPosSemi.getValueAt(0, 1), 0, 1);
//        tablaFinal.setValueAt(tablaPosSemi.getValueAt(1, 1), 1, 1);
//        try {
//            conectarBase();
//        } catch (SQLException ex) {
//            Logger.getLogger(LigaMXController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        String local, visitante;
//        for (int fila = 0; fila < 2; fila += 2) {
//            local = tablaFinal.getValueAt(fila, 1).toString();
//            visitante = tablaFinal.getValueAt(fila + 1, 1).toString();
//            System.out.println("local= " + local + " visitante= " + visitante);
//
//            try {
//                rset = stmn.executeQuery("Select * from C16 where JORNADA=20 and LOCAL='" + visitante + "'");
//                while (rset.next()) {
//                    tablaFinal.setValueAt(rset.getInt("ML"), fila + 1, 2);
//                    tablaFinal.setValueAt(rset.getInt("MV"), fila, 2);
//
//                }//while
//                // conn.close();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 1");
//            }
//            try {
//                rset = stmn.executeQuery("Select * from C16 where JORNADA=20 and LOCAL='" + local + "'");
//                while (rset.next()) {
//
//                    tablaFinal.setValueAt(rset.getInt("ML"), fila, 3);
//                    tablaFinal.setValueAt(rset.getInt("MV"), fila + 1, 3);
//
//                }
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 213");
//            }
//        }
    }

    private void CalcularFinal() {
//        int ida, vuelta, global, fila, global1, global2;
//        String local, campeon = "", sql = "";
//        int ml, mv;
//
//        String equipos;
//
//        for (fila = 0; fila < 2; fila++) {
//            ida = Integer.parseInt(tablaFinal.getValueAt(fila, 2).toString());
//            vuelta = Integer.parseInt(tablaFinal.getValueAt(fila, 3).toString());
//
//            global = ida + vuelta;
//            tablaFinal.setValueAt(global, fila, 4);
//
//        }
//        for (fila = 0; fila < 2; fila += 2) {
//            global1 = Integer.parseInt(tablaFinal.getValueAt(fila, 4).toString());
//            global2 = Integer.parseInt(tablaFinal.getValueAt(fila + 1, 4).toString());
//            if (global1 > global2) {
//                campeon = tablaFinal.getValueAt(fila, 1).toString();
//                Penales.setVisible(false);
//
//            } else if (global2 > global1) {
//                campeon = tablaFinal.getValueAt(fila + 1, 1).toString();
//                Penales.setVisible(false);
//            } else if (global1 == global2) {
//                Penales.setVisible(true);
//                lblLocal.setText(tablaFinal.getValueAt(fila, 1).toString());
//                lblVisit.setText(tablaFinal.getValueAt(fila + 1, 1).toString());
//
//            }
//            jLabel7.setText("Campeón " + campeon + "");
//
//        }
//
//        try {
//            conectarBase();
//        } catch (SQLException ex) {
//            Logger.getLogger(LigaMXController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        for (int j = 0; j < 2; j += 2) {
//            try {
//                local = tablaFinal.getValueAt(j + 1, 1).toString();
//                ml = Integer.parseInt(tablaFinal.getValueAt((j + 1), 2).toString());
//                mv = Integer.parseInt(tablaFinal.getValueAt(j, 2).toString());
//                sql = "Update C16 set ML = " + ml + ", MV=" + mv + " where JORNADA=20 and LOCAL='" + local + "'";
//                stmn.executeUpdate(sql);
//                // conn.close();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 2 aqui1");
//            }
//            try {
//
//                local = tablaFinal.getValueAt(j, 1).toString();
//                ml = Integer.parseInt(tablaFinal.getValueAt(j, 3).toString());
//                mv = Integer.parseInt(tablaFinal.getValueAt(j + 1, 3).toString());
//                sql = "Update C16 set ML = " + ml + ", MV=" + mv + " where JORNADA=19 and LOCAL='" + local + "'";
//                stmn.executeUpdate(sql);
//
//                //System.out.println("tercero");
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 2 aqui2");
//            }
//        }

    }

    private void aSemiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aSemiActionPerformed
//        String sql, local, visitante, str = "";
//        int idpart = 162;
//        for (int i = 0; i < 4; i += 2) {
//            local = tablaSemi.getValueAt(i, 1).toString();
//            visitante = tablaSemi.getValueAt(i + 1, 1).toString();
//            try {
//                sql = "Insert into C16 values (" + (idpart + 2) + ",19,'" + local + "',0,'" + visitante + "',0)";
//                stmn.executeUpdate(sql);
//                sql = "Insert into C16 values (" + (idpart) + " ,19,'" + visitante + "',0,'" + local + "',0)";
//                stmn.executeUpdate(sql);
//                str += "" + local + "-" + visitante + "\n";
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Error en tabla LIGAMX ");
//            }
//            idpart++;
//        }
//
//        JOptionPane.showMessageDialog(null, "Partidos agregados \n" + str);
    }//GEN-LAST:event_aSemiActionPerformed

    private void aFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aFinalActionPerformed
//        String sql, local, visitante, str = "";
//
//        for (int i = 0; i < 2; i += 2) {
//            local = tablaFinal.getValueAt(i, 1).toString();
//            visitante = tablaFinal.getValueAt(i + 1, 1).toString();
//            try {
//                sql = "Insert into C16 values (167,20,'" + local + "',0,'" + visitante + "',0)";
//                stmn.executeUpdate(sql);
//                sql = "Insert into C16 values (166,20,'" + visitante + "',0,'" + local + "',0)";
//                stmn.executeUpdate(sql);
//                str += "" + local + "-" + visitante + "\n";
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Error en tabla LIGAMX ");
//            }
//        }
//        JOptionPane.showMessageDialog(null, "Partidos agregados \n" + str);
    }//GEN-LAST:event_aFinalActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//        Transferencia trans = new Transferencia();
//        trans.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jCheckBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox10ActionPerformed
        /*  if(jCheckBox10.isSelected())
         {
         pen2++;
         }
         else if(!jCheckBox10.isSelected())
         {
         pen2--;
         }
         jLabel6.setText(pen2+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox10ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        /*  if(jCheckBox9.isSelected())
         {
         pen2++;
         }
         else if(!jCheckBox9.isSelected())
         {
         pen2--;
         }
         jLabel6.setText(pen2+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        /*   if(jCheckBox8.isSelected())
         {
         pen2++;
         }
         else if(!jCheckBox8.isSelected())
         {
         pen2--;
         }
         jLabel6.setText(pen2+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        /*   if(jCheckBox7.isSelected())
         {
         pen2++;
         }
         else if(!jCheckBox7.isSelected())
         {
         pen2--;
         }
         jLabel6.setText(pen2+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        /*    if(jCheckBox6.isSelected())
         {
         pen2++;
         }
         else if(!jCheckBox6.isSelected())
         {
         pen2--;
         }
         jLabel6.setText(pen2+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        /*    if(jCheckBox5.isSelected())
         {
         pen1++;
         }
         else if(!jCheckBox5.isSelected())
         {
         pen1--;
         }
         jLabel5.setText(pen1+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        /*   if(jCheckBox4.isSelected())
         {
         pen1++;
         }
         else if(!jCheckBox4.isSelected())
         {
         pen1--;
         }
         jLabel5.setText(pen1+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        /*   if(jCheckBox3.isSelected())
         {
         pen1++;
         }
         else if(!jCheckBox3.isSelected())
         {
         pen1--;
         }
         jLabel5.setText(pen1+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        /*   if(jCheckBox2.isSelected())
         {
         pen1++;
         }
         else if(!jCheckBox2.isSelected())
         {
         pen1--;
         }
         jLabel5.setText(pen1+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        /* if(jCheckBox1.isSelected())
         {
         pen1++;
         }
         else if(!jCheckBox1.isSelected())
         {
         pen1--;
         }
         jLabel5.setText(pen1+"");
         Penales();*/
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnCalcularFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularFinalActionPerformed
        CalcularFinal();
    }//GEN-LAST:event_btnCalcularFinalActionPerformed

    private void btnCalcularSemiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularSemiActionPerformed
        CalcularSemi();
    }//GEN-LAST:event_btnCalcularSemiActionPerformed

    private void btnCalcularCuartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularCuartosActionPerformed
        CalcularCuartos();
    }//GEN-LAST:event_btnCalcularCuartosActionPerformed

    private void tablaGeneralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaGeneralMouseClicked
        JTable tabla1 = (JTable) evt.getSource();
        String nombre1 = tabla1.getValueAt(tabla1.getSelectedRow(), 2).toString();
        JTable tabla2;
        if (tabla1.getName().equals("tablaGeneral")) {
            tabla2 = tablaCociente;
        } else {
            tabla2 = tablaGeneral;
        }
        tabla2.clearSelection();
        boolean fin = false;
        for (int i = 0; i < tabla2.getRowCount() && !fin; i++) {
            if (nombre1.equals(tabla2.getValueAt(i, 2).toString())) {
                tabla2.addRowSelectionInterval(i, i);
                fin = true;
            }
        }
        cLocal1.setSelectedItem(nombre1);
    }//GEN-LAST:event_tablaGeneralMouseClicked

    private void cLocal1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cLocal1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
                BuscarEquipo();
            } catch (ExcepcionAplicacion ex) {
                lanzarExcepcion(ex);
            }
        }
    }//GEN-LAST:event_cLocal1ItemStateChanged

    private void jGolWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jGolWindowClosing
        this.setEnabled(true);
        this.toFront();
    }//GEN-LAST:event_jGolWindowClosing

    private void jPanel9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel9KeyTyped
        
    }//GEN-LAST:event_jPanel9KeyTyped

    private void jGolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jGolKeyTyped
        if(evt.getKeyChar() == '\n') {
            this.agregarGol();
        }
    }//GEN-LAST:event_jGolKeyTyped

    private void lJugadoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lJugadoresKeyTyped
        if(evt.getKeyChar() == '\n') {
            this.agregarGol();
        }
    }//GEN-LAST:event_lJugadoresKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.pantallaCarga.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    public void detalles(PartidoDTO partidoDTO) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a detalles");
        }
        partidoDTO.setGoles(golService.buscarGolesporPartido(partidoDTO));
        EquipoDTO localDTO = equipoService.buscarEquipoporId(partidoDTO.getIdLocal());
        EquipoDTO visitanteDTO = equipoService.buscarEquipoporId(partidoDTO.getIdVisitante());
        tablaDetalles.getColumnModel().getColumn(0).setHeaderValue(localDTO.getNombre());
        tablaDetalles.getColumnModel().getColumn(1).setHeaderValue(visitanteDTO.getNombre());
        tablaDetalles.getTableHeader().repaint();
        for (int i = 0; i < tablaDetalles.getRowCount(); i++) {
            for (int j = 0; j < tablaDetalles.getColumnCount(); j++) {
                tablaDetalles.setValueAt("", i, j);
            }
        }
        int filal = 0;
        int filav = 0;
        String detalle;
        if (null != partidoDTO.getGoles()) {
            for (GolDTO golDTO : partidoDTO.getGoles()) {
                String ag = "";
                if (golDTO.getAutogol().equals('S')) {
                    ag = "(AG)";
                }
                JugadorDTO jugadorDTO = jugadorService.buscarJugadorPorId(golDTO.getIdJugador());
                detalle = jugadorDTO.getNombre() + "(" + golDTO.getMinuto() + ") " + ag;
                if (golDTO.getIdEquipo().equals(partidoDTO.getIdLocal())) {
                    tablaDetalles.setValueAt(detalle, filal, 0);
                    filal++;
                } else {
                    tablaDetalles.setValueAt(detalle, filav, 1);
                    filav++;
                }

            }
        }
        tablaGeneral.clearSelection();
        for (int filag = 0; filag < tablaGeneral.getRowCount(); filag++) {
            String nombre = tablaGeneral.getValueAt(filag, 2).toString();
            if (nombre.equals(localDTO.getNombre()) || nombre.equals(visitanteDTO.getNombre())) {
                tablaGeneral.addRowSelectionInterval(filag, filag);
            }
        }
        tablaCociente.clearSelection();
        for (int filac = 0; filac < tablaCociente.getRowCount(); filac++) {
            String nombre = tablaCociente.getValueAt(filac, 2).toString();
            if (nombre.equals(localDTO.getNombre()) || nombre.equals(visitanteDTO.getNombre())) {
                tablaCociente.addRowSelectionInterval(filac, filac);
            }
        }

    }

    public void llenarJugadores() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a llenar jugadores");
        }
        boolean golb = jrEquipo1.isSelected();
        String golequipo = null;
        if ((golb && !cAutogol.isSelected()) || (!golb && cAutogol.isSelected())) {
            golequipo = equipo1;
        } else if ((!golb && !cAutogol.isSelected()) || (golb && cAutogol.isSelected())) {
            golequipo = equipo2;
        }
        EquipoDTO equipoDTO = equipoService.buscarEquipoporNombre(golequipo);
        DefaultListModel<String> listModel;
        listModel = new DefaultListModel<>();
        List<JugadorDTO> jugadoresList = jugadorService.buscarJugadores(equipoDTO);
        List<JugadorDTO> jugadoresList2 = new ArrayList<>();
        String nombre = nomJugBuscar.getText();
        if (!nombre.isEmpty()) {
            for (JugadorDTO jugadorDTO : jugadoresList) {
                if (jugadorDTO.getNombre().toUpperCase().contains(nombre.toUpperCase())) {
                    jugadoresList2.add(jugadorDTO);
                }
            }
        } else {
            jugadoresList2 = jugadoresList;
        }
        jugadoresList2.stream().forEach((jugadorDTO) -> {
            listModel.addElement(jugadorDTO.getNombre());
        });
        lJugadores.setModel(listModel);
    }

    public void nuevoGol(String equipo1, String equipo2, int idpartido, int row) throws ExcepcionAplicacion {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.idpartido = idpartido;
        this.row = row;
        jrEquipo1.setText(this.equipo1);
        jrEquipo2.setText(this.equipo2);
        llenarJugadores();
    }

    private void ActualizarGeneral() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a actualizar general");
        }
        Map<String, PosicionDTO> posicionMap = posicionService.obtenerPosicion();
        List<Entry<String, PosicionDTO>> list = new LinkedList<>(posicionMap.entrySet());
        Collections.sort(list, (Entry<String, PosicionDTO> o1, Entry<String, PosicionDTO> o2) -> o1.getValue().compareTo(o2.getValue()));
        Map<String, PosicionDTO> sortedMap = new LinkedHashMap<>();
        list.stream().forEach((entry) -> {
            sortedMap.put(entry.getKey(), entry.getValue());
        });
        int fila = 0;
        for (Entry<String, PosicionDTO> entry : sortedMap.entrySet()) {
            PosicionDTO posicionDTO = entry.getValue();
            tablaGeneral.setValueAt(fila + 1, fila, 0);
            tablaGeneral.setValueAt(entry.getKey(), fila, 2);
            tablaGeneral.setValueAt(posicionDTO.getJj(), fila, 3);
            tablaGeneral.setValueAt(posicionDTO.getJg(), fila, 4);
            tablaGeneral.setValueAt(posicionDTO.getJe(), fila, 5);
            tablaGeneral.setValueAt(posicionDTO.getJp(), fila, 6);
            tablaGeneral.setValueAt(posicionDTO.getGf(), fila, 7);
            tablaGeneral.setValueAt(posicionDTO.getGc(), fila, 8);
            tablaGeneral.setValueAt(posicionDTO.getDg(), fila, 9);
            tablaGeneral.setValueAt(posicionDTO.getPts(), fila, 10);
            fila++;
        }
        List<Integer> JJList = new ArrayList<>();
        for (int i = 0; i < tablaGeneral.getRowCount(); i++) {
            String nombre = (String) tablaGeneral.getValueAt(i, 2);
            String url = "ligamx/resources/images/" + nombre + ".png";
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(url)));
            icon.setImage(icon.getImage().getScaledInstance(17, 17, Image.SCALE_DEFAULT));
            tablaGeneral.setValueAt(icon, i, 1);
            JJList.add((Integer) tablaGeneral.getValueAt(i, 3));
        }
        maximo = Collections.max(JJList);

        for (int i = 0; i < 18; i++) {
            if (i<8){
                if (Integer.parseInt(tablaGeneral.getValueAt(i, 10).toString()) >= Integer.parseInt(tablaGeneral.getValueAt(8, 10).toString()) + 3 * (17 - Integer.parseInt(tablaGeneral.getValueAt(8, 3).toString()))) {
                    tablaGeneral.setValueAt("☑", i, 11);
                } else {
                    tablaGeneral.setValueAt("", i, 11);
                }
            } else {
                 if (Integer.parseInt(tablaGeneral.getValueAt(7, 10).toString()) >= Integer.parseInt(tablaGeneral.getValueAt(i, 10).toString()) + 3 * (17 - Integer.parseInt(tablaGeneral.getValueAt(i, 3).toString()))) {
                     tablaGeneral.setValueAt("☒", i, 11);
                 } else {
                    tablaGeneral.setValueAt("", i, 11);
                }
            }
        }
        liguilla();
        Cociente();
//
//        grafica.llenarTabla();
    }

    public void liguilla() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a liguilla");
        }
        tablaCuartos.setValueAt(tablaGeneral.getValueAt(0, 2), 0, 1);
        tablaCuartos.setValueAt(tablaGeneral.getValueAt(1, 2), 2, 1);
        tablaCuartos.setValueAt(tablaGeneral.getValueAt(2, 2), 4, 1);
        tablaCuartos.setValueAt(tablaGeneral.getValueAt(3, 2), 6, 1);
        tablaCuartos.setValueAt(tablaGeneral.getValueAt(4, 2), 7, 1);
        tablaCuartos.setValueAt(tablaGeneral.getValueAt(5, 2), 5, 1);
        tablaCuartos.setValueAt(tablaGeneral.getValueAt(6, 2), 3, 1);
        tablaCuartos.setValueAt(tablaGeneral.getValueAt(7, 2), 1, 1);

    }
    int posicion;
    String equipo, coc;
    int PA13, PC14, PA14, PC15, PA15, PC16, TPTS, JA13, JC14, JA14, JC15, JA15, JC16, TJ;
    double COCIENTE;

    public void Cociente() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a Cociente");
        }
        Map<String, CocienteDTO> cocienteMap = cocienteService.obtenerCociente();
        List<Entry<String, CocienteDTO>> list = new LinkedList<>(cocienteMap.entrySet());
        Collections.sort(list, (Entry<String, CocienteDTO> o1, Entry<String, CocienteDTO> o2) -> o1.getValue().compareTo(o2.getValue()));
        Map<String, CocienteDTO> sortedMap = new LinkedHashMap<>();
        list.stream().forEach((entry) -> {
            sortedMap.put(entry.getKey(), entry.getValue());
        });

        int fila = 0;
        DecimalFormat df = new DecimalFormat("0.0000");
        for (Entry<String, CocienteDTO> entry : sortedMap.entrySet()) {
            CocienteDTO cocienteDTO = entry.getValue();
            tablaCociente.setValueAt(fila + 1, fila, 0);
            tablaCociente.setValueAt(entry.getKey(), fila, 2);
            tablaCociente.setValueAt(cocienteDTO.getPa14(), fila, 3);
            tablaCociente.setValueAt(cocienteDTO.getPc15(), fila, 4);
            tablaCociente.setValueAt(cocienteDTO.getJa15(), fila, 5);
            tablaCociente.setValueAt(cocienteDTO.getJc16(), fila, 6);
            tablaCociente.setValueAt(cocienteDTO.getJa16(), fila, 7);
            tablaCociente.setValueAt(cocienteDTO.getJc17(), fila, 8);
            tablaCociente.setValueAt(cocienteDTO.getTp(), fila, 9);
            tablaCociente.setValueAt(null, fila, 10);
            tablaCociente.setValueAt(cocienteDTO.getJa14(), fila, 11);
            tablaCociente.setValueAt(cocienteDTO.getJc15(), fila, 12);
            tablaCociente.setValueAt(cocienteDTO.getJa15(), fila, 13);
            tablaCociente.setValueAt(cocienteDTO.getJc16(), fila, 14);
            tablaCociente.setValueAt(cocienteDTO.getJa16(), fila, 15);
            tablaCociente.setValueAt(cocienteDTO.getJc17(), fila, 16);
            tablaCociente.setValueAt(cocienteDTO.getTj(), fila, 17);
            tablaCociente.setValueAt(df.format(cocienteDTO.getCociente()), fila, 18);
            fila++;
        }
        int partidosPendientes = 17 - (Integer.parseInt(tablaCociente.getValueAt(17, 16).toString()));
        int tj = Integer.parseInt(tablaCociente.getValueAt(17, 17).toString()) + partidosPendientes;
        int tp = Integer.parseInt(tablaCociente.getValueAt(17, 9).toString()) + 3 * partidosPendientes;
        double tcoc = (tp + 0.0) / tj;
        for (int i = 0; i < tablaCociente.getRowCount(); i++) {
            
            String nombre = (String) tablaCociente.getValueAt(i, 2);
            String url = "ligamx/resources/images/" + nombre + ".png";
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(url)));
            icon.setImage(icon.getImage().getScaledInstance(17, 17, Image.SCALE_DEFAULT));
            tablaCociente.setValueAt(icon, i, 1);
            int pPf = 17 - (Integer.parseInt(tablaCociente.getValueAt(i, 16).toString()));
            int tpf = Integer.parseInt(tablaCociente.getValueAt(i, 9).toString());
            int tjf = Integer.parseInt(tablaCociente.getValueAt(i, 17).toString()) + pPf;
            double cocf = (tpf + 0.0) / tjf;
            if (cocf <= tcoc) {
                tablaCociente.setValueAt("☒", i, 19);
            } else {
                tablaCociente.setValueAt("☑", i, 19);
            }

        }
    }

    public void CalcularCuartos() {
//        int ida, vuelta, global, fila, global1, global2, vis1, vis2;
//        String local, sql = "";
//        int ml, mv;
//        int[] pos = new int[4];
//        String[] equipos = new String[4];
//
//        for (fila = 0; fila < 8; fila++) {
//            ida = Integer.parseInt(tablaCuartos.getValueAt(fila, 2).toString());
//            vuelta = Integer.parseInt(tablaCuartos.getValueAt(fila, 3).toString());
//
//            global = ida + vuelta;
//            tablaCuartos.setValueAt(global, fila, 4);
//
//        }
//        for (fila = 0; fila < 8; fila += 2) {
//            global1 = Integer.parseInt(tablaCuartos.getValueAt(fila, 4).toString());
//            global2 = Integer.parseInt(tablaCuartos.getValueAt(fila + 1, 4).toString());
//            if (global1 > global2) {
//                pos[fila / 2] = Integer.parseInt(tablaCuartos.getValueAt(fila, 0).toString());
//            } else if (global2 > global1) {
//                pos[fila / 2] = Integer.parseInt(tablaCuartos.getValueAt(fila + 1, 0).toString());
//            } else if (global1 == global2) {
//                vis1 = Integer.parseInt(tablaCuartos.getValueAt(fila, 2).toString());
//                vis2 = Integer.parseInt(tablaCuartos.getValueAt(fila + 1, 3).toString());
//                if (vis1 > vis2 || vis1 == vis2) {
//                    pos[fila / 2] = Integer.parseInt(tablaCuartos.getValueAt(fila, 0).toString());
//                } else if (vis2 > vis1) {
//                    pos[fila / 2] = Integer.parseInt(tablaCuartos.getValueAt(fila + 1, 0).toString());
//                }
//            }
//
//        }
//        Arrays.sort(pos);
//        for (int i = 0; i < 4; i++) {
//            for (fila = 0; fila < 8; fila++) {
//                if (pos[i] == Integer.parseInt(tablaCuartos.getValueAt(fila, 0).toString())) {
//                    equipos[i] = tablaCuartos.getValueAt(fila, 1).toString();
//                }
//            }
//        }
//        for (fila = 3; fila >= 0; fila--) {
//            tablaPosCuartos.setValueAt(equipos[fila], fila, 1);
//        }
//        try {
//            conectarBase();
//        } catch (SQLException ex) {
//            Logger.getLogger(LigaMXController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        for (int j = 0; j < 8; j += 2) {
//            try {
//                local = tablaCuartos.getValueAt(j + 1, 1).toString();
//                ml = Integer.parseInt(tablaCuartos.getValueAt((j + 1), 2).toString());
//                mv = Integer.parseInt(tablaCuartos.getValueAt(j, 2).toString());
//                sql = "Update C16 set ML = " + ml + ", MV=" + mv + " where JORNADA=18 and LOCAL='" + local + "'";
//                stmn.executeUpdate(sql);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 2 aqui1");
//            }
//            try {
//
//                local = tablaCuartos.getValueAt(j, 1).toString();
//                ml = Integer.parseInt(tablaCuartos.getValueAt(j, 3).toString());
//                mv = Integer.parseInt(tablaCuartos.getValueAt(j + 1, 3).toString());
//                sql = "Update C16 set ML = " + ml + ", MV=" + mv + " where JORNADA=18 and LOCAL='" + local + "'";
//                stmn.executeUpdate(sql);
//
//                // conn.close();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e + "\nError en tabla LIGA MX 2 aqui2");
//            }
//        }
//        //Semi();
    }

    private void lanzarExcepcion(ExcepcionAplicacion ex) {
        LOGGER.error(ex.getMessage());
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LigaMXController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LigaMXController().setVisible(true);
        });
    }

    public JFrame getjGol() {
        return jGol;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ACuartos;
    private javax.swing.JPanel Penales;
    private javax.swing.JMenuItem aFinal;
    private javax.swing.JMenuItem aSemi;
    private javax.swing.JButton btnCalcularCuartos;
    private javax.swing.JButton btnCalcularFinal;
    private javax.swing.JButton btnCalcularSemi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cAutogol;
    public javax.swing.JComboBox cJornada1;
    private javax.swing.JComboBox cLocal1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JFrame jGol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JMenuItem jmGrafica;
    private javax.swing.JRadioButton jrEquipo1;
    private javax.swing.JRadioButton jrEquipo2;
    private javax.swing.JTextField jtMinuto;
    private javax.swing.JList<String> lJugadores;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblLocal;
    private javax.swing.JLabel lblVisit;
    private javax.swing.JMenuItem menuAgrega;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JTextField nomJugBuscar;
    private javax.swing.JScrollPane panDescenso;
    private javax.swing.JPanel panGlobal;
    private javax.swing.JPanel panLiguilla;
    private javax.swing.JPanel panMarcador;
    private javax.swing.JTabbedPane panPrincipal;
    private javax.swing.JTabbedPane panSecundario;
    private javax.swing.JSlider sMinuto;
    private javax.swing.JTable tGoleadores;
    private javax.swing.JTable tablaCociente;
    public javax.swing.JTable tablaCuartos;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTable tablaEquipo;
    public javax.swing.JTable tablaFinal;
    public javax.swing.JTable tablaGeneral;
    private javax.swing.JTable tablaGeneral1;
    public javax.swing.JTable tablaJornada1;
    private javax.swing.JTable tablaPosCuartos;
    private javax.swing.JTable tablaPosSemi;
    public javax.swing.JTable tablaSemi;
    // End of variables declaration//GEN-END:variables

    private void agregarGol() {
        try {
            String equipoString;
            int column;
            Character autogol;
            GolDTO golDTO = new GolDTO();
            if (jrEquipo1.isSelected()) {
                equipoString = equipo1;
                column = 2;
            } else {
                equipoString = equipo2;
                column = 3;
            }
            JugadorDTO jugadorDTO = jugadorService.buscarJugadorPorNombre(lJugadores.getSelectedValue());
            EquipoDTO equipoDTO = equipoService.buscarEquipoporNombre(equipoString);
            if (cAutogol.isSelected()) {
                autogol = 'S';
            } else {
                autogol = 'N';
            }

            golDTO.setIdJugador(jugadorDTO.getIdJugador());
            golDTO.setIdEquipo(equipoDTO.getIdEquipo());
            golDTO.setMinuto(Integer.parseInt(jtMinuto.getText()));
            golDTO.setAutogol(autogol);
            PartidoDTO partidoDTO = partidoService.buscarPartidosporId(idpartido);
            golService.insertarGol(golDTO, partidoDTO);
            int m = Integer.parseInt(tablaJornada1.getValueAt(row, column).toString());
            tablaJornada1.setValueAt(m + 1, row, column);
            nomJugBuscar.setText("");
            ActualizarTabla();
            BuscarEquipo();
            tablaGoleadores();
            detalles(partidoDTO);
            jGol.setVisible(false);
            this.setEnabled(true);
            this.toFront();
        } catch (ExcepcionAplicacion ex) {
            lanzarExcepcion(ex);
        }
    }

    public void setPantallaCarga(PantallaCarga pantallaCarga) {
        this.pantallaCarga = pantallaCarga;
    }
    
}
