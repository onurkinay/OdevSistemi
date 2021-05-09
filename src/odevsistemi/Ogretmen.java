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
public class Ogretmen extends Kullanici implements IKullanici {

    private String departman;

    public Ogretmen(String kadi, String sifre, String adsoyad, String departman) {
        super(kadi, sifre, adsoyad);
        this.departman = departman;
    }

    public Ogretmen(int id,  String kadi, String sifre, String adsoyad, String departman) {
        super(kadi, sifre, adsoyad, id);
        this.departman = departman;
    }

    @Override
    public String getKadi() {
        return kadi;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    @Override
    public String getSifre() {
        return sifre;
    }

    public ArrayList<Ders> Dersleri() {
        ArrayList<Ders> ogretmeninDersleri = new ArrayList<>();
        for (Ders ders : OdevSistemi.dersler) {
            if (ders.getOgretmen() == this) {
                ogretmeninDersleri.add(ders);
            }
        }
        return ogretmeninDersleri;
    }

}
