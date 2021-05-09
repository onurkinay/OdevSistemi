/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Onur Kınay
 */


public class DersEkle extends JPanel {

    private JTextField jDersK;
    private JLabel jLDersK;
    private JButton jEkle;
    private JTextField jDersA;
    private JLabel jLDers; 
    private JLabel jtDurum;

    public DersEkle(Ogretmen ogretmen) { 
        jDersK = new JTextField(5);
        jLDersK = new JLabel("Ders Kodu: ");
        jEkle = new JButton("Ekle");
        jDersA = new JTextField(5);
        jLDers = new JLabel("Ders Adı:");
        jtDurum = new JLabel("");
 
        setPreferredSize(new Dimension(233, 90));
        setLayout(null);

        //add components
        add(jDersK);
        add(jLDersK);
        add(jEkle);
        add(jDersA);
        add(jLDers);
        add(jtDurum);
 
        jDersK.setBounds(70, 32, 160, 25);
        jLDersK.setBounds(5, 33, 67, 20);
        jEkle.setBounds(129, 61, 100, 25); 
        jtDurum.setBounds(5, 61, 100, 25);
        jDersA.setBounds(70, 5, 160, 25);
        jLDers.setBounds(5, 5, 58, 25);

        jEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OdevSistemi.dersler.add(new Ders(jDersA.getText(), jDersK.getText(), ogretmen));
                jtDurum.setText("Ders eklendi.");

            }
        });
    }
} 