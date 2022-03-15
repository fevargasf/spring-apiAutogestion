package co.gov.corantioquia.utils;

import co.gov.corantioquia.models.dto.ConsultaObligaArchivosDTO;
import co.gov.corantioquia.models.dto.DataPdf;
import co.gov.corantioquia.models.dto.RadicarPdfDTO;
import com.google.common.io.Files;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.element.Paragraph;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterContext;
import net.sf.jasperreports.export.*;
import org.apache.poi.util.IOUtils;
import org.springframework.util.ResourceUtils;
import java.io.*;
import java.sql.SQLException;
import java.util.*;


public class CoMerge{

    public static byte[] merge(RadicarPdfDTO radicarPdfDTO) {
        List<InputStream> listaArchivos = new ArrayList<>();
        try {
            listaArchivos = validarExtension(radicarPdfDTO.getArchivos());
        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        byte[] pdf = new byte[4];
        pdf = construirPortada(radicarPdfDTO);

        byte[] files = new byte[4];
        if(listaArchivos.size()>0) {
            try {
                files = mezclarArchivos(listaArchivos);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }


            try {
                return mezclarArchivosByte(pdf, files);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

                return new byte[4];
            }
        }else{
            return pdf;
        }
    }
    public static byte[] merge(List<ConsultaObligaArchivosDTO> archivos) {

        // List<Blob> listaArchivos = validarExtension(archivos);
        List<InputStream> listaArchivos = new ArrayList<>();
        try {
            listaArchivos = validarExtension(archivos);
        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        byte[] files = new byte[4];
        if(listaArchivos.size()>0){
            try {
                files = mezclarArchivos(listaArchivos);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

        return files;
    }
    public static List<InputStream> validarExtension(List<ConsultaObligaArchivosDTO> archivos) throws Exception {
        List<InputStream> listaArchivos = new ArrayList<>();
        boolean api = false;
        byte[] fileBytes ;

        List<List<String>> listaNombresArchivos = llenarListaNombreArchivos(archivos);

        int obligaLinea = 0;
        int index = 0;
        for (ConsultaObligaArchivosDTO dto : archivos ) {
            //***********
            if(obligaLinea != dto.getObligaLinea() ){
                listaArchivos.add( new ByteArrayInputStream(construirPortadaObligacion(dto, listaNombresArchivos.get(index))));
                obligaLinea = dto.getObligaLinea();
                index++;
            }
            //***********
            // Validar que solo guarde tipo PDF. Validar extension
            if (dto.getArchivo() != null && dto.getArchivoNombre() !=null) {
               // String[] ext = dto.getArchivoNombre().split("\\.");
                String extension = Files.getFileExtension(dto.getArchivoNombre()).toLowerCase();
                switch (extension) {
                    case "pdf":
                        listaArchivos.add(dto.getArchivo().getBinaryStream());
                        break;

                    case "png":
                    case "jpg":
                        fileBytes = ITextUtil.generatePDFFromImage(IOUtils.toByteArray(dto.getArchivo().getBinaryStream()), dto.getArchivoNombre());
                        listaArchivos.add( new ByteArrayInputStream(fileBytes));
                        break;

                    case "doc":
                    case "docx":
                        fileBytes = ITextUtil.generatePDFFromDocAspose(IOUtils.toByteArray(dto.getArchivo().getBinaryStream()), dto.getArchivoNombre());
                        listaArchivos.add( new ByteArrayInputStream(fileBytes));
                        break;

                    case "xlsx":
                    case "xlsm":
                        fileBytes = ITextUtil.generatePDFFromExcel(IOUtils.toByteArray(dto.getArchivo().getBinaryStream()), dto.getArchivoNombre());
                        listaArchivos.add( new ByteArrayInputStream(fileBytes));
                        break;

                    default:

                        break;
                }
            }
        }
        return listaArchivos;
    }

    public static byte[] construirPortada(RadicarPdfDTO radicarPdfDTO)  {

        DataPdf dataPdf = new DataPdf();
        dataPdf.setExpediente(radicarPdfDTO.getExpedientDTO().getExpediente());
        dataPdf.setRadicado(radicarPdfDTO.getRadicado());
        dataPdf.setObligaFechaCreacion(radicarPdfDTO.getArchivos().get(0).getObligaFechaCreacion());
        dataPdf.setFechaRadicado(radicarPdfDTO.getFechaRadicado());
        dataPdf.setRespon_nombre(radicarPdfDTO.getExpedientDTO().getRespon_nombre());
        dataPdf.setRespon_correo(radicarPdfDTO.getExpedientDTO().getRespon_correo());
        dataPdf.setTer_celular(radicarPdfDTO.getExpedientDTO().getTer_celular());
        dataPdf.setRespon_telefono_celu(radicarPdfDTO.getExpedientDTO().getRespon_telefono_celu());
        dataPdf.setRespon_cargo(radicarPdfDTO.getExpedientDTO().getRespon_cargo());
        dataPdf.setTer_dir_municipio(radicarPdfDTO.getExpedientDTO().getTer_dir_municipio());
        dataPdf.setExpediente(radicarPdfDTO.getExpedientDTO().getExpediente());
        dataPdf.setExpediente(radicarPdfDTO.getArchivos().get(0).getExpediente());
        dataPdf.setAnexos(radicarPdfDTO.getArchivos().size());
        dataPdf.setTer_nombres(radicarPdfDTO.getExpedientDTO().getTer_nombres());
        dataPdf.setTer_celular(radicarPdfDTO.getExpedientDTO().getTer_celular());
        dataPdf.setTer_email(radicarPdfDTO.getExpedientDTO().getTer_email());
        ByteArrayOutputStream bytesArray = new ByteArrayOutputStream();
        JRAbstractExporter<PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput, JRPdfExporterContext> exporter = new JRPdfExporter();
        PdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();

        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:pdf.jrxml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JasperReport jasperReport = null;
        try {
            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        } catch (JRException e) {
            e.printStackTrace();
        }
        JRDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(dataPdf));

        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(radicarPdfDTO.getArchivos());
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("evidencias", itemsJRBean);

        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
        List<JasperPrint> jasperPrintList = new ArrayList<>(1);
        jasperPrintList.add(jasperPrint);

        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bytesArray));
        exporter.setConfiguration(reportConfig);
        try {
            exporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }

        return bytesArray.toByteArray();

    }

    public static byte[] construirPortadaObligacion(ConsultaObligaArchivosDTO obligaDto, List<String> nombreArchivosObligacion)  {

        ByteArrayOutputStream bytesArray = new ByteArrayOutputStream();
        JRAbstractExporter<PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput, JRPdfExporterContext> exporter = new JRPdfExporter();
        PdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();

        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:portada2.jrxml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JasperReport jasperReport = null;
        try {
            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        } catch (JRException e) {
            e.printStackTrace();
        }
        JRDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(obligaDto));

        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(Collections.singletonList(nombreArchivosObligacion));
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("evidencias", itemsJRBean);


        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
        List<JasperPrint> jasperPrintList = new ArrayList<>(1);
        jasperPrintList.add(jasperPrint);

        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bytesArray));
        exporter.setConfiguration(reportConfig);
        try {
            exporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }

        return bytesArray.toByteArray();
       }

    private static List<List<String>> llenarListaNombreArchivos  (List<ConsultaObligaArchivosDTO> archivos){
        List<List<String>> lista = new ArrayList<>();
        List<String> nombresPorObligacion = new ArrayList<>();
        int obligaLinea = 0;

        for (ConsultaObligaArchivosDTO dto : archivos){
            if(dto.getObligaLinea() != obligaLinea){
                nombresPorObligacion = new ArrayList<>();
                obligaLinea = dto.getObligaLinea();
                lista.add(nombresPorObligacion);
            }
            nombresPorObligacion.add(dto.getArchivoNombre());
        }

        return lista;
    }

    private static Paragraph paragraph(String text){
        if(text==null){
            text = "";
        }
        return new Paragraph(text);
    }

    public static byte[] mezclarArchivos(List<InputStream> listaArchivos) throws SQLException, IOException {
        ByteArrayOutputStream destino = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(destino));
        PdfMerger merger = new PdfMerger(pdf);

        PdfDocument blobPdf;
        for (InputStream blobStream : listaArchivos) {
            // crear pdfDocument
            blobPdf = new PdfDocument(new PdfReader(blobStream));
            // mezclar
            try {
                merger.merge(blobPdf, 1, blobPdf.getNumberOfPages());
            }catch (Exception e){
                //validar pdf con clave
            }


            blobPdf.close();
            blobStream.close();
        }

        pdf.close();
        // destino.close();

        return destino.toByteArray();
    }

    public static byte[] mezclarArchivosByte(byte[] archivo1, byte[] archivo2) throws IOException {
        ByteArrayOutputStream destino = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(destino));
        PdfMerger merger = new PdfMerger(pdf);

        InputStream isArchivo1 = new ByteArrayInputStream(archivo1);
        PdfDocument blobPdf = new PdfDocument(new PdfReader(isArchivo1));
        merger.merge(blobPdf, 1, blobPdf.getNumberOfPages());

        InputStream isArchivo2 = new ByteArrayInputStream(archivo2);
        PdfDocument blobPdf2 = new PdfDocument(new PdfReader(isArchivo2));
        merger.merge(blobPdf2, 1, blobPdf2.getNumberOfPages());

        blobPdf2.close();
        blobPdf.close();
        pdf.close();

        return destino.toByteArray();
    }


}

