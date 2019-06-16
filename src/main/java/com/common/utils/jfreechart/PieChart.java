package com.common.utils.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;


public class PieChart {
    public PieChart() {
    }

    public DefaultPieDataset createDataset() {
        String[] categories = {"Bananas", "Kiwi", "Mixed nuts", "Oranges", "Apples", "Pears", "Clementines", "Reddish (bag)", "Grapes (bunch)",};
        Object[] datas = {8, 3, 1, 6, 8, 4, 4, 1, 1};
        DefaultPieDataset dataset = JfreeChartUtils.createDefaultPieDataset(categories, datas);
        return dataset;
    }

    public ChartPanel createChart() {
        JFreeChart chart = ChartFactory.createPieChart("Contents of Highsoft's weekly fruit delivery", createDataset());
        JfreeChartUtils.setAntiAlias(chart);
        JfreeChartUtils.setPieRender(chart.getPlot());
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 420);
        frame.setLocationRelativeTo(null);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // ����ͼ��
                ChartPanel chartPanel = new PieChart().createChart();
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });

    }

}
