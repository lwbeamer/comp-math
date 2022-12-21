import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Программа запущена");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите уравнение:");
        System.out.println("1: y' = y + (1+x)*y^2 на отрезке [1:1,5] с начальным условием y(1) = -1");
        System.out.println("2: y' = (1+x)^2 - 2*y на отрезке [0:5] с начальным условием y(0) = 1");

        String strEqNum = scanner.nextLine();
        int eqNum=0;

        if (strEqNum.equals("1")){
            eqNum = 1;
        } else if (strEqNum.equals("2")){
            eqNum = 2;
        } else {
            System.out.println("Номер уравнения это число 1 или 2");
            System.exit(0);
        }

        double h=0,eps=0;

        System.out.println("Введите h:");
        String strH = scanner.nextLine();
        System.out.println("Введите точность:");
        String strEps = scanner.nextLine();


        try{
            h = Double.parseDouble(strH);
            eps = Double.parseDouble(strEps);
        } catch (NumberFormatException e){
            System.out.println("Шаг и точность должны быть представлены числом");
            System.exit(0);
        }

        Equation equation = null;

        if (eqNum == 1){
            equation = new Equation(1,1.5,-1,eps,h,eqNum);
        } else if (eqNum == 2){
            equation = new Equation(0,5,1,eps,h,eqNum);
        }



        System.out.println("Выберите метод решения ОДУ:");
        System.out.println("1: Усовершенствованный метод Эйлера");
        System.out.println("2: Метод Милна");

        String strMode = scanner.nextLine();

        if (strMode.equals("1")){
            ModifiedEuler modifiedEuler = new ModifiedEuler(equation);
            modifiedEuler.solve(true);
        } else if (strMode.equals("2")){
            Miln miln = new Miln(equation);
            miln.solve();
        } else {
            System.out.println("Номер метода это число 1 или 2");
            System.exit(0);
        }

    }
}

//
//1
//        Введите h:
//        0.1
//        Введите точность:
//        0.01
//        Выберите метод решения ОДУ:
//        1: Усовершенствованный метод Эйлера
//        2: Метод Милна
//        1
//        ----------------------------------------------------------------------------
//        | i  |       x     |     y(x)    |    f(x,y)   |   по Рунге  | Точное знач |
//        ----------------------------------------------------------------------------
//        | 0  | 1,0000000   | -1,0000000  | 1,0000000   | 0,000000000 | -1,0000000  |
//        ----------------------------------------------------------------------------
//        | 1  | 1,1000000   | -0,9099500  | 0,8288689   | 0,000220128 | -0,9090909  |
//        ----------------------------------------------------------------------------
//        | 2  | 1,2000000   | -0,8346160  | 0,6978686   | 0,000328709 | -0,8333333  |
//        ----------------------------------------------------------------------------
//        | 3  | 1,3000000   | -0,7706932  | 0,5954333   | 0,000374781 | -0,7692308  |
//        ----------------------------------------------------------------------------
//        | 4  | 1,4000000   | -0,7157910  | 0,5138651   | 0,000385720 | -0,7142857  |
//        ----------------------------------------------------------------------------
//        | 5  | 1,5000000   | -0,6681388  | 0,4478848   | 0,000377184 | -0,6666667