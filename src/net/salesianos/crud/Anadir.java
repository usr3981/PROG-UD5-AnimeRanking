package net.salesianos.crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.salesianos.elementos.Elemento;
import net.salesianos.menus.MenuPrincipal;
import net.salesianos.validaciones.Validacion;

public class Anadir extends JFrame {

    public Anadir() {
        super("Añadir Anime");

        // Panel principal con BorderLayout
        JPanel panelAnadir = new JPanel(new BorderLayout(10, 10));
        panelAnadir.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel del contenido (campos + título)
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        JLabel tituloVentana = new JLabel("Añadir nuevo Anime");
        tituloVentana.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(tituloVentana);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField campoCodigo = new JTextField(20);
        JTextField campoTitulo = new JTextField(20);
        JTextField campoDescripcion = new JTextField(20);
        JTextField campoPuntuacion = new JTextField(20);

        panelContenido.add(crearFila("Código:", campoCodigo));
        panelContenido.add(crearFila("Título:", campoTitulo));
        panelContenido.add(crearFila("Descripción:", campoDescripcion));
        panelContenido.add(crearFila("Puntuación (0-5):", campoPuntuacion));

        // Panel de botones abajo
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton botonGuardar = new JButton("Guardar");
        JButton botonVolver = new JButton("Volver");

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = campoCodigo.getText();
                String titulo = campoTitulo.getText();
                String descripcion = campoDescripcion.getText();
                String puntuacionTexto = campoPuntuacion.getText();

                if (!Validacion.validarCampoTexto(codigo) ||
                        !Validacion.validarCampoTexto(titulo) ||
                        !Validacion.validarCampoTexto(descripcion) ||
                        !Validacion.validarCampoTexto(puntuacionTexto)) {
                    JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos correctamente.");
                    return;
                }

                byte puntuacion;
                try {
                    puntuacion = Byte.parseByte(puntuacionTexto);
                    if (Validacion.validarCampoNumerico(puntuacion)) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La puntuación debe ser un número entre 0 y 5.");
                    return;
                }

                Elemento nuevo = new Elemento(codigo, titulo, descripcion, puntuacion);
                MenuPrincipal.listaElementos.add(nuevo);

                campoCodigo.setText("");
                campoTitulo.setText("");
                campoDescripcion.setText("");
                campoPuntuacion.setText("");

                JOptionPane.showMessageDialog(null, "Anime guardado correctamente");
            }
        });

        botonVolver.addActionListener(e -> dispose());

        panelBotones.add(botonGuardar);
        panelBotones.add(botonVolver);

        // Añadir contenido y botones al panel principal
        panelAnadir.add(panelContenido, BorderLayout.CENTER);
        panelAnadir.add(panelBotones, BorderLayout.SOUTH);

        this.add(panelAnadir);
        this.setSize(400, 300);
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
