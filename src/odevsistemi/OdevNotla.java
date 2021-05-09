/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Onur Kınay
 */
public class OdevNotla extends JPanel {
    
    private JLabel jOdevAdi;
    private JLabel jOgrenci;
    private JLabel jOdevDurum;
    private JLabel jcomp4;
    private JTextArea jOdev;
    private JButton jNotlandir;
    private JTextField jNot;
    private JLabel jtDurum;
    
    public OdevNotla(OgrencininOdevi ogrencininOdev, Odev odev) {
        
        jOdevAdi = new JLabel("Ödev Adı: " + odev.getAd());
        jOgrenci = new JLabel("Öğrenci: " + ogrencininOdev.getOgrenci().getAdsoyad());
        jOdevDurum = new JLabel("Ödev Durumu: " + ogrencininOdev.getNot());
        jcomp4 = new JLabel("Ödev");
        jOdev = new JTextArea(5, 5);
        jOdev.setText(ogrencininOdev.getOgrencininOdevi());
        jNotlandir = new JButton("Notlandır");
        jNot = new JTextField(5);
        jtDurum = new JLabel("sadsad");
 
        setPreferredSize(new Dimension(615, 353));
        setLayout(null);
 
        add(jOdevAdi);
        add(jOgrenci);
        add(jOdevDurum);
        add(jcomp4);
        add(jOdev);
        add(jNotlandir);
        add(jNot);
        add(jtDurum);
 
        jOdevAdi.setBounds(30, 20, 640, 25);
        jOgrenci.setBounds(30, 50, 640, 25);
        jOdevDurum.setBounds(30, 80, 640, 25);
        jcomp4.setBounds(30, 110, 100, 25);
        jOdev.setBounds(115, 105, 485, 195);
        jNotlandir.setBounds(495, 315, 100, 25);
        jNot.setBounds(320, 315, 170, 25);
        jtDurum.setBounds(30, 315, 170, 25);
        
        jNotlandir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int not = Integer.parseInt(jNot.getText());
                    if (not > 0 && not < 100) {
                        ogrencininOdev.NotVer(not); 
                        jtDurum.setText("Odev Notlandırıldı.");
                        jOdevDurum.setText("Ödev Durumu: " + ogrencininOdev.getNot());
                    } else {
                        jtDurum.setText("0-100 arasında not veriniz");
                    }
                } catch (NumberFormatException ex) {
                    jtDurum.setText("Sayısal veri giriniz...");
                }
                
            }
        });
    }
    
}
