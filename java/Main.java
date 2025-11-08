
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputPath = "data/sample.csv";
        String cleanedPath = "data/cleaned.csv";

        CsvImporter importer = new CsvImporter();
        DataCleaner cleaner = new DataCleaner();
        Exporter exporter = new Exporter();

        System.out.println("Importing data...");
        List<Map<String, Object>> data = importer.importCsv(inputPath);

        System.out.println("Cleaning data...");
        List<Map<String, Object>> cleaned = cleaner.cleanData(data);

        System.out.println("Exporting cleaned data...");
        exporter.exportCsv(cleaned, cleanedPath);

        System.out.println("Running Python visualization...");
        ProcessBuilder pb = new ProcessBuilder("python", "python/visualizer.py", cleanedPath);
        pb.inheritIO();
        Process process = pb.start();
        process.waitFor();

        System.out.println("Visualization complete! Check 'output/charts/' folder.");
    }
}
