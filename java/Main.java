import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            String inputPath = args.length > 0 ? args[0] : "data/sample.csv";
            String cleanedPath = "output/cleaned.csv";

            System.out.println("Importing data...");
            CsvImporter importer = new CsvImporter();
            List<Map<String, String>> data = importer.importCsv(inputPath);

            System.out.println("Cleaning data...");
            DataCleaner cleaner = new DataCleaner();
            List<Map<String, String>> cleanedData = cleaner.cleanData(data);

            System.out.println("Exporting cleaned data...");
            Exporter exporter = new Exporter();
            exporter.exportCsv(cleanedData, cleanedPath);

            System.out.println("Computing statistics...");
            List<Map<String, Object>> objectData = new ArrayList<>();
            for (Map<String, String> row : cleanedData) {
                objectData.add(new HashMap<>(row));
            }
            StatsCalculator statsCalc = new StatsCalculator();
            Map<String, Map<String, Double>> stats = statsCalc.computeStats(objectData);
            statsCalc.saveStatsToFile(stats, "output/stats.txt");

            System.out.println("Launching Python visualization...");
            ProcessBuilder pb = new ProcessBuilder("python", "python/visualizer.py", cleanedPath);
            pb.inheritIO();
            Process p = pb.start();
            p.waitFor();

            System.out.println("Data processing complete!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
