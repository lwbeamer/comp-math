import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.util.Collections;

public class Chart {

    public static double a;
    public static double b;
    public static int n;

    public static Lagranzh lagranzh;
    public static Gauss gauss;

    public static double [] x = new double[20];
    public static double [] yLagranzh = new double[20];
    public static double [] yGauss = new double[20];

    public static double [] pointsX;
    public static double [] pointsY;

    public static double answerX;
    public static double answerY;



    public static void drawFunctionForLagranzh(){
        double k = (b - a) / 350;

        x[0] = a;
        double currX = x[0];
        yLagranzh[0] = lagranzh.calculate(currX);



        for (int i = 1; i < 350; i++){
            currX += k;
            x[i] = currX;
            yLagranzh[i] = lagranzh.calculate(currX);
        }

        XYChart chart = new XYChart(new XYChartBuilder());

        chart.setXAxisTitle("X");
        chart.setXAxisTitle("Y");
        chart.setTitle("Полином Лагранжа");

        chart.addSeries("Приблизительная функция", x, yLagranzh).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(Color.orange).setLineWidth(0).setLineColor(Color.orange);
        chart.addSeries("Точки", pointsX, pointsY).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.BLACK).setLineWidth(0).setLineColor(Color.CYAN);
        chart.addSeries("Найденное значение", new double[]{answerX}, new double[]{answerY}).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.red).setLineWidth(0);


        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

//        try {
//            BitmapEncoder.saveBitmap(chart, "./График", BitmapEncoder.BitmapFormat.PNG);
//        } catch (IOException e){
//            e.printStackTrace();
//        }

    }

    public static void drawFunctionForGauss(){
        double k = (b - a) / 20;

        x[0] = a;
        double currX = x[0];
        yGauss[0] = gauss.calculate(currX);


        for (int i = 1; i < 20; i++){
            currX += k;
            x[i] = currX;
            yGauss[i] = gauss.calculate(currX);
        }

        XYChart chart = new XYChart(new XYChartBuilder());

        chart.setXAxisTitle("X");
        chart.setXAxisTitle("Y");
        chart.setTitle("Полином Гаусса");

        chart.addSeries("Приблизительная функция", x, yGauss).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(Color.orange).setLineWidth(0).setLineColor(Color.orange);
        chart.addSeries("Точки", pointsX, pointsY).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.BLACK).setLineWidth(0).setLineColor(Color.CYAN);
        chart.addSeries("Найденное значение", new double[]{answerX}, new double[]{answerY}).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.red).setLineWidth(0);




        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

//        try {
//            BitmapEncoder.saveBitmap(chart, "./График", BitmapEncoder.BitmapFormat.PNG);
//        } catch (IOException e){
//            e.printStackTrace();
//        }

    }

    public static void setValues(Lagranzh lagranzh, Gauss gauss){
        Chart.lagranzh = lagranzh;
        Chart.gauss = gauss;
    }

    public static void setABN(double a, double b){
        Chart.a = a;
        Chart.b = b;
    }

    public static void setXY(double[] x, double[] y){
        Chart.pointsX = x;
        Chart.pointsY = y;
    }

    public static void setAnswer(double x, double y){
        Chart.answerX = x;
        Chart.answerY = y;
    }


}
