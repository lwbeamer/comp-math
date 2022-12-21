public class SqrFunc {

    private Table table;
    private int n;

    private double sx, sxx, sxxx, sxxxx, sy, sxy, sxxy;
    private double d, d1, d2, d3;
    private double a,b,c,s,sigma;

    public SqrFunc(Table table) {
        this.table = table;
        n = table.getN();
        sx = calculateSX();
        sxx = calculateSXX();
        sxxx = calculateSXXX();
        sxxxx = calculateSXXXX();
        sy = calculateSY();
        sxy = calculateSXY();
        sxxy = calculateSXXY();

        d = n * sxx * sxxxx + sx * sxxx * sxx + sx * sxxx * sxx - sxx * sxx * sxx - sx * sx * sxxxx - sxxx * sxxx * n;
        d1 = sy * sxx * sxxxx + sx * sxxx * sxxy + sxy * sxxx * sxx - sxx * sxx * sxxy - sx * sxy * sxxxx - sxxx * sxxx * sy;
        d2 = n * sxy * sxxxx + sy * sxxx * sxx + sx * sxxy * sxx - sxx * sxy * sxx - sy * sx * sxxxx - sxxx * sxxy * n;
        d3 = n * sxx * sxxy + sx * sxy * sxx + sx * sxxx * sy - sy * sxx * sxx - sx * sx * sxxy - sxy * sxxx * n;

        c = d1/d;
        b = d2/d;
        a = d3/d;

        s = deviationMeasure();
        sigma = Math.sqrt(s/n);
    }


    private double deviationMeasure(){
        double e;
        double s = 0;

        for (int i = 0; i < n; i++) {
            e = p(table.getX()[i])- table.getY()[i];
            s+=e*e;
        }
        return s;
    }

    public double p(double x){
        return a*x*x+b*x+c;
    }

    private double calculateSX() {
        double[] x = table.getX();
        double sx = 0;

        for (int i = 0; i < n; i++) {
            sx += x[i];
        }
        return sx;
    }

    private double calculateSXX() {
        double[] x = table.getX();
        double sxx = 0;

        for (int i = 0; i < n; i++) {
            sxx += x[i] * x[i];
        }
        return sxx;
    }

    private double calculateSXXX() {
        double[] x = table.getX();
        double sxxx = 0;

        for (int i = 0; i < n; i++) {
            sxxx += x[i] * x[i] * x[i];
        }
        return sxxx;
    }

    private double calculateSXXXX() {
        double[] x = table.getX();
        double sxxxx = 0;

        for (int i = 0; i < n; i++) {
            sxxxx += x[i] * x[i] * x[i] * x[i];
        }
        return sxxxx;
    }

    private double calculateSY() {
        double[] y = table.getY();
        double sy = 0;

        for (int i = 0; i < n; i++) {
            sy += y[i];
        }
        return sy;
    }

    private double calculateSXY() {
        double[] x = table.getX();
        double[] y = table.getY();
        double sxy = 0;

        for (int i = 0; i < n; i++) {
            sxy += x[i] * y[i];
        }
        return sxy;
    }

    private double calculateSXXY() {
        double[] x = table.getX();
        double[] y = table.getY();
        double sxxy = 0;

        for (int i = 0; i < n; i++) {
            sxxy += x[i] * x[i] * y[i];
        }
        return sxxy;
    }


    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getS() {
        return s;
    }

    public double getSigma() {
        return sigma;
    }
}
