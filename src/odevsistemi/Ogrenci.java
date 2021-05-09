/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.util.ArrayList;

/**
 *
 * @author Onur Kınay
 */
public class Ogrenci extends Kullanici implements IKullanici {

    private String bolum;
    protected ArrayList<Ders> dersler;

    public Ogrenci(String bolum, String kadi, String sifre, String adsoyad) {
        super(kadi, sifre, adsoyad);
        this.bolum = bolum;
        this.dersler = new ArrayList<>();
    }

    public Ogrenci(int id, String kadi, String sifre, String adsoyad, String bolum) {
        super(kadi, sifre, adsoyad,id); 
        this.bolum = bolum; 
        this.dersler = new ArrayList<>();
    }

    @Override
    public String getKadi() {
        return kadi;
    }

    public ArrayList<Ders> getDersler() {
        if(!this.dersler.isEmpty())
             return dersler;
        else return new ArrayList<>();
    }

    public String getBolum() {
        return bolum;
    }

    @Override
    public String toString() {
        return adsoyad+" / Bölüm: "+bolum;
    }

    @Override
    public String getSifre() {
        return sifre;
    }

    public Ders DerseKatil(ArrayList<Ders> dersler, String dersKodu) throws Exception { 
        for (Ders ders : dersler) {
            if (ders.getSifre().equals(dersKodu)) {
                if (!this.dersler.contains(ders)) {
                    this.dersler.add(ders);
                    return ders;
                } else {
                    throw new Exception("Derse zaten katıldınız");
                }
            }
        }
        throw new Exception("Ders bulunamadı");
    }
 
}
