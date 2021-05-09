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
public class Istatistik extends JPanel{
    private JLabel jOdevAdi;
    private JLabel jOdevYapanlar;
    private JLabel jOdevYapmayanlar;
    private JLabel jOgrenciSayisi;
    private JLabel jMin;
    private JLabel jMax;
    private JLabel jOrt;

    public Istatistik(ArrayList<OgrencininOdevi> ogrencininOdevleri, ArrayList<Ogrenci> sorumlular, Odev odev){
         
        int min = 100;
        int max = -1;
        int ort = 0;
        int toplamOdev = 0;
        int yapanlar = 0;
        int yapmayanlar = 0;
        
        ArrayList<Ogrenci> yapanOgrenciler = new ArrayList<>();
        
        for(OgrencininOdevi oodev : ogrencininOdevleri){
            if(min > oodev.getINot()){
                min = oodev.getINot();
            }
            
            if(max < oodev.getINot()){
                max = oodev.getINot();
            }
            ort += oodev.getINot();
            toplamOdev++;
           yapanOgrenciler.add(oodev.getOgrenci());
        }
        
        for(Ogrenci ogrenci: sorumlular){
               boolean yapti = false;
           
            for(Ogrenci yapan:yapanOgrenciler){
                if(yapan.equals(ogrenci)){
                    yapanlar++;
                    yapti=true;
                }
            }
            
            if(!yapti) yapmayanlar++;
        }
        ort /= sorumlular.size();
        
        jOdevAdi = new JLabel ("Ödev Adı: "+odev.getAd());
        jOdevYapanlar = new JLabel ("Ödev Yapanların sayısı: "+yapanlar);
        jOdevYapmayanlar = new JLabel ("Ödev Yapmayanların sayısı: "+yapmayanlar);
        jOgrenciSayisi = new JLabel ("Öğrenci Sayısı: "+ sorumlular.size());
        jMin = new JLabel ("Minimum Not:"+ min);
        jMax = new JLabel ("Maximum Not: "+max);
        jOrt = new JLabel ("Ortalama Not:"+ort);

        //adjust size and set layout
        setPreferredSize (new Dimension (413, 216));
        setLayout (null);

        //add components
        add (jOdevAdi);
        add (jOdevYapanlar);
        add (jOdevYapmayanlar);
        add (jOgrenciSayisi);
        add (jMin);
        add (jMax);
        add (jOrt);

        //set component bounds (only needed by Absolute Positioning)
        jOdevAdi.setBounds (20, 15, 300, 25);
        jOdevYapanlar.setBounds (20, 65, 300, 25);
        jOdevYapmayanlar.setBounds (20, 90, 300, 25);
        jOgrenciSayisi.setBounds (20, 40, 100, 25);
        jMin.setBounds (20, 115, 100, 25);
        jMax.setBounds (20, 140, 100, 25);
        jOrt.setBounds (20, 165, 100, 25);
        
     
        
      
    }
}
