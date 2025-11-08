# Data Processing and Visualization Project

## Detailed Code Explanation

### Java Components

#### 1. CsvImporter.java
```java
public class CsvImporter {
    public List<Map<String, Object>> importCsv(String path)
```
- Uses `BufferedReader` for efficient file reading
- First line is treated as headers using `split(",")`
- Each row is stored as a `Map<String, Object>` where:
  - Keys are column headers
  - Values are the corresponding data
- Returns a `List` of row maps for flexible data handling

#### 2. DataCleaner.java
```java
public class DataCleaner {
    public List<Map<String, Object>> cleanData(List<Map<String, Object>>)
```
- Implements data validation and cleaning:
  - Removes empty values
  - Trims whitespace
  - Filters out "NaN" values
  - Validates row completeness
- Creates new clean dataset preserving original data
- Uses `HashMap` for efficient key-value operations

#### 3. Exporter.java
```java
public class Exporter {
    public void exportCsv(List<Map<String, Object>> data, String outputPath)
```
- Writes processed data back to CSV format
- Maintains consistent header order
- Uses `BufferedWriter` for efficient file writing
- Handles empty datasets gracefully
- Joins values with comma delimiter

#### 4. Main.java
```java
public class Main {
    public static void main(String[] args)
```
- Orchestrates the entire data processing pipeline
- Creates instances of processor classes
- Manages file paths and data flow
- Initiates Python visualization process
- Implements process synchronization

### Python Components

#### visualizer.py
```python
# Key imports
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
```

**Data Loading and Processing:**
```python
df = pd.read_csv(input_path)
desc = df.describe(include='all')
```
- Uses pandas for efficient data handling
- Generates comprehensive statistical summary

**Visualization Components:**
1. Line Plot
```python
sns.lineplot(data=df.select_dtypes('number'))
```
- Visualizes trends in numeric data
- Automatically handles multiple columns

2. Scatter Matrix
```python
sns.pairplot(df.select_dtypes('number'))
```
- Shows correlations between numeric variables
- Creates comprehensive variable relationships

3. Bar Charts
```python
for col in df.select_dtypes('object').columns:
    df[col].value_counts().plot(kind='bar')
```
- Visualizes categorical data distributions
- Dynamic chart generation based on data types

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
- Line plots of numeric data
- Scatter matrix for correlation analysis
- Bar charts for categorical data distributions

## Requirements

1. Java (JDK 8 or higher)
2. Python 3.x
3. Python packages:
   ```bash
   pip install pandas matplotlib seaborn
   ```

## How to Run

1. Place your input CSV file in the `data/` directory as `sample.csv`

2. Compile Java files:
   ```bash
   cd java
   javac *.java
   ```

3. Run the project:
   ```bash
   java Main
   ```

4. Check the output:
   - Processed data: `data/cleaned.csv`
   - Statistics: `output/stats.txt`
   - Visualizations: `output/charts/` directory

## Output Description

- `Department_bar.png`: Distribution of departments
- `line_plot.png`: Trends in numeric data
- `Name_bar.png`: Distribution of names
- `scatter_matrix.png`: Correlation matrix of numeric variables
- `stats.txt`: Statistical summary of the dataset

## Note
The project automatically creates necessary directories and handles the entire pipeline from data processing to visualization. Make sure you have proper read/write permissions in the project directory.