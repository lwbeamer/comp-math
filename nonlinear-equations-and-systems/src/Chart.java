import org.knowm.xchart.*;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.w3c.dom.Node;

import java.awt.*;
import java.io.IOException;

public class Chart {
    public static double a;
    public static double b;

    public static Equation f;
    public static NLESystem fS;

    public static double [] xs = new double[15000];
    public static double [] ys = new double[15000];
    public static double [] ys2 = new double[15000];
    public static double [] y2s = new double[15000];

    public static void setValues(Equation f){
        Chart.a = -5;
        Chart.b = 5;
        Chart.f = f;
    }

    public static void setValues(NLESystem fS){
        Chart.a = -2;
        Chart.b = 2;
        Chart.fS = fS;
    }

    public static void drawFunction(){
        double k = (b - a) / 15000;

        xs[0] = a;
        ys[0] = f.f(a);


        double currX = xs[0];
        for (int i = 1; i < 15000; i++){
            currX += k;
            xs[i] = currX;
            ys[i] = f.f(currX);
        }


        XYChart chart = QuickChart.getChart("График", "X", "Y", "f(x)", xs, ys);

        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

        try {
            BitmapEncoder.saveBitmap(chart, "./График", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void drawSystemOfFunctions(){
        double k = (b - a) / 15000;

        xs[0] = a;
        ys[0] = fS.getYFromF1(a);
        ys2[0] = -fS.getYFromF1(a);
        y2s[0] = fS.getYFromF2(a);


        double currX = xs[0];
        for (int i = 1; i < 15000; i++){
            currX += k;
            xs[i] = currX;
            ys[i] = fS.getYFromF1(currX);
            ys2[i] = -ys[i];
            y2s[i] = fS.getYFromF2(currX);
        }


        XYChart chart = new XYChart(new XYChartBuilder());

        chart.setXAxisTitle("X");
        chart.setXAxisTitle("Y");
        chart.setTitle("График");
        chart.addSeries("f1", xs, ys).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.blue);
        chart.addSeries("f1-2", xs, ys2).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.blue).setLineColor(Color.blue).setShowInLegend(false);
        chart.addSeries("f2", xs, y2s).setMarker(SeriesMarkers.NONE);

        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

    }
}
