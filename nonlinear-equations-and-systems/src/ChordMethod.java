public class ChordMethod {

    private Equation equation;
    private CubicEquation cubicEquation;
    private TrigonometricEquation trigonometricEquation;

    private double x1,x2,E;

    public ChordMethod(Equation equation) {
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




    public void solve() { // метод хорд
        int n = 0;
        double xi_1, xi_0, c;
        if (f(x1) * f(x2) < 0) {
            do {
                xi_0 = (x1 * f(x2) - x2 * f(x1))
                        / (f(x2) - f(x1));

                if (f(x1)*f(xi_0)<0){
                    x2 = xi_0;
                } else{
                    x1 = x2;
                    x2 = xi_0;
                }
                n++;
                xi_1 = (x1 * f(x2) - x2 * f(x1))
                        / (f(x2) - f(x1));
            } while ((f(xi_1)>E) || (Math.abs(xi_1 - xi_0) > E));
            System.out.println("Корень уравнения: "+xi_1);
            System.out.println("Значение функции в корне: "+f(xi_1));
            System.out.println("Итераций: " + n);
        } else {
            System.out.println("Корня на данном промежутке нет");
        }
    }

    public void setEquation(Equation equation) {
        this.equation = equation;
    }
}
