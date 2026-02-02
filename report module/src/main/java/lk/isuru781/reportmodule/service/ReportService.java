package lk.isuru781.reportmodule.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Generates a PDF report from the provided SQL query result.
     *
     * @param query the SQL query to execute
     * @return byte array containing the PDF file
     * @throws Exception if query execution fails
     */
    public byte[] generatePdfReport(String query) throws Exception {
        // Execute the query and get results
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);

        if (results.isEmpty()) {
            return generateEmptyPdf("No data found for the query.");
        }

        // Create PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Add title
        Paragraph title = new Paragraph("Dynamic Report")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18);
        document.add(title);

        // Get column names from the first result
        Map<String, Object> firstRow = results.get(0);
        Set<String> columnNames = firstRow.keySet();
        int columnCount = columnNames.size();

        // Create table with dynamic column count
        Table table = new Table(columnCount);
        table.setWidth(UnitValue.createPercentValue(100));

        // Add header row
        for (String columnName : columnNames) {
            Cell headerCell = new Cell()
                    .add(new Paragraph(columnName))
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addHeaderCell(headerCell);
        }

        // Add data rows
        for (Map<String, Object> row : results) {
            for (String columnName : columnNames) {
                Object cellValue = row.get(columnName);
                String cellContent = cellValue != null ? cellValue.toString() : "N/A";

                Cell dataCell = new Cell()
                        .add(new Paragraph(cellContent))
                        .setTextAlignment(TextAlignment.LEFT)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE);
                table.addCell(dataCell);
            }
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

    /**
     * Generates an empty PDF with a message.
     *
     * @param message the message to display
     * @return byte array containing the PDF file
     * @throws Exception if PDF creation fails
     */
    private byte[] generateEmptyPdf(String message) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        Paragraph para = new Paragraph(message)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(14);
        document.add(para);
        document.close();

        return baos.toByteArray();
    }
}

