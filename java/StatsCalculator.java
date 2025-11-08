import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class StatsCalculator {

    // Detect numeric columns from the first row
    public Set<String> detectNumericColumns(List<Map<String, Object>> data) {
        if (data.isEmpty()) return new HashSet<>();
        Map<String, Object> firstRow = data.get(0);
        Set<String> numericCols = new HashSet<>();

        for (String key : firstRow.keySet()) {
            try {
                Double.parseDouble(firstRow.get(key).toString());
                numericCols.add(key);
            } catch (NumberFormatException e) {
                // ignore non-numeric
            }
        }
        return numericCols;
    }

    // Compute statistics for each numeric column
    public Map<String, Map<String, Double>> computeStats(List<Map<String, Object>> data) {
        Map<String, Map<String, Double>> stats = new LinkedHashMap<>();
        Set<String> numericCols = detectNumericColumns(data);

        for (String col : numericCols) {
            List<Double> values = data.stream()
                    .map(row -> {
                        try {
                            return Double.parseDouble(row.get(col).toString());
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (values.isEmpty()) continue;

            Collections.sort(values);
            double mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double median = (values.size() % 2 == 0)
                    ? (values.get(values.size()/2 - 1) + values.get(values.size()/2)) / 2.0
                    : values.get(values.size()/2);
            double min = values.get(0);
            double max = values.get(values.size() - 1);
            double variance = values.stream()
                    .mapToDouble(v -> Math.pow(v - mean, 2))
                    .average().orElse(0);
            double stdDev = Math.sqrt(variance);
            double mode = calculateMode(values);

            Map<String, Double> colStats = new LinkedHashMap<>();
            colStats.put("count", (double) values.size());
            colStats.put("mean", mean);
            colStats.put("median", median);
            colStats.put("mode", mode);
            colStats.put("min", min);
            colStats.put("max", max);
            colStats.put("std_dev", stdDev);

            stats.put(col, colStats);
        }

        return stats;
    }

    private double calculateMode(List<Double> values) {
        Map<Double, Integer> freq = new HashMap<>();
        for (Double v : values) {
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }
        return freq.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(Double.NaN);
    }

    // Save stats to file
    public void saveStatsToFile(Map<String, Map<String, Double>> stats, String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String col : stats.keySet()) {
                writer.write("Column: " + col + "\n");
                for (Map.Entry<String, Double> e : stats.get(col).entrySet()) {
                    writer.write(String.format("  %-10s : %.3f%n", e.getKey(), e.getValue()));
                }
                writer.write("\n");
            }
        }
    }
}
