package net.salesianos.crud;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Anadir extends JFrame {

    public Anadir() {

        super("Añadir Anime");

        JPanel panelAnadir = new JPanel();

        JLabel e = new JLabel("Anadir");

        panelAnadir.add(e);
        this.add(panelAnadir);

        this.setSize(300, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}
