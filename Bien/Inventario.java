
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import oracle.jdbc.driver.OracleDriver;

public final class Inventario
        extends JFrame {

    public static Connection conn;
    public static Statement stmn;
    public static ResultSet rset;
    public static PreparedStatement pstmn;
    private String sql;
    String producto;
    JComboBox jcProductos = new JComboBox();
    DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
    NumberFormat dispFormat = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
    NumberFormat editFormat = NumberFormat.getNumberInstance(new Locale("es", "MX"));
    NumberFormatter dnFormat = new NumberFormatter(this.dispFormat);
    NumberFormatter enFormat = new NumberFormatter(this.editFormat);
    DefaultFormatterFactory currFactory = new DefaultFormatterFactory(this.dnFormat, this.dnFormat, this.enFormat);
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    
    public Inventario() {
        initComponents();
        llenarInventario();
        llenarClientes();
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("logo.png"));
        
        return retValue;
    }
    
    private void initComponents() {
        this.Productos = new JFrame();
        this.panProductos = new JPanel();
        this.lblDescProd = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.txtDescProd = new JTextArea();
        this.lblExistProd = new JLabel();
        this.jcbExistProd = new JComboBox();
        this.lblCostoProd = new JLabel();
        this.lblPrecioProd = new JLabel();
        this.btnAceptarProd = new JButton();
        this.txtCostoProd = new JFormattedTextField();
        this.txtPrecioProd = new JFormattedTextField();
        this.Clientes = new JFrame();
        this.panClientes = new JPanel();
        this.lblNombreCliente = new JLabel();
        this.lblSaldoCliente = new JLabel();
        this.txtNombreCliente = new JTextField();
        this.btnAceptarCliente = new JButton();
        this.txtSaldoCliente = new JFormattedTextField();
        this.BuscarClientes = new JFrame();
        this.panBuscarClientes = new JPanel();
        this.jScrollPane3 = new JScrollPane();
        this.listaClientes = new JList();
        this.btnAceptarBuscarClientes = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.jPanel1 = new JPanel();
        this.pnClientes = new JPanel();
        this.lblCliente1 = new JLabel();
        this.txtCliente1 = new JTextField();
        this.txtTotal1 = new JFormattedTextField();
        this.btnBuscarCliente1 = new JButton();
        this.jScrollPane5 = new JScrollPane();
        this.tbHistorial = new JTable();
        this.jLabel3 = new JLabel();
        this.jTabbedPane1 = new JTabbedPane();
        this.panVentas = new JPanel();
        this.lblCliente = new JLabel();
        this.txtCliente = new JTextField();
        this.btnBuscarCliente = new JButton();
        this.jLabel1 = new JLabel();
        this.fechaVenta = new JDateChooser();
        this.jScrollPane4 = new JScrollPane();
        this.tbVenta = new JTable();
        this.jLabel2 = new JLabel();
        this.txtTotal = new JFormattedTextField();
        this.btnAceptarVenta = new JButton();
        this.btnBorrar = new JButton();
        this.jLabel10 = new JLabel();
        this.txtAjuste = new JFormattedTextField();
        this.pnAbono = new JPanel();
        this.lblCliente2 = new JLabel();
        this.txtCliente2 = new JTextField();
        this.btnBuscarCliente2 = new JButton();
        this.jLabel4 = new JLabel();
        this.txtAbono = new JFormattedTextField();
        this.jLabel5 = new JLabel();
        this.fechaAbono = new JDateChooser();
        this.btnAceptarPago = new JButton();
        this.jPanel2 = new JPanel();
        this.jScrollPane6 = new JScrollPane();
        this.tbInventario = new JTable();
        this.pnAbono1 = new JPanel();
        this.jScrollPane7 = new JScrollPane();
        this.tbClientes = new JTable();
        this.jLabel6 = new JLabel();
        this.txtTotalVentas = new JFormattedTextField();
        this.jLabel7 = new JLabel();
        this.txtTotalDeudas = new JFormattedTextField();
        this.Menu = new JMenuBar();
        this.menuArchivo = new JMenu();
        this.mNuevoProd = new JMenuItem();
        this.mnCliente = new JMenuItem();
        this.mEditCliente = new JMenuItem();
        this.jMenuItem2 = new JMenuItem();
        this.jMenu2 = new JMenu();
        this.mnAcerca = new JMenuItem();
        
        this.Productos.setTitle("Nuevo Producto");
        this.Productos.setAlwaysOnTop(true);
        this.Productos.setIconImage(getIconImage());
        this.Productos.setResizable(false);
        this.Productos.setType(Window.Type.POPUP);
        
        this.lblDescProd.setText("Descripción");
        
        this.txtDescProd.setColumns(20);
        this.txtDescProd.setRows(2);
        this.txtDescProd.setName("");
        this.jScrollPane2.setViewportView(this.txtDescProd);
        
        this.lblExistProd.setText("Existencias");
        
        this.jcbExistProd.setEditable(true);
        this.jcbExistProd.setMaximumRowCount(10);
        this.jcbExistProd.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"}));
        
        this.lblCostoProd.setText("Costo");
        
        this.lblPrecioProd.setText("Precio");
        
        this.btnAceptarProd.setText("Aceptar");
        this.btnAceptarProd.addActionListener(Inventario.this::btnAceptarProdActionPerformed);
        this.txtCostoProd.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
        this.txtCostoProd.setFocusCycleRoot(true);
        this.txtCostoProd.setValue(0);
        
        this.txtPrecioProd.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
        this.txtPrecioProd.setFocusCycleRoot(true);
        this.txtPrecioProd.setValue(0);
        
        GroupLayout panProductosLayout = new GroupLayout(this.panProductos);
        this.panProductos.setLayout(panProductosLayout);
        panProductosLayout.setHorizontalGroup(panProductosLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panProductosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panProductosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(panProductosLayout.createSequentialGroup()
                                        .addGroup(panProductosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.lblDescProd)
                                                .addComponent(this.lblExistProd)
                                                .addComponent(this.lblCostoProd)
                                                .addComponent(this.lblPrecioProd))
                                        .addGap(29, 29, 29)
                                        .addGroup(panProductosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(panProductosLayout.createSequentialGroup()
                                                        .addGroup(panProductosLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(this.txtPrecioProd, -1, 70, 32767)
                                                                .addComponent(this.txtCostoProd))
                                                        .addContainerGap(-1, 32767))
                                                .addGroup(panProductosLayout.createSequentialGroup()
                                                        .addGroup(panProductosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(this.jScrollPane2, -2, 260, -2)
                                                                .addComponent(this.jcbExistProd, -2, 54, -2))
                                                        .addGap(0, 0, 32767))))
                                .addGroup(GroupLayout.Alignment.TRAILING, panProductosLayout.createSequentialGroup()
                                        .addComponent(this.btnAceptarProd)
                                        .addGap(134, 134, 134)))));
        
        panProductosLayout.setVerticalGroup(panProductosLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panProductosLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(panProductosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.lblDescProd)
                                .addComponent(this.jScrollPane2, -2, 40, -2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panProductosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.lblExistProd)
                                .addComponent(this.jcbExistProd, -2, -1, -2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panProductosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.lblCostoProd)
                                .addComponent(this.txtCostoProd, -2, -1, -2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panProductosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.lblPrecioProd)
                                .addComponent(this.txtPrecioProd, -2, -1, -2))
                        .addGap(18, 18, 18)
                        .addComponent(this.btnAceptarProd)
                        .addGap(0, 0, 0)));
        
        this.txtCostoProd.setFormatterFactory(this.currFactory);
        this.txtPrecioProd.setFormatterFactory(this.currFactory);
        
        GroupLayout ProductosLayout = new GroupLayout(this.Productos.getContentPane());
        this.Productos.getContentPane().setLayout(ProductosLayout);
        ProductosLayout.setHorizontalGroup(ProductosLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ProductosLayout.createSequentialGroup()
                        .addComponent(this.panProductos, -2, -1, -2)
                        .addContainerGap(-1, 32767)));
        
        ProductosLayout.setVerticalGroup(ProductosLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.panProductos, -1, -1, 32767));
        
        this.Clientes.setTitle("Nuevo Cliente");
        this.Clientes.setAlwaysOnTop(true);
        this.Clientes.setIconImage(getIconImage());
        this.Clientes.setResizable(false);
        this.Clientes.setType(Window.Type.POPUP);
        
        this.lblNombreCliente.setText("Nombre");
        
        this.lblSaldoCliente.setText("Saldo");
        
        this.btnAceptarCliente.setText("Aceptar");
        this.btnAceptarCliente.addActionListener(Inventario.this::btnAceptarClienteActionPerformed);
        this.txtSaldoCliente.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
        this.txtSaldoCliente.setValue(0);
        
        GroupLayout panClientesLayout = new GroupLayout(this.panClientes);
        this.panClientes.setLayout(panClientesLayout);
        panClientesLayout.setHorizontalGroup(panClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panClientesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.lblNombreCliente)
                                .addComponent(this.lblSaldoCliente))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panClientesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.txtNombreCliente, -2, 238, -2)
                                .addComponent(this.txtSaldoCliente, -2, 63, -2))
                        .addGap(0, 47, 32767))
                .addGroup(GroupLayout.Alignment.TRAILING, panClientesLayout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(this.btnAceptarCliente)
                        .addGap(119, 119, 119)));
        
        panClientesLayout.setVerticalGroup(panClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panClientesLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(panClientesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.txtNombreCliente, -2, -1, -2)
                                .addComponent(this.lblNombreCliente))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panClientesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.txtSaldoCliente, -2, -1, -2)
                                .addComponent(this.lblSaldoCliente))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.btnAceptarCliente)));
        
        this.txtSaldoCliente.setFormatterFactory(this.currFactory);
        
        GroupLayout ClientesLayout = new GroupLayout(this.Clientes.getContentPane());
        this.Clientes.getContentPane().setLayout(ClientesLayout);
        ClientesLayout.setHorizontalGroup(ClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ClientesLayout.createSequentialGroup()
                        .addComponent(this.panClientes, -2, -1, -2)
                        .addGap(0, 0, 0)));
        
        ClientesLayout.setVerticalGroup(ClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.panClientes, -2, -1, -2));
        
        this.BuscarClientes.setTitle("Buscar Clientes");
        this.BuscarClientes.setAlwaysOnTop(true);
        this.BuscarClientes.setIconImage(getIconImage());
        this.BuscarClientes.setResizable(false);
        this.BuscarClientes.setType(Window.Type.POPUP);
        
        this.listaClientes.setSelectionMode(0);
        this.listaClientes.setVisibleRowCount(10);
        this.jScrollPane3.setViewportView(this.listaClientes);
        
        this.btnAceptarBuscarClientes.setText("Aceptar");
        this.btnAceptarBuscarClientes.addActionListener(Inventario.this::btnAceptarBuscarClientesActionPerformed);
        GroupLayout panBuscarClientesLayout = new GroupLayout(this.panBuscarClientes);
        this.panBuscarClientes.setLayout(panBuscarClientesLayout);
        panBuscarClientesLayout.setHorizontalGroup(panBuscarClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panBuscarClientesLayout.createSequentialGroup()
                        .addComponent(this.jScrollPane3, -2, 300, -2)
                        .addGap(0, 0, 32767))
                .addGroup(panBuscarClientesLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(this.btnAceptarBuscarClientes)
                        .addContainerGap(-1, 32767)));
        
        panBuscarClientesLayout.setVerticalGroup(panBuscarClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panBuscarClientesLayout.createSequentialGroup()
                        .addComponent(this.jScrollPane3, -2, 265, -2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.btnAceptarBuscarClientes)
                        .addGap(0, 11, 32767)));
        
        GroupLayout BuscarClientesLayout = new GroupLayout(this.BuscarClientes.getContentPane());
        this.BuscarClientes.getContentPane().setLayout(BuscarClientesLayout);
        BuscarClientesLayout.setHorizontalGroup(BuscarClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.panBuscarClientes, -1, -1, 32767));
        
        BuscarClientesLayout.setVerticalGroup(BuscarClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.panBuscarClientes, -1, -1, 32767));
        
        setDefaultCloseOperation(3);
        setTitle("Inventario");
        setIconImage(getIconImage());
        setResizable(false);
        
        this.jScrollPane1.setToolTipText("");
        
        this.jPanel1.setAutoscrolls(true);
        
        this.pnClientes.setBackground(new Color(51, 255, 102));
        this.pnClientes.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        
        this.lblCliente1.setText("Cliente");
        
        this.txtCliente1.setEditable(false);
        
        this.txtTotal1.setEditable(false);
        this.txtTotal1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
        this.txtTotal1.setHorizontalAlignment(0);
        this.txtTotal1.setFocusCycleRoot(true);
        this.txtTotal1.setValue(0);
        
        this.btnBuscarCliente1.setText("Buscar");
        this.btnBuscarCliente1.addActionListener((ActionEvent evt) -> {
            Inventario.this.btnBuscarClienteActionPerformed(evt);
        });
        this.tbHistorial.setModel(new DefaultTableModel(new Object[0][], new String[]{"Cantidad", "Descripción", "Total", "Fecha"}) {
            Class[] types = {Integer.class, String.class, Float.class, Object.class};
            boolean[] canEdit = {false, false, false, false};
            
            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tbHistorial.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane5.setViewportView(this.tbHistorial);
        if (this.tbHistorial.getColumnModel().getColumnCount() > 0) {
            this.tbHistorial.getColumnModel().getColumn(0).setResizable(false);
            this.tbHistorial.getColumnModel().getColumn(0).setPreferredWidth(30);
            this.tbHistorial.getColumnModel().getColumn(1).setResizable(false);
            this.tbHistorial.getColumnModel().getColumn(1).setPreferredWidth(150);
            this.tbHistorial.getColumnModel().getColumn(2).setResizable(false);
            this.tbHistorial.getColumnModel().getColumn(2).setPreferredWidth(10);
            this.tbHistorial.getColumnModel().getColumn(3).setResizable(false);
            this.tbHistorial.getColumnModel().getColumn(3).setPreferredWidth(40);
        }
        this.jLabel3.setText("Saldo");
        
        GroupLayout pnClientesLayout = new GroupLayout(this.pnClientes);
        this.pnClientes.setLayout(pnClientesLayout);
        pnClientesLayout.setHorizontalGroup(pnClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnClientesLayout.createSequentialGroup()
                        .addComponent(this.lblCliente1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.txtCliente1, -2, 250, -2)
                        .addGap(31, 31, 31)
                        .addComponent(this.btnBuscarCliente1)
                        .addContainerGap(-1, 32767))
                .addGroup(GroupLayout.Alignment.TRAILING, pnClientesLayout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addGroup(pnClientesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, pnClientesLayout.createSequentialGroup()
                                        .addComponent(this.jLabel3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(this.txtTotal1, -2, 95, -2)
                                        .addGap(76, 76, 76))
                                .addComponent(this.jScrollPane5, GroupLayout.Alignment.TRAILING, -2, 404, -2))));
        
        pnClientesLayout.setVerticalGroup(pnClientesLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnClientesLayout.createSequentialGroup()
                        .addGroup(pnClientesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.lblCliente1)
                                .addComponent(this.txtCliente1, -2, -1, -2)
                                .addComponent(this.btnBuscarCliente1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.jScrollPane5, -1, 294, 32767)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnClientesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.jLabel3)
                                .addComponent(this.txtTotal1, -2, -1, -2))
                        .addGap(9, 9, 9)));
        
        this.txtTotal1.setFormatterFactory(this.currFactory);
        
        this.panVentas.setBackground(new Color(204, 0, 0));
        this.panVentas.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        
        this.lblCliente.setText("Cliente");
        
        this.txtCliente.setEditable(false);
        
        this.btnBuscarCliente.setText("Buscar");
        this.btnBuscarCliente.addActionListener((ActionEvent evt) -> {
            Inventario.this.btnBuscarClienteActionPerformed(evt);
        });
        this.jLabel1.setText("Fecha");
        
        this.fechaVenta.setToolTipText("");
        this.fechaVenta.setDateFormatString("dd/MM/yyyy");
        this.fechaVenta.setMaxSelectableDate(new java.util.Date());
        this.fechaVenta.setDate(new java.util.Date());
        this.fechaVenta.setMinSelectableDate(new java.util.Date(-62135744299000L));
        
        this.tbVenta.setModel(new DefaultTableModel(new Object[][]{{null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}}, new String[]{"Cantidad", "Producto", "Precio", "Total"}) {
            Class[] types = {Object.class, Object.class, Float.class, Float.class};
            boolean[] canEdit = {true, true, false, false};
            
            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tbVenta.getTableHeader().setReorderingAllowed(false);
        this.tbVenta.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            Inventario.this.tbVentaPropertyChange(evt);
        });
        this.jScrollPane4.setViewportView(this.tbVenta);
        String[] cant = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
        TableColumn col0 = this.tbVenta.getColumnModel().getColumn(0);
        JComboBox jcCantidad = new JComboBox(cant);
        col0.setCellEditor(new DefaultCellEditor(jcCantidad));
        llenarProductos();
        if (this.tbVenta.getColumnModel().getColumnCount() > 0) {
            this.tbVenta.getColumnModel().getColumn(0).setResizable(false);
            this.tbVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            this.tbVenta.getColumnModel().getColumn(1).setResizable(false);
            this.tbVenta.getColumnModel().getColumn(1).setPreferredWidth(200);
            this.tbVenta.getColumnModel().getColumn(2).setResizable(false);
            this.tbVenta.getColumnModel().getColumn(2).setPreferredWidth(11);
            this.tbVenta.getColumnModel().getColumn(3).setResizable(false);
            this.tbVenta.getColumnModel().getColumn(3).setPreferredWidth(10);
        }
        this.jLabel2.setText("Total");
        
        this.txtTotal.setEditable(false);
        this.txtTotal.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
        this.txtTotal.setHorizontalAlignment(0);
        this.txtTotal.setFocusCycleRoot(true);
        this.txtTotal.setValue(0);
        this.txtTotal.addActionListener((ActionEvent evt) -> {
            Inventario.this.txtTotalActionPerformed(evt);
        });
        this.btnAceptarVenta.setText("Aceptar");
        this.btnAceptarVenta.addActionListener((ActionEvent evt) -> {
            Inventario.this.btnAceptarVentaActionPerformed(evt);
        });
        this.btnBorrar.setText("Borrar");
        this.btnBorrar.addActionListener((ActionEvent evt) -> {
            Inventario.this.btnBorrarActionPerformed(evt);
        });
        this.jLabel10.setText("Ajuste");
        
        this.txtAjuste.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
        this.txtAjuste.setHorizontalAlignment(0);
        this.txtAjuste.setFocusCycleRoot(true);
        this.txtAjuste.setValue(0);
        this.txtAjuste.addInputMethodListener(new InputMethodListener() {
            @Override
            public void caretPositionChanged(InputMethodEvent evt) {
            }
            
            @Override
            public void inputMethodTextChanged(InputMethodEvent evt) {
                Inventario.this.txtAjusteInputMethodTextChanged(evt);
            }
        });
        this.txtAjuste.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            Inventario.this.txtAjustePropertyChange(evt);
        });
        GroupLayout panVentasLayout = new GroupLayout(this.panVentas);
        this.panVentas.setLayout(panVentasLayout);
        panVentasLayout.setHorizontalGroup(panVentasLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panVentasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panVentasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(this.jLabel1)
                                .addComponent(this.lblCliente))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panVentasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(panVentasLayout.createSequentialGroup()
                                        .addComponent(this.txtCliente, -2, 250, -2)
                                        .addGap(31, 31, 31)
                                        .addComponent(this.btnBuscarCliente))
                                .addComponent(this.fechaVenta, -2, 133, -2))
                        .addContainerGap(-1, 32767))
                .addComponent(this.jScrollPane4, GroupLayout.Alignment.TRAILING, -2, 0, 32767)
                .addGroup(GroupLayout.Alignment.TRAILING, panVentasLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(this.btnBorrar)
                        .addGap(18, 18, 18)
                        .addComponent(this.btnAceptarVenta)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
                        .addGroup(panVentasLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(panVentasLayout.createSequentialGroup()
                                        .addComponent(this.jLabel10)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
                                        .addComponent(this.txtAjuste, -2, 101, -2))
                                .addGroup(panVentasLayout.createSequentialGroup()
                                        .addComponent(this.jLabel2)
                                        .addGap(25, 25, 25)
                                        .addComponent(this.txtTotal, -2, 101, -2)))
                        .addContainerGap()));
        
        panVentasLayout.setVerticalGroup(panVentasLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panVentasLayout.createSequentialGroup()
                        .addGroup(panVentasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.lblCliente)
                                .addComponent(this.txtCliente, -2, -1, -2)
                                .addComponent(this.btnBuscarCliente))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panVentasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.jLabel1)
                                .addComponent(this.fechaVenta, -2, -1, -2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.jScrollPane4, -2, 193, -2)
                        .addGap(13, 13, 13)
                        .addGroup(panVentasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.txtAjuste, -2, -1, -2)
                                .addComponent(this.jLabel10))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panVentasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.txtTotal, -2, -1, -2)
                                .addComponent(this.jLabel2)
                                .addComponent(this.btnBorrar)
                                .addComponent(this.btnAceptarVenta))
                        .addGap(0, 0, 0)));
        
        this.txtTotal.setFormatterFactory(this.currFactory);
        this.txtAjuste.setFormatterFactory(this.currFactory);
        
        this.jTabbedPane1.addTab("Ventas", this.panVentas);
        
        this.pnAbono.setBackground(new Color(51, 204, 255));
        this.pnAbono.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        
        this.lblCliente2.setText("Cliente");
        
        this.txtCliente2.setEditable(false);
        
        this.btnBuscarCliente2.setText("Buscar");
        this.btnBuscarCliente2.addActionListener((ActionEvent evt) -> {
            Inventario.this.btnBuscarClienteActionPerformed(evt);
        });
        this.jLabel4.setText("Abono");
        
        this.txtAbono.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
        this.txtAbono.setHorizontalAlignment(0);
        this.txtAbono.setFocusCycleRoot(true);
        this.txtAbono.setValue(0);
        
        this.jLabel5.setText("Fecha");
        
        this.fechaAbono.setToolTipText("");
        this.fechaAbono.setDateFormatString("dd/MM/yyyy");
        this.fechaAbono.setMaxSelectableDate(new java.util.Date());
        this.fechaAbono.setDate(new java.util.Date());
        this.fechaAbono.setMinSelectableDate(new java.util.Date(-62135744299000L));
        
        this.btnAceptarPago.setText("Aceptar");
        this.btnAceptarPago.addActionListener((ActionEvent evt) -> {
            Inventario.this.btnAceptarPagoActionPerformed(evt);
        });
        GroupLayout pnAbonoLayout = new GroupLayout(this.pnAbono);
        this.pnAbono.setLayout(pnAbonoLayout);
        pnAbonoLayout.setHorizontalGroup(pnAbonoLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnAbonoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnAbonoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnAbonoLayout.createSequentialGroup()
                                        .addComponent(this.lblCliente2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtCliente2, -2, 250, -2)
                                        .addGap(31, 31, 31)
                                        .addComponent(this.btnBuscarCliente2))
                                .addGroup(pnAbonoLayout.createSequentialGroup()
                                        .addGroup(pnAbonoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(pnAbonoLayout.createSequentialGroup()
                                                        .addGap(4, 4, 4)
                                                        .addComponent(this.jLabel5))
                                                .addComponent(this.jLabel4))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnAbonoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.txtAbono, -2, 95, -2)
                                                .addComponent(this.fechaAbono, -2, 133, -2)))
                                .addGroup(pnAbonoLayout.createSequentialGroup()
                                        .addGap(159, 159, 159)
                                        .addComponent(this.btnAceptarPago)))
                        .addContainerGap(-1, 32767)));
        
        pnAbonoLayout.setVerticalGroup(pnAbonoLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnAbonoLayout.createSequentialGroup()
                        .addGroup(pnAbonoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.lblCliente2)
                                .addComponent(this.txtCliente2, -2, -1, -2)
                                .addComponent(this.btnBuscarCliente2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnAbonoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.jLabel5)
                                .addComponent(this.fechaAbono, -2, -1, -2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnAbonoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.txtAbono, -2, -1, -2)
                                .addComponent(this.jLabel4))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.btnAceptarPago)
                        .addContainerGap()));
        
        this.txtAbono.setFormatterFactory(this.currFactory);
        
        this.jTabbedPane1.addTab("Pagos", this.pnAbono);
        
        this.jPanel2.setBackground(new Color(255, 102, 0));
        this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        
        this.tbInventario.setModel(new DefaultTableModel(new Object[0][], new String[]{"Producto", "Existencias", "Costo", "Precio"}) {
            Class[] types = {String.class, Integer.class, Float.class, Float.class};
            boolean[] canEdit = {false, true, false, false};
            
            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tbInventario.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane6.setViewportView(this.tbInventario);
        if (this.tbInventario.getColumnModel().getColumnCount() > 0) {
            this.tbInventario.getColumnModel().getColumn(0).setResizable(false);
            this.tbInventario.getColumnModel().getColumn(0).setPreferredWidth(150);
            this.tbInventario.getColumnModel().getColumn(1).setResizable(false);
            this.tbInventario.getColumnModel().getColumn(1).setPreferredWidth(30);
            this.tbInventario.getColumnModel().getColumn(2).setResizable(false);
            this.tbInventario.getColumnModel().getColumn(2).setPreferredWidth(10);
            this.tbInventario.getColumnModel().getColumn(3).setResizable(false);
            this.tbInventario.getColumnModel().getColumn(3).setPreferredWidth(10);
        }
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.jScrollPane6, -1, 23, 32767));
        
        jPanel2Layout.setVerticalGroup(jPanel2Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.jScrollPane6, -1, 359, 32767));
        
        this.jTabbedPane1.addTab("Productos", this.jPanel2);
        
        this.pnAbono1.setBackground(new Color(51, 204, 255));
        this.pnAbono1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        
        this.tbClientes.setModel(new DefaultTableModel(new Object[0][], new String[]{"Nombre", "Saldo"}) {
            Class[] types = {String.class, Float.class};
            boolean[] canEdit = {false, false};
            
            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jScrollPane7.setViewportView(this.tbClientes);
        if (this.tbClientes.getColumnModel().getColumnCount() > 0) {
            this.tbClientes.getColumnModel().getColumn(0).setResizable(false);
            this.tbClientes.getColumnModel().getColumn(1).setResizable(false);
            this.tbClientes.getColumnModel().getColumn(1).setPreferredWidth(10);
        }
        GroupLayout pnAbono1Layout = new GroupLayout(this.pnAbono1);
        this.pnAbono1.setLayout(pnAbono1Layout);
        pnAbono1Layout.setHorizontalGroup(pnAbono1Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, pnAbono1Layout.createSequentialGroup()
                        .addComponent(this.jScrollPane7, -2, 404, -2)
                        .addGap(0, 0, 32767)));
        
        pnAbono1Layout.setVerticalGroup(pnAbono1Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.jScrollPane7, -1, 359, 32767));
        
        this.jTabbedPane1.addTab("Clientes", this.pnAbono1);
        
        this.jLabel6.setText("Total Ventas");
        
        this.txtTotalVentas.setEditable(false);
        this.txtTotalVentas.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
        this.txtTotalVentas.setHorizontalAlignment(0);
        this.txtTotalVentas.setFocusCycleRoot(true);
        this.txtTotalVentas.setValue(0);
        
        this.jLabel7.setText("Total Deudas");
        
        this.txtTotalDeudas.setEditable(false);
        this.txtTotalDeudas.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
        this.txtTotalDeudas.setHorizontalAlignment(0);
        this.txtTotalDeudas.setFocusCycleRoot(true);
        this.txtTotalDeudas.setValue(0);
        
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(this.jTabbedPane1, -2, 410, -2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.pnClientes, -2, -1, -2)
                        .addGap(0, 0, 0))
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(this.jLabel6)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.txtTotalVentas, -2, 95, -2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
                        .addComponent(this.jLabel7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.txtTotalDeudas, -2, 95, -2)
                        .addGap(101, 101, 101)));
        
        jPanel1Layout.setVerticalGroup(jPanel1Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.jTabbedPane1, -2, 389, -2)
                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(this.pnClientes, -2, -1, -2)))
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.jLabel6)
                                .addComponent(this.txtTotalVentas, -2, -1, -2)
                                .addComponent(this.jLabel7)
                                .addComponent(this.txtTotalDeudas, -2, -1, -2))
                        .addGap(0, 0, 0)));
        
        this.txtTotalVentas.setFormatterFactory(this.currFactory);
        this.txtTotalDeudas.setFormatterFactory(this.currFactory);
        
        this.jScrollPane1.setViewportView(this.jPanel1);
        
        this.menuArchivo.setMnemonic('A');
        this.menuArchivo.setText("Archivo");
        
        this.mNuevoProd.setAccelerator(KeyStroke.getKeyStroke(80, 8));
        this.mNuevoProd.setIcon(new ImageIcon(getClass().getResource("/nuevoProd.png")));
        this.mNuevoProd.setMnemonic('P');
        this.mNuevoProd.setText("Nuevo Producto");
        this.mNuevoProd.addActionListener((ActionEvent evt) -> {
            Inventario.this.mNuevoProdActionPerformed(evt);
        });
        this.menuArchivo.add(this.mNuevoProd);
        
        this.mnCliente.setAccelerator(KeyStroke.getKeyStroke(67, 8));
        this.mnCliente.setIcon(new ImageIcon(getClass().getResource("/nuevoCliente.png")));
        this.mnCliente.setMnemonic('C');
        this.mnCliente.setText("Nuevo Cliente");
        this.mnCliente.addActionListener((ActionEvent evt) -> {
            Inventario.this.mnClienteActionPerformed(evt);
        });
        this.menuArchivo.add(this.mnCliente);
        
        this.mEditCliente.setIcon(new ImageIcon(getClass().getResource("/Edit-Male-User-icon.png")));
        this.mEditCliente.setText("Editar Cliente");
        this.mEditCliente.addActionListener((ActionEvent evt) -> {
            Inventario.this.btnBuscarClienteActionPerformed(evt);
        });
        this.menuArchivo.add(this.mEditCliente);
        
        this.jMenuItem2.setAccelerator(KeyStroke.getKeyStroke(115, 8));
        this.jMenuItem2.setIcon(new ImageIcon(getClass().getResource("/salir.png")));
        this.jMenuItem2.setText("Salir");
        this.jMenuItem2.addActionListener((ActionEvent evt) -> {
            Inventario.this.jMenuItem2ActionPerformed(evt);
        });
        this.menuArchivo.add(this.jMenuItem2);
        
        this.Menu.add(this.menuArchivo);
        
        this.jMenu2.setText("Ayuda");
        
        this.mnAcerca.setIcon(new ImageIcon(getClass().getResource("/about.png")));
        this.mnAcerca.setText("Acerca de");
        this.mnAcerca.addActionListener((ActionEvent evt) -> {
            Inventario.this.mnAcercaActionPerformed(evt);
        });
        this.jMenu2.add(this.mnAcerca);
        
        this.Menu.add(this.jMenu2);
        
        setJMenuBar(this.Menu);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(-1, 32767)
                        .addComponent(this.jScrollPane1, -2, 848, -2)
                        .addContainerGap()));
        
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.jScrollPane1, -2, 439, -2));
        
        pack();
    }
    
    public void conectarBase()
            throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "inventario", "1234");
        stmn = conn.createStatement();
    }
    
    public void llenarProductos() {
        try {
            conectarBase();
            TableColumn col = this.tbVenta.getColumnModel().getColumn(1);
            rset = stmn.executeQuery("SELECT DESCRIPCIONPROD FROM PRODUCTOS WHERE DESCRIPCIONPROD != 'Ajuste' ORDER BY DESCRIPCIONPROD ");
            this.comboModel.removeAllElements();
            while (rset.next()) {
                this.producto = rset.getString("DESCRIPCIONPROD");
                this.comboModel.addElement(this.producto);
            }
            this.jcProductos.setModel(this.comboModel);
            conn.close();
            col.setCellEditor(new DefaultCellEditor(this.jcProductos));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + "\nError en tabla Productos");
        }
    }
    
    private void llenarInventario() {
        try {
            conectarBase();
            rset = stmn.executeQuery("SELECT SUM(ACUMULADOCLIENTE) FROM CLIENTES");
            while (rset.next()) {
                this.txtTotalDeudas.setValue(rset.getFloat(1));
            }
            rset = stmn.executeQuery("SELECT SUM(TOTALVENTA) FROM VENTAS");
            while (rset.next()) {
                this.txtTotalVentas.setValue(rset.getFloat(1));
            }
            DefaultTableModel dm = (DefaultTableModel) this.tbInventario.getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            rset = stmn.executeQuery("SELECT DESCRIPCIONPROD, EXISTENCIAS, COSTOUNITARIO, PRECIOUNITARIO FROM PRODUCTOS WHERE DESCRIPCIONPROD != 'Ajuste' ORDER BY DESCRIPCIONPROD");
            while (rset.next()) {
                String Descripcion = rset.getString(1);
                int Existencias = rset.getInt(2);
                float Costo = rset.getFloat(3);
                float Precio = rset.getFloat(4);
                Vector row = new Vector();
                row.add(Descripcion);
                row.add(Existencias);
                row.add(Costo);
                row.add(Precio);
                dm.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + "\nError en tablas");
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void llenarClientes() {
        try {
            conectarBase();
            DefaultTableModel dm = (DefaultTableModel) this.tbClientes.getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            rset = stmn.executeQuery("SELECT NOMBRECLIENTE, ACUMULADOCLIENTE FROM CLIENTES ORDER BY NOMBRECLIENTE");
            while (rset.next()) {
                String nombre = rset.getString(1);
                float acumulado = rset.getFloat(2);
                Vector row = new Vector();
                row.add(nombre);
                row.add(acumulado);
                dm.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + "\nError en tablas");
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void llenarHistorial() {
        int NumCliente = 0;
        String Cliente = this.txtCliente1.getText();
        
        float Acumulado = 0.0F;
        try {
            conectarBase();
            rset = stmn.executeQuery("SELECT NUMCLIENTE,ACUMULADOCLIENTE FROM CLIENTES WHERE NOMBRECLIENTE='" + Cliente + "'");
            while (rset.next()) {
                NumCliente = rset.getInt(1);
                Acumulado = rset.getFloat(2);
            }
            DefaultTableModel dm = (DefaultTableModel) this.tbHistorial.getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            rset = stmn.executeQuery("SELECT D.DETVENTACANTIDAD AS CANTIDAD , P.DESCRIPCIONPROD AS PRODUCTO, D.DETVENTASUBTOTAL AS TOTAL, V.FECHAVENTA AS FECHA FROM CLIENTES C INNER JOIN VENTAS V ON C.NUMCLIENTE=V.NUMCLIENTE INNER JOIN DETALLEVENTAS D ON V.NUMVENTA=D.NUMVENTA INNER JOIN PRODUCTOS P ON D.CLAVEPROD=P.CLAVEPROD WHERE C.NUMCLIENTE=" + NumCliente + " " + "UNION ALL " + "SELECT NULL AS CANTIDAD, 'Pago' AS TOTAL, A.PAGO AS TOTAL, A.FECHA " + "FROM PAGOS A INNER JOIN CLIENTES C " + "ON C.NUMCLIENTE=A.NUMCLIENTE " + "WHERE C.NUMCLIENTE=" + NumCliente + " " + "ORDER BY FECHA DESC");
            while (rset.next()) {
                int cantidad = rset.getInt(1);
                String descripcion = rset.getString(2);
                float total = rset.getFloat(3);
                java.util.Date fecha = rset.getDate(4);
                
                Vector row = new Vector();
                row.add(cantidad);
                row.add(descripcion);
                row.add(total);
                row.add(this.df.format(fecha));
                dm.addRow(row);
            }
            this.txtTotal1.setValue(Acumulado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + "\nError en tablas");
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
      
    private void mNuevoProdActionPerformed(ActionEvent evt) {
        this.Productos.setSize(381, 232);
        this.Productos.setVisible(true);
    }
    
    private void btnAceptarProdActionPerformed(ActionEvent evt) {
        String descProd = this.txtDescProd.getText();
        int existProd = Integer.parseInt(this.jcbExistProd.getSelectedItem().toString());
        float costoProd = Float.parseFloat(this.txtCostoProd.getValue().toString());
        float precioProd = Float.parseFloat(this.txtPrecioProd.getValue().toString());
        String error = "";
        if (descProd.isEmpty()) {
            error = error + "No se ha insertado descripción\n";
        }
        if (existProd == 0) {
            error = error + "No se han a�adido productos\n";
        }
        if (costoProd == 0.0F) {
            error = error + "No se ha insertado costo de producto\n";
        }
        if (precioProd == 0.0F) {
            error = error + "No se ha insertado precio de producto\n";
        }
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(this.Productos, error, "Error", 0);
        } else {
            try {
                conectarBase();
                this.sql = "INSERT INTO PRODUCTOS (DESCRIPCIONPROD,EXISTENCIAS,COSTOUNITARIO,PRECIOUNITARIO) VALUES (?,?,?,?)";
                pstmn = conn.prepareStatement(this.sql);
                pstmn.setString(1, descProd);
                pstmn.setInt(2, existProd);
                pstmn.setFloat(3, costoProd);
                pstmn.setFloat(4, precioProd);
                pstmn.executeUpdate();
                JOptionPane.showMessageDialog(this.Productos, "Producto insertado", null, 1);
                this.txtDescProd.setText(null);
                this.jcbExistProd.setSelectedItem(0);
                this.txtCostoProd.setValue(0);
                this.txtPrecioProd.setValue(0);
                this.Productos.setVisible(false);
                toFront();
                llenarProductos();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this.Productos, "Error al insertar nuevo producto", "Error", 0);
            }
            llenarInventario();
            llenarClientes();
        }
    }
    
    private void mnClienteActionPerformed(ActionEvent evt) {
        this.Clientes.setSize(333, 173);
        this.Clientes.setVisible(true);
    }
    
    private void btnAceptarClienteActionPerformed(ActionEvent evt) {
        String nombreCliente = this.txtNombreCliente.getText();
        float saldoCliente = Float.parseFloat(this.txtSaldoCliente.getValue().toString());
        String error;
        if (nombreCliente.isEmpty()) {
            error = "No se ha insertado nombre del cliente";
            JOptionPane.showMessageDialog(this.Clientes, error, "Error", 0);
        } else {
            try {
                conectarBase();
                this.sql = "INSERT INTO CLIENTES (NOMBRECLIENTE, ACUMULADOCLIENTE) VALUES (?,?)";
                pstmn = conn.prepareStatement(this.sql);
                pstmn.setString(1, nombreCliente);
                pstmn.setFloat(2, saldoCliente);
                pstmn.executeUpdate();
                JOptionPane.showMessageDialog(this.Clientes, "Cliente insertado", null, 1);
                this.txtNombreCliente.setText(null);
                this.txtSaldoCliente.setValue(0);
                this.Clientes.setVisible(false);
                toFront();
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this.Clientes, "Error al insertar nuevo cliente\n" + ex, "Error", 0);
            }
        }
    }
    
    private void jMenuItem2ActionPerformed(ActionEvent evt) {
        System.exit(0);
    }
    
    private void btnAceptarBuscarClientesActionPerformed(ActionEvent evt) {
        if (this.buscar == 0) {
            this.txtCliente.setText(this.listaClientes.getSelectedValue().toString());
        }
        if (this.buscar == 1) {
            this.txtCliente1.setText(this.listaClientes.getSelectedValue().toString());
            llenarHistorial();
        }
        if (this.buscar == 2) {
            this.txtCliente2.setText(this.listaClientes.getSelectedValue().toString());
        }
        if (this.buscar == 3) {
            String cliente = this.listaClientes.getSelectedValue().toString();
            String cnuevo = JOptionPane.showInputDialog(this.BuscarClientes, "Inserte el nuevo nombre para: " + cliente);
            if (!cnuevo.isEmpty()) {
                try {
                    conectarBase();
                    this.sql = ("UPDATE CLIENTES SET NOMBRECLIENTE= '" + cnuevo + "' WHERE NOMBRECLIENTE='" + cliente + "'");
                    stmn.executeUpdate(this.sql);
                    llenarClientes();
                } catch (Exception ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se ha insertado nombre", "Error", 0);
            }
        }
        this.BuscarClientes.setVisible(false);
    }
    
    int buscar = 0;
    private JFrame BuscarClientes;
    private JFrame Clientes;
    private JMenuBar Menu;
    private JFrame Productos;
    private JButton btnAceptarBuscarClientes;
    private JButton btnAceptarCliente;
    private JButton btnAceptarPago;
    private JButton btnAceptarProd;
    private JButton btnAceptarVenta;
    private JButton btnBorrar;
    private JButton btnBuscarCliente;
    private JButton btnBuscarCliente1;
    private JButton btnBuscarCliente2;
    private JDateChooser fechaAbono;
    private JDateChooser fechaVenta;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JMenu jMenu2;
    private JMenuItem jMenuItem2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JScrollPane jScrollPane6;
    private JScrollPane jScrollPane7;
    private JTabbedPane jTabbedPane1;
    private JComboBox jcbExistProd;
    private JLabel lblCliente;
    private JLabel lblCliente1;
    private JLabel lblCliente2;
    private JLabel lblCostoProd;
    private JLabel lblDescProd;
    private JLabel lblExistProd;
    private JLabel lblNombreCliente;
    private JLabel lblPrecioProd;
    private JLabel lblSaldoCliente;
    private JList listaClientes;
    private JMenuItem mEditCliente;
    private JMenuItem mNuevoProd;
    private JMenu menuArchivo;
    private JMenuItem mnAcerca;
    private JMenuItem mnCliente;
    private JPanel panBuscarClientes;
    private JPanel panClientes;
    private JPanel panProductos;
    private JPanel panVentas;
    private JPanel pnAbono;
    private JPanel pnAbono1;
    private JPanel pnClientes;
    private JTable tbClientes;
    private JTable tbHistorial;
    private JTable tbInventario;
    private JTable tbVenta;
    private JFormattedTextField txtAbono;
    private JFormattedTextField txtAjuste;
    private JTextField txtCliente;
    private JTextField txtCliente1;
    private JTextField txtCliente2;
    private JFormattedTextField txtCostoProd;
    private JTextArea txtDescProd;
    private JTextField txtNombreCliente;
    private JFormattedTextField txtPrecioProd;
    private JFormattedTextField txtSaldoCliente;
    private JFormattedTextField txtTotal;
    private JFormattedTextField txtTotal1;
    private JFormattedTextField txtTotalDeudas;
    private JFormattedTextField txtTotalVentas;
    
    private void btnBuscarClienteActionPerformed(ActionEvent evt) {
        if (evt.getSource() == this.btnBuscarCliente) {
            this.buscar = 0;
        }
        if (evt.getSource() == this.btnBuscarCliente1) {
            this.buscar = 1;
        }
        if (evt.getSource() == this.btnBuscarCliente2) {
            this.buscar = 2;
        }
        if (evt.getSource() == this.mEditCliente) {
            this.buscar = 3;
        }
        try {
            conectarBase();
            
            DefaultListModel listModel = new DefaultListModel();
            rset = stmn.executeQuery("SELECT NOMBRECLIENTE FROM CLIENTES ORDER BY 1");
            while (rset.next()) {
                listModel.addElement(rset.getString("NOMBRECLIENTE"));
            }
            this.listaClientes.setModel(listModel);
            this.BuscarClientes.pack();
            this.BuscarClientes.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mnAcercaActionPerformed(ActionEvent evt) {
        String msg = "Héctor López  V 1.2\n*V1.1(21-03-16)\n"
/*                + "V1.1\n
                + "-Total ventas/deudas\n"
                + "-Combo cantidad\n"
                + "-Ordenar tabla inventario\n"
                + "-Ajustes\n"
                + "-Tabla clientes*/
                + "V1.2\n"
                + "-Correción de errores";
        
        
        JOptionPane.showMessageDialog(this, msg, "Acerca de", 1);
    }
    
    public void CalcularVenta() {
        float precio = 0.0F;
        
        float gtotal = 0.0F;
        float ajuste = Float.parseFloat(this.txtAjuste.getValue().toString());
        for (int i = 0; i < tbVenta.getRowCount(); i++) {
            if ((this.tbVenta.getValueAt(i, 0) != null) && (this.tbVenta.getValueAt(i, 1) != null)) {
                int cantidad = Integer.parseInt(this.tbVenta.getValueAt(i, 0).toString());
                String prod = this.tbVenta.getValueAt(i, 1).toString();
                try {
                    conectarBase();
                    rset = stmn.executeQuery("SELECT PRECIOUNITARIO FROM PRODUCTOS WHERE DESCRIPCIONPROD='" + prod + "'");
                    while (rset.next()) {
                        precio = rset.getFloat("PRECIOUNITARIO");
                        this.tbVenta.setValueAt(precio, i, 2);
                    }
                    float total = cantidad * precio;
                    gtotal += total;
                    this.tbVenta.setValueAt(total, i, 3);
                    conn.close();
                } catch (Exception e) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        this.txtTotal.setValue(gtotal + ajuste);
    }
    
    private void btnAceptarVentaActionPerformed(ActionEvent evt) {
        CalcularVenta();
        float Total = Float.parseFloat(this.txtTotal.getValue().toString());
        float ajuste = Float.parseFloat(this.txtAjuste.getValue().toString());
        String Cliente = this.txtCliente.getText();
        String error = "";
        java.util.Date Fecha = this.fechaVenta.getDate();
        String strFecha = this.df.format(Fecha);
        String str1Fecha = this.df1.format(Fecha);
        int NumCliente = 0;
        int NumVenta = 0;
        int ClaveProd = 0;
        int existencias = 0;
        float Acumulado = 0.0F;
        if (Cliente.isEmpty()) {
            error = error + "No se ha seleccionado cliente\n";
        }
        if (Total == 0.0D) {
            error = error + "No se han a�adido productos\n";
        }
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(this, error, "Error", 0);
        } else {
            int opcion = JOptionPane.showConfirmDialog(this, "Desea agregar a " + Cliente + " la cantidad de $" + Total + "", "Aceptar venta", 0, 2);
            if (opcion == 0) {
                try {
                    conectarBase();
                    rset = stmn.executeQuery("SELECT NUMCLIENTE,ACUMULADOCLIENTE FROM CLIENTES WHERE NOMBRECLIENTE='" + Cliente + "'");
                    while (rset.next()) {
                        NumCliente = rset.getInt(1);
                        Acumulado = rset.getFloat(2);
                    }
                    conn.close();
                } catch (Exception e) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    conectarBase();
                    this.sql = "INSERT INTO VENTAS (FECHAVENTA,NUMCLIENTE,TOTALVENTA) VALUES (?,?,?)";
                    pstmn = conn.prepareStatement(this.sql);
                    pstmn.setDate(1, java.sql.Date.valueOf(str1Fecha));
                    pstmn.setInt(2, NumCliente);
                    pstmn.setFloat(3, Total);
                    pstmn.executeUpdate();
                    toFront();
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Error al insertar venta", "Error", 0);
                }
                try {
                    conectarBase();
                    rset = stmn.executeQuery("SELECT NUMVENTA FROM VENTAS WHERE FECHAVENTA='" + strFecha + "' and NUMCLIENTE=" + NumCliente + "");
                    while (rset.next()) {
                        NumVenta = rset.getInt(1);
                    }
                    for (int i = 0; i < 10; i++) {
                        if ((this.tbVenta.getValueAt(i, 0) != null) && (this.tbVenta.getValueAt(i, 1) != null)) {
                            int cantidad = Integer.parseInt(this.tbVenta.getValueAt(i, 0).toString());
                            rset = stmn.executeQuery("SELECT CLAVEPROD,EXISTENCIAS FROM PRODUCTOS WHERE DESCRIPCIONPROD='" + this.tbVenta.getValueAt(i, 1).toString() + "'");
                            while (rset.next()) {
                                ClaveProd = rset.getInt(1);
                                existencias = rset.getInt(2);
                            }
                            this.sql = "INSERT INTO DETALLEVENTAS (NUMVENTA,CLAVEPROD,DETVENTACANTIDAD,DETVENTAPRECIO,DETVENTASUBTOTAL) VALUES (?,?,?,?,?)";
                            pstmn = conn.prepareStatement(this.sql);
                            pstmn.setInt(1, NumVenta);
                            pstmn.setInt(2, ClaveProd);
                            pstmn.setInt(3, Integer.parseInt(this.tbVenta.getValueAt(i, 0).toString()));
                            pstmn.setFloat(4, ((Float) this.tbVenta.getValueAt(i, 2)));
                            pstmn.setFloat(5, ((Float) this.tbVenta.getValueAt(i, 3)));
                            pstmn.executeUpdate();
                            toFront();
                            this.sql = ("UPDATE PRODUCTOS SET EXISTENCIAS= " + (existencias - cantidad) + " WHERE CLAVEPROD=" + ClaveProd + "");
                            stmn.executeUpdate(this.sql);
                        }
                    }
                    if (ajuste > 0.0F) {
                        this.sql = "INSERT INTO DETALLEVENTAS (NUMVENTA,CLAVEPROD,DETVENTACANTIDAD,DETVENTAPRECIO,DETVENTASUBTOTAL) VALUES (?,?,?,?,?)";
                        pstmn = conn.prepareStatement(this.sql);
                        pstmn.setInt(1, NumVenta);
                        pstmn.setInt(2, 0);
                        pstmn.setInt(3, 0);
                        pstmn.setFloat(4, ajuste);
                        pstmn.setFloat(5, ajuste);
                        pstmn.executeUpdate();
                    }
                    this.sql = ("UPDATE CLIENTES SET ACUMULADOCLIENTE= " + (Acumulado + Total) + " WHERE NUMCLIENTE=" + NumCliente + "");
                    stmn.executeUpdate(this.sql);
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Venta insertada", null, 1);
                    BorrarVentas();
                    this.txtCliente1.setText(Cliente);
                    this.txtCliente2.setText(Cliente);
                    llenarHistorial();
                    llenarInventario();
                    llenarClientes();
                } catch (SQLException | NumberFormatException | HeadlessException e) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
    private void btnBorrarActionPerformed(ActionEvent evt) {
        BorrarVentas();
    }
    
    private void btnAceptarPagoActionPerformed(ActionEvent evt) {
        String Cliente = this.txtCliente2.getText();
        float Total = Float.parseFloat(this.txtAbono.getValue().toString());
        java.util.Date Fecha = this.fechaAbono.getDate();
        String str1Fecha = this.df1.format(Fecha);
        String error = "";
        int NumCliente = 0;
        float Acumulado = 0.0F;
        if (Cliente.isEmpty()) {
            error = error + "No se ha seleccionado cliente\n";
        }
        if (Total == 0.0D) {
            error = error + "No se ha insertado cantidad\n";
        }
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(this, error, "Error", 0);
        } else {
            int opcion = JOptionPane.showConfirmDialog(this, "Desea abonar al " + Cliente + " la cantidad de $" + Total + "", "Aceptar pago", 0, 2);
            if (opcion == 0) {
                try {
                    conectarBase();
                    rset = stmn.executeQuery("SELECT NUMCLIENTE,ACUMULADOCLIENTE FROM CLIENTES WHERE NOMBRECLIENTE='" + Cliente + "'");
                    while (rset.next()) {
                        NumCliente = rset.getInt(1);
                        Acumulado = rset.getFloat(2);
                    }
                    this.sql = "INSERT INTO PAGOS (NUMCLIENTE, PAGO, FECHA) VALUES (?,?,?)";
                    pstmn = conn.prepareStatement(this.sql);
                    
                    pstmn.setInt(1, NumCliente);
                    pstmn.setFloat(2, Total);
                    pstmn.setDate(3, java.sql.Date.valueOf(str1Fecha));
                    pstmn.executeUpdate();
                    this.sql = ("UPDATE CLIENTES SET ACUMULADOCLIENTE= " + (Acumulado - Total) + " WHERE NUMCLIENTE=" + NumCliente + "");
                    stmn.executeUpdate(this.sql);
                    JOptionPane.showMessageDialog(this, "Pago insertado", null, 1);
                    this.txtCliente2.setText(null);
                    this.txtAbono.setValue(0);
                    this.fechaAbono.setDate(new java.util.Date());
                    toFront();
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Error al insertar pago", "Error", 0);
                }
                this.txtCliente1.setText(Cliente);
                llenarHistorial();
            }
        }
    }
    
    private void tbVentaPropertyChange(PropertyChangeEvent evt) {
        CalcularVenta();
    }
    
    private void txtTotalActionPerformed(ActionEvent evt) {
    }
    
    private void txtAjusteInputMethodTextChanged(InputMethodEvent evt) {
        CalcularVenta();
    }
    
    private void txtAjustePropertyChange(PropertyChangeEvent evt) {
        CalcularVenta();
    }
    
    public void BorrarVentas() {
        this.txtCliente.setText(null);
        this.fechaVenta.setDate(new java.util.Date());
        this.txtTotal.setValue(0);
        for (int f = 0; f < tbVenta.getRowCount(); f++) {
            for (int c = 0; c < 4; c++) {
                this.tbVenta.setValueAt(null, f, c);
            }
        }
        txtAjuste.setValue(0);
    }
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> {
            new Inventario().setVisible(true);
        });
    }
    
    class MiComboBoxRenderer
            extends JComboBox
            implements TableCellRenderer {

        public MiComboBoxRenderer(JComboBox comboBox) {
            super();
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            System.out.println("value: " + value);
            System.out.println("row " + row);
            System.out.println("column" + column);
            setSelectedItem(value);
            return this;
        }
    }
    
    class MiComboBoxEditor
            extends DefaultCellEditor {

        private boolean isPushed;
        private int row;
        private int column;
        private JTable table;
        Object value;
        String label;
        
        public MiComboBoxEditor(JComboBox comboBox) {
            super(comboBox);
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                Inventario.this.setForeground(table.getSelectionForeground());
                Inventario.this.setBackground(table.getSelectionBackground());
            } else {
                Inventario.this.setForeground(table.getForeground());
                Inventario.this.setBackground(table.getBackground());
            }
            this.label = (value == null ? "" : value.toString());
            this.isPushed = true;
            System.out.println("true");
            this.row = row;
            this.column = column;
            this.value = value;
            return getComponent();
        }
        
        @Override
        public Object getCellEditorValue() {
            if ((this.isPushed)
                    && (this.column == 1)) {
                System.out.println("fila" + this.row);
            }
            System.out.println("21234");
            this.isPushed = false;
            return this.label;
        }
        
        @Override
        public boolean stopCellEditing() {
            this.isPushed = false;
            return super.stopCellEditing();
        }
        
        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
