import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        TableReader tableReader = new TableReader();
        Table table = null;

        System.out.println("Программа запущена");
        System.out.println("Каким способом вы хотите ввести исходные данные?");
        System.out.println("1 - Набор точек");
        System.out.println("2 - Функция");


        Scanner scanner = new Scanner(System.in);

        Function function = new Function();
        String inputWay = scanner.nextLine();
        boolean funcInput = true;

        try {
            if (Integer.parseInt(inputWay) == 1) {
                funcInput = false;
            } else if (Integer.parseInt(inputWay) == 2) {
                System.out.println("Выберите функцию:");
                System.out.println("1 - y = sin(x)");
                System.out.println("2 - y = e^x");
                System.out.println("3 - y = x^2 + 2*x + 3");
                String funcNumber = scanner.nextLine();
                if (funcNumber.equals("1") || funcNumber.equals("2") || funcNumber.equals("3")){
                    function.setNumber(Integer.parseInt(funcNumber));
                } else {
                    System.out.println("Номер функции выбран неверно");
                    System.exit(0);
                }
            } else {
                System.out.println("Выбран неверный режим ввода. Доступные режимы:\n1 - Набор точек\n2 - Функция");
                System.exit(0);
            }
        } catch (NumberFormatException e){
            System.out.println("Режим ввода и номер функции должны быть представлены числом");
            System.exit(0);
        }


        table = tableReader.read(scanner,funcInput, function);

        System.out.println("Каким методом хотите провести интерполяцию?");
        System.out.println("1 - с помощью многочлена Лагранжа");
        System.out.println("2 - с помощью многочлена Гаусса");

        String strMethod = scanner.nextLine();


        System.out.println("Введите аргумент: ");
        String strArg = scanner.nextLine();
        double arg = Double.parseDouble(strArg);

        System.out.println("Xi:");
        table.printX();
        System.out.println("Yi");
        table.printY();
        System.out.println();

        Lagranzh lagranzh = new Lagranzh(table);
        Gauss gauss = new Gauss(table);

        Chart.setValues(lagranzh,gauss);

        if (strMethod.equals("1")){
            System.out.println("Приближенное значение функции y = f(x) при x = "+arg+" с помощью многочлена Лагранжа: "+lagranzh.calculate(arg));
            Chart.setAnswer(arg, lagranzh.calculate(arg));
            Chart.drawFunctionForLagranzh();

        } else if (strMethod.equals("2")){
            System.out.println("Приближенное значение функции y = f(x) при x = "+arg+" с помощью многочлена Гаусса: "+gauss.calculate(arg));
            gauss.printMatrix();
            Chart.setAnswer(arg, gauss.calculate(arg));
            Chart.drawFunctionForGauss();
        } else{
            System.out.println("Метод решения выбран неверно");
            System.exit(0);
        }

    }
}
