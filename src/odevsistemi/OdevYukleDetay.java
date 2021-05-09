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
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * @author Onur Kınay
 */
public class OdevYukleDetay extends JPanel {

    private JLabel jOdevAdi;
    private JLabel jTeslim;
    private JLabel jOlusturulma;
    private JLabel jDurum;
    private JLabel jDers;
    private JLabel jOdev;
    private JLabel jAciklama;
    private JTextArea ogrencininOdevi;
    private JButton jOdevEkle;
    private JButton jOdevDuzenle;
    private JTextArea jOgAciklama;
    private Ogrenci ogrenci;
    private Odev odev;
    private OgrencininOdevi oOdev = null;
    private JLabel jtDurum;

    public OdevYukleDetay(Odev odev, Ogrenci ogrenci) {
        this.ogrenci = ogrenci;
        this.odev = odev;
        //construct components
        jOdevAdi = new JLabel("Ödev Adı: " + odev.getAd());
        jTeslim = new JLabel("Teslim Tarihi: " + odev.getTeslimtarihi() + " / " + odev.kalanSureYazi());
        jDurum = new JLabel("Ödev Durumu: Yeni ödev / Gönderilebilir");
        jOlusturulma = new JLabel("Oluşturulma Tarihi: " + odev.getOlusturulmatarihi());
        jDers = new JLabel("Ders: " + odev.getDers().getAd());
        jOdev = new JLabel("Ödev");
        jAciklama = new JLabel("Açıklama");
        ogrencininOdevi = new JTextArea(5, 5);
        jOgAciklama = new JTextArea(5, 5);
        jOdevEkle = new JButton("Ödev Ekle");
        jOdevDuzenle = new JButton("Ödev Düzenle");
        jtDurum = new JLabel("");

        //adjust size and set layout
        setPreferredSize(new Dimension(451, 550));
        setLayout(null);

        //add components
        add(jOdevAdi);
        add(jTeslim);
        add(jOlusturulma);
        add(jAciklama);
        add(jDurum);
        add(jDers);
        add(jOdev);
        add(ogrencininOdevi);
        add(jOgAciklama);
        add(jtDurum);

        jOgAciklama.setText(odev.getAciklama());
        jOgAciklama.setEnabled(false);
        boolean odevVarmi = false;

        for (OgrencininOdevi yOdev : odev.getOdevler()) {
            if (yOdev.getOgrenci() == ogrenci) {
                odevVarmi = true;
                oOdev = yOdev;
                ogrencininOdevi.setText(yOdev.getOgrencininOdevi());
                break;
            }
        }
        if (odevVarmi) {
            add(jOdevDuzenle);
            jDurum.setText("Odev Durumu: Düzenlenebilir / Gönderildi / " + oOdev.getNot());
            if (odev.kalanSure() < 0) {
                jOdevDuzenle.setEnabled(false);
                jDurum.setText("Ödev Durumu: Düzenlenemez / Gönderildi /" + oOdev.getNot());

            }
        } else {
            add(jOdevEkle);
            if (odev.kalanSure() < 0) {
                jOdevEkle.setEnabled(false);
                jDurum.setText("Ödev Durumu: Geç kalındı / Gönderilmedi");
            }
        }

        //set component bounds (only needed by Absolute Positioning)
        jOdevAdi.setBounds(30, 20, 500, 25);
        jTeslim.setBounds(30, 50, 500, 25);
        jOlusturulma.setBounds(30, 80, 500, 25);
        jDurum.setBounds(30, 110, 500, 25);
        jDers.setBounds(30, 140, 500, 25);
        jOdev.setBounds(30, 350, 500, 25);
        ogrencininOdevi.setBounds(81, 350, 345, 170);
        jAciklama.setBounds(30, 100, 345, 170);
        jOgAciklama.setBounds(81, 170, 345, 170);
        jOdevEkle.setBounds(300, 520, 125, 25);
        jOdevDuzenle.setBounds(300, 520, 125, 25);
        jtDurum.setBounds(30, 520, 125, 25);

        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jTeslim.setText("Teslim Tarihi: " + odev.getTeslimtarihi() + " / " + odev.kalanSureYazi());
            }
        });
        t.start();

        jOdevEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(ogrencininOdevi.getText())) {
                    odev.OdevYap(ogrenci, ogrencininOdevi.getText());//boş ödev hatası
                    jtDurum.setText("Ödev gönderildi.");
                } else {
                    jtDurum.setText("Ödev girmediniz.");
                }
            }
        });

        jOdevDuzenle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(ogrencininOdevi.getText())) {
                    String yeniOdev = ogrencininOdevi.getText();
                    oOdev.setOgrencininOdevi(yeniOdev);
                } else {
                    jtDurum.setText("Ödev girmediniz.");
                }
            }
        });
    }

}
