package com.common.utils.jfreechart;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class TimeSeriesChart {
    public TimeSeriesChart() {
    }

    public TimeSeriesCollection createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        String[] categories = {"�ɶ��ܻ���", "ǰʮ���ֹɱ���"};

        Random random = new Random();
        for (int row = 0; row < categories.length; row++) {
            Vector<Object[]> dateValues = new Vector<Object[]>();
            for (int i = 0; i < 20; i++) {
                String date = (2000 + i) + "-0" + i + "-21";
                System.out.println(date);
                Object[] dateValue = {date, random.nextInt(10)};
                dateValues.add(dateValue);

            }
            TimeSeries timeSeries = JfreeChartUtils.createTimeseries(categories[row], dateValues);
            dataset.addSeries(timeSeries);
        }
        return dataset;
    }

    public ChartPanel createChart() {
        TimeSeriesCollection dataset = createDataset();
        JFreeChart chart = ChartFactory.createTimeSeriesChart("�ɶ��ܻ���", "", "", dataset);
        JfreeChartUtils.setAntiAlias(chart);
        JfreeChartUtils.setTimeSeriesRender(chart.getPlot(), true, true);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        JfreeChartUtils.setXY_XAixs(xyplot);
        JfreeChartUtils.setXY_YAixs(xyplot);
        DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
        domainAxis.setAutoTickUnitSelection(false);
        DateTickUnit dateTickUnit = null;
        if (dataset.getItemCount(0) < 16) {
            dateTickUnit = new DateTickUnit(DateTickUnitType.MONTH, 6, new SimpleDateFormat("yyyy-MM"));
        } else {
            XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
            xyRenderer.setDefaultItemLabelsVisible(false);
            dateTickUnit = new DateTickUnit(DateTickUnitType.YEAR, 1, new SimpleDateFormat("yyyy"));
        }
        domainAxis.setTickUnit(dateTickUnit);
        JfreeChartUtils.setLegendEmptyBorder(chart);
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
                ChartPanel chartPanel = new TimeSeriesChart().createChart();
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });

    }

}
