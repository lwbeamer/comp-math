

public class CubicEquation extends Equation {


    private double a, b, c, d;

    public CubicEquation(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double f(double x) {
        return a * x * x * x + b * x * x + c * x + d;
    }

    @Override
    public double fFirstDerivative(double x) {
        return 3 * a * x * x + 2 * b * x + c;
    }

    @Override
    public double fSecondDerivative(double x) {
        return 6 * a * x + 2 * b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }
}
