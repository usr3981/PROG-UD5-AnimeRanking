package net.salesianos.crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import net.salesianos.elementos.Elemento;
import net.salesianos.menus.MenuPrincipal;
import net.salesianos.validaciones.Validacion;

public class Editar extends JFrame {

    private JTextField campoCodigoBuscar;
    private JTextField campoCodigo;
    private JTextField campoTitulo;
    private JTextField campoDescripcion;
    private JTextField campoPuntuacion;
    private JButton botonGuardar;

    private Elemento elementoEncontrado = null;

    public Editar() {
        super("Editar Anime");

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        JLabel tituloVentana = new JLabel("Buscar Anime por Código");
        tituloVentana.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(tituloVentana);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10)));

        campoCodigoBuscar = new JTextField(20);
        JButton botonBuscar = new JButton("Buscar");

        JPanel filaBusqueda = new JPanel(new BorderLayout(5, 5));
        filaBusqueda.add(new JLabel("Código:"), BorderLayout.WEST);
        filaBusqueda.add(campoCodigoBuscar, BorderLayout.CENTER);
        filaBusqueda.add(botonBuscar, BorderLayout.EAST);
        filaBusqueda.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panelContenido.add(filaBusqueda);

        campoCodigo = new JTextField(20);
        campoTitulo = new JTextField(20);
        campoDescripcion = new JTextField(20);
        campoPuntuacion = new JTextField(20);

        campoCodigo.setEnabled(false);
        campoTitulo.setEnabled(false);
        campoDescripcion.setEnabled(false);
        campoPuntuacion.setEnabled(false);

        panelContenido.add(crearFila("Nuevo Código:", campoCodigo));
        panelContenido.add(crearFila("Título:", campoTitulo));
        panelContenido.add(crearFila("Descripción:", campoDescripcion));
        panelContenido.add(crearFila("Puntuación (0-5):", campoPuntuacion));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botonGuardar = new JButton("Guardar Cambios");
        JButton botonVolver = new JButton("Volver");
        botonGuardar.setEnabled(false);

        // Acción del botón Buscar
        botonBuscar.addActionListener((ActionEvent e) -> {
            String codigoBuscado = campoCodigoBuscar.getText();

            if (!Validacion.validarCampoTexto(codigoBuscado)) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un código válido.");
                return;
            }

            elementoEncontrado = buscarElementoPorCodigo(codigoBuscado);

            if (elementoEncontrado != null) {
                // Activar campos y mostrar datos
                campoCodigo.setText(elementoEncontrado.getCodigo());
                campoTitulo.setText(elementoEncontrado.getTitulo());
                campoDescripcion.setText(elementoEncontrado.getDescripcion());
                campoPuntuacion.setText(String.valueOf(elementoEncontrado.getPuntuacion()));

                campoCodigo.setEnabled(true);
                campoTitulo.setEnabled(true);
                campoDescripcion.setEnabled(true);
                campoPuntuacion.setEnabled(true);
                botonGuardar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún anime con ese código.");
            }
        });

        // Acción del botón Guardar
        botonGuardar.addActionListener((ActionEvent e) -> {
            if (elementoEncontrado == null)
                return;

            String nuevoCodigo = campoCodigo.getText();
            String nuevoTitulo = campoTitulo.getText();
            String nuevaDescripcion = campoDescripcion.getText();
            String puntuacionTexto = campoPuntuacion.getText();

            if (!Validacion.validarCampoTexto(nuevoCodigo) ||
                    !Validacion.validarCampoTexto(nuevoTitulo) ||
                    !Validacion.validarCampoTexto(nuevaDescripcion) ||
                    !Validacion.validarCampoTexto(puntuacionTexto)) {

                JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos correctamente.");
                return;
            }

            byte nuevaPuntuacion;
            try {
                nuevaPuntuacion = Byte.parseByte(puntuacionTexto);
                if (Validacion.validarCampoNumerico(nuevaPuntuacion)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La puntuación debe ser un número entre 0 y 5.");
                return;
            }

            // Si cambia el código, verificar que no exista duplicado
            if (!nuevoCodigo.equals(elementoEncontrado.getCodigo()) && existeElementoConCodigo(nuevoCodigo)) {
                JOptionPane.showMessageDialog(null, "Ya existe otro anime con ese código.");
                return;
            }

            elementoEncontrado.setCodigo(nuevoCodigo);
            elementoEncontrado.setTitulo(nuevoTitulo);
            elementoEncontrado.setDescripcion(nuevaDescripcion);
            elementoEncontrado.setPuntuacion(nuevaPuntuacion);

            JOptionPane.showMessageDialog(null, "Anime editado correctamente.");

        });

        botonVolver.addActionListener(e -> dispose());

        panelBotones.add(botonGuardar);
        panelBotones.add(botonVolver);

        panelPrincipal.add(panelContenido, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        this.add(panelPrincipal);
        this.setSize(450, 350);
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

    private Elemento buscarElementoPorCodigo(String codigo) {
        for (Elemento e : MenuPrincipal.listaElementos) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return e;
            }
        }
        return null;
    }

    private boolean existeElementoConCodigo(String codigo) {
        for (Elemento e : MenuPrincipal.listaElementos) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }
}
