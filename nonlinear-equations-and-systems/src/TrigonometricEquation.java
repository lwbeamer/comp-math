public class TrigonometricEquation extends Equation{

    private double a,b;

    public TrigonometricEquation(double a, double b) {
        this.a = a;
        this.b = b;
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

    @Override
    public double f(double x) {
        return Math.sin(x)+0.5;
    }

    @Override
    public double fFirstDerivative(double x) {
        return Math.cos(x);
    }

    @Override
    public double fSecondDerivative(double x) {
        return 0;
    }
}
