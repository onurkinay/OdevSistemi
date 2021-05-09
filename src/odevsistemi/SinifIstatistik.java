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
public class SinifIstatistik extends JPanel {

    private JLabel jDersAdi;
    private JComboBox jLSiniflar;
    private JLabel jMax;
    private JLabel jMin;
    private JLabel jOrt;

    public SinifIstatistik(Ders ders) {

        for (Odev odev : ders.getOdevler()) {

        }
        ArrayList<String> bolumler = new ArrayList<>();
        for (Kullanici kullanici : OdevSistemi.kullanicilar) {
            if (kullanici instanceof Ogrenci) {
                Ogrenci ogrenci = (Ogrenci) kullanici;
                if (!bolumler.contains(ogrenci.getBolum())) {
                    bolumler.add(ogrenci.getBolum());
                }
            }
        }
        jDersAdi = new JLabel("Ders Adı: " + ders.getAd());
        jLSiniflar = new JComboBox(bolumler.toArray());
        jMax = new JLabel("Maximum");
        jMin = new JLabel("Minimum");
        jOrt = new JLabel("Ortalama");
 
        setPreferredSize(new Dimension(258, 170));
        setLayout(null);
 
        add(jDersAdi);
        add(jLSiniflar);
        add(jMax);
        add(jMin);
        add(jOrt);
 
        jDersAdi.setBounds(15, 15, 220, 25);
        jLSiniflar.setBounds(15, 50, 220, 25);
        jMax.setBounds(15, 85, 220, 25);
        jMin.setBounds(15, 105, 100, 25);
        jOrt.setBounds(15, 125, 100, 25);

        jLSiniflar.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent arg0) {
                ArrayList<Ogrenci> sinifOgrencileri = new ArrayList<>();
                for (Kullanici kullanici : OdevSistemi.kullanicilar) {
                    if (kullanici instanceof Ogrenci) {
                        Ogrenci ogrenci = (Ogrenci) kullanici;
                        if (ogrenci.getBolum().equals((String) jLSiniflar.getSelectedItem()) && ogrenci.getDersler().contains(ders)) {
                            sinifOgrencileri.add(ogrenci);
                        }
                    }

                }
                int min = 100;
                int max = 0;
                int toplam = 0;
                int ortSayac = 0;
                for (Ogrenci ogrenci : sinifOgrencileri) {
                    for (Ders ogrenciDers : ogrenci.getDersler()) {
                        if (ders.equals(ogrenciDers)) {
                            for (Odev odev : ogrenciDers.getOdevler()) {
                                for (OgrencininOdevi oodev : odev.getOdevler()) {
                                    if (oodev.getOgrenci().equals(ogrenci)) {
                                        if (min > oodev.getINot()) {
                                            min = oodev.getINot();
                                        }

                                        if (max < oodev.getINot()) {
                                            max = oodev.getINot();
                                        }
                                        toplam += oodev.getINot();
                                        ortSayac++;
                                    }
                                }
                            }
                        }
                    }
                }
                if (ortSayac > 0) {
                    jMax.setText("Maximum: " + max);
                    jMin.setText("Minimum: " + min);
                    jOrt.setText("Ortalama: " + (toplam / ortSayac));
                } else {
                    jMax.setText("Ödev Bulunamadı");
                    jMin.setText("");
                    jOrt.setText("");
                }
            }
        }
        );
    }
}
