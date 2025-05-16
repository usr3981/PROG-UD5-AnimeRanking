package net.salesianos.crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.salesianos.elementos.Elemento;

public class Anadir extends JFrame {

    public Anadir() {
        super("Añadir Anime");

        // Panel principal con layout en columna
        JPanel panelAnadir = new JPanel();
        panelAnadir.setLayout(new BoxLayout(panelAnadir, BoxLayout.Y_AXIS));
        panelAnadir.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiqueta principal
        JLabel tituloVentana = new JLabel("Añadir nuevo Anime");
        tituloVentana.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAnadir.add(tituloVentana);
        panelAnadir.add(Box.createRigidArea(new Dimension(0, 10)));

        // Campos de entrada
        JTextField campoCodigo = new JTextField(20);
        JTextField campoTitulo = new JTextField(20);
        JTextField campoDescripcion = new JTextField(20);
        JTextField campoPuntuacion = new JTextField(20);

        panelAnadir.add(crearFila("Código:", campoCodigo));
        panelAnadir.add(crearFila("Título:", campoTitulo));
        panelAnadir.add(crearFila("Descripción:", campoDescripcion));
        panelAnadir.add(crearFila("Puntuación (0-5):", campoPuntuacion));

        // Botón Guardar
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = campoCodigo.getText();
                String titulo = campoTitulo.getText();
                String descripcion = campoDescripcion.getText();
                byte puntuacion;

                try {
                    puntuacion = Byte.parseByte(campoPuntuacion.getText());

                    // Validar rango
                    if (puntuacion < 0 || puntuacion > 5) {
                        throw new NumberFormatException("La puntuación debe estar entre 0 y 5.");
                    }

                    Elemento nuevo = new Elemento(codigo, titulo, descripcion, puntuacion);
                    System.out.println("Elemento creado:");
                    System.out.println("Código: " + nuevo.getCodigo());
                    System.out.println("Título: " + nuevo.getTitulo());
                    System.out.println("Descripción: " + nuevo.getDescripcion());
                    System.out.println("Puntuación: " + nuevo.getPuntuacion());

                    campoCodigo.setText("");
                    campoTitulo.setText("");
                    campoDescripcion.setText("");
                    campoPuntuacion.setText("");

                    JOptionPane.showMessageDialog(null, "Anime guardado correctamente");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Puntuación inválida. Debe ser un número entre 0 y 5.");
                }
            }
        });

        panelAnadir.add(Box.createRigidArea(new Dimension(0, 5)));
        panelAnadir.add(botonGuardar);

        this.add(panelAnadir);
        this.setSize(350, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    // Método para crear una fila con etiqueta y campo de texto
    private JPanel crearFila(String etiquetaTexto, JTextField campoTexto) {
        JPanel fila = new JPanel(new BorderLayout(5, 5));
        JLabel etiqueta = new JLabel(etiquetaTexto);
        fila.add(etiqueta, BorderLayout.WEST);
        fila.add(campoTexto, BorderLayout.CENTER);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return fila;
    }
}
