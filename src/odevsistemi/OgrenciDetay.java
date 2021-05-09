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
public class OgrenciDetay extends JPanel {

    private JLabel jOAdi;
    private JLabel jOBolum;
    private JList jODersler;
    private JLabel jtDersler; 
    private JScrollPane scroll;

    public OgrenciDetay(Ogrenci ogrenci) {
        jOAdi = new JLabel("Öğrenci Adı: " + ogrenci.getAdsoyad());
        jOBolum = new JLabel("Bölümü: " + ogrenci.getBolum());
        jODersler = new JList();
        jtDersler = new JLabel("Öğrencinin Aldığı Dersler");

        jODersler.setListData((ogrenci.getDersler().toArray().length != 0 ? ogrenci.getDersler().toArray() : new String[]{"Ders bulunamadı"}));

        setPreferredSize(new Dimension(343, 307));
        setLayout(null);

        add(jOAdi);
        add(jOBolum); 
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
