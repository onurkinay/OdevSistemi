/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Onur Kınay
 */
public class OgrencininOdevi {

    private static int Kid = 0;
    private int id;
    private String ogrencininOdevi;
    private Ogrenci ogrenci;
    private Date yuklemeTarihi;
    private int not;

    public OgrencininOdevi(Ogrenci ogrenci, String ogrencininOdevi) {
        this.id = Kid++;
        this.ogrencininOdevi = ogrencininOdevi;
        this.ogrenci = ogrenci;
        this.yuklemeTarihi = new Date();
        this.not = -1;
    }

    public OgrencininOdevi(int id, String ogrencininOdevi, Ogrenci ogrenci, Date yuklemeTarihi, int not) {
        Kid++;
        this.id = id;
        this.ogrencininOdevi = ogrencininOdevi;
        this.ogrenci = ogrenci;
        this.yuklemeTarihi = yuklemeTarihi;
        this.not = not;
    }

    
    
    public String getOgrencininOdevi() {
        return ogrencininOdevi;
    }

    public Ogrenci getOgrenci() {
        return ogrenci;
    }

    @Override
    public String toString() {
        return ogrenci.getAdsoyad() + " // " + getNot();
    }

    public void NotVer(int not) { 
        if (this.not == -1) {
            this.not = not;
        }
    }

    public void setOgrencininOdevi(String ogrencininOdevi) {
        this.ogrencininOdevi = ogrencininOdevi;
    }
    
    public String yuklemeTarihi(){
        SimpleDateFormat tarihFormati = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return tarihFormati.format(this.yuklemeTarihi);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getINot() {
        return not;
    }

    public String getNot() {
        if (not == -1) {
            return "Notlandırılmadı";
        } else {
            return "Notlandırıldı - (" + not + ")";
        }
    }

    public void setNot(int not) {
        this.not = not;
    }

}
