public class NLESystem {
    private double a1, a2, b1, b2, eps;


    //0.1x + x + 0.2y^2 - 0.3
    //0.2y^2=-0.1x-x+0.3
    //y^2=(-0.1x-x+0.3)/0.2
    //y=sqrt(-0.1x-x+0.3)
    //sin(x₁) + x₂

    public double getYFromF1(double x){
        return Math.sqrt((-0.1*x-x+0.3)/0.2);
        //return 0.1*x+x+0.2*x*x-0.3;
    }

    public double getYFromF2(double x){
        return -Math.sin(x);
    }

    public double f1(double x1, double x2){
        return 0.1*x1*x1 + x1 + 0.2*x2*x2 - 0.3;
    }

    public double f2(double x1, double x2){
        return Math.sin(x1)+x2;
    }

    public double PHI1(double x1, double x2) {
        return -(0.1 * x1 * x1) - (0.2 * x2 * x2) + 0.3;
    }

    public double PHI2(double x1, double x2) {
        return -Math.sin(x1);
    }

    public double PHI1DerivativeX1(double x1){
        return -0.2*x1;
    }

    public double PHI1DerivativeX2(double x2){
        return -0.4*x2;
    }

    public double PHI2DerivativeX1(double x1){
        return -Math.cos(x1);
    }

    public double PHI2DerivativeX2(double x2){
        return 0;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public double getB1() {
        return b1;
    }

    public void setB1(double b1) {
        this.b1 = b1;
    }

    public double getB2() {
        return b2;
    }

    public void setB2(double b2) {
        this.b2 = b2;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }
}
