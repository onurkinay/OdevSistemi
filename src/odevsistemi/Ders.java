/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Onur Kınay
 */
public class Ders {

    private static int Kid = 0;
    private int id;
    private String ad;
    private String sifre;
    private Ogretmen ogretmen;
    private ArrayList<Odev> odevler;

    public Ders(String ad, String sifre, Ogretmen ogretmen) {
        for(Ders ders: OdevSistemi.dersler){
            if(Kid == ders.getId()){
                Kid++;
            }
        }
        this.id = Kid;
        this.ad = ad;
        this.sifre = sifre;
        this.ogretmen = ogretmen;
        this.odevler = new ArrayList<>();
    }

    public Ders(int id, String ad, String sifre, int ogretmen, ArrayList<Odev> odevler) {
        Kid++;
        this.id = id;
        this.ad = ad;
        this.sifre = sifre;
        for (Kullanici kullanici : OdevSistemi.kullanicilar) {
            if (kullanici instanceof Ogretmen) {
                if (ogretmen == ((Ogretmen) kullanici).id) {
                    this.ogretmen = (Ogretmen) kullanici;
                }
            }
        } 
        this.odevler = odevler;
    }

    @Override
    public String toString() {
        return ad;
    }

    public Ogretmen getOgretmen() {
        return ogretmen;
    }
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void OdevEkle(String ad, Date teslimtarihi, String aciklama) {
        odevler.add(new Odev(ad, teslimtarihi, this, aciklama));
    }

    public ArrayList<Odev> getOdevler() {
        return odevler;
    }
}
