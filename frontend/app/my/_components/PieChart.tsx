"use client";
import React from "react";
import CustomBadge from "@/app/_components/CustomBadge";
type categoryType = "kpop" | "drama" | "novel" | "movie";
import { Pie } from "react-chartjs-2";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  ArcElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  ArcElement,
  CategoryScale,
  LinearScale,
);

interface PieChartProps {
  data: number[];
  labels: string[];
}

const PieChart: React.FC<PieChartProps> = ({ data, labels }) => {
  const generateColors = (labels: string[]) => {
    const colorMap: Record<string, string> = {
      kpop: "#7C3AED",
      drama: "#F59E0B",
      novel: "#F87171",
      movie: "#38BDF8",
    };
    return labels.map((label) => colorMap[label]);
  };

  const maxPercentage = Math.max(...data);
  const charTitle: categoryType = labels[
    data.indexOf(maxPercentage)
  ] as categoryType;

  const chartData = {
    labels: labels,
    datasets: [
      {
        data: data,
        backgroundColor: generateColors(labels),
      },
    ],
  };

  const chartOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: "right" as const,
      },
    },
  };

  const formatTitle = (category: string): string => {
    if (category === "kpop") return "K-Pop";
    if (category === "drama") return "Drama";
    if (category === "novel") return "Novel";
    if (category === "movie") return "Movie";
    return category;
  };

  return (
    <div>
      <div className="flex items-center justify-center pb-2">
        <CustomBadge
          title={`${formatTitle(charTitle)} Maniac`}
          category={charTitle}
        />
      </div>
      <Pie data={chartData} options={chartOptions} />
    </div>
  );
};

export default PieChart;
