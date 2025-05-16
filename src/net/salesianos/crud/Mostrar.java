package net.salesianos.crud;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import net.salesianos.elementos.Elemento;
import net.salesianos.menus.MenuPrincipal;

public class Mostrar extends JFrame {

    public Mostrar() {
        super("Lista de Animes");

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Lista de Animes");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // Panel contenedor para las tarjetas
        JPanel panelTarjetas = new JPanel();
        panelTarjetas.setLayout(new BoxLayout(panelTarjetas, BoxLayout.X_AXIS)); // horizontal
        panelTarjetas.setBackground(Color.WHITE);

        List<Elemento> elementos = MenuPrincipal.listaElementos;

        if (elementos.isEmpty()) {
            JLabel vacio = new JLabel("No hay elementos para mostrar.");
            vacio.setHorizontalAlignment(SwingConstants.CENTER);
            panelPrincipal.add(vacio, BorderLayout.CENTER);
        } else {
            for (Elemento elem : elementos) {
                JPanel tarjeta = crearTarjeta(elem);
                panelTarjetas.add(tarjeta);
                panelTarjetas.add(Box.createRigidArea(new Dimension(10, 0))); // espacio entre tarjetas
            }

            JScrollPane scrollPane = new JScrollPane(panelTarjetas);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(new Dimension(600, 250));
            scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

            panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        }

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> dispose());

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonVolver);

        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        this.add(panelPrincipal);
        this.setSize(650, 350);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel crearTarjeta(Elemento elem) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        tarjeta.setBackground(new Color(240, 248, 255));
        tarjeta.setPreferredSize(new Dimension(200, 150));

        tarjeta.add(new JLabel("Código: " + elem.getCodigo()));
        tarjeta.add(new JLabel("Título: " + elem.getTitulo()));
        tarjeta.add(new JLabel("Descripción: " + elem.getDescripcion()));
        tarjeta.add(new JLabel("Puntuación: " + elem.getPuntuacion()));

        return tarjeta;
    }
}
