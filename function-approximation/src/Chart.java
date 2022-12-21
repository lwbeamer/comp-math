import java.io.IOException;

import org.knowm.xchart.*;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.w3c.dom.Node;

import java.awt.*;


public class Chart {
    public static double a;
    public static double b;
    public static int n;

    public static LinearFunc linearFunc;
    public static PowerFunc powerFunc;
    public static ExpFunc expFunc;
    public static LogFunc logFunc;
    public static SqrFunc sqrFunc;
    public static CubicFunc cubicFunc;

    public static double [] x = new double[350];
    public static double [] yLinear = new double[350];
    public static double [] yPower = new double[350];
    public static double [] yExp = new double[350];
    public static double [] yLog = new double[350];
    public static double [] ySqr = new double[350];
    public static double [] yCubic = new double[350];
    public static double [] pointsX;
    public static double [] pointsY;



    public static void drawFunction(){
        double k = (b - a) / 350;

        x[0] = a;
        double currX = x[0];
        yLinear[0] = linearFunc.p(a);
        yPower[0] = powerFunc.p(a);
        yExp[0] = expFunc.p(a);
        yLog[0] = logFunc.p(a);
        ySqr[0] = sqrFunc.p(a);
        yCubic[0] = cubicFunc.p(a);


        for (int i = 1; i < 350; i++){
            currX += k;
            x[i] = currX;
            yLinear[i] = linearFunc.p(currX);
            yPower[i] = powerFunc.p(currX);
            yExp[i] = expFunc.p(currX);
            yLog[i] = logFunc.p(currX);
            ySqr[i] = sqrFunc.p(currX);
            yCubic[i] = cubicFunc.p(currX);
        }

        XYChart chart = new XYChart(new XYChartBuilder());

        chart.setXAxisTitle("X");
        chart.setXAxisTitle("Y");
        chart.setTitle("Графики функций");
//        chart.addSeries("Линейная функция\n"+linearFunc.getA()+" * x + "+linearFunc.getB(), x, yLinear).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.blue);
//        chart.addSeries("Степенная функция\n"+powerFunc.getA()+" * x ^ "+powerFunc.getB(), x, yPower).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.red);
//        chart.addSeries("Экспоненциальная функция\n"+expFunc.getA()+" * e ^ ("+expFunc.getB()+" * x)", x, yExp).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.yellow);
//        chart.addSeries("Логарифмическая функция\n"+logFunc.getA()+" * ln(x) + "+logFunc.getB(), x, yLog).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.green);
//        chart.addSeries("Полиномиальная функция 2-й степени\n"+sqrFunc.getA()+" * x^2 + "+sqrFunc.getB()+" * x + "+sqrFunc.getC(), x, ySqr).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.CYAN);
//        chart.addSeries("Полиномиальная функция 3-й степени\n"+cubicFunc.getA()+" * x^3 + "+cubicFunc.getB()+" * x^2 + "+cubicFunc.getC()+" * x + "+ cubicFunc.getD(), x, yCubic).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.MAGENTA);

        chart.addSeries("Линейная функция", x, yLinear).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.blue);
        chart.addSeries("Степенная функция", x, yPower).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.red);
        chart.addSeries("Экспоненциальная функция", x, yExp).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.yellow);
        chart.addSeries("Логарифмическая функция", x, yLog).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.green);
        chart.addSeries("Полиномиальная функция 2-й степени", x, ySqr).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.CYAN);
        chart.addSeries("Полиномиальная функция 3-й степени", x, yCubic).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.MAGENTA);
        chart.addSeries("Точки", pointsX, pointsY).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.BLACK).setLineWidth(0);


        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

//        try {
//            BitmapEncoder.saveBitmap(chart, "./График", BitmapEncoder.BitmapFormat.PNG);
//        } catch (IOException e){
//            e.printStackTrace();
//        }

    }

    public static void setValues(LinearFunc linearFunc, PowerFunc powerFunc, ExpFunc expFunc, LogFunc logFunc, SqrFunc sqrFunc, CubicFunc cubicFunc){
        Chart.linearFunc = linearFunc;
        Chart.powerFunc = powerFunc;
        Chart.expFunc = expFunc;
        Chart.logFunc = logFunc;
        Chart.sqrFunc = sqrFunc;
        Chart.cubicFunc = cubicFunc;
    }

    public static void setABN(double a, double b, int n){
        Chart.a = a;
        Chart.b = b;
        Chart.n = n;
    }

    public static void setXY(double[] x, double[] y){
        Chart.pointsX = x;
        Chart.pointsY = y;
    }



}
