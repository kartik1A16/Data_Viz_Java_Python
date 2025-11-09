# Project Report: Data Processing and Visualization

This report provides a detailed explanation of the concepts, techniques, and implementations used in the project. Each file is analyzed from the most basic to the most advanced concepts.

---

## Java Components

### 1. CsvImporter.java
**Purpose:**
- To import data from a CSV file into a Java data structure.

**Basic Concepts:**
- **File Handling:** Uses `BufferedReader` to read files efficiently.
- **String Manipulation:** Splits lines using delimiters to parse CSV data.

**Intermediate Concepts:**
- **Dynamic Delimiter Detection:** Automatically detects delimiters (comma, tab, semicolon).
- **Header Validation:** Ensures the first row contains valid headers; generates default headers if necessary.

**Advanced Concepts:**
- **Error Handling:** Catches and handles `IOException` to ensure robust file operations.
- **Data Representation:** Stores rows as `Map<String, String>` for flexible data handling.

---

### 2. DataCleaner.java
**Purpose:**
- To clean and preprocess the imported data.

**Basic Concepts:**
- **Iteration:** Loops through rows and columns to process data.
- **String Manipulation:** Trims whitespace and removes invalid characters.

**Intermediate Concepts:**
- **Type Detection:** Differentiates between numeric and categorical columns.
- **Missing Value Handling:**
  - Replaces missing numeric values with `0`.
  - Replaces missing categorical values with `Unknown`.

**Advanced Concepts:**
- **Regex:** Cleans numeric values by removing special characters (e.g., `₹`, `$`, `,`).
- **Dynamic Cleaning:** Applies different cleaning rules based on column type.

---

### 3. Exporter.java
**Purpose:**
- To export cleaned data back to a CSV file.

**Basic Concepts:**
- **File Writing:** Uses `BufferedWriter` to write data efficiently.
- **String Joining:** Joins values with commas to create CSV rows.

**Intermediate Concepts:**
- **Header Management:** Ensures consistent header order during export.
- **Error Handling:** Handles empty datasets and file write errors gracefully.

**Advanced Concepts:**
- **Directory Management:** Creates output directories if they do not exist.
- **Feedback Mechanism:** Provides status messages for successful or failed exports.

---

### 4. StatsCalculator.java
**Purpose:**
- To compute statistical summaries for numeric columns.

**Basic Concepts:**
- **Iteration:** Loops through rows and columns to calculate statistics.
- **Math Operations:** Computes mean, median, min, max, etc.

**Intermediate Concepts:**
- **Sorting:** Sorts values to calculate median.
- **Variance and Standard Deviation:** Implements formulas for advanced statistical measures.

**Advanced Concepts:**
- **Mode Calculation:** Determines the most frequent value using a frequency map.
- **Dynamic Column Detection:** Automatically identifies numeric columns.
- **File Output:** Saves statistics to a formatted text file.

---

### 5. Main.java
**Purpose:**
- To orchestrate the entire data processing pipeline.

**Basic Concepts:**
- **Method Calls:** Invokes methods from other classes to perform tasks.
- **Command-Line Arguments:** Reads input file path from arguments.

**Intermediate Concepts:**
- **Process Flow:**
  1. Imports data using `CsvImporter`.
  2. Cleans data using `DataCleaner`.
  3. Exports data using `Exporter`.
  4. Computes statistics using `StatsCalculator`.
  5. Launches Python visualization script.

**Advanced Concepts:**
- **Process Synchronization:** Waits for the Python script to complete before proceeding.
- **Error Handling:** Catches and logs exceptions to ensure smooth execution.

---

## Python Components

### visualizer.py
**Purpose:**
- To generate visualizations from the cleaned data.

**Basic Concepts:**
- **File Handling:** Reads CSV files using `pandas`.
- **Data Manipulation:** Uses `pandas` to filter and process data.
- **Plotting:** Creates basic plots using `matplotlib` and `seaborn`.

**Intermediate Concepts:**
- **Dynamic Column Detection:** Automatically identifies numeric and categorical columns.
- **Visualization Types:**
  - Histograms for numeric distributions.
  - Box plots for statistical summaries.
  - Bar charts for categorical frequencies.
  - Scatter plots for relationships between numeric columns.
  - Line plots for trends.
  - Pie charts for categorical proportions.
  - Correlation heatmaps for numeric relationships.

**Advanced Concepts:**
- **Dynamic Plot Generation:** Automatically generates plots based on column types.
- **Styling:** Applies consistent color schemes and formatting.
- **Error Handling:** Validates input data and handles missing or invalid values.
- **Directory Management:** Creates output directories for charts.

---

## Summary
This project demonstrates a hybrid approach using Java for data processing and Python for visualization. It combines:
- Java's strong type safety, performance, and robust error handling.
- Python's rich ecosystem for data manipulation and visualization.
