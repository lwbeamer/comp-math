public class SimpleIterationMethod {


    private Equation equation;
    private CubicEquation cubicEquation;
    private TrigonometricEquation trigonometricEquation;

    private double x1,x2,E;
    final double PHI = (1 + Math.sqrt(5)) / 2;

    public SimpleIterationMethod(Equation equation) {
        this.equation = equation;
        x1 = equation.getL();
        x2 = equation.getR();
        E = equation.getEps();
        switch (equation.getEquationNumber()){
            case(1):
            case(2): {
                this.cubicEquation = (CubicEquation) equation;
                break;
            }
            case(3):{
                this.trigonometricEquation = (TrigonometricEquation) equation;
                break;
            }
        }
    }

    private double f(double x){
        return equation.f(x);
    }

    private double firstDerivative(double x){
        return equation.fFirstDerivative(x);
    }

    private double secondDerivative(double x){
        return equation.fSecondDerivative(x);
    }

    private double fMaxForFunc(double a, double b){
        double x1;
        double x2;

        while (true){
            x1 = b - ((b-a)/PHI);
            x2 = a + ((b-a)/PHI);
            if (firstDerivative(x1)<=firstDerivative(x2)){
                a = x1;
            } else b = x2;
            if (Math.abs(b-a)<E)
                break;
        }

        return (a+b)/2;
    }

    private double fMaxForPHI(double a, double b, double lambda){
        double x1;
        double x2;

        while (true){
            x1 = b - ((b-a)/PHI);
            x2 = a + ((b-a)/PHI);
            if (phiDerivative(x1,lambda)<= phiDerivative(x2,lambda)){
                a = x1;
            } else b = x2;
            if (Math.abs(b-a)<E)
                break;
        }

        return (a+b)/2;
    }

    private double phiDerivative(double x, double lambda){
        return 1+lambda*firstDerivative(x);
    }

    private double chooseX0(double a, double b){

        if (firstDerivative(a)>firstDerivative(b))
            return a;
        else return b;


    }

    public void solve(){
        double lambda = -1/firstDerivative(fMaxForFunc(x1,x2));
        System.out.println("phi'(a) = "+phiDerivative(x1,lambda)+" \"phi'(b) = \""+phiDerivative(x2,lambda));
        double q = phiDerivative(fMaxForPHI(x1,x2,lambda),lambda);
        if (Math.abs(q)<1) {
            double x = x1;
            double fx;
            double x0;
            int count = 0;
            System.out.printf("%s %8s %15s\n", "Итераций", "x", "f(x)");
            do {
                x0 = x;
                x = x + lambda * f(x);
                fx = f(x0);
                System.out.printf("%5d %15.8f %15.8f\n", ++count, x0, fx);
            } while (Math.abs(x - x0) > E || Math.abs(f(x)) > E);
            System.out.println("Корень: " + x);
            System.out.println("Значение в корне: "+f(x));
            System.out.println("Число итераций: " + count);
        } else System.out.println("Не выполнено достаточное условие сходимости");
    }


}
