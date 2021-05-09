/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.ParseException; 
import java.util.ArrayList; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static odevsistemi.OdevSistemi.kullanicilar;

/**
 *
 * @author Onur Kınay
 *
 *
 * TO - DO ** ERROR CATCHES ** statistics **
 * show students who took the lessons on the teacher panel
 */
public class OdevSistemi extends JPanel {

    private JLabel jKadi;
    private JLabel jSifre;
    private JPasswordField jtSifre;
    private JTextField jtKadi;
    private JButton jGiris;
    private JLabel jDurum;
    static ArrayList<Kullanici> kullanicilar = new ArrayList<>();
    static ArrayList<Ders> dersler = new ArrayList<>();

    static JFrame frame = new JFrame("Ödev Sistemi");

    public OdevSistemi() throws Exception, ParseException, FileNotFoundException {
 
        Veri.KullanicilariCek();
        Veri.DersleriCek();
        Veri.OgrenciOdevleriCek();
         
        jKadi = new JLabel("Kullanıcı Adı");
        jSifre = new JLabel("Şifre");
        jDurum = new JLabel("");
        jtSifre = new JPasswordField(5);
        jtKadi = new JTextField(5);
        jGiris = new JButton("Giriş");
        setPreferredSize(new Dimension(377, 204));
        setLayout(null);

        setFocusable(true); 
        //add components
        add(jKadi);
        add(jSifre);
        add(jtSifre);
        add(jtKadi);
        add(jGiris);
        add(jDurum);

        jtSifre.addKeyListener(new OSKeyListener());
 
        jKadi.setBounds(45, 35, 100, 25);
        jSifre.setBounds(45, 75, 40, 25);
        jtSifre.setBounds(130, 75, 215, 25);
        jtKadi.setBounds(130, 40, 215, 25);
        jGiris.setBounds(240, 125, 100, 25);
        jDurum.setBounds(45, 150, 300, 25);

        jGiris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GirisYap();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(OdevSistemi.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    System.out.println("Belirsiz hata tespiti");
                    ex.printStackTrace();
                }

            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception, ParseException, FileNotFoundException {
        frame.setLocation(450, 450);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new OdevSistemi());
        frame.setVisible(true);
        frame.pack();
    }

    void GirisYap() throws ClassNotFoundException {
        if (!"".equals(jtKadi.getText()) && !"".equals(jtSifre.getText())) {
            Kullanici kid = Kullanici.GirisYap(jtKadi.getText(), jtSifre.getText(), kullanicilar);
            if (kid != null) {

                JFrame subframe = new JFrame("Ödev Takip Sistemi Alpha v0.1");
                subframe.setLocation(450, 450);

                if (kid instanceof Ogretmen) {
                    OgretmenPanel panel = new OgretmenPanel((Ogretmen) kid);
                    subframe.getContentPane().add(panel);
                    subframe.addWindowListener(panel.new CustomWindowListener());
                } else if (kid instanceof Ogrenci) {
                    OgrenciPanel panel = new OgrenciPanel((Ogrenci) kid);
                    subframe.addWindowListener(panel.new CustomWindowListener());
                    subframe.getContentPane().add(panel);
                } else {
                    throw new ClassNotFoundException("Belirsiz sınıf tespit edildi.");
                }

                subframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                subframe.pack();
                subframe.setVisible(true);
                frame.setVisible(false);

            } else {
                jDurum.setText("Kullanıcı veya şifre hatalıdır. Tekrar giriniz.");
            }
        } else {
            jDurum.setText("Giriş bilgilerini tam giriniz");
        }
    }

    class OSKeyListener extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER: {
                    try {
                        GirisYap();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(OdevSistemi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }

        }
    }
}
