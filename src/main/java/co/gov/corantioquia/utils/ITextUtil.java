package co.gov.corantioquia.utils;

import com.aspose.cells.PdfCompliance;
import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.Workbook;
import com.aspose.words.PdfImageCompression;
import com.aspose.words.PdfTextCompression;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.lowagie.text.BadElementException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


import java.io.*;

public class ITextUtil {
    public  static byte[] generatePDFFromImage(byte[] imageBytes, String filename) throws BadElementException, IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document document = new Document(pdfDoc);

        ImageData imageData = ImageDataFactory.create(imageBytes);
        document.add(new Image(imageData));
        document.close();
        byte[] b;

        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        baos.writeTo(os);

        b = baos.toByteArray();
        os.flush();
        os.close();

        return b;
    }


    public  static byte[] generatePDFFromExcel(byte[] docBytes, String filename) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document document = new Document(pdfDoc);

        InputStream is = new ByteArrayInputStream(docBytes);
        Workbook book = new Workbook(is);
        PdfSaveOptions options = new PdfSaveOptions();
        options.setCompliance(PdfCompliance.PDF_A_1_A);
        book.save(baos,options);
        /*
        XWPFDocument doc = new XWPFDocument(docBytes);// docx
        PdfOptions options = PdfOptions.create();
        PdfConverter.getInstance().convert(doc, new FileOutputStream("template.pdf"), options);// pdf
        */


        byte[] b;

        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        baos.writeTo(os);

        b = baos.toByteArray();
        os.flush();
        os.close();

        return b;
    }

    public  static byte[] generatePDFFromDoc(byte[] docBytes, String filename) throws BadElementException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document document = new Document(pdfDoc);

        InputStream is = new ByteArrayInputStream(docBytes);
        //XSSFWorkbook doc = new XSSFWorkbook(is);
        //is = new FileInputStream("C:\\Users\\daniela bonilla\\Desktop\\Documentos\\Acta de inicio Daniela Bonilla_signed.docx");
        XWPFDocument doc = new XWPFDocument(is);


        PdfConverter.getInstance().convert(doc,baos,null);

        byte[] b;

        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        baos.writeTo(os);

        b = baos.toByteArray();
        os.flush();
        os.close();

        return b;
    }



    public  static byte[] generatePDFFromDocAspose(byte[] docBytes, String filename) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        InputStream is = new ByteArrayInputStream(docBytes);
        com.aspose.words.Document doc = new com.aspose.words.Document(is);
        com.aspose.words.PdfSaveOptions options = new com.aspose.words.PdfSaveOptions();
        options.setTextCompression(PdfTextCompression.FLATE);
        options.setImageCompression(PdfImageCompression.AUTO);
        doc.save(baos, options);

        byte[] b;

        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        baos.writeTo(os);

        b = baos.toByteArray();
        os.flush();
        os.close();

        return b;
    }
    }
