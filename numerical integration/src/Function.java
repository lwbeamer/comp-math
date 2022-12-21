public class Function {

    private int number;
    private int methodNumber;
    private double a,b,c,d;
    private double eps,left,right;


    public Function(int number, double a, double b, double c, double d) {
        this.number = number;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double F(double x){
        return a*x*x*x + b*x*x + c*x +d;
    }

    public double certainIntegral(double a, double b){
        return this.a*(b*b*b*b/4-a*a*a*a/4) + this.b*(b*b*b/3-a*a*a/3) + this.c*(b*b/2-a*a/2) + this.d*(b-a);
    }

    public int getMethodNumber() {
        return methodNumber;
    }

    public void setMethodNumber(int methodNumber) {
        this.methodNumber = methodNumber;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }
}
