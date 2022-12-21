public class PowerFunc {

    private Table table;
    private int n;
    private double sx;
    private double sxx;
    private double sy;
    private double sxy;
    private double d,d1,d2,A,B,s,a,b,sigma;
    private double[] lnX;
    private double[] lnY;


    public PowerFunc(Table table) {
        this.table = table;

        n = table.getN();

        lnX = getLnX();
        lnY = getLnY();


        sx = calculateSX();
        sxx = calculateSXX();
        sy = calculateSY();
        sxy = calculateSXY();

        d = sxx*n-sx*sx;
        d1 = sxy*n-sx*sy;
        d2 = sxx*sy-sx*sxy;

        B = d1/d;
        A = d2/d;

        a = Math.pow(Math.E,A);
        b = B;

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
        return a*Math.pow(x,b);
    }


    public double[] getLnX() {
        double a[] = new double[n];

        for (int i = 0; i < a.length; i++) {
            a[i] = Math.log(table.getX()[i]);
        }

        return a;
    }

    public double[] getLnY() {
        double a[] = new double[n];

        for (int i = 0; i < a.length; i++) {
            a[i] = Math.log(table.getY()[i]);
        }

        return a;
    }

    private double calculateSX(){
        double sx = 0;

        for (int i = 0; i < n; i++) {
            sx+=lnX[i];
        }
        return sx;
    }

    private double calculateSXX(){
        double sxx = 0;

        for (int i = 0; i < n; i++) {
            sxx+=lnX[i]*lnX[i];
        }
        return sxx;
    }

    private double calculateSY(){
        double sy = 0;

        for (int i = 0; i < n; i++) {
            sy+=lnY[i];
        }
        return sy;
    }

    private double calculateSXY(){
        double sxy = 0;

        for (int i = 0; i < n; i++) {
            sxy+=lnX[i]*lnY[i];
        }
        return sxy;
    }

    public double getS() {
        return s;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getSigma() {
        return sigma;
    }
}
