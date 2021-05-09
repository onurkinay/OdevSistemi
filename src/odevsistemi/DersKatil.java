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
public class DersKatil extends JPanel {

    private JTextField jKod;
    private JLabel jtKod;
    private JLabel jtDurum;
    private JButton jKatil;

    public DersKatil(Ogrenci ogrenci) { 
        jKod = new JTextField(5);
        jtKod = new JLabel("Ders Kodu: ");
        jtDurum = new JLabel("");
        jKatil = new JButton("Katıl");
 
        setPreferredSize(new Dimension(343, 65));
        setLayout(null);

        //add components
        add(jKod);
        add(jtKod);
        add(jKatil);
        add(jtDurum);
 
        jKod.setBounds(70, 6, 157, 25);
        jtKod.setBounds(5, 8, 67, 20);
        jKatil.setBounds(235, 5, 100, 25);
        jtDurum.setBounds(5, 25, 300, 25);

        jKatil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ders ders;
                try {
                    ders = ogrenci.DerseKatil(OdevSistemi.dersler, jKod.getText()); 
                    jtDurum.setText("Derse katıldınız:" + ders.getAd() + "// Hoca: " + ders.getOgretmen().getAdsoyad());

                } catch (Exception ex) {
                    jtDurum.setText(ex.getMessage());
                }

            }
        }
        );
    }
}
