/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Onur Kınay
 */
public class OdevDetay extends JPanel {

    private JLabel jOdev;
    private JLabel jDers;
    private JLabel jTeslim;
    private JLabel jOlusTarihi;
    private JTextArea jtAciklama;
    private JLabel jAciklama;
    private JButton jOdevDuzenle;
    private JLabel jOdevDurumlari;
    private JList jOdevler;
    private JButton jOdevGoruntule;
    private JTextField jtOdevAdi;
    private JComboBox jGun;
    private JComboBox jAy;
    private JComboBox jYil;
    private JComboBox jSaat;
    private JLabel jAyrac;
    private JComboBox jDakika;
    private JLabel jtDers;
    private JLabel jtDurum;
    private JScrollPane scroll;
    private JButton jIstatistik;

    public OdevDetay(Odev odev) {

        String[] jGunItems = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] jAyItems = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] jYilItems = {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029"};
        String[] jSaatItems = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] jDakikaItems = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

        jOdev = new JLabel("Ödev Adı: ");
        jDers = new JLabel("Ders: ");
        jTeslim = new JLabel("Ödev Teslim Tarihi");
        jOlusTarihi = new JLabel("Ödev Oluşturulma Tarihi: " + odev.getOlusturulmatarihi());
        jtAciklama = new JTextArea(5, 5);
        jAciklama = new JLabel("Açıklama");
        jOdevDuzenle = new JButton("Ödevi Düzenle");
        jOdevDurumlari = new JLabel("Ödev Durumları");
        jOdevler = new JList();
        jOdevGoruntule = new JButton("Ödev Görüntüle");
        jtOdevAdi = new JTextField(5);
        jGun = new JComboBox(jGunItems);
        jAy = new JComboBox(jAyItems);
        jYil = new JComboBox(jYilItems);
        jSaat = new JComboBox(jSaatItems);
        jAyrac = new JLabel(":");
        jDakika = new JComboBox(jDakikaItems);
        jtDers = new JLabel(odev.getDers().getAd());
        jtDurum = new JLabel("Durum: ");
        jtAciklama.setText(odev.getAciklama());
        jtOdevAdi.setText(odev.getAd());
        jIstatistik = new JButton("İstatistik");

        String[] tarih = odev.getTeslimtarihi().split(" ")[0].split("/");
        String[] saat = odev.getTeslimtarihi().split(" ")[1].split(":");

        jGun.setSelectedItem(tarih[0]);
        jAy.setSelectedItem(tarih[1]);
        jYil.setSelectedItem(tarih[2]);
        jSaat.setSelectedItem(saat[0]);
        jDakika.setSelectedItem(saat[1]);

        //adjust size and set layout
        setPreferredSize(new Dimension(680, 650));
        setLayout(null);

        //add components
        add(jOdev);
        add(jDers);
        add(jTeslim);
        add(jOlusTarihi);
        add(jtAciklama);
        add(jAciklama);
        add(jOdevDuzenle);
        add(jOdevDurumlari);
        add(jOdevGoruntule);
        add(jtOdevAdi);
        add(jGun);
        add(jAy);
        add(jYil);
        add(jSaat);
        add(jAyrac);
        add(jDakika);
        add(jtDers);
        add(jtDurum);
        add(jIstatistik);

        scroll = new JScrollPane(jOdevler);
        jOdevler.setLayoutOrientation(JList.VERTICAL);
        add(scroll);

        //set component bounds (only needed by Absolute Positioning)
        jOdev.setBounds(30, 10, 59, 25);
        jDers.setBounds(30, 40, 36, 25);
        jTeslim.setBounds(30, 70, 125, 25);
        jOlusTarihi.setBounds(30, 100, 570, 25);
        jtAciklama.setBounds(90, 140, 565, 130);
        jAciklama.setBounds(30, 140, 60, 25);
        jOdevDuzenle.setBounds(30, 285, 145, 25);
        jOdevDurumlari.setBounds(30, 340, 100, 25);
        scroll.setBounds(30, 375, 625, 230);
        jOdevGoruntule.setBounds(30, 615, 140, 25);
        jIstatistik.setBounds(180, 615, 140, 25);
        jtDurum.setBounds(185, 285, 140, 25);
        jtOdevAdi.setBounds(175, 10, 250, 25);
        jGun.setBounds(175, 70, 40, 25);
        jAy.setBounds(220, 70, 40, 25);
        jYil.setBounds(265, 70, 60, 25);
        jSaat.setBounds(335, 70, 40, 25);
        jAyrac.setBounds(378, 70, 7, 25);
        jDakika.setBounds(385, 70, 40, 25);
        jtDers.setBounds(175, 40, 250, 25);

        jOdevler.setListData(odev.getOdevler().toArray());
        jOdevler.setEnabled(true);
        if (odev.getOdevler().toArray().length == 0) {
            jOdevler.setListData(new String[]{"Ödev bulunamadı"});
            jOdevler.setEnabled(false);
        }

        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jTeslim.setText("Ödev Teslim Tarihi: " + odev.getTeslimtarihi() + " / Kalan Süre: " + odev.kalanSure());
            }
        });

        t.start();

        jOdevDuzenle.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    odev.setAciklama(jtAciklama.getText());
                    odev.setAd(jtOdevAdi.getText());

                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    Date teslim = formatter1.parse(jGun.getSelectedItem() + "/" + jAy.getSelectedItem() + "/" + jYil.getSelectedItem() + " " + jSaat.getSelectedItem() + ":" + jDakika.getSelectedItem());
                    odev.setTeslimtarihi(teslim);

                    jtDurum.setText("Ödev düzenlendi.");
                } catch (ParseException ex) {
                    Logger.getLogger(OdevDetay.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );

        jOdevGoruntule.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (jOdevler.getSelectedIndex() != -1) {
                    JFrame frame = new JFrame("Ödev Görüntüle");
                    frame.getContentPane().add(new OdevNotla((OgrencininOdevi) jOdevler.getSelectedValue(), odev));
                    frame.pack();
                    frame.setVisible(true);
                } else {
                    jtDurum.setText("Ödev seçilmedi.");
                }
            }
        }
        );
        jIstatistik.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if(jOdevler.isEnabled()){
                ArrayList<OgrencininOdevi> ogrencininOdevleri = new ArrayList<>();
                boolean hepsiNotlandirildi = true;
                for (int i = 0; i < jOdevler.getModel().getSize(); i++) {
                    ogrencininOdevleri.add(((OgrencininOdevi) jOdevler.getModel().getElementAt(i)));
                    if(ogrencininOdevleri.get(i).getINot() == -1) {
                        hepsiNotlandirildi = false;
                        break;
                    }
                }
                ArrayList<Ogrenci> sorumluluar = new ArrayList<>();
                for(Kullanici kullanici : OdevSistemi.kullanicilar){
                    if(kullanici instanceof Ogrenci){
                        Ogrenci ogrenci = (Ogrenci)kullanici;
                        for(Ders ders:ogrenci.getDersler()){
                            if(odev.getDers().equals(ders)){
                                sorumluluar.add(ogrenci);
                            }
                        }
                    }
                }
                
                if(hepsiNotlandirildi){
                JFrame frame = new JFrame("İstatistik");
                frame.getContentPane().add(new Istatistik(ogrencininOdevleri,sorumluluar,odev));
                frame.pack();
                frame.setVisible(true);
                }else{
                      jtDurum.setText("Ödevleri hepsini notlandırmadığınız için istatistik açılamaz.");
                }

            }else{
                      jtDurum.setText("Ödev bulunamadı");
                }
            }
        }
        );
    }

}
