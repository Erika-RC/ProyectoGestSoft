package views;

import domain.Articulo;
import domain.Tienda;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class InventarioDialog extends JDialog {
    private Tienda tienda;
    private JTable tabla;
    private DefaultTableModel modelo;

    public InventarioDialog(JFrame parent, Tienda tienda) {
        super(parent, "Gestión de Inventario", true);
        this.tienda = tienda;
        configurarDialogo();
        cargarTabla();
        agregarBotones();
    }

    private void configurarDialogo() {
        setSize(600, 400);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
    }

    private void cargarTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoría");
        modelo.addColumn("Precio");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        for (Articulo a : tienda.getInventario()) {
            modelo.addRow(new Object[]{
                a.getIdArticulo(),
                a.getNombre(),
                a.getCategoria(),
                a.getPrecio()
            });
        }
    }

    private void agregarBotones() {
        JPanel panelBotones = new JPanel();
        panelBotones.add(crearBoton("Agregar", Color.GREEN, e -> abrirFormulario()));
        panelBotones.add(crearBoton("Eliminar", Color.RED, e -> eliminarArticulo()));
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
        JDialog formulario = new JDialog(this, "Nuevo Artículo", true);
        formulario.setSize(350, 250);
        formulario.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtCategoria = new JTextField();
        JTextField txtPrecio = new JTextField();

        formulario.add(new JLabel("ID:"));
        formulario.add(txtId);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Categoría:"));
        formulario.add(txtCategoria);
        formulario.add(new JLabel("Precio:"));
        formulario.add(txtPrecio);

        JButton btnGuardar = crearBoton("Guardar", Color.BLUE, e -> guardarArticulo(txtId, txtNombre, txtCategoria, txtPrecio, formulario));
        JButton btnCancelar = crearBoton("Cancelar", Color.RED, e -> formulario.dispose());

        formulario.add(btnGuardar);
        formulario.add(btnCancelar);
        formulario.setVisible(true);
    }

    private void guardarArticulo(JTextField txtId, JTextField txtNombre, JTextField txtCategoria, JTextField txtPrecio, JDialog formulario) {
        try {
            Articulo nuevo = new Articulo(
                txtId.getText(),
                txtNombre.getText(),
                txtCategoria.getText(),
                Double.parseDouble(txtPrecio.getText()));
            
            tienda.agregarArticulo(nuevo);
            modelo.addRow(new Object[]{
                nuevo.getIdArticulo(),
                nuevo.getNombre(),
                nuevo.getCategoria(),
                nuevo.getPrecio()
            });
            formulario.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato del precio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarArticulo() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un artículo", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tienda.eliminarArticulo(tienda.getInventario().get(fila));
        modelo.removeRow(fila);
    }
}