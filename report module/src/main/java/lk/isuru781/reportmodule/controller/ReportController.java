package lk.isuru781.reportmodule.controller;

import lk.isuru781.reportmodule.dto.ReportRequest;
import lk.isuru781.reportmodule.service.ReportService;
import lk.isuru781.reportmodule.util.QueryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * Generates a PDF report from a custom SQL query.
     * Only SELECT statements are allowed.
     *
     * @param reportRequest the request containing the SQL query
     * @return ResponseEntity with PDF file as byte array
     */
    @PostMapping("/download")
    public ResponseEntity<?> generateReport(@RequestBody ReportRequest reportRequest) {
        try {
            // Validate query
            String query = reportRequest.getQuery();

            if (query == null || query.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Query cannot be empty.");
            }

            if (!QueryValidator.isValidSelectQuery(query)) {
                return ResponseEntity.badRequest()
                        .body("Invalid query. Only SELECT statements are allowed. " +
                                "Dangerous keywords (DROP, DELETE, UPDATE, TRUNCATE, INSERT) are not permitted.");
            }

            // Generate PDF
            byte[] pdfBytes = reportService.generatePdfReport(query);

            // Return PDF as response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");
            headers.setContentLength(pdfBytes.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error generating report: " + e.getMessage());
        }
    }

    /**
     * Health check endpoint to verify the API is running.
     *
     * @return success message
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Report Module API is running!");
    }
}

