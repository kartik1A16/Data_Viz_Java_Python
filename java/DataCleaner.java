
import java.util.*;

public class DataCleaner {
    public List<Map<String, Object>> cleanData(List<Map<String, Object>> data) {
        List<Map<String, Object>> cleaned = new ArrayList<>();

        for (Map<String, Object> row : data) {
            boolean valid = true;
            Map<String, Object> cleanedRow = new HashMap<>();
            for (String key : row.keySet()) {
                String value = row.get(key).toString().trim();
                if (value.isEmpty() || value.equalsIgnoreCase("NaN")) {
                    valid = false;
                    break;
                }
                cleanedRow.put(key, value);
            }
            if (valid) cleaned.add(cleanedRow);
        }
        return cleaned;
    }
}
