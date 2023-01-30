package com.example.moneycoachconsumer.util;

import com.example.moneycoachconsumer.model.Person;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Slf4j
public class PdfGenerator {

    private List<Person> personList;

    public  PdfGenerator (List<Person> listPersona){
        this.personList = listPersona;
    }

    private void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Lastname", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Username", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for (Person persona : personList){
            table.addCell(String.valueOf(persona.getId()));
            table.addCell(persona.getName());
            table.addCell(persona.getLastname());
            table.addCell(persona.getUsername());
        }
    }

    public  void export (HttpServletResponse response) throws DocumentException, IOException{

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Lista de Personas", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
        log.info("Report created");
    }
}
