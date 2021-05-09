/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 *
 * @author Onur Kınay
 */
public class OgrenciPanel extends JPanel {

    private JLabel jOgrenci;
    private JButton jGirisBilgileriDuzenle;
    private JLabel jDers;
    private JLabel jtDurum;
    private JComboBox jDersler;
    private JList jOdevler;
    private JButton jDersKatil;
    private JButton jOdevYukle;
    private JButton jOgretmenDetay;
    private Ogrenci ogrenci;
    private int exIndex = 0;
    private JScrollPane scroll;

    public OgrenciPanel(Ogrenci ogrenci) {

        this.ogrenci = ogrenci;
        jOgrenci = new JLabel(ogrenci.getAdsoyad() + " / Bölüm: " + ogrenci.getBolum());
        jGirisBilgileriDuzenle = new JButton("Giriş Bilgileri Düzenle");
        jOdevYukle = new JButton("Ödev Yükle");
        jOgretmenDetay = new JButton("Öğretmen Detay");
        jDersKatil = new JButton("Derse Katıl");
        jDers = new JLabel("Dersler: ");
        jtDurum = new JLabel("");
        jOdevler = new JList();

        Object[] dersler = ogrenci.getDersler().toArray();

        if (dersler.length != 0) {
            jDersler = new JComboBox(ogrenci.getDersler().toArray());
            Object[] odevler = ogrenci.getDersler().get(0).getOdevler().toArray();
            if (odevler.length != 0) {
                jOdevler = new JList(ogrenci.getDersler().get(0).getOdevler().toArray());
            }
        } else {
            jDersler = new JComboBox(new String[]{"Ders yok"});
            jDersler.setEnabled(false);
        }

        scroll = new JScrollPane(jOdevler);
        jOdevler.setLayoutOrientation(JList.VERTICAL);
        add(scroll);

        setPreferredSize(new Dimension(492, 401));
        setLayout(null);

        add(jOgrenci);
        add(jGirisBilgileriDuzenle);
        add(jDers);
        add(jDersler); 
        add(jOgretmenDetay);
        add(jDersKatil);
        add(jOdevYukle);
        add(jtDurum);

        jOgrenci.setBounds(15, 7, 280, 25);
        jGirisBilgileriDuzenle.setBounds(15, 40, 178, 25);
        jDers.setBounds(15, 80, 50, 25);
        jDersler.setBounds(65, 80, 414, 25);
        scroll.setBounds(10, 115, 470, 240);
        jDersKatil.setBounds(200, 40, 100, 25);
        jOdevYukle.setBounds(10, 361, 165, 25);
        jOgretmenDetay.setBounds(185, 361, 165, 25);
        jtDurum.setBounds(180, 361, 160, 25);
 

        jDersler.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {

                exIndex = jDersler.getSelectedIndex();
                boolean bosmu = true;
                for (Ders ders : OdevSistemi.dersler) {
                    if (ders.getAd().equals(jDersler.getSelectedItem().toString())) {
                        jOdevler.setListData(ders.getOdevler().toArray());
                        jOdevler.setEnabled(true);

                        if (ders.getOdevler().toArray().length != 0) {
                            bosmu = false;
                        }
                    }
                }

                if (bosmu) {
                    jOdevler.setListData(new String[]{"Ödev bulunamadı"});
                    jOdevler.setEnabled(false);
                }
            }
        });

        jOdevYukle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jOdevler.getSelectedIndex() != -1) {

                    jtDurum.setText("");
                    JFrame frame = new JFrame("");

                    frame.setLocation(450, 450);
                    frame.getContentPane().add(new OdevYukleDetay((Odev) jOdevler.getSelectedValue(), ogrenci));
                    frame.pack();
                    frame.setVisible(true);
                } else {
                    jtDurum.setText("Ödev seçilmedi.");
                }
            }
        });

        jGirisBilgileriDuzenle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Giriş Bilgisi Düzenleme");

                frame.setLocation(450, 450);
                frame.getContentPane().add(new GirisBilgileriDuzenle((Kullanici) ogrenci));
                frame.pack();
                frame.setVisible(true);
            }
        });

        jDersKatil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Derse Katılma");

                frame.setLocation(450, 450);
                frame.getContentPane().add(new DersKatil(ogrenci));
                frame.pack();
                frame.setVisible(true);
            }
        });

        jOgretmenDetay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jDersler.getSelectedIndex() != -1) {
                    JFrame frame = new JFrame("Öğretmen Detay");

                    frame.setLocation(450, 450);
                    frame.getContentPane().add(new OgretmenDetay(((Ders) jDersler.getSelectedItem()).getOgretmen()));
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
    }

    class CustomWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            Veri.DersleriKaydet();
            Veri.KullanicilarKaydet();
            Veri.OgrencininOdevleriKaydet();
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
            DefaultComboBoxModel model = new DefaultComboBoxModel(ogrenci.getDersler().toArray());
            jDersler.setModel(model);
            if (ogrenci.getDersler().toArray().length != 0) {
                jDersler.setEnabled(true);
                jDersler.setSelectedIndex(exIndex);

            }

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
