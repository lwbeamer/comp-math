public class ExpFunc {

    private Table table;
    private int n;
    private double sx;
    private double sxx;
    private double sy;
    private double sxy;
    private double d,d1,d2,s,A,B,a,b,sigma;
    private double[] lnY;

    public ExpFunc(Table table) {
        this.table = table;

        n = table.getN();

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
        return a*Math.pow(Math.E,b*x);
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
            sx+=table.getX()[i];
        }
        return sx;
    }

    private double calculateSXX(){
        double sxx = 0;

        for (int i = 0; i < n; i++) {
            sxx+=table.getX()[i]*table.getX()[i];
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
            sxy+=table.getX()[i]*lnY[i];
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
