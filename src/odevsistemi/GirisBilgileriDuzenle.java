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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Onur Kınay
 */
public class GirisBilgileriDuzenle extends JPanel {

    private JLabel jKadi;
    private JLabel jSifre;
    private JPasswordField jtSifre;
    private JTextField jtKadi;
    private JButton jDegistir; 
    private JLabel jDurum;

    public GirisBilgileriDuzenle(Kullanici kullanici) {
        //construct components
        jKadi = new JLabel("Kullanıcı Adı");
        jSifre = new JLabel("Şifre");
        jtSifre = new JPasswordField(5);
        jtKadi = new JTextField(5);
        jDegistir = new JButton("Degiştir");
        jDurum = new JLabel("");

        //adjust size and set layout
        setPreferredSize(new Dimension(377, 204));
        setLayout(null);

        //add components
        add(jKadi);
        add(jSifre);
        add(jtSifre);
        add(jtKadi);
        add(jDegistir); 
        add(jDurum);
        
        //set component bounds (only needed by Absolute Positioning)
        jKadi.setBounds(45, 35, 100, 25);
        jSifre.setBounds(45, 75, 40, 25);
        jtSifre.setBounds(130, 75, 215, 25);
        jtKadi.setBounds(130, 40, 215, 25);
        jDegistir.setBounds(240, 125, 100, 25);
        jDurum.setBounds(45, 125, 300, 25);
        
        jtKadi.setText(kullanici.getKadi());
        
        jDegistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(jtKadi.getText())) {
                    kullanici.setKadi(jtKadi.getText());
                }
                if (!"".equals(jtSifre.getText())) {
                    kullanici.setSifre(jtSifre.getText());
                }
                jDurum.setText("Kullanıcı adı ve şifreniz değiştirildi");

            }
        });
    }
}