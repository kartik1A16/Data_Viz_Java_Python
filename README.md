# Data Processing and Visualization Project

# Data Processing and Visualization Project

## Project Overview
This project implements a data processing and visualization that combines Java's robust data processing capabilities with Python's powerful visualization libraries. It processes CSV data, performs cleaning operations, and generates comprehensive visualizations for both numeric and categorical data.

## Data Pipeline
1. Data Import (Java) → 2. Data Cleaning (Java) → 3. Export Cleaned Data (Java) → 4. Data Visualization (Python)

## Detailed Code Explanation

### Java Components

#### 1. CsvImporter.java
```java
public class CsvImporter {
    public List<Map<String, String>> importCsv(String filePath)
```
- CSV parsing with automatic delimiter detection (comma, tab, semicolon)
- Efficient file reading using `BufferedReader`
- Header validation and auto-generation if needed
- Features:
  - Automatic delimiter detection
  - Header validation
  - Missing value handling
  - Robust error handling for file operations
- Returns data as List of Maps for flexible processing

#### 2. DataCleaner.java
```java
public class DataCleaner {
    public List<Map<String, String>> cleanData(List<Map<String, String>> data)
```
- Data cleaning with type detection:
  - Identifies numeric vs categorical columns
  - Handles missing values differently for each type
  - Numeric columns: Replaces missing with "0"
  - Categorical columns: Replaces missing with "Unknown"
  - Removes special characters from numeric values (₹, $, commas)
  - Trims whitespace
  - Handles NaN and null values
- Preserves original data structure while cleaning

#### 3. Exporter.java
```java
public class Exporter {
    public void exportCsv(List<Map<String, String>> data, String outputPath)
```
- Efficient CSV export functionality:
  - Uses `BufferedWriter` for optimized file writing
  - Maintains consistent header order
  - Handles missing values with empty strings
  - Provides export status feedback
- Error handling:
  - Validates input data
  - Handles empty datasets
  - Reports export failures with messages
- Creates output directories if needed

#### 4. Main.java
```java
public class Main {
    public static void main(String[] args)
```
- Central orchestrator for the data processing pipeline:
  - Handles command-line arguments for input file
  - Default input path: "data/sample.csv"
  - Output path: "output/cleaned.csv"
- Process Flow:
  1. Data Import: Reads and parses CSV
  2. Data Cleaning: Applies cleaning rules
  3. Data Export: Saves cleaned data
  4. Visualization: Launches Python script
- Comprehensive error handling with stack traces
- Process synchronization with Python visualization

#### 5. StatsCalculator.java
```java
public class StatsCalculator {
    public Map<String, Map<String, Double>> computeStats(List<Map<String, Object>> data)
```
- Statistical analysis:
  - Automatic numeric column detection
  - Comprehensive statistics calculation
  - Handles missing/invalid values
- Statistics computed:
  - Mean, median, mode
  - Min, max values
  - Standard deviation
  - Variance
  - Count
- Output saved in organized format

### Python Components

#### visualizer.py
The Python component uses several powerful data science libraries to create comprehensive visualizations:

**Key Libraries:**
```python
import pandas as pd          # Data manipulation
import matplotlib.pyplot as plt  # Base plotting
import seaborn as sns       # Statistical visualizations
```

**Features:**
1. **Automatic Data Type Detection**
   - Identifies numeric and categorical columns
   - Applies appropriate visualizations based on data type
   
2. **Comprehensive Visualization Suite**
   - Distribution Analysis (histograms, box plots)
   - Relationship Analysis (scatter plots, correlation heatmap)
   - Categorical Analysis (bar charts, pie charts)
   - Trend Analysis (line plots)

**Dynamic Visualization Generation:**
- Automatically generates appropriate charts based on data types
- Handles multiple numeric columns for relationship analysis
- Creates visualizations with proper titles, labels, and formatting
- Implements consistent color schemes and styling

**Key Visualization Methods:**
```python
# Numeric Analysis
sns.histplot()      # Distribution with density curves
sns.boxplot()       # Statistical summaries
sns.heatmap()       # Correlation analysis

# Categorical Analysis
plt.bar()           # Frequency distributions
plt.pie()           # Proportional analysis

# Relationship Analysis
sns.scatterplot()   # Bivariate relationships
plt.plot()          # Trend visualization
```

This project demonstrates a hybrid approach using Java for data processing and Python for visualization, combining the strengths of both languages.

## Project Structure

```
├── data/
│   ├── sample.csv      # Input data
│   └── cleaned.csv     # Processed data
├── java/
│   ├── CsvImporter.java
│   ├── DataCleaner.java
│   ├── Exporter.java
│   ├── StatsCalculator.java
│   └── Main.java
├── output/
│   ├── stats.txt       # Statistical summary
│   └── charts/         # Generated visualizations
└── python/
    └── visualizer.py
```

## Technology Stack

### Java Components
- **Why Java?** 
  - Strong type safety for data processing
  - Robust error handling
  - Excellent performance for data operations
  - Object-oriented design for maintainable code

- **Key Classes:**
  - `CsvImporter`: Handles reading and parsing CSV data
  - `DataCleaner`: Processes and sanitizes data
  - `Exporter`: Saves processed data
  - `Main`: Orchestrates the workflow

### Python Components
- **Why Python?**
  - Rich ecosystem of data visualization libraries
  - Pandas for efficient data manipulation
  - Matplotlib and Seaborn for professional-grade visualizations

- **Libraries Used:**
  - [pandas](https://pandas.pydata.org/): Data manipulation and analysis
  - [matplotlib](https://matplotlib.org/): Basic plotting library
  - [seaborn](https://seaborn.pydata.org/): Statistical data visualization

## Generated Visualizations

The project generates various types of charts in the `output/charts/` directory:

### Distribution Charts
- Histograms: `Age_hist.png`, `Experience_hist.png`, `Salary_hist.png`
- Box Plots: `Age_box.png`, `Experience_box.png`, `Salary_box.png`
- Bar Charts: `Department_bar.png`, `Name_bar.png`
- Pie Chart: `Department_pie.png`

### Relationship Charts
- Correlation Heatmap: `correlation_heatmap.png`
- Scatter Plots:
  - `scatter_Experience_vs_Age.png`
  - `scatter_Salary_vs_Age.png`
  - `scatter_Salary_vs_Experience.png`
- Trend Analysis: `line_trend.png`

## Requirements

### Software Requirements
1. Java Development Kit (JDK 8 or higher)
2. Python 3.x
3. Git (optional, for version control)

### Python Dependencies
```bash
pip install pandas matplotlib seaborn plotly
```

### Input Data Requirements
The input CSV file should have:
- At least one numeric column (for statistical analysis)
- Header row with column names
- Clean data format (proper CSV formatting)
- No missing values (or they will be cleaned)

### How to Run

### Setup
1. Clone or download the repository:
   ```bash
   git clone https://github.com/kartik1A16/Data_Viz_Java_Python.git
   cd Data_Viz_Java_Python
   ```

2. Input Data Format:
   The project expects a CSV file with:
   ```csv
   Name,Department,Salary,Experience,Age
   Aarav,Data Science,75000,3,26
   Diya,AI Research,82000,4,29
   ...
   ```
   - Numeric columns: Salary, Experience, Age
   - Categorical columns: Name, Department

2. Place your input CSV file:
   - Save as `sample.csv` in the `data/` directory
   - Ensure proper CSV formatting
   - Include headers in the first row

### Execution
1. Compile Java files:
   ```bash
   cd java
   javac *.java
   ```

2. Run the project:
   ```bash
   java Main
   ```

3. Check the outputs:
   ```
   data/
   └── cleaned.csv     # Processed data
   output/
   ├── stats.txt       # Statistical summary
   └── charts/         # Generated visualizations
   ```

### Troubleshooting
- Ensure all Python dependencies are installed
- Check file permissions in output directories
- Verify CSV file format and contents
- Make sure Java and Python are in system PATH

## Output Files

### Data Files
- `data/cleaned.csv`: Processed and cleaned dataset
- `output/stats.txt`: Statistical summary of the dataset

### Visualization Categories
1. **Distribution Analysis**
   - Histograms and box plots for numeric variables (Age, Experience, Salary)
   - Bar and pie charts for categorical data (Department, Name)

2. **Relationship Analysis**
   - Correlation heatmap for numeric variables
   - Scatter plots showing relationships between Age, Salary, and Experience
   - Line trend visualization for temporal analysis

## Note
The project automatically creates necessary directories and handles the entire pipeline from data processing to visualization. Make sure you have proper read/write permissions in the project directory.