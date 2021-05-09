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

/**
 *
 * @author Onur Kınay
 */
public class OgretmenPanel extends JPanel {

    private JLabel jDers;
    private JComboBox jDersler;
    private JList jOdevler;
    private JButton jYeniOdev;
    private JButton jOdevSil;
    private JButton jGirisDuzenle;
    private JLabel jWelcome;
    private JButton jOdevGoster;
    private JButton jDersEkle;
    private JButton jDersAlanlar;
    private JButton jDersSil;
    private JLabel jtDurum;
    private JButton jIstatistik;
    private Ogretmen ogretmen;
    private JScrollPane scroll;
    private int exIndex = 0;

    public OgretmenPanel(Ogretmen ogretmen) {
        this.ogretmen = ogretmen;

        Object[] dersler = ogretmen.Dersleri().toArray();
        if (!ogretmen.Dersleri().isEmpty()) {
            jDersler = new JComboBox(dersler);

            if (ogretmen.Dersleri().get(0).getOdevler().isEmpty()) {
                jOdevler = new JList(new String[]{"Ödev bulunamadı"});
                jOdevler.setEnabled(false);
            } else {
                jOdevler = new JList(ogretmen.Dersleri().get(0).getOdevler().toArray());
            }

        } else {
            jDersler = new JComboBox(new String[]{"Ders yok"});
            jDersler.setEnabled(false);
            jOdevler = new JList(new String[]{"Ödev bulunamadı"});
            jOdevler.setEnabled(false);

        }

        jDers = new JLabel("Ders: ");
        jYeniOdev = new JButton("Yeni Ödev");
        jOdevSil = new JButton("Ödevi Sil");
        jGirisDuzenle = new JButton("Giriş Bilgileri Düzenle");
        jWelcome = new JLabel("Hoşgeldiniz " + ogretmen.getAdsoyad());
        jOdevGoster = new JButton("Ödev Göster");
        jDersAlanlar = new JButton("Ders Alanlar");
        jDersEkle = new JButton("Ders Ekle");
        jDersSil = new JButton("Ders Sil"); 
        jIstatistik = new JButton("Sınıf Istatistiği");
        jtDurum = new JLabel("");

        setPreferredSize(new Dimension(461, 310));
        setLayout(null);

        add(jDers);
        add(jDersler); 
        add(jYeniOdev);
        add(jOdevSil);
        add(jGirisDuzenle);
        add(jWelcome);
        add(jOdevGoster);
        add(jDersAlanlar);
        add(jDersEkle);
        add(jDersSil);
        add(jtDurum);  
        add(jIstatistik); 
        
        
        scroll = new JScrollPane(jOdevler);
        jOdevler.setLayoutOrientation(JList.VERTICAL);
        add(scroll);

        jDers.setBounds(10, 35, 45, 25);
        jDersler.setBounds(45, 35, 415, 25);
        scroll.setBounds(10, 100, 450, 155);
        jYeniOdev.setBounds(10, 260, 100, 25);
        jOdevSil.setBounds(115, 260, 100, 25);
        jGirisDuzenle.setBounds(290, 5, 170, 25);
        jWelcome.setBounds(10, 5, 450, 25);
        jOdevGoster.setBounds(220, 260, 115, 25);  
        jDersEkle.setBounds(10, 65, 100, 25);
        jDersSil.setBounds(115, 65, 100, 25);
        jDersAlanlar.setBounds(220, 65, 100, 25); 
        jIstatistik.setBounds(325, 65, 125, 25);
        jtDurum.setBounds(10, 285, 130, 25);

        jYeniOdev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Ödev Ekle");
                frame.setLocation(450, 450);
                frame.getContentPane().add(new OdevEkle(ogretmen));
                frame.pack();
                frame.setVisible(true);

                frame.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        jtDurum.setText("Ödev sisteme eklendi.");
                        OdevleriGetir();

                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }

                });
            }
        });

        jOdevSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jOdevler.getSelectedIndex() != -1) {
                    ((Ders) jDersler.getSelectedItem()).getOdevler().remove(((Odev) jOdevler.getSelectedValue()));
                    jtDurum.setText("Ödev Silindi");
                } else {
                    jtDurum.setText("Ödev seçmediniz.");
                }

            }
        }
        );

        jOdevGoster.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (jOdevler.getSelectedIndex() != -1) {
                    JFrame frame = new JFrame("Ödev Göster");
                    frame.getContentPane().add(new OdevDetay((Odev) jOdevler.getSelectedValue()));
                    frame.pack();
                    frame.setVisible(true);
                } else {
                    jtDurum.setText("Ödev seçmediniz.");
                }
            }
        }
        );
        
         jIstatistik.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (jDersler.getSelectedIndex() != -1) {
                    JFrame frame = new JFrame("Sınıf İstatistiği");
                    frame.getContentPane().add(new SinifIstatistik(((Ders) jDersler.getSelectedItem())));
                    frame.pack();
                    frame.setVisible(true);
                } else {
                    jtDurum.setText("Ödev seçmediniz.");
                }
            }
        }
        );

        jGirisDuzenle.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                JFrame frame = new JFrame("Giriş Bilgisi Düzenleme");
                frame.getContentPane().add(new GirisBilgileriDuzenle((Kullanici) ogretmen));
                frame.pack();
                frame.setVisible(true);
            }
        }
        );

        jDersler.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent arg0) {
                OdevleriGetir();
                exIndex = jDersler.getSelectedIndex();
            }
        }
        );
        jDersEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Ders Ekleme");

                frame.setLocation(450, 450);
                frame.getContentPane().add(new DersEkle((ogretmen)));
                frame.pack();
                frame.setVisible(true);

            }
        });
        jDersAlanlar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jDersler.getSelectedIndex() != -1) {
                    JFrame frame = new JFrame("Ders Alanlar");

                    frame.setLocation(450, 450);
                    frame.getContentPane().add(new DersAlanlar(((Ders) jDersler.getSelectedItem())));
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        jDersSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jDersler.getSelectedIndex() != -1) {
                    Ders ders = (Ders) jDersler.getSelectedItem();
                    OdevSistemi.dersler.remove(ders);
                    jtDurum.setText("Ders silindi.");
                    DersleriGetir();
                }

            }
        });

    }

    private void DersleriGetir() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(ogretmen.Dersleri().toArray());
        jDersler.setModel(model);
        if (ogretmen.Dersleri().toArray().length != 0) {
            jDersler.setEnabled(true);
            jDersler.setSelectedIndex(exIndex);
        }
    }

    private void OdevleriGetir() {
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
            DersleriGetir();
        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }

}
