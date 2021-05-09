/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Onur Kınay
 */
public class DersAlanlar extends JPanel {

    private JList jLOgrenci;
    private JLabel jtDers;
    private JButton jGoster;
    private JScrollPane scroll;

    public DersAlanlar(Ders ders) {
        //construct preComponents 

        //construct components
        jLOgrenci = new JList();
        jtDers = new JLabel("Ders: " + ders.getAd());
        jGoster = new JButton("Öğrenci Göster");

        //adjust size and set layout
        setPreferredSize(new Dimension(361, 261));
        setLayout(null);

        //add components
        scroll = new JScrollPane(jLOgrenci); 
        jLOgrenci.setLayoutOrientation(JList.VERTICAL);
        
        add(scroll); 
        add(jtDers);
        add(jGoster);

        //set component bounds (only needed by Absolute Positioning)
        scroll.setBounds(5, 35, 350, 185);
        jtDers.setBounds(5, 5, 250, 25);
        jGoster.setBounds(5, 225, 150, 25);
         
 
        ArrayList<Ogrenci> dersiAlanlar = new ArrayList<>();
        for (Kullanici kullanici : OdevSistemi.kullanicilar) {
            if (kullanici instanceof Ogrenci) {
                Ogrenci ogrenci = (Ogrenci) kullanici;
                for (Ders OgrenciDersi : ogrenci.getDersler()) {
                    if (ders.getId() == OgrenciDersi.getId()) {
                        dersiAlanlar.add(ogrenci);

                    }
                }
            }
        }
        jLOgrenci.setListData(dersiAlanlar.toArray());

        jGoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jLOgrenci.getSelectedIndex() != -1) {
                    JFrame frame = new JFrame("Ders Alanlar");

                    frame.setLocation(450, 450);
                    frame.getContentPane().add(new OgrenciDetay(((Ogrenci) jLOgrenci.getSelectedValue())));
                    frame.pack();
                    frame.setVisible(true);
                }

            }
        });
    }
}
