import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FunctionBuilder {

    Function function1 = new Function(1,2,-2,7,-14);
//    Function function2 = new Function(2,-1,-2,3,23);
    Function function2 = new Function(2,-2,-4,8,-4);
    Function function3 = new Function(3,1,-2,-5,24);

    int funcNumber,methodNumber;
    double a,b,eps;

    public Function read(Path path, Scanner scanner, boolean fileMode) throws IOException {
        if (fileMode) scanner = new Scanner(path);
        if (!fileMode) {
            System.out.println("Выберете функцию:");
            System.out.println("1: 2x³ - 2x² + 7x- 14");
//            System.out.println("2: -x³ - 2x² + 3x + 23");
            System.out.println("2: -2x³ - 4x² + 8x - 4");
            System.out.println("3: x³ - 2x² -5x + 24");
        }

        funcNumber = Integer.parseInt(scanner.nextLine());

        if (!fileMode) {
            System.out.println("Выберете метод решения:");
            System.out.println("1: Трапеции");
            System.out.println("2: Прямоугольников");
        }

        methodNumber = Integer.parseInt(scanner.nextLine());

        if (!fileMode) System.out.println("Введите левую границу a:");
        a = Double.parseDouble(scanner.nextLine());

        if (!fileMode) System.out.println("Введите правую границу b:");
        b = Double.parseDouble(scanner.nextLine());

        if (!fileMode) System.out.println("Введите погрешность вычислений:");
        eps = Double.parseDouble(scanner.nextLine());
        if (eps < 0.0000000001 || eps > 1) throw new NumberFormatException();

        switch (funcNumber) {
            case (1) -> {
                System.out.println("Выбрана функция 2x³ - 2x² + 7x- 14");
                System.out.println("C интервалом от " + a + " до " + b);
                System.out.println("С погрешностью E = " + eps);
                function1.setEps(eps);
                function1.setRight(b);
                function1.setLeft(a);
                function1.setMethodNumber(methodNumber);
                return function1;
            }
            case (2) -> {
//                System.out.println("Выбрана функция -x³ - 2x² + 3x + 23");
                System.out.println("Выбрана функция -2x³ - 4x² + 8x - 4");
                System.out.println("C интервалом от " + a + " до " + b);
                System.out.println("С погрешностью E = " + eps);
                function2.setEps(eps);
                function2.setRight(b);
                function2.setLeft(a);
                function2.setMethodNumber(methodNumber);
                return function2;
            }
            case (3) -> {
                System.out.println("Выбрана функция x³ - 2x² -5x + 24");
                System.out.println("C интервалом от " + a + " до " + b);
                System.out.println("С погрешностью E = " + eps);
                function3.setEps(eps);
                function3.setRight(b);
                function3.setLeft(a);
                function3.setMethodNumber(methodNumber);
                return function3;
            }
            default -> {
                System.out.println("Вы выбрали неверный номер функции");
                System.exit(0);
            }
        }

        return new Function(0,0,0,0,0);
    }

}
