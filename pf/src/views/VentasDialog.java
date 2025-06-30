package views;

import domain.Articulo;
import domain.Venta;
import domain.Tienda;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class VentasDialog extends JDialog {
    private Tienda tienda;
    private JTable tabla;
    private DefaultTableModel modelo;

    public VentasDialog(JFrame parent, Tienda tienda) {
        super(parent, "Gestión de Ventas", true);
        this.tienda = tienda;
        configurarDialogo();
        cargarTabla();
        agregarBotones();
    }

    private void configurarDialogo() {
        setSize(700, 450);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
    }

    private void cargarTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("ID Venta");
        modelo.addColumn("Artículo");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        modelo.addColumn("Fecha");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        for (Venta v : tienda.getVentas()) {
            modelo.addRow(new Object[]{
                v.getIdVenta(),
                v.getArticulo().getNombre(),
                v.getCantidad(),
                v.getTotal(),
                v.getFecha()
            });
        }
    }

    private void agregarBotones() {
        JPanel panelBotones = new JPanel();
        panelBotones.add(crearBoton("Agregar", Color.GREEN, e -> abrirFormulario()));
        panelBotones.add(crearBoton("Eliminar", Color.RED, e -> eliminarVenta()));
        panelBotones.add(crearBoton("Cerrar", Color.GRAY, e -> dispose()));

        add(panelBotones, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color color, ActionListener accion) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(accion);
        return btn;
    }

    private void abrirFormulario() {
        JDialog formulario = new JDialog(this, "Nueva Venta", true);
        formulario.setSize(400, 300);
        formulario.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField txtId = new JTextField();
        JComboBox<Articulo> comboArticulos = new JComboBox<>();
        JTextField txtCantidad = new JTextField();
        JTextField txtFecha = new JTextField();

        for (Articulo a : tienda.getInventario()) {
            comboArticulos.addItem(a);
        }

        formulario.add(new JLabel("ID Venta:"));
        formulario.add(txtId);
        formulario.add(new JLabel("Artículo:"));
        formulario.add(comboArticulos);
        formulario.add(new JLabel("Cantidad:"));
        formulario.add(txtCantidad);
        formulario.add(new JLabel("Fecha:"));
        formulario.add(txtFecha);

        JButton btnGuardar = crearBoton("Guardar", Color.BLUE, e -> guardarVenta(txtId, comboArticulos, txtCantidad, txtFecha, formulario));
        JButton btnCancelar = crearBoton("Cancelar", Color.RED, e -> formulario.dispose());

        formulario.add(btnGuardar);
        formulario.add(btnCancelar);
        formulario.setVisible(true);
    }

    private void guardarVenta(JTextField txtId, JComboBox<Articulo> comboArticulos, JTextField txtCantidad, JTextField txtFecha, JDialog formulario) {
        try {
            Articulo articulo = (Articulo) comboArticulos.getSelectedItem();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            Venta nueva = new Venta(
                txtId.getText(),
                articulo,
                cantidad,
                txtFecha.getText()
            );
            tienda.agregarVenta(nueva);
            modelo.addRow(new Object[]{
                nueva.getIdVenta(),
                nueva.getArticulo().getNombre(),
                nueva.getCantidad(),
                nueva.getTotal(),
                nueva.getFecha()
            });
            formulario.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarVenta() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una venta", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tienda.eliminarVenta(tienda.getVentas().get(fila));
        modelo.removeRow(fila);
    }
}