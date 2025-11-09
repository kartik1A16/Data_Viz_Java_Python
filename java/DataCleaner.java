import java.util.*;

public class DataCleaner {

    public List<Map<String, String>> cleanData(List<Map<String, String>> data) {
        if (data.isEmpty()) return data;

        List<String> headers = new ArrayList<>(data.get(0).keySet());
        List<Map<String, String>> cleaned = new ArrayList<>();

        for (Map<String, String> row : data) {
            Map<String, String> cleanRow = new HashMap<>();

            for (String key : headers) {
                String value = row.getOrDefault(key, "").trim();

                // Handle missing values
                if (value.isEmpty() || value.equalsIgnoreCase("NaN") || value.equalsIgnoreCase("null")) {
                    if (isNumericColumn(data, key)) {
                        value = "0"; // replace numeric missing with 0
                    } else {
                        value = "Unknown"; // replace categorical missing with "Unknown"
                    }
                }

                // Clean numeric values (remove commas, ₹, $, etc.)
                if (isNumericColumn(data, key)) {
                    value = value.replaceAll("[^0-9.-]", ""); 
                    if (value.isEmpty()) value = "0";
                }

                cleanRow.put(key, value);
            }
            cleaned.add(cleanRow);
        }

        return cleaned;
    }

    private boolean isNumericColumn(List<Map<String, String>> data, String key) {
        for (Map<String, String> row : data) {
            String val = row.get(key);
            if (val == null) continue;
            if (val.matches("-?\\d+(\\.\\d+)?")) return true;
        }
        return false;
    }
}
