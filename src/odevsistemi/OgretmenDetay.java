/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Onur Kınay
 */
public class OgretmenDetay extends JPanel {

    private JLabel jOAdi;
    private JLabel jOBolum;
    private JList jODersler;
    private JLabel jtDersler;
    private JScrollPane scroll;

    public OgretmenDetay(Ogretmen ogretmen) {
        jOAdi = new JLabel("Öğretmen Adı: " + ogretmen.getAdsoyad());
        jOBolum = new JLabel("Departman: " + ogretmen.getDepartman());
        jODersler = new JList();
        jtDersler = new JLabel("Öğretmenin Verdiği Dersler");
        jODersler.setListData((ogretmen.Dersleri().toArray().length != 0 ? ogretmen.Dersleri().toArray() : new String[]{"Ders bulunamadı"}));
        setPreferredSize(new Dimension(343, 307));
        setLayout(null);

        add(jOAdi);
        add(jOBolum);
        add(jODersler);
        add(jtDersler);

        scroll = new JScrollPane(jODersler);
        jODersler.setLayoutOrientation(JList.VERTICAL);
        add(scroll);

        jOAdi.setBounds(15, 20, 300, 25);
        jOBolum.setBounds(15, 50, 300, 25);
        scroll.setBounds(15, 110, 310, 180);
        jtDersler.setBounds(15, 80, 300, 25);

    }
}
