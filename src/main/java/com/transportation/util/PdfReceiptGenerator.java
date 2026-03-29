package com.transportation.util;

import com.transportation.model.Client;
import com.transportation.model.Reservation;
import com.transportation.model.Voiture;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PdfReceiptGenerator {
    public static void generateReceiptPdf(String filePath, Reservation reservation, Client client, Voiture voiture, int reste) throws DocumentException, IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            generateReceiptPdf(fos, reservation, client, voiture, reste);
        }
    }

    public static void generateReceiptPdf(OutputStream os, Reservation reservation, Client client, Voiture voiture, int reste) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, os);
        document.open();

        document.add(new Paragraph("Recu de reservation"));
        document.add(new Paragraph("Reference: " + reservation.getIdreserv()));
        document.add(new Paragraph("Client: " + client.getNom() + " - " + client.getNumtel()));
        document.add(new Paragraph("Voiture: " + (voiture != null ? voiture.getDesign() : "N/A") + " (" + reservation.getIdvoit() + ")"));
        document.add(new Paragraph("Place: " + reservation.getPlace()));
        document.add(new Paragraph("Date reservation: " + reservation.getDate_reserv()));
        document.add(new Paragraph("Date voyage: " + reservation.getDate_voyage()));
        document.add(new Paragraph("Payment: " + reservation.getPayment()));
        document.add(new Paragraph("Montant avance: " + reservation.getMontant_avance()));
        document.add(new Paragraph("Frais total: " + (voiture != null ? voiture.getFrais() : 0)));
        document.add(new Paragraph("Reste a payer: " + reste));

        document.close();
    }
}
