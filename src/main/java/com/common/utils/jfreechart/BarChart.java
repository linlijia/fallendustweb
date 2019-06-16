package com.common.utils.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * @author ccw
 * @date 2014-6-11
 * <p>
 * ����ͼ���裺<br/>
 * 1���������ݼ���<br/>
 * 2������Chart��<br/>
 * 3:���ÿ���ݣ���ֹ������ʾ�����<br/>
 * 4:�����ӽ�����Ⱦ��<br/>
 * 5:���������ֽ�����Ⱦ<br/>
 * 6:ʹ��chartPanel����<br/>
 *
 * </p>
 */
public class BarChart {
    public BarChart() {
    }

    public DefaultCategoryDataset createDataset() {
        String[] categories = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Vector<Serie> series = new Vector<Serie>();
        series.add(new Serie("Tokyo", new Double[]{49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4}));
        series.add(new Serie("New York", new Double[]{83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3}));
        series.add(new Serie("London", new Double[]{48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2}));
        series.add(new Serie("Berlin", new Double[]{42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1}));
        DefaultCategoryDataset dataset = JfreeChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

    public ChartPanel createChart(DefaultCategoryDataset defaultCategoryDataset) {

        JFreeChart chart = ChartFactory.createBarChart("Monthly Average Rainfall", "", "Rainfall (mm)", defaultCategoryDataset);
        JfreeChartUtils.setAntiAlias(chart);
        JfreeChartUtils.setBarRenderer(chart.getCategoryPlot(), false);
        JfreeChartUtils.setXAixs(chart.getCategoryPlot());
        JfreeChartUtils.setYAixs(chart.getCategoryPlot());
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    /**
     * @param title                  图片标题
     * @param ategoryAxisLabel       类别分类标签
     * @param valueAxisLabel         值标签
     * @param width                  图片跨度
     * @param height                 图片高度
     * @param defaultCategoryDataset 数据集
     * @return
     */
    public byte[] createByteChart(String title, String categoryAxisLabel, String valueAxisLabel, int width, int height, DefaultCategoryDataset defaultCategoryDataset) {
        JFreeChart chart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, defaultCategoryDataset);
        JfreeChartUtils.setAntiAlias(chart);
        JfreeChartUtils.setBarRenderer(chart.getCategoryPlot(), false);
        JfreeChartUtils.setXAixs(chart.getCategoryPlot());
        JfreeChartUtils.setYAixs(chart.getCategoryPlot());
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        CategoryTextAnnotation a = new CategoryTextAnnotation("Minimum grade to pass", "Robert", 100);
        a.setCategoryAnchor(CategoryAnchor.START);
        a.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a.setTextAnchor(TextAnchor.BOTTOM_LEFT);
//        chart.getCategoryPlot().addAnnotation(a);

        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        try {
            return ChartUtils.encodeAsPNG(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        BarChart barChart = new BarChart();
        byte[] r = barChart.createByteChart("测试图片", "我是分类标签", "我是值标签", 550, 350, barChart.createDataset());
        String filePaht = "bar.JPEG";
        JfreeChartUtils.write2File(r, filePaht);
//        final JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1024, 420);
//        frame.setLocationRelativeTo(null);
//
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                ChartPanel chartPanel = new BarChart().createChart();
//                frame.getContentPane().add(chartPanel);
//                frame.setVisible(true);
//            }
//        });

    }

}
