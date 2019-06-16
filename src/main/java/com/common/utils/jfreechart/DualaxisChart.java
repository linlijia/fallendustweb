package com.common.utils.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class DualaxisChart {
    public DualaxisChart() {
    }

    public ChartPanel createChart() {
        String[] categories = {"1999-12-31", "2000-12-31", "2001-12-31", "2002-12-31", "2003-12-31", "2004-12-31", "2005-12-31", "2006-12-31", "2007-12-31",
                "2008-12-31", "2009-12-31", "2010-12-31", "2011-12-31", "2012-12-31", "2013-12-31"};
        for (int i = 0; i < categories.length; i++) {
            categories[i] = categories[i].substring(0, 4);
        }
        Vector<Serie> seriesNetProfit = new Vector<Serie>();

        Object[] netProfit = {92669.20, 95790.47, 106187.80, 128530.88, 156608.82, 193003.08, 255800.38, 335302.66, 549877.54, 1251596.81, 1321658.11,
                1917721.09, 2728598.10, 3418600.00, 4092200.00};

        Object[] payoutRatio = {"39.01", "--", "45.39", "30.46", "27.50", "24.34", "19.90", "19.48", "12.67", "10.40", "10.02", "11.97", "20.51", "30.01",
                " --"};


        seriesNetProfit.add(new Serie("������", netProfit));

        Vector<Serie> seriesPayoutRatio = new Vector<Serie>();
        seriesPayoutRatio.add(new Serie("����֧����", payoutRatio));

        DefaultCategoryDataset datasetNetProfit = JfreeChartUtils.createDefaultCategoryDataset(seriesNetProfit, categories);
        JFreeChart chart = ChartFactory.createBarChart("", "", "������(��Ԫ)", datasetNetProfit);
        JfreeChartUtils.setAntiAlias(chart);
        JfreeChartUtils.setBarRenderer(chart.getCategoryPlot(), false);

        JfreeChartUtils.setXAixs(chart.getCategoryPlot());
        JfreeChartUtils.setYAixs(chart.getCategoryPlot());

        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        DefaultCategoryDataset datasetPayoutRatio = JfreeChartUtils.createDefaultCategoryDataset(seriesPayoutRatio, categories);
        categoryplot.setDataset(1, datasetPayoutRatio);
        categoryplot.mapDatasetToRangeAxis(1, 1);

        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        NumberAxis numberaxis = new NumberAxis("����֧����(%)");
        categoryplot.setRangeAxis(1, numberaxis);

        numberaxis.setAxisLineVisible(false);
        numberaxis.setTickMarksVisible(false);

        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        lineRenderer.setSeriesPaint(0, new Color(255, 185, 1));
        lineRenderer.setDefaultShapesVisible(true);
        categoryplot.setRenderer(1, lineRenderer);

        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        chart.getLegend().setPosition(RectangleEdge.TOP);
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
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
                ChartPanel chartPanel = new DualaxisChart().createChart();
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });

    }

}
