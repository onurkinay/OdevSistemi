/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odevsistemi;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Onur Kınay
 */
public class OdevEkle extends JPanel {

    private JLabel jcomp1;
    private JLabel jcomp2;
    private JTextField jAdi;
    private JLabel jcomp5;
    private JTextArea jAciklama;
    private JLabel jcomp7;
    private JComboBox jDersler;
    private JButton jEkle;

    private JComboBox jGun;
    private JComboBox jAy;
    private JComboBox jYil;
    private JComboBox jSaat;
    private JLabel jcomp13;
    private JComboBox jDakika;

    public OdevEkle(Ogretmen ogretmen) {
        String[] jGunItems = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] jAyItems = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] jYilItems = {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029"};
        String[] jSaatItems = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] jDakikaItems = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
 
        jcomp1 = new JLabel("Ödev Adı:");
        jcomp2 = new JLabel("Ödev Teslim Tarihi: ");
        jAdi = new JTextField(5);
        jcomp5 = new JLabel("Açıklama: ");
        jAciklama = new JTextArea(5, 5);
        jcomp7 = new JLabel("Ders: ");
        jDersler = new JComboBox(ogretmen.Dersleri().toArray());
        jEkle = new JButton("Ödev Ekle");

        jGun = new JComboBox(jGunItems);
        jAy = new JComboBox(jAyItems);
        jYil = new JComboBox(jYilItems);
        jSaat = new JComboBox(jSaatItems);
        jcomp13 = new JLabel(":");
        jDakika = new JComboBox(jDakikaItems);
 
        setPreferredSize(new Dimension(662, 323));
        setLayout(null);
 
        add(jcomp1);
        add(jcomp2);
        add(jAdi);
        add(jcomp5);
        add(jAciklama);
        add(jcomp7);
        add(jDersler);
        add(jEkle);

        add(jGun);
        add(jAy);
        add(jYil);
        add(jSaat);
        add(jcomp13);
        add(jDakika);
 
        jcomp1.setBounds(30, 20, 65, 25);
        jcomp2.setBounds(30, 50, 115, 25);
        jAdi.setBounds(145, 20, 500, 25);
        jGun.setBounds(145, 50, 50, 25);
        jAy.setBounds(200, 50, 50, 25);
        jYil.setBounds(255, 50, 65, 25);
        jSaat.setBounds(330, 50, 45, 25);
        jcomp13.setBounds(380, 50, 10, 25);
        jDakika.setBounds(390, 50, 45, 25);
        jcomp5.setBounds(30, 80, 100, 25);
        jAciklama.setBounds(145, 85, 500, 155);
        jcomp7.setBounds(30, 250, 100, 25);
        jDersler.setBounds(145, 250, 500, 25);
        jEkle.setBounds(545, 285, 100, 25);

        jEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Ders ders : OdevSistemi.dersler) {
                    if (ders.getAd().equals(jDersler.getSelectedItem().toString())) {
                        try {
                            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            Date teslim = formatter1.parse(jGun.getSelectedItem() + "/" + jAy.getSelectedItem() + "/" + jYil.getSelectedItem() + " " + jSaat.getSelectedItem() + ":" + jDakika.getSelectedItem());
                            ders.OdevEkle(jAdi.getText(), teslim, jAciklama.getText());
                            
                            JComponent comp = (JComponent) e.getSource();
                            Window win = SwingUtilities.getWindowAncestor(comp);
                            win.dispose();
                        } catch (ParseException ex) {
                            Logger.getLogger(OdevEkle.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }

            }
        });
    }

}
