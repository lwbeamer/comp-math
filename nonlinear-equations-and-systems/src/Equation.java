public abstract class Equation {
    private double r,l,eps;
    private int methodNumber, equationNumber;


    public abstract double f(double x);
    public abstract double fFirstDerivative(double x);
    public abstract double fSecondDerivative(double x);


    public int getEquationNumber() {
        return equationNumber;
    }

    public void setEquationNumber(int equationNumber) {
        this.equationNumber = equationNumber;
    }

    public int getMethodNumber() {
        return methodNumber;
    }

    public void setMethodNumber(int methodNumber) {
        this.methodNumber = methodNumber;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }
}
