

import java.io.*;
import java.util.*;

public class CsvImporter {
    public List<Map<String, Object>> importCsv(String path) throws IOException {
        List<Map<String, Object>> data = new ArrayList<>();
        BufferedReader bw = new BufferedReader(new FileReader(path));
        String[] headers = bw.readLine().split(",");
        String line;
        while ((line = bw.readLine()) != null) {
            String[] values = line.split(",");
            Map<String, Object> row = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                row.put(headers[i], values[i]);
            }
            data.add(row);
        }
        bw.close();
        return data;
    }
}
