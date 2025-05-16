package net.salesianos.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.salesianos.crud.Anadir;
import net.salesianos.crud.Editar;
import net.salesianos.crud.Eliminar;
import net.salesianos.crud.Mostrar;
import net.salesianos.elementos.Elemento;

public class MenuPrincipal extends JFrame {

    public static ArrayList<Elemento> listaElementos = new ArrayList<>();

    public MenuPrincipal() {

        JFrame frame = new JFrame("Ranking de Anime");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);

        JPanel panelPrincipal = new JPanel();

        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // Añadir el item al panel principal

        panelPrincipal.add(nuevaOpcion("Anadir"));
        panelPrincipal.add(nuevaOpcion("Editar"));
        panelPrincipal.add(nuevaOpcion("Mostrar"));
        panelPrincipal.add(nuevaOpcion("Eliminar"));
        panelPrincipal.add(nuevoBoton("Salir", frame));

        frame.add(panelPrincipal);
        frame.setVisible(true);

    }

    public static JPanel nuevaOpcion(String texto) {

        JPanel panelOpcion = new JPanel();
        panelOpcion.setLayout(new BoxLayout(panelOpcion, BoxLayout.X_AXIS));

        JLabel etiqueta = new JLabel(texto + " Anime");
        JButton boton = new JButton(texto);

        panelOpcion.add(etiqueta);

        panelOpcion.add(Box.createHorizontalStrut(10)); // 10 píxeles de espacio

        boton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                switch (texto) {
                    case "Anadir":
                        Anadir ventanaAnadir = new Anadir();
                        break;
                    case "Editar":
                        Editar ventanaEditar = new Editar();
                        break;
                    case "Mostrar":
                        Mostrar mostrar = new Mostrar();

                        break;
                    case "Eliminar":
                        Eliminar eliminar = new Eliminar();
                        break;
                    case "Salir":

                        break;

                    default:
                        break;
                }

            }

        });

        panelOpcion.add(boton);

        // Margen exterior del item completo
        panelOpcion.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        return panelOpcion;
    }

    public static JPanel nuevoBoton(String texto, JFrame frame) {
        JPanel panelOpcion = new JPanel();
        panelOpcion.setLayout(new BoxLayout(panelOpcion, BoxLayout.X_AXIS));

        // Componentes del item
        JLabel etiqueta = new JLabel(texto + " del programa");
        JButton boton = new JButton(texto);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra la ventana principal
            }
        });

        panelOpcion.add(etiqueta);

        panelOpcion.add(Box.createHorizontalStrut(10)); // 10 píxeles de espacio

        panelOpcion.add(boton);

        // Margen exterior del item completo
        panelOpcion.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        return panelOpcion;
    }

}
