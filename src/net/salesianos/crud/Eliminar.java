package net.salesianos.crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import net.salesianos.elementos.Elemento;
import net.salesianos.menus.MenuPrincipal;
import net.salesianos.validaciones.Validacion;

public class Eliminar extends JFrame {

    public Eliminar() {
        super("Eliminar Anime");

        // Panel principal
        JPanel panelEliminar = new JPanel(new BorderLayout(10, 10));
        panelEliminar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de entrada
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        JLabel tituloVentana = new JLabel("Eliminar Anime por Código");
        tituloVentana.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(tituloVentana);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField campoCodigo = new JTextField(20);
        panelContenido.add(crearFila("Código:", campoCodigo));

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton botonEliminar = new JButton("Eliminar");
        JButton botonVolver = new JButton("Volver");

        botonEliminar.addActionListener((ActionEvent e) -> {
            String codigo = campoCodigo.getText().trim();

            if (!Validacion.validarCampoTexto(codigo)) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un código válido.");
                return;
            }

            Elemento encontrado = null;
            for (Elemento elem : MenuPrincipal.listaElementos) {
                if (elem.getCodigo().equals(codigo)) {
                    encontrado = elem;
                    break;
                }
            }

            if (encontrado != null) {
                int confirmacion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Seguro que deseas eliminar el anime con título: " + encontrado.getTitulo() + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    MenuPrincipal.listaElementos.remove(encontrado);
                    JOptionPane.showMessageDialog(null, "Anime eliminado correctamente.");
                    campoCodigo.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún anime con ese código.");
            }
        });

        botonVolver.addActionListener(e -> dispose());

        panelBotones.add(botonEliminar);
        panelBotones.add(botonVolver);

        panelEliminar.add(panelContenido, BorderLayout.CENTER);
        panelEliminar.add(panelBotones, BorderLayout.SOUTH);

        this.add(panelEliminar);
        this.setSize(400, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel crearFila(String etiquetaTexto, JTextField campoTexto) {
        JPanel fila = new JPanel(new BorderLayout(5, 5));
        JLabel etiqueta = new JLabel(etiquetaTexto);
        fila.add(etiqueta, BorderLayout.WEST);
        fila.add(campoTexto, BorderLayout.CENTER);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return fila;
    }
}
