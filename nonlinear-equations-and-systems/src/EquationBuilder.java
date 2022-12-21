import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class EquationBuilder {

    Equation equation1 = new CubicEquation(2.3,5.75,-7.41,-10.6);
    Equation equation2 = new CubicEquation(1,0,-1,-1);
    Equation equation3 = new TrigonometricEquation(1,0.5);

    int equationNumber,methodNumber;
    double r,l,eps;


    public Equation read(Path path, Scanner scanner, boolean fileMode) throws IOException {
        System.out.println("Equation read");
        if (fileMode) scanner = new Scanner(path);
        if (!fileMode) {
            System.out.println("Выберете функцию:");
            System.out.println("1: 2.3x³ + 5.75x² - 7.41x- 10.6");
            System.out.println("2: x³ - x - 1");
            System.out.println("3: sin(x) + 0.5");
        }

        equationNumber = Integer.parseInt(scanner.nextLine());

        if (!fileMode) {
            System.out.println("Выберете метод решения:");
            System.out.println("1: Метод хорд");
            System.out.println("2: Метод простой итерации");
        }

        methodNumber = Integer.parseInt(scanner.nextLine());


        if (!fileMode) System.out.println("Введите левую границу интервала:");
        l = Double.parseDouble(scanner.nextLine());

        if (!fileMode) System.out.println("Введите правую границу интервала:");
        r = Double.parseDouble(scanner.nextLine());

        if (!fileMode) System.out.println("Введите погрешность вычислений:");
        eps = Double.parseDouble(scanner.nextLine());
        if (eps < 0.0000000001 || eps > 1) throw new NumberFormatException();


        switch (equationNumber) {
            case (1):{
                System.out.println("Выбрана функция 2.3x³ + 5.75x² - 7.41x- 10.6");
                System.out.println("C интервалом от "+l+" до "+r);
                System.out.println("С погрешностью E = "+eps);
                equation1.setL(l);
                equation1.setR(r);
                equation1.setEps(eps);
                equation1.setMethodNumber(methodNumber);
                equation1.setEquationNumber(equationNumber);
                return equation1;
            }
            case(2):{
                System.out.println("Выбрана функция x³ - x - 1");
                System.out.println("C интервалом от "+l+" до "+r);
                System.out.println("С погрешностью E = "+eps);
                equation2.setL(l);
                equation2.setR(r);
                equation2.setEps(eps);
                equation2.setMethodNumber(methodNumber);
                equation2.setEquationNumber(equationNumber);
                return equation2;
            }
            case(3):{
                System.out.println("Выбрана функция sin(x) + 0.5");
                System.out.println("C интервалом от "+l+" до "+r);
                System.out.println("С погрешностью E = "+eps);
                equation3.setL(l);
                equation3.setR(r);
                equation3.setEps(eps);
                equation3.setMethodNumber(methodNumber);
                equation3.setEquationNumber(equationNumber);
                return equation3;
            }
            default:{
                System.out.println("Вы выбрали неверный номер функции");
                System.exit(0);
            }
        }

        return new CubicEquation(0,0,0,0);
    }
}
