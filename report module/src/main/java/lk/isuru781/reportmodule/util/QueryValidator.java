package lk.isuru781.reportmodule.util;

public class QueryValidator {

    private static final String[] DANGEROUS_KEYWORDS = {
            "DROP", "DELETE", "UPDATE", "TRUNCATE", "INSERT",
            "ALTER", "EXEC", "EXECUTE", "SCRIPT", "UNION", "--", "/*", "*/"
    };

    /**
     * Validates the SQL query to ensure it's a SELECT statement only.
     * Blocks dangerous keywords to prevent SQL injection.
     *
     * @param query the SQL query to validate
     * @return true if the query is safe, false otherwise
     */
    public static boolean isValidSelectQuery(String query) {
        if (query == null || query.trim().isEmpty()) {
            return false;
        }

        String trimmedQuery = query.trim().toUpperCase();

        // Must start with SELECT
        if (!trimmedQuery.startsWith("SELECT")) {
            return false;
        }

        // Check for dangerous keywords
        for (String keyword : DANGEROUS_KEYWORDS) {
            if (trimmedQuery.contains(keyword)) {
                return false;
            }
        }

        return true;
    }
}

