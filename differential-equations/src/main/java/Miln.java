import java.util.Vector;

public class Miln {

    private Equation equation;
    Vector<Double> x =  new Vector<>();
    Vector<Double> y = new Vector<>();
    Vector<Double> exactValues = new Vector<>();

    Vector<Double> rungeErrors = new Vector<>();


    public Miln(Equation equation) {
        this.equation = equation;
    }


    public void solve(){
        Equation tmpEquation = new Equation(equation.getA(),equation.getA()+3*equation.getH(), equation.getY0(), equation.getEps(), equation.getH(), equation.getEqNumber());
        ModifiedEuler modifiedEuler = new ModifiedEuler(tmpEquation);
        modifiedEuler.solve(false);
        x = modifiedEuler.getForMilnX();
        y = modifiedEuler.getForMilnY();

        rungeErrors = modifiedEuler.getRungeErrorsForMiln();

        double h = equation.getH();
        int n = (int) ((equation.getB()-equation.getA())/h);

        for (int i = 4; i < n+1; i++){
            double curX = x.lastElement()+h;
            curX = curX * 10000;
            int tmp = (int) Math.round(curX);
            curX = (double) tmp / 10000;
            double yProgn = y.elementAt(i-4) + (4*h/3)*(2*equation.func(x.elementAt(i-3), y.elementAt(i-3))- equation.func(x.elementAt(i-2), y.elementAt(i-2))+2*equation.func(x.elementAt(i-1), y.elementAt(i-1)));
            double fProgn = equation.func(curX,yProgn);
            double yCorr = y.elementAt(i-2)+(h/3)*(equation.func(x.elementAt(i-2),y.elementAt(i-2))+4*equation.func(x.elementAt(i-1),y.elementAt(i-1))+fProgn);
            while (Math.abs(yCorr-yProgn)/15 > equation.getEps()){
                yProgn = yCorr;
                fProgn = equation.func(curX,yCorr);
                yCorr = y.elementAt(i-2)+(h/3)*(equation.func(x.elementAt(i-2),y.elementAt(i-2))+4*equation.func(x.elementAt(i-1),y.elementAt(i-1))+fProgn);
                System.out.println("yCorr = " + yCorr);
            }
            rungeErrors.add(Math.abs(yCorr-yProgn)/15);
            x.add(curX);
            y.add(yCorr);
        }

        for(int i = 0; i < x.size(); i++){
            exactValues.add(equation.exactValue(x.elementAt(i)));
        }


        System.out.println("----------------------------------------------------------------------------");
        System.out.println("| i  |       x     |     y(x)    |    f(x,y)   |   по Рунге  | Точное знач |");
        System.out.println("----------------------------------------------------------------------------");
        for (int i = 0; i < x.size(); i++) {
            System.out.printf("| %-3d| %-11.7f | %-11.7f | %-11.7f | %-11.9f | %-11.7f |\n", i, x.elementAt(i), y.elementAt(i), equation.func(x.elementAt(i), y.elementAt(i)), rungeErrors.elementAt(i),exactValues.elementAt(i));
            System.out.println("----------------------------------------------------------------------------");
        }

        Chart.setEquation(equation);
        Chart.setInterval(equation.getA(),equation.getB());
        Chart.setPoints(x,y);
        Chart.drawChart();

    }


}
