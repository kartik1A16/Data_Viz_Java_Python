
import java.io.*;
import java.util.*;

public class Exporter {
    public void exportCsv(List<Map<String, Object>> data, String outputPath) throws IOException {
        if (data.isEmpty()) return;
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
        Set<String> headers = data.get(0).keySet();
        writer.write(String.join(",", headers));
        writer.newLine();
        for (Map<String, Object> row : data) {
            List<String> values = new ArrayList<>();
            for (String h : headers) {
                values.add(row.get(h).toString());
            }
            writer.write(String.join(",", values));
            writer.newLine();
        }
        writer.close();
    }
}
