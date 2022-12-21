public class Equation {
    private double a,b,eps,h,y0;
    private int eqNumber;


    public Equation(double a, double b, double y0, double eps, double h, int eqNumber) {
        this.a = a;
        this.b = b;
        this.y0 = y0;
        this.eps = eps;
        this.h = h;
        this.eqNumber = eqNumber;
    }

    public double func(double x, double y){

        if (eqNumber == 1) return func1(x,y);
        else return func2(x,y);

    }

    public double exactValue(double x){
        if (eqNumber == 1) return exactValue1(x);
        else return exactValue2(x);
    }

    //y+(1+x)y^2
    private double func1(double x, double y){
        return (1+x)*y*y+y;
    }

    //(x+1)^2-2y
    private double func2(double x, double y){
        return (x+1)*(x+1)-2*y;
    }

    private double exactValue1(double x){
        return -1/x;
    }

    private double exactValue2(double x){
        return 0.75*Math.exp(-2*x)+0.5*x*x+0.5*x+0.25;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getEps() {
        return eps;
    }

    public double getH() {
        return h;
    }

    public double getY0() {
        return y0;
    }

    public int getEqNumber() {
        return eqNumber;
    }
}
