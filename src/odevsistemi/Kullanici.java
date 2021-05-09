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
public class Kullanici implements IKullanici {

    private static int Kid = 0;
    protected int id;
    protected String kadi;
    protected String sifre;
    protected String adsoyad;

    public Kullanici(String kadi, String sifre, String adsoyad) {
        for(Kullanici kullanici : OdevSistemi.kullanicilar){
            if(kullanici.getId() == Kid) Kid++;
        }
        this.id = Kid;
        this.kadi = kadi;
        this.sifre = sifre;
        this.adsoyad = adsoyad;
    }

    public Kullanici(String kadi, String sifre, String adsoyad, int id) {
        Kid++;
        this.id = id;
        this.kadi = kadi;
        this.sifre = sifre;
        this.adsoyad = adsoyad;
    }

    public void GirisBilgileriDuzenle(String yeniKadi, String yeniSifre) {
        this.kadi = yeniKadi;
        this.sifre = yeniSifre;
    }

    public int getId() {
        return id;
    }

    public void setKadi(String kadi) {
        this.kadi = kadi;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    @Override
    public String getKadi() {
        return kadi;
    }

    @Override
    public String getSifre() {
        return sifre;
    }

    public String getAdsoyad() {
        return adsoyad;
    }

    public static Kullanici GirisYap(String ka, String sifre, ArrayList<Kullanici> kullanicilar) {
        for (Kullanici kullanici : kullanicilar) {
            if (kullanici.getKadi().equals(ka) && kullanici.getSifre().equals(sifre)) {
                return kullanici;
            }
        }
        return null;
    }
}
