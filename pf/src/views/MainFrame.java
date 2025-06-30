package views;

import domain.Tienda;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private Tienda tienda;

    public MainFrame(Tienda tienda) {
        this.tienda = tienda;
        configurarVentana();
        agregarBotones();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Tienda");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));
    }

    private void agregarBotones() {
        JButton btnInventario = crearBoton("Inventario", Color.BLUE, e -> abrirInventario());
        JButton btnVentas = crearBoton("Ventas", Color.GREEN, e -> abrirVentas());
        JButton btnDevoluciones = crearBoton("Devoluciones", Color.ORANGE, e -> abrirDevoluciones());

        add(btnInventario);
        add(btnVentas);
        add(btnDevoluciones);
    }

    private JButton crearBoton(String texto, Color color, ActionListener accion) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.addActionListener(accion);
        return btn;
    }

    private void abrirInventario() {
        new InventarioDialog(this, tienda).setVisible(true);
    }

    private void abrirVentas() {
        new VentasDialog(this, tienda).setVisible(true);
    }

    private void abrirDevoluciones() {
        new DevolucionesDialog(this, tienda).setVisible(true);
    }

    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        SwingUtilities.invokeLater(() -> new MainFrame(tienda).setVisible(true));
    }
}