package com.ione.util;

import com.ione.entity.Consignment;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class PdfGenerator {

    private static final String PDF_DIR = "generated_pdfs/";

    public static String generateConsignmentPdf(Consignment consignment) throws IOException {

        String filename = "consignment_" + consignment.getOrder().getId() + "_" + System.currentTimeMillis() + ".pdf";
        String fullPath = PDF_DIR + filename;
        new File(PDF_DIR).mkdirs(); // Ensures folder exists
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fullPath));
        document.open();
        Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
        document.add(new Paragraph("Consignment Report", titleFont));
        document.add(new Paragraph("Issued at: " + consignment.getIssuedAt().format(DateTimeFormatter.ISO_DATE_TIME), normalFont));
        document.add(new Paragraph("Delivered at: " + consignment.getDeliveredAt().format(DateTimeFormatter.ISO_DATE_TIME), normalFont));
        document.add(new Paragraph("Order ID: " + consignment.getOrder().getId(), normalFont));
        document.add(new Paragraph("Customer: " + consignment.getOrder().getCustomer().getCompanyName(), normalFont));
        document.add(new Paragraph("Driver: " + consignment.getOrder().getVehicle().getOwner().getFullName(), normalFont));
        document.add(new Paragraph("Total Cost: $" + consignment.getTotalCost(), normalFont));
        document.add(new Paragraph("Platform Fee: $" + consignment.getPlatformFee(), normalFont));
        document.add(new Paragraph("Driver Payment: $" + consignment.getDriverPayment(), normalFont));
        document.close();
        return fullPath;
    }
}
