import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import sys, os

# Read file path from Java argument
input_path = sys.argv[1]
output_dir = "output/charts"
os.makedirs(output_dir, exist_ok=True)

print("Loading data for visualization...")
df = pd.read_csv(input_path)

# Basic stats
desc = df.describe(include='all')
desc.to_csv("output/stats.txt")
print("Saved summary stats to output/stats.txt")

# Line Plot
sns.lineplot(data=df.select_dtypes('number'))
plt.title("Numeric Columns Line Plot")
plt.savefig(f"{output_dir}/line_plot.png")
plt.close()

# Scatter Matrix
sns.pairplot(df.select_dtypes('number'))
plt.savefig(f"{output_dir}/scatter_matrix.png")
plt.close()

# Bar Chart (if categorical columns exist)
for col in df.select_dtypes('object').columns:
    plt.figure()
    df[col].value_counts().plot(kind='bar')
    plt.title(f"Distribution of {col}")
    plt.tight_layout()
    plt.savefig(f"{output_dir}/{col}_bar.png")
    plt.close()

print("Charts saved in 'output/charts/'.")
