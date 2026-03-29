package com.cooperative.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Reservation {
    private String idreserv;
    private String idvoit;
    private int idcli;
    private int place;
    private Timestamp date_reserv;
    private Date date_voyage;
    private String payment;
    private int montant_avance;

    public Reservation() {
    }

    public Reservation(String idreserv, String idvoit, int idcli, int place, Timestamp date_reserv, Date date_voyage, String payment, int montant_avance) {
        this.idreserv = idreserv;
        this.idvoit = idvoit;
        this.idcli = idcli;
        this.place = place;
        this.date_reserv = date_reserv;
        this.date_voyage = date_voyage;
        this.payment = payment;
        this.montant_avance = montant_avance;
    }

    public String getIdreserv() {
        return idreserv;
    }

    public void setIdreserv(String idreserv) {
        this.idreserv = idreserv;
    }

    public String getIdvoit() {
        return idvoit;
    }

    public void setIdvoit(String idvoit) {
        this.idvoit = idvoit;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Timestamp getDate_reserv() {
        return date_reserv;
    }

    public void setDate_reserv(Timestamp date_reserv) {
        this.date_reserv = date_reserv;
    }

    public Date getDate_voyage() {
        return date_voyage;
    }

    public void setDate_voyage(Date date_voyage) {
        this.date_voyage = date_voyage;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getMontant_avance() {
        return montant_avance;
    }

    public void setMontant_avance(int montant_avance) {
        this.montant_avance = montant_avance;
    }

    public int calculerReste(int frais) {
        return frais - this.montant_avance;
    }
}
