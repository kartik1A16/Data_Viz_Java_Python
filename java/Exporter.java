import java.io.*;
import java.util.*;

public class Exporter {

    public void exportCsv(List<Map<String, String>> data, String outputPath) {
        if (data.isEmpty()) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            List<String> headers = new ArrayList<>(data.get(0).keySet());
            writer.write(String.join(",", headers));
            writer.newLine();

            for (Map<String, String> row : data) {
                List<String> values = new ArrayList<>();
                for (String header : headers) {
                    values.add(row.getOrDefault(header, ""));
                }
                writer.write(String.join(",", values));
                writer.newLine();
            }

            System.out.println("Cleaned data exported to: " + outputPath);
        } catch (IOException e) {
            System.err.println("Export failed: " + e.getMessage());
        }
    }
}
