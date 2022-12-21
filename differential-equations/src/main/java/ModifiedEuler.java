import java.util.Vector;

public class ModifiedEuler {

    private Equation equation;
    Vector<Double> x1 =  new Vector<>();
    Vector<Double> y1 = new Vector<>();
    Vector<Double> x2 = new Vector<>();
    Vector<Double> y2 = new Vector<>();
    Vector<Double> forMilnX = new Vector<>();
    Vector<Double> forMilnY = new Vector<>();
    Vector<Double> answerX = new Vector<>();
    Vector<Double> answerY = new Vector<>();
    Vector<Double> rungeErrorsAll = new Vector<>();
    Vector<Double> rungeErrorsAnswer = new Vector<>();
    Vector<Double> rungeErrorsForMiln = new Vector<>();
    Vector<Double> exactValues = new Vector<>();


    public ModifiedEuler(Equation equation) {
        this.equation = equation;
    }

    public void solve(boolean toPrint){
        int n = 0;
        double h = equation.getH();
        while (true){
            x1.add(equation.getA());
            y1.add(equation.getY0());
            x2.add(equation.getA());
            y2.add(equation.getY0());

            n = (int) ((equation.getB()-equation.getA())/h);

            for (int i = 0; i < n; i++){
                double xPrev = x1.lastElement();
                double yPrev = y1.lastElement();
                double xCur = xPrev + h;
                xCur =xCur*10000;
                int tmp = (int) Math.round(xCur);
                xCur = (double) tmp/10000;
                double yCur = yPrev + (h/2)*(equation.func(xPrev,yPrev)+equation.func(xCur,yPrev+h*equation.func(xPrev,yPrev)));
                x1.add(xCur);
                y1.add(yCur);
            }

            h = h/2;

            for (int i = 0; i < 2*n; i++){
                double xPrev = x2.lastElement();
                double yPrev = y2.lastElement();
                double xCur = xPrev + h;
                xCur =xCur*10000;
                int tmp = (int) Math.round(xCur);
                xCur = (double) tmp/10000;
                double yCur = yPrev + (h/2)*(equation.func(xPrev,yPrev)+equation.func(xCur,yPrev+h*equation.func(xPrev,yPrev)));
                x2.add(xCur);
                y2.add(yCur);
            }

            if (checkRunge(n)){
                break;
            } else{
                x1.clear();
                x2.clear();
                y1.clear();
                y2.clear();
                rungeErrorsAll.clear();
            }
        }

        //------------------------------------------------------------------------------------


//        n = (int) ((equation.getB()-equation.getA())/ equation.getH());
//
//        double curX = equation.getA();
//
//        for (int i = 0; i < n+1 ; i++) {
//            for (int j = 0; j < x1.size(); j++) {
//                if (curX == x1.elementAt(j)) {
//                    answerX.add(curX);
//                    answerY.add(y1.elementAt(j));
//                    rungeErrorsAnswer.add(rungeErrorsAll.elementAt(j));
//                    exactValues.add(equation.exactValue(curX));
//                    break;
//                }
//            }
//            curX += equation.getH();
//            curX = curX * 10000;
//            int tmp = (int) Math.round(curX);
//            curX = (double) tmp / 10000;
//        }
//
//
//        if (toPrint) {
//            System.out.println("----------------------------------------------------------------------------");
//            System.out.println("| i  |       x     |     y(x)    |    f(x,y)   |   по Рунге  | Точное знач |");
//            System.out.println("----------------------------------------------------------------------------");
//            for (int i = 0; i < answerX.size(); i++) {
//                System.out.printf("| %-3d| %-11.7f | %-11.7f | %-11.7f | %-11.9f | %-11.7f |\n", i, answerX.elementAt(i), answerY.elementAt(i), equation.func(answerX.elementAt(i), answerY.elementAt(i)), rungeErrorsAnswer.elementAt(i),exactValues.elementAt(i));
//                System.out.println("----------------------------------------------------------------------------");
//            }
//
//            Chart.setEquation(equation);
//            Chart.setInterval(equation.getA(),equation.getB());
//            Chart.setPoints(answerX,answerY);
//            Chart.drawChart();
//        }

        //-------------------------------------------------------------------------------------


        for (int i = 0; i < x1.size(); i++){
            exactValues.add(equation.exactValue(x1.elementAt(i)));
        }

        if (toPrint) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("| i  |       x     |     y(x)    |    f(x,y)   |   по Рунге  | Точное знач |");
            System.out.println("----------------------------------------------------------------------------");
            for (int i = 0; i < x1.size(); i++) {
                System.out.printf("| %-3d| %-11.7f | %-11.7f | %-11.7f | %-11.9f | %-11.7f |\n", i, x1.elementAt(i), y1.elementAt(i), equation.func(x1.elementAt(i), y1.elementAt(i)), rungeErrorsAll.elementAt(i),exactValues.elementAt(i));
                System.out.println("----------------------------------------------------------------------------");
            }

            Chart.setEquation(equation);
            Chart.setInterval(equation.getA(),equation.getB());
            Chart.setPoints(x1,y1);
            Chart.drawChart();
        }

        //--------------------------------------------------------------------------------------------
    }

    public boolean checkRunge(int n){
        boolean t = true;
        double max = 0;


        for (int i = 0; i<n; i++){
            if (Math.abs(y2.elementAt(i*2)-y1.elementAt(i))/3 > equation.getEps()){
                t = false;
            }
            if (Math.abs(y2.elementAt(i*2)-y1.elementAt(i))/3 > max){
                max = Math.abs(y2.elementAt(i*2)-y1.elementAt(i))/3;
            }
        }

        if (t){
            for (int i = 0; i<=n; i++){
                rungeErrorsAll.add(Math.abs(y2.elementAt(i*2)-y1.elementAt(i))/3);
            }
        }
        return t;
    }


    public Vector<Double> getForMilnX() {
        double curX = equation.getA();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < x1.size(); j++) {
                if (curX == x1.elementAt(j)){
                    forMilnX.add(curX);
                    forMilnY.add(y1.elementAt(j));
                    rungeErrorsForMiln.add(rungeErrorsAll.elementAt(j));
                }
            }
            curX += equation.getH();
            curX = curX * 10000;
            int tmp = (int) Math.round(curX);
            curX = (double) tmp / 10000;
        }

        return forMilnX;
    }

    public Vector<Double> getForMilnY() {
        return forMilnY;
    }

    public Vector<Double> getRungeErrorsForMiln() {
        return rungeErrorsForMiln;
    }
}
