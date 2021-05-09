/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Onur Kınay
 */
public class Veri {

    private static ArrayList<ArrayList<String>> dersKayitlari = new ArrayList<>(); 

    public static void KullanicilarKaydet() {
        File file = new File("kullanicilar.txt");//proje içinde text.txt adında bir txt oluşturun.
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            for (Kullanici kullanici : OdevSistemi.kullanicilar) {
                if (kullanici instanceof Ogrenci) {
                    br.write("Ogrenci=" + kullanici.getId() + "," + kullanici.getKadi() + "," + kullanici.getSifre() + "," + kullanici.getAdsoyad() + "," + ((Ogrenci) kullanici).getBolum() + ",dersler{");
                    for (Ders ders : ((Ogrenci) kullanici).getDersler()) {
                        br.write(ders.getId() + ",");
                    }
                    br.write("}");
                    br.newLine();
                } else if (kullanici instanceof Ogretmen) {
                    br.write("Ogretmen=" + kullanici.getId() + "," + kullanici.getKadi() + "," + kullanici.getSifre() + "," + kullanici.getAdsoyad() + "," + ((Ogretmen) kullanici).getDepartman());
                    br.newLine();
                }

            }
        } catch (IOException e) {
            System.out.println("Unable to read file " + file.toString());
            System.exit(0);
        }
    }
//Ders=id,ad,sifre,ogretmen,odevler

    public static void DersleriKaydet() {
        File file = new File("dersler.txt");//proje içinde text.txt adında bir txt oluşturun.
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            for (Ders ders : OdevSistemi.dersler) {

                br.write("Ders=" + ders.getId() + "," + ders.getAd() + "," + ders.getSifre() + "," + ders.getOgretmen().getId() + ",odevler{");
                for (Odev odev : ders.getOdevler()) {
                    br.write(odev.getId() + ",");
                }
                br.write("}");
                br.newLine();

            }
        } catch (IOException e) {
            System.out.println("Unable to read file " + file.toString());
            System.exit(0);
        }
    }

    public static void OgrencininOdevleriKaydet() {
        File file = new File("odevler.txt");//proje içinde text.txt adında bir txt oluşturun.
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            for (Ders ders : OdevSistemi.dersler) {
                br.write("-" + ders.getId());
                br.newLine();
                for (Odev odev : ders.getOdevler()) {
                    br.write("--" + odev.getId() + "~" + odev.getAd() + "~" + odev.getAciklama() + "~" + odev.getOlusturulmatarihi() + "~" + odev.getTeslimtarihi() + "");
                    br.newLine();
                    for (OgrencininOdevi oodev : odev.getOdevler()) {
                        br.write("---" + oodev.getId() + "," + oodev.getOgrenci().getId() + "," + oodev.getINot() + "," + oodev.yuklemeTarihi() + "," + oodev.getOgrencininOdevi());
                        br.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read file " + file.toString());
            System.exit(0);
        }
    }

    public static void KullanicilariCek() throws FileNotFoundException {
        String filename = "kullanicilar.txt";
        File textFile = new File(filename);
        try (Scanner in = new Scanner(textFile)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] kullaniciBilgi = line.split("=", 2);
                if ("Ogrenci".equals(kullaniciBilgi[0])) {
                    String[] bilgi = kullaniciBilgi[1].split(",", 6);
                    dersKayitlari.add(new ArrayList<>());
                    dersKayitlari.get(dersKayitlari.size() - 1).add(bilgi[0]);
                    dersKayitlari.get(dersKayitlari.size() - 1).add(bilgi[5].split("dersler")[1].replaceAll("\\{", "").replaceAll("\\}", ""));
                    Ogrenci ogrenci = new Ogrenci(Integer.parseInt(bilgi[0]), bilgi[1], bilgi[2], bilgi[3], bilgi[4]);
                    OdevSistemi.kullanicilar.add(ogrenci);

                } else if ("Ogretmen".equals(kullaniciBilgi[0])) {
                    String[] bilgi = kullaniciBilgi[1].split(",", 6);
                    Ogretmen ogretmen = new Ogretmen(Integer.parseInt(bilgi[0]), bilgi[1], bilgi[2], bilgi[3], bilgi[4]);
                    OdevSistemi.kullanicilar.add(ogretmen);
                }
            }
        }
    }

    public static void DersleriCek() throws FileNotFoundException {
        String filename = "dersler.txt";
        File textFile = new File(filename);
        Scanner in = new Scanner(textFile);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] dDersler = line.split("=", 2);
            String[] bilgi = dDersler[1].split(",", 5);

            OdevSistemi.dersler.add(new Ders(Integer.parseInt(bilgi[0]), bilgi[1], bilgi[2], Integer.parseInt(bilgi[3]), new ArrayList<Odev>()));
        }
        in.close();

        for (Kullanici ogrenci : OdevSistemi.kullanicilar) {
            if (ogrenci instanceof Ogrenci) {

                for (ArrayList<String> dersKaydi : dersKayitlari) {
                    if (ogrenci.getId() == Integer.parseInt(dersKaydi.get(0))) {

                        if (dersKaydi.get(1).length() != 0) {
                            String[] ders2 = dersKaydi.get(1).substring(0, dersKaydi.get(1).lastIndexOf(",")).split(",");
                            for (Ders sistemDersi : OdevSistemi.dersler) {
                                for (String ders22 : ders2) {
                                    if (sistemDersi.getId() == Integer.parseInt(ders22)) {
                                        ((Ogrenci) ogrenci).dersler.add(sistemDersi);

                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    public static void OgrenciOdevleriCek() throws FileNotFoundException {
        String filename = "odevler.txt";
        File textFile = new File(filename);
        Scanner in = new Scanner(textFile);
        int dersId = -1;
        int odevId = -1;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            try {

                if ("-".equals(line.substring(0, 1))) {
                    if ("--".equals(line.substring(0, 2))) {
                        if ("---".equals(line.substring(0, 3))) {
                            String[] odev = line.substring(3).split(",", 5);

                            SimpleDateFormat tarihFormati = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
                            String ogrencininOdevi = odev[4];
                            Date yuklenmeTarihi = tarihFormati.parse(odev[3]); 
                            for (Kullanici kullanici : OdevSistemi.kullanicilar) {
                                if (kullanici instanceof Ogrenci) {
                                    if (kullanici.getId() == Integer.parseInt(odev[1])) {
                                       Ogrenci ogrenci = (Ogrenci) kullanici;
                                        OgrencininOdevi oodev = new OgrencininOdevi(Integer.parseInt(odev[0]), ogrencininOdevi, ogrenci, yuklenmeTarihi, Integer.parseInt(odev[2]));
                                        for (Ders ders : OdevSistemi.dersler) {
                                            if (ders.getId() == dersId) {
                                                for (Odev Sodev : ders.getOdevler()) {
                                                    if (Sodev.getId() == odevId) {

                                                        Sodev.OdevYap(oodev);
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                            }

                            continue;
                        }
                        String[] odev = line.substring(2).split("~", 5);

                        odevId = Integer.parseInt(odev[0]);
                        for (Ders ders : OdevSistemi.dersler) {
                            boolean varmi = false;
                            if (ders.getId() == dersId) {
                                for (Odev Dodev : ders.getOdevler()) {
                                    if (odevId == Dodev.getId()) {
                                        varmi = true;
                                        break;
                                    }
                                }
                                if (!varmi) {
                                    ders.getOdevler().add(new Odev(odevId, odev[1], odev[3], odev[4], ders, odev[2]));
                                }

                            }
                        }
                        continue;
                    }
                    dersId = Integer.parseInt(line.substring(1));
                }

            } catch (Exception ex) {

            }
        }
        in.close();
    }
}
