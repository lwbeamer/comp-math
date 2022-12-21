import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.util.Vector;

public class Chart {

    public static double a;
    public static double b;
    public static int n;

    public static Equation equation;

    public static double [] x = new double[350];
    public static double [] y = new double[350];
    public static double [] pointsX;
    public static double [] pointsY;

    public static void drawChart(){
        double k = (b - a) / 350;

        x[0] = a;
        double currX = x[0];
        y[0] = equation.exactValue(currX);

        for (int i = 1; i < 350; i++){
            currX += k;
            x[i] = currX;
            y[i] = equation.exactValue(currX);
        }

        XYChart chart = new XYChart(new XYChartBuilder());

        chart.setXAxisTitle("X");
        chart.setXAxisTitle("Y");
        chart.setTitle("Графики точного и приблизительного решения");

        chart.addSeries("Точное значение", x, y).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(Color.orange).setLineWidth(0).setLineColor(Color.orange);
        chart.addSeries("Полученное решение", pointsX, pointsY).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.BLACK).setLineWidth(0).setLineColor(Color.CYAN);


        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

//        try {
//            BitmapEncoder.saveBitmap(chart, "./График", BitmapEncoder.BitmapFormat.PNG);
//        } catch (IOException e){
//            e.printStackTrace();
//        }

    }




    public static void setInterval(double a, double b){
        Chart.a = a;
        Chart.b = b;
    }

    public static void setPoints(Vector<Double> x, Vector<Double> y){
        pointsX = new double[x.size()];
        pointsY = new double[y.size()];

        for (int i = 0; i < x.size(); i++){
            pointsX[i] = x.elementAt(i);
            pointsY[i] = y.elementAt(i);
        }

    }

    public static void setEquation(Equation equation){
        Chart.equation = equation;
    }
}
