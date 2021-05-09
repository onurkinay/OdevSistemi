/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Onur Kınay
 */
public class Odev {

    private static int Kid = 0;
    private int id;
    private String ad;
    private Date teslimtarihi;
    private Date olusturulmatarihi;
    private Ders ders;
    private String aciklama;
    private ArrayList<OgrencininOdevi> odevler;

    public Odev(String ad, Date teslimtarihi, Ders ders, String aciklama) {
        for (Odev odev : ders.getOdevler()) {
            if (Kid == odev.getId()) {
                Kid++;
            }
        }
        this.id = Kid;
        this.ad = ad;
        this.teslimtarihi = teslimtarihi;
        this.olusturulmatarihi = new Date();
        this.ders = ders;
        this.aciklama = aciklama;
        this.odevler = new ArrayList<>();
    }

    public Odev(int id, String ad, String olusturulmatarihi, String teslimtarihi, Ders ders, String aciklama) throws ParseException {
        Kid++;
        SimpleDateFormat tarihFormati = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.id = id;
        this.ad = ad;
        this.teslimtarihi = tarihFormati.parse(teslimtarihi);
        this.olusturulmatarihi = tarihFormati.parse(olusturulmatarihi);
        this.ders = ders;
        this.aciklama = aciklama;
        this.odevler = new ArrayList<>();
    }

    public ArrayList<OgrencininOdevi> getOdevler() {
        return odevler;
    }

    @Override
    public String toString() {
        return ad + " " + this.kalanSureYazi();
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

    public String getTeslimtarihi() {
        return tarihFormati(teslimtarihi);
    }

    public void setTeslimtarihi(Date teslimtarihi) {
        this.teslimtarihi = teslimtarihi;
    }

    public String getOlusturulmatarihi() {
        return tarihFormati(olusturulmatarihi);
    }

    public void setOlusturulmatarihi(Date olusturulmatarihi) {
        this.olusturulmatarihi = olusturulmatarihi;
    }

    public Ders getDers() {
        return ders;
    }

    public void setDers(Ders ders) {
        this.ders = ders;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public void OdevYap(Ogrenci ogrenci, String ogrencininOdevi) {
        OgrencininOdevi odev = new OgrencininOdevi(ogrenci, ogrencininOdevi);
        odevler.add(odev);
    }

    public void OdevYap(OgrencininOdevi odev) {
        odevler.add(odev);
    }

    public long kalanSure() {
        long diff = this.teslimtarihi.getTime() - (new Date()).getTime();
        return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String kalanSureYazi() {
        if (this.kalanSure() > 0) {
            long diff = this.teslimtarihi.getTime() - (new Date()).getTime();
            int kalanDakika = (int) TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);

            int gun = kalanDakika / (60 * 24);
            int saat = (kalanDakika / 60) % 24;
            int dakika = kalanDakika % (60);
            return "Kalan Süre: " + gun + " Gün " + saat + " Saat " + dakika + " Dakika";
        } else {
            return "Tarihi geçen ödev";
        }
    }

    private String tarihFormati(Date tarih) {
        SimpleDateFormat tarihFormati = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return tarihFormati.format(tarih);
    }
}
