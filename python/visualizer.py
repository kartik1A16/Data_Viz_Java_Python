import sys
import os
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import plotly.express as px

# --- Setup ---
if len(sys.argv) < 2:
    print("Usage: python visualizer.py <csv_path>")
    sys.exit(1)

csv_path = sys.argv[1]
output_dir = "output/charts"
os.makedirs(output_dir, exist_ok=True)

print(f"Reading cleaned data from: {csv_path}")
df = pd.read_csv(csv_path)

# --- Column detection ---
numeric_cols = df.select_dtypes(include=['int64', 'float64']).columns.tolist()
categorical_cols = df.select_dtypes(include=['object']).columns.tolist()

# --- 1️⃣ Histogram for numeric columns ---
for col in numeric_cols:
    plt.figure(figsize=(6, 4))
    sns.histplot(df[col], kde=True, color='skyblue')
    plt.title(f'Distribution of {col}')
    plt.tight_layout()
    plt.savefig(f"{output_dir}/{col}_hist.png")
    plt.close()

# --- 2️⃣ Boxplot for numeric columns ---
for col in numeric_cols:
    plt.figure(figsize=(4, 5))
    sns.boxplot(y=df[col], color='lightcoral')
    plt.title(f'{col} Boxplot')
    plt.tight_layout()
    plt.savefig(f"{output_dir}/{col}_box.png")
    plt.close()

# --- 3️⃣ Correlation heatmap ---
if len(numeric_cols) > 1:
    plt.figure(figsize=(6, 5))
    sns.heatmap(df[numeric_cols].corr(), annot=True, cmap='coolwarm', fmt=".2f")
    plt.title('Correlation Heatmap')
    plt.tight_layout()
    plt.savefig(f"{output_dir}/correlation_heatmap.png")
    plt.close()

# --- 4️⃣ Bar charts for categorical columns ---
for col in categorical_cols:
    plt.figure(figsize=(6, 4))
    df[col].value_counts().plot(kind='bar', color='mediumseagreen')
    plt.title(f'Frequency of {col}')
    plt.xlabel(col)
    plt.ylabel('Count')
    plt.tight_layout()
    plt.savefig(f"{output_dir}/{col}_bar.png")
    plt.close()

# --- 5️⃣ Scatter plots for pairs of numeric columns ---
if len(numeric_cols) >= 2:
    for i in range(len(numeric_cols)):
        for j in range(i + 1, len(numeric_cols)):
            x, y = numeric_cols[i], numeric_cols[j]
            plt.figure(figsize=(6, 5))
            sns.scatterplot(x=df[x], y=df[y], hue=df[categorical_cols[0]] if categorical_cols else None)
            plt.title(f'{y} vs {x}')
            plt.tight_layout()
            plt.savefig(f"{output_dir}/scatter_{x}_vs_{y}.png")
            plt.close()

# --- Line plot for numeric trend (just first few numeric columns) ---
if len(numeric_cols) >= 2:
    plt.figure(figsize=(8, 5))
    for col in numeric_cols:
        plt.plot(df[col], label=col)
    plt.title('Numeric Trends')
    plt.legend()
    plt.tight_layout()
    plt.savefig(f"{output_dir}/line_trend.png")
    plt.close()

# --- Pie chart for categorical distribution (first category) ---
if categorical_cols:
    first_cat = categorical_cols[0]
    plt.figure(figsize=(6, 6))
    df[first_cat].value_counts().plot.pie(autopct='%1.1f%%', colors=sns.color_palette('pastel'))
    plt.title(f'{first_cat} Distribution')
    plt.ylabel('')
    plt.tight_layout()
    plt.savefig(f"{output_dir}/{first_cat}_pie.png")
    plt.close()

print(f"All charts saved in '{output_dir}'")
