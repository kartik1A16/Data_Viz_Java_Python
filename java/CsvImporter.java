import java.io.*;
import java.util.*;

public class CsvImporter {

    // Detect delimiter automatically
    private String detectDelimiter(String line) {
        if (line.contains("\t")) return "\t";
        if (line.contains(";")) return ";";
        return ","; // default to comma
    }

    public List<Map<String, String>> importCsv(String filePath) {
        List<Map<String, String>> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String firstLine = reader.readLine();
            if (firstLine == null) throw new IOException("Empty CSV file");

            String delimiter = detectDelimiter(firstLine);
            String[] headers = firstLine.split(delimiter);

            // If header line seems to contain numbers, auto-generate names
            boolean headerIsValid = Arrays.stream(headers).anyMatch(h -> h.matches("[a-zA-Z]+"));
            if (!headerIsValid) {
                headers = new String[headers.length];
                for (int i = 0; i < headers.length; i++) {
                    headers[i] = "Column" + (i + 1);
                }
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(delimiter);
                Map<String, String> row = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    String key = headers[i];
                    String value = (i < values.length) ? values[i].trim() : "";
                    row.put(key, value);
                }
                data.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return data;
    }
}
