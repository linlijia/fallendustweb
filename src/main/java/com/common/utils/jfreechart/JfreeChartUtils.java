package com.common.utils.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


/**
 * Jfreechart工具类
 * <p>
 * 解决中午乱码问题<br>
 * 用来创建类别图表数据集、创建饼图数据集、时间序列图数据集<br>
 * 用来对柱状图、折线图、饼图、堆积柱状图、时间序列图的样式进行渲染<br>
 * 设置X-Y坐标轴样式
 * <p>
 *
 * @author chenchangwen
 * @since:2014-2-18
 */
public class JfreeChartUtils {
    private static String NO_DATA_MSG = "数据加载失败";
    private static Font FONT = new Font("宋体", Font.PLAIN, 12);
    public static Color[] CHART_COLORS = {
            new Color(31, 129, 188), new Color(92, 92, 97), new Color(144, 237, 125), new Color(255, 188, 117),
            new Color(153, 158, 255), new Color(255, 117, 153), new Color(253, 236, 109), new Color(128, 133, 232),
            new Color(158, 90, 102), new Color(255, 204, 102)};

    static {
        setChartTheme();
    }

    public JfreeChartUtils() {
    }

    /**
     * 中文主题样式 解决乱码
     */
    public static void setChartTheme() {
        // 设置中文主题样式 解决乱码
        StandardChartTheme chartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        chartTheme.setExtraLargeFont(FONT);
        // 设置图例的字体
        chartTheme.setRegularFont(FONT);
        // 设置轴向的字体
        chartTheme.setLargeFont(FONT);
        chartTheme.setSmallFont(FONT);
        chartTheme.setTitlePaint(new Color(51, 51, 51));
        chartTheme.setSubtitlePaint(new Color(85, 85, 85));
        // 设置标注
        chartTheme.setLegendBackgroundPaint(Color.WHITE);
        chartTheme.setLegendItemPaint(Color.BLACK);
        chartTheme.setChartBackgroundPaint(Color.WHITE);
        // 绘制颜色绘制颜色.轮廓供应商
        // paintSequence,outlinePaintSequence,strokeSequence,outlineStrokeSequence,shapeSequence

        Paint[] OUTLINE_PAINT_SEQUENCE = new Paint[]{Color.WHITE};
        // 绘制器颜色源
        DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(CHART_COLORS, CHART_COLORS, OUTLINE_PAINT_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
        chartTheme.setDrawingSupplier(drawingSupplier);
        // 绘制区域
        chartTheme.setPlotBackgroundPaint(Color.WHITE);
        // 绘制区域外边框
        chartTheme.setPlotOutlinePaint(Color.WHITE);
        // 链接标签颜色
        chartTheme.setLabelLinkPaint(new Color(8, 55, 114));
        chartTheme.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

        chartTheme.setAxisOffset(new RectangleInsets(5, 12, 5, 12));
        // X坐标轴垂直网格颜色
        chartTheme.setDomainGridlinePaint(new Color(192, 208, 224));
        // Y坐标轴水平网格颜色
        chartTheme.setRangeGridlinePaint(new Color(192, 192, 192));

        chartTheme.setBaselinePaint(Color.WHITE);
        // 不确定含义
        chartTheme.setCrosshairPaint(Color.BLUE);
        // 坐标轴标题文字颜色
        chartTheme.setAxisLabelPaint(new Color(51, 51, 51));
        // 刻度数字
        chartTheme.setTickLabelPaint(new Color(67, 67, 72));
        // 设置柱状图渲染
        chartTheme.setBarPainter(new StandardBarPainter());
        // XYBar 渲染
        chartTheme.setXYBarPainter(new StandardXYBarPainter());

        chartTheme.setItemLabelPaint(Color.black);
        // 温度计
        chartTheme.setThermometerPaint(Color.white);

        ChartFactory.setChartTheme(chartTheme);
    }

    /**
     * 必须设置文本抗锯齿
     */
    public static void setAntiAlias(JFreeChart chart) {
        chart.setTextAntiAlias(false);

    }

    /**
     * 设置图例无边框，默认黑色边框
     */
    public static void setLegendEmptyBorder(JFreeChart chart) {
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));

    }

    /**
     * 创建类别数据集合
     */
    public static DefaultCategoryDataset createDefaultCategoryDataset(Vector<Serie> series, String[] categories) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Serie serie : series) {
            String name = serie.getName();
            Vector<Object> data = serie.getData();
            if (data != null && categories != null && data.size() == categories.length) {
                for (int index = 0; index < data.size(); index++) {
                    String value = data.get(index) == null ? "" : data.get(index).toString();
                    if (isPercent(value)) {
                        value = value.substring(0, value.length() - 1);
                    }
                    if (isNumber(value)) {
                        dataset.setValue(Double.parseDouble(value), name, categories[index]);
                    }
                }
            }

        }
        return dataset;

    }

    /**
     * 创建饼图数据集合
     */
    public static DefaultPieDataset createDefaultPieDataset(String[] categories, Object[] datas) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < categories.length && categories != null; i++) {
            String value = datas[i].toString();
            if (isPercent(value)) {
                value = value.substring(0, value.length() - 1);
            }
            if (isNumber(value)) {
                dataset.setValue(categories[i], Double.valueOf(value));
            }
        }
        return dataset;

    }

    /**
     * 创建时间序列数据
     *
     * @param category   类别
     * @param dateValues 日期-值 数组
     * @param
     * @return
     */
    public static TimeSeries createTimeseries(String category, Vector<Object[]> dateValues) {
        TimeSeries timeseries = new TimeSeries(category);

        if (dateValues != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Object[] objects : dateValues) {
                Date date = null;
                try {
                    date = dateFormat.parse(objects[0].toString());
                } catch (ParseException e) {
                }
                String sValue = objects[1].toString();
                double dValue = 0;
                if (date != null && isNumber(sValue)) {
                    dValue = Double.parseDouble(sValue);
                    timeseries.add(new Day(date), dValue);
                }
            }
        }

        return timeseries;
    }

    /**
     * 设置 折线图样式
     *
     * @param plot
     * @param isShowDataLabels 是否显示数据标签 默认不显示节点形状
     */
    public static void setLineRender(CategoryPlot plot, boolean isShowDataLabels) {
        setLineRender(plot, isShowDataLabels, false);
    }

    /**
     * 设置折线图样式
     *
     * @param plot
     * @param isShowDataLabels 是否显示数据标签
     */
    public static void setLineRender(CategoryPlot plot, boolean isShowDataLabels, boolean isShapesVisible) {
        plot.setNoDataMessage(NO_DATA_MSG);
        plot.setInsets(new RectangleInsets(10, 10, 0, 10), false);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultStroke(new BasicStroke(1.5F));
        if (isShowDataLabels) {
            renderer.setDefaultItemLabelsVisible(true);
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator(StandardCategoryItemLabelGenerator.DEFAULT_LABEL_FORMAT_STRING,
                    NumberFormat.getInstance()));
            renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.BOTTOM_CENTER));
        }
        // 数据点绘制形状
        renderer.setDefaultShapesVisible(isShapesVisible);
        setXAixs(plot);
        setYAixs(plot);

    }

    /**
     * 设置时间序列图样式
     *
     * @param plot
     * @param isShowData      是否显示数据
     * @param isShapesVisible 是否显示数据节点形状
     */
    public static void setTimeSeriesRender(Plot plot, boolean isShowData, boolean isShapesVisible) {

        XYPlot xyplot = (XYPlot) plot;
        xyplot.setNoDataMessage(NO_DATA_MSG);
        xyplot.setInsets(new RectangleInsets(10, 10, 5, 10));

        XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
        xyRenderer.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyRenderer.setDefaultShapesVisible(false);
        if (isShowData) {
            xyRenderer.setDefaultItemLabelsVisible(true);
            xyRenderer.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
            xyRenderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.BOTTOM_CENTER));
        }
        // 数据点绘制形状
        xyRenderer.setDefaultShapesVisible(isShapesVisible);

        DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
        domainAxis.setAutoTickUnitSelection(false);
        // 第二个参数是时间轴间距
        DateTickUnit dateTickUnit = new DateTickUnit(DateTickUnitType.YEAR, 1, new SimpleDateFormat("yyyy-MM"));
        domainAxis.setTickUnit(dateTickUnit);

        StandardXYToolTipGenerator xyTooltipGenerator = new StandardXYToolTipGenerator("{1}:{2}", new SimpleDateFormat("yyyy-MM-dd"), new DecimalFormat("0"));
        xyRenderer.setDefaultToolTipGenerator(xyTooltipGenerator);

        setXY_XAixs(xyplot);
        setXY_YAixs(xyplot);

    }

    /**
     * 设置时间序列图样式 -默认不显示数据节点形状
     *
     * @param plot
     * @param isShowData 是否显示数据
     */

    public static void setTimeSeriesRender(Plot plot, boolean isShowData) {
        setTimeSeriesRender(plot, isShowData, false);
    }

    /**
     * 设置时间序列图渲染：但是存在一个问题：如果timeseries里面的日期是按照天组织， 那么柱子的宽度会非常小，和直线一样粗细
     *
     * @param plot
     * @param isShowDataLabels
     */

    public static void setTimeSeriesBarRender(Plot plot, boolean isShowDataLabels) {

        XYPlot xyplot = (XYPlot) plot;
        xyplot.setNoDataMessage(NO_DATA_MSG);

        XYBarRenderer xyRenderer = new XYBarRenderer(0.1D);
        xyRenderer.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());

        if (isShowDataLabels) {
            xyRenderer.setDefaultItemLabelsVisible(true);
            xyRenderer.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
        }

        StandardXYToolTipGenerator xyTooltipGenerator = new StandardXYToolTipGenerator("{1}:{2}", new SimpleDateFormat("yyyy-MM-dd"), new DecimalFormat("0"));
        xyRenderer.setDefaultToolTipGenerator(xyTooltipGenerator);
        setXY_XAixs(xyplot);
        setXY_YAixs(xyplot);

    }

    /**
     * 设置柱状图渲染
     *
     * @param plot
     * @param isShowDataLabels
     */
    public static void setBarRenderer(CategoryPlot plot, boolean isShowDataLabels) {

        plot.setNoDataMessage(NO_DATA_MSG);
        plot.setInsets(new RectangleInsets(10, 10, 5, 10));
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        // 设置柱子最大宽度
        renderer.setMaximumBarWidth(0.075);

        if (isShowDataLabels) {
            renderer.setDefaultItemLabelsVisible(true);
        }

        setXAixs(plot);
        setYAixs(plot);
    }

    /**
     * 设置堆积柱状图渲染
     *
     * @param plot
     */

    public static void setStackBarRender(CategoryPlot plot) {
        plot.setNoDataMessage(NO_DATA_MSG);
        plot.setInsets(new RectangleInsets(10, 10, 5, 10));
        StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        plot.setRenderer(renderer);
        setXAixs(plot);
        setYAixs(plot);
    }

    /**
     * 设置类别图表(CategoryPlot) X坐标轴线条颜色和样式
     *
     * @param
     */
    public static void setXAixs(CategoryPlot plot) {
        Color lineColor = new Color(31, 121, 170);
        // X坐标轴颜色
        plot.getDomainAxis().setAxisLinePaint(lineColor);
        // X坐标轴标记|竖线颜色
        plot.getDomainAxis().setTickMarkPaint(lineColor);

    }

    /**
     * 设置类别图表(CategoryPlot) Y坐标轴线条颜色和样式 同时防止数据无法显示
     *
     * @param
     */
    public static void setYAixs(CategoryPlot plot) {
        Color lineColor = new Color(192, 208, 224);
        ValueAxis axis = plot.getRangeAxis();
        // Y坐标轴颜色
        axis.setAxisLinePaint(lineColor);
        // Y坐标轴标记|竖线颜色
        axis.setTickMarkPaint(lineColor);
        // 隐藏Y刻度
        axis.setAxisLineVisible(false);
        axis.setTickMarksVisible(false);
        // Y轴网格线条
        plot.setRangeGridlinePaint(new Color(192, 192, 192));
        plot.setRangeGridlineStroke(new BasicStroke(1));

        // 设置顶部Y坐标轴间距,防止数据无法显示
        plot.getRangeAxis().setUpperMargin(0.1);
        // 设置底部Y坐标轴间距
        plot.getRangeAxis().setLowerMargin(0.1);

    }

    /**
     * 设置XY图表(XYPlot) X坐标轴线条颜色和样式
     *
     * @param
     */
    public static void setXY_XAixs(XYPlot plot) {
        Color lineColor = new Color(31, 121, 170);
        // X坐标轴颜色
        plot.getDomainAxis().setAxisLinePaint(lineColor);
        // X坐标轴标记|竖线颜色
        plot.getDomainAxis().setTickMarkPaint(lineColor);

    }

    /**
     * 设置XY图表(XYPlot) Y坐标轴线条颜色和样式 同时防止数据无法显示
     *
     * @param
     */
    public static void setXY_YAixs(XYPlot plot) {
        Color lineColor = new Color(192, 208, 224);
        ValueAxis axis = plot.getRangeAxis();
        // X坐标轴颜色
        axis.setAxisLinePaint(lineColor);
        // X坐标轴标记|竖线颜色
        axis.setTickMarkPaint(lineColor);
        // 隐藏Y刻度
        axis.setAxisLineVisible(false);
        axis.setTickMarksVisible(false);
        // Y轴网格线条
        plot.setRangeGridlinePaint(new Color(192, 192, 192));
        plot.setRangeGridlineStroke(new BasicStroke(1));
        plot.setDomainGridlinesVisible(false);
        // 设置顶部Y坐标轴间距,防止数据无法显示
        plot.getRangeAxis().setUpperMargin(0.12);
        // 设置底部Y坐标轴间距
        plot.getRangeAxis().setLowerMargin(0.12);

    }

    /**
     * 设置饼状图渲染
     */
    public static void setPieRender(Plot plot) {

        plot.setNoDataMessage(NO_DATA_MSG);
        plot.setInsets(new RectangleInsets(10, 10, 5, 10));
        PiePlot piePlot = (PiePlot) plot;
        piePlot.setInsets(new RectangleInsets(0, 0, 0, 0));
        // 圆形
        piePlot.setCircular(true);

        // piePlot.setSimpleLabels(true);// 简单标签
        piePlot.setLabelGap(0.01);
        piePlot.setInteriorGap(0.05D);
        // 图例形状
        piePlot.setLegendItemShape(new Rectangle(10, 10));
        piePlot.setIgnoreNullValues(true);
        // 去掉背景色
        piePlot.setLabelBackgroundPaint(null);
        // 去掉阴影
        piePlot.setLabelShadowPaint(null);
        // 去掉边框
        piePlot.setLabelOutlinePaint(null);
        piePlot.setShadowPaint(null);
        // 0:category 1:value:2 :percentage
        // 显示标签数据
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{2}"));
    }

    /**
     * 是不是一个%形式的百分比
     *
     * @param str
     * @return
     */
    public static boolean isPercent(String str) {
        return str != null ? str.endsWith("%") && isNumber(str.substring(0, str.length() - 1)) : false;
    }

    /**
     * 是不是一个数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return str != null ? str.matches("^[-+]?(([0-9]+)((([.]{0})([0-9]*))|(([.]{1})([0-9]+))))$") : false;
    }

    /**
     * 将字节数组写入到文件
     *
     * @param imageBytes
     */
    public static void write2File(byte[] imageBytes, String fullPath) {
        File f = new File(fullPath);
        FileOutputStream fos = null;
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fos = new FileOutputStream(f);
            fos.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}



