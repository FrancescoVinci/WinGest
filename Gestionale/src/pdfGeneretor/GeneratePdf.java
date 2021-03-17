package pdfGeneretor;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gestioneRiepilogo.ControllerR;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import model.*;

import gestioneRiepilogo.ControllerR;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class GeneratePdf {
    private Model model = new Model();

    private List<RiepilogoCostiRef> lc;
    private List<RiepilogoRicaviRef> lr;

    public GeneratePdf(List<RiepilogoCostiRef> lc, List<RiepilogoRicaviRef> lr){
        this.lc=lc;
        this.lr=lr;
    }

    public void createPdf(){

        FileChooser dialog = new FileChooser();
        File path = dialog.showSaveDialog(null);
        if (path != null){

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(path));
                document.open();

                createTableHeader(document);

                document.close();
            }
            catch(DocumentException | IOException de) {
                de.printStackTrace();
            }
        }
    }

    public void createTableHeader(Document document){
        try {

            Paragraph paragraph = new Paragraph("Riepilogo: " + lc.get(0).getParrocchiaid());

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Costi"));
            cell1.setBorderColor(BaseColor.BLACK);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.YELLOW);


            PdfPCell cell2 = new PdfPCell(new Paragraph("Cassa"));
            cell2.setBorderColor(BaseColor.BLACK);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.YELLOW);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Banca"));
            cell3.setBorderColor(BaseColor.BLACK);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.YELLOW);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Ricavi"));
            cell4.setBorderColor(BaseColor.BLACK);
            cell4.setPaddingLeft(10);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell4.setBackgroundColor(BaseColor.YELLOW);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Cassa"));
            cell5.setBorderColor(BaseColor.BLACK);
            cell5.setPaddingLeft(10);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBackgroundColor(BaseColor.YELLOW);

            PdfPCell cell6 = new PdfPCell(new Paragraph("Banca"));
            cell6.setBorderColor(BaseColor.BLACK);
            cell6.setPaddingLeft(10);
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBackgroundColor(BaseColor.YELLOW);


            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);

            addCostiRicavi(table);

            document.add(paragraph);

            document.add(table);
        }catch (DocumentException ex){
            ex.printStackTrace();
        }
    }

    public void addCostiRicavi(PdfPTable table){
        try {
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);
            int lcSize = lc.size()-1;
            int lrSize = lr.size()-1;
            int size = Math.max(lcSize, lrSize);
            int index = 0;

            while(index <= size){
                if(lcSize >= 0 && lrSize >=0){

                    PdfPCell cell1 = new PdfPCell(new Paragraph(lc.get(index).getNome()));
                    cell1.setBorderColor(BaseColor.BLACK);
                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell1.setBackgroundColor(BaseColor.CYAN);

                    PdfPCell cell2 = new PdfPCell(new Paragraph(lc.get(index).getValorebanca()));
                    cell2.setBorderColor(BaseColor.BLACK);
                    cell2.setPaddingLeft(10);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell2.setBackgroundColor(BaseColor.RED);

                    PdfPCell cell3 = new PdfPCell(new Paragraph(lc.get(index).getValorecassa()));
                    cell3.setBorderColor(BaseColor.BLACK);
                    cell3.setPaddingLeft(10);
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell3.setBackgroundColor(BaseColor.RED);

                    PdfPCell cell4 = new PdfPCell(new Paragraph(lr.get(index).getNome()));
                    cell4.setBorderColor(BaseColor.BLACK);
                    cell4.setPaddingLeft(10);
                    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell4.setBackgroundColor(BaseColor.CYAN);

                    PdfPCell cell5 = new PdfPCell(new Paragraph(lr.get(index).getValorebanca()));
                    cell5.setBorderColor(BaseColor.BLACK);
                    cell5.setPaddingLeft(10);
                    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell5.setBackgroundColor(BaseColor.GREEN);

                    PdfPCell cell6 = new PdfPCell(new Paragraph(lr.get(index).getValorecassa()));
                    cell6.setBorderColor(BaseColor.BLACK);
                    cell6.setPaddingLeft(10);
                    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell6.setBackgroundColor(BaseColor.GREEN);

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);
                    table.addCell(cell5);
                    table.addCell(cell6);

                    lcSize--;
                    lrSize--;

                    index++;


                }else if(lcSize >= 0 && lrSize < 0){
                    PdfPCell cell1 = new PdfPCell(new Paragraph(lc.get(index).getNome()));
                    cell1.setBorderColor(BaseColor.BLACK);
                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell1.setBackgroundColor(BaseColor.CYAN);

                    PdfPCell cell2 = new PdfPCell(new Paragraph(lc.get(index).getValorebanca()));
                    cell2.setBorderColor(BaseColor.BLACK);
                    cell2.setPaddingLeft(10);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell2.setBackgroundColor(BaseColor.RED);

                    PdfPCell cell3 = new PdfPCell(new Paragraph(lc.get(index).getValorecassa()));
                    cell3.setBorderColor(BaseColor.BLACK);
                    cell3.setPaddingLeft(10);
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell3.setBackgroundColor(BaseColor.RED);


                    PdfPCell cell4 = new PdfPCell(new Paragraph("-"));
                    cell4.setBorderColor(BaseColor.BLACK);
                    cell4.setPaddingLeft(10);
                    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell5 = new PdfPCell(new Paragraph("-"));
                    cell5.setBorderColor(BaseColor.BLACK);
                    cell5.setPaddingLeft(10);
                    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell6 = new PdfPCell(new Paragraph("-"));
                    cell6.setBorderColor(BaseColor.BLACK);
                    cell6.setPaddingLeft(10);
                    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);
                    table.addCell(cell5);
                    table.addCell(cell6);

                    lcSize--;

                    index++;

                }else if(lcSize < 0 && lrSize >= 0){

                    PdfPCell cell1 = new PdfPCell(new Paragraph("-"));
                    cell1.setBorderColor(BaseColor.BLACK);
                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell2 = new PdfPCell(new Paragraph("-"));
                    cell2.setBorderColor(BaseColor.BLACK);
                    cell2.setPaddingLeft(10);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell3 = new PdfPCell(new Paragraph("-"));
                    cell3.setBorderColor(BaseColor.BLACK);
                    cell3.setPaddingLeft(10);
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell4 = new PdfPCell(new Paragraph(lr.get(index).getNome()));
                    cell4.setBorderColor(BaseColor.BLACK);
                    cell4.setPaddingLeft(10);
                    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell4.setBackgroundColor(BaseColor.CYAN);

                    PdfPCell cell5 = new PdfPCell(new Paragraph(lr.get(index).getValorebanca()));
                    cell5.setBorderColor(BaseColor.BLACK);
                    cell5.setPaddingLeft(10);
                    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell5.setBackgroundColor(BaseColor.GREEN);

                    PdfPCell cell6 = new PdfPCell(new Paragraph(lr.get(index).getValorecassa()));
                    cell6.setBorderColor(BaseColor.BLACK);
                    cell6.setPaddingLeft(10);
                    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell6.setBackgroundColor(BaseColor.GREEN);

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);
                    table.addCell(cell5);
                    table.addCell(cell6);

                    lrSize--;

                    index++;
                }
            }

            PdfPCell cell1 = new PdfPCell(new Paragraph("Totale"));
            cell1.setBorderColor(BaseColor.BLACK);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.YELLOW);

            PdfPCell cell2 = new PdfPCell(new Paragraph(totaleCosti(lc.get(0).getParrocchiaid()).getKey()));
            cell2.setBorderColor(BaseColor.BLACK);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.RED);

            PdfPCell cell3 = new PdfPCell(new Paragraph(totaleCosti(lc.get(0).getParrocchiaid()).getValue()));
            cell3.setBorderColor(BaseColor.BLACK);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.RED);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Totale"));
            cell4.setBorderColor(BaseColor.BLACK);
            cell4.setPaddingLeft(10);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell4.setBackgroundColor(BaseColor.YELLOW);

            PdfPCell cell5 = new PdfPCell(new Paragraph(totaleRicavi(lc.get(0).getParrocchiaid()).getKey()));
            cell5.setBorderColor(BaseColor.BLACK);
            cell5.setPaddingLeft(10);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBackgroundColor(BaseColor.GREEN);

            PdfPCell cell6 = new PdfPCell(new Paragraph(totaleRicavi(lc.get(0).getParrocchiaid()).getValue()));
            cell6.setBorderColor(BaseColor.BLACK);
            cell6.setPaddingLeft(10);
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBackgroundColor(BaseColor.GREEN);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);

        }catch (DocumentException ex){
            ex.printStackTrace();
        }
    }

    public Pair<String,String> totaleCosti(String parrocchiaid){
        List<RiepilogoCosti> listCosti = model.getRiepilogoCostiPerParrocchie(parrocchiaid);

        double totBanca = 0;
        double totCassa = 0;
        for(RiepilogoCosti x : listCosti){
            totBanca += x.getValoreBanca();
            totCassa += x.getValoreCassa();
        }

        String pattern = "€ ###,###.###";
        DecimalFormat formatter = new DecimalFormat(pattern);
        String strBanca = formatter.format(totBanca);
        String strCassa = formatter.format(totCassa);
        return new Pair<>(strBanca,strCassa);
    }

    public Pair<String,String> totaleRicavi(String parrocchiaid){
        List<RiepilogoRicavi> listRicavi = model.getRiepilogoRicaviPerParrocchie(parrocchiaid);

        double totBanca = 0;
        double totCassa = 0;
        for(RiepilogoRicavi x : listRicavi){
            totBanca += x.getValoreBanca();
            totCassa += x.getValoreCassa();
        }

        String pattern = "€ ###,###.###";
        DecimalFormat formatter = new DecimalFormat(pattern);
        String strBanca = formatter.format(totBanca);
        String strCassa = formatter.format(totCassa);
        return new Pair<>(strBanca,strCassa);

    }
}
