package views;

import domain.Devolucion;
import domain.Venta;
import domain.Tienda;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class DevolucionesDialog extends JDialog {
    private Tienda tienda;
    private JTable tabla;
    private DefaultTableModel modelo;

    public DevolucionesDialog(JFrame parent, Tienda tienda) {
        super(parent, "Gestión de Devoluciones", true);
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
        modelo.addColumn("ID Devolución");
        modelo.addColumn("ID Venta");
        modelo.addColumn("Artículo");
        modelo.addColumn("Cantidad Devuelta");
        modelo.addColumn("Fecha");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        for (Devolucion d : tienda.getDevoluciones()) {
            modelo.addRow(new Object[]{
                d.getIdDevolucion(),
                d.getVenta().getIdVenta(),
                d.getVenta().getArticulo().getNombre(),
                d.getCantidadDevuelta(),
                d.getFecha()
            });
        }
    }

    private void agregarBotones() {
        JPanel panelBotones = new JPanel();
        panelBotones.add(crearBoton("Agregar", Color.GREEN, e -> abrirFormulario()));
        panelBotones.add(crearBoton("Eliminar", Color.RED, e -> eliminarDevolucion()));
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
        JDialog formulario = new JDialog(this, "Nueva Devolución", true);
        formulario.setSize(400, 300);
        formulario.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField txtId = new JTextField();
        JComboBox<Venta> comboVentas = new JComboBox<>();
        JTextField txtCantidad = new JTextField();
        JTextField txtFecha = new JTextField();

        for (Venta v : tienda.getVentas()) {
            comboVentas.addItem(v);
        }

        formulario.add(new JLabel("ID Devolución:"));
        formulario.add(txtId);
        formulario.add(new JLabel("Venta:"));
        formulario.add(comboVentas);
        formulario.add(new JLabel("Cantidad Devuelta:"));
        formulario.add(txtCantidad);
        formulario.add(new JLabel("Fecha:"));
        formulario.add(txtFecha);

        JButton btnGuardar = crearBoton("Guardar", Color.BLUE, e -> guardarDevolucion(txtId, comboVentas, txtCantidad, txtFecha, formulario));
        JButton btnCancelar = crearBoton("Cancelar", Color.RED, e -> formulario.dispose());

        formulario.add(btnGuardar);
        formulario.add(btnCancelar);
        formulario.setVisible(true);
    }

    private void guardarDevolucion(JTextField txtId, JComboBox<Venta> comboVentas, JTextField txtCantidad, JTextField txtFecha, JDialog formulario) {
        try {
            Venta venta = (Venta) comboVentas.getSelectedItem();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            
            if (cantidad > venta.getCantidad()) {
                JOptionPane.showMessageDialog(this, "No puede devolver más de lo vendido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Devolucion nueva = new Devolucion(
                txtId.getText(),
                venta,
                cantidad,
                txtFecha.getText()
            );
            tienda.agregarDevolucion(nueva);
            modelo.addRow(new Object[]{
                nueva.getIdDevolucion(),
                nueva.getVenta().getIdVenta(),
                nueva.getVenta().getArticulo().getNombre(),
                nueva.getCantidadDevuelta(),
                nueva.getFecha()
            });
            formulario.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarDevolucion() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una devolución", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tienda.eliminarDevolucion(tienda.getDevoluciones().get(fila));
        modelo.removeRow(fila);
    }
}