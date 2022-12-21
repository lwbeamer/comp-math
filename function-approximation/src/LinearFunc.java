public class LinearFunc {


    private Table table;
    private int n;
    private double sx;
    private double sxx;
    private double sy;
    private double sxy;
    private double d,d1,d2,a,b,s,sigma;
    private double deltaX, deltaY;
    private double correlation;


    public LinearFunc(Table table) {
        this.table = table;
        n = table.getN();
        sx = calculateSX();
        sxx = calculateSXX();
        sy = calculateSY();
        sxy = calculateSXY();

        deltaX = sx/n;
        deltaY = sy/n;

        d = sxx*n-sx*sx;
        d1 = sxy*n-sx*sy;
        d2 = sxx*sy-sx*sxy;

        a = d1/d;
        b = d2/d;

        s = deviationMeasure();
        sigma = Math.sqrt(s/n);

        correlation = calculateCorrelation(deltaX,deltaY);
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
        return a*x+b;
    }

    private double calculateCorrelation(double deltaX, double deltaY){
        double[] x = table.getX();
        double[] y = table.getY();

        double dx,dy,sqrdx=0,sqrdy=0,dxdy,numerator=0;


        for (int i = 0; i < n; i++) {
            dx = x[i]-deltaX;
            dy = y[i]-deltaY;
            dxdy = dx*dy;
            numerator +=dxdy;
            sqrdx+=dx * dx;
            sqrdy+=dy * dy;
        }

        return numerator/Math.sqrt(sqrdx*sqrdy);
    }

    private double calculateSX(){
        double[] x = table.getX();
        double sx = 0;

        for (int i = 0; i < n; i++) {
            sx+=x[i];
        }
        return sx;
    }

    private double calculateSXX(){
        double[] x = table.getX();
        double sxx = 0;

        for (int i = 0; i < n; i++) {
            sxx+=x[i]*x[i];
        }
        return sxx;
    }

    private double calculateSY(){
        double[] y = table.getY();
        double sy = 0;

        for (int i = 0; i < n; i++) {
            sy+=y[i];
        }
        return sy;
    }

    private double calculateSXY(){
        double[] x = table.getX();
        double[] y = table.getY();
        double sxy = 0;

        for (int i = 0; i < n; i++) {
            sxy+=x[i]*y[i];
        }
        return sxy;
    }

    public double getCorrelation() {
        return correlation;
    }

    public double getS() {
        return s;
    }

    public double getSigma() {
        return sigma;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}
