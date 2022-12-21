import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        TableReader tableReader = new TableReader();
        Table table = null;

        System.out.println("Программа запущена.\nВведите -f для чтения из файла.\nВведите -k для чтения с клавиатуры.");

        Scanner scanner = new Scanner(System.in);

        String inputMode = scanner.nextLine();

        System.out.println("Куда хотите вывести результат?\n1 - в файл\n2 - в консоль");

        String stringOutputMode = scanner.nextLine();

        boolean outputFileMode = false;

        try {
            if (Integer.parseInt(stringOutputMode) == 1) {
                outputFileMode = true;
                Printer.clearFile();
            } else if (Integer.parseInt(stringOutputMode) != 2) {
                System.out.println("Выбран неверный режим вывода. Доступные режимы:\n1 - вывод в файл\n2 - вывод в консоль");
                System.exit(0);
            }
        } catch (NumberFormatException e){
            System.out.println("Режим ввода должен быть представлен числом");
            System.exit(0);
        }

        try {
            switch (inputMode) {
                case ("-f"):  {
                    System.out.println("Режим чтения из файла. Введите путь до файла:");
                    String path = scanner.nextLine();
                    table = tableReader.read(Paths.get(path), scanner, true,outputFileMode);
                    break;
                }
                case ("-k"): {
                    System.out.println("Режим чтения с клавиатуры");
                    table = tableReader.read(null, scanner, false,outputFileMode);
                    break;
                }
                default: {
                    System.out.println("Неверный режим.\nДоступные режимы:\n [-f] для чтения из файла\n[-k] для ввода с клавиатуры");
                    System.exit(0);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.err.println("Неверное количество аргументов или ключ отсутствует");
        } catch (NumberFormatException e) {
            System.err.println("Похоже, что введенные данные должны быть представлены числом");
        } catch (NoSuchElementException e) {
            System.err.println("Неправильный формат текста в файле");
        } catch (NullPointerException e) {
            System.err.println("Похоже вы ничего не передали. Проверьте ввод");
        } catch (NoSuchFileException e){
            System.err.println("Не найден указанный файл");
        }
        catch (IOException e) {
            e.printStackTrace();
        }




        LinearFunc linearFunc = new LinearFunc(table);
        SqrFunc sqrFunc = new SqrFunc(table);
        CubicFunc cubicFunc = new CubicFunc(table);
        PowerFunc powerFunc = new PowerFunc(table);
        ExpFunc expFunc = new ExpFunc(table);
        LogFunc logFunc = new LogFunc(table);


        Printer.println("----------------------------------------------------------------------------------------------------",outputFileMode);
        Printer.println("|Вид функции                     |     a    |     b    |     c    |     d    |     s    |   sigma  |",outputFileMode);
        Printer.println("----------------------------------------------------------------------------------------------------",outputFileMode);
        Printer.print(String.format("|y = ax + b                      | %-9.5f| %-9.5f|     -    |     -    | %-9.5f| %-9.5f|\n",linearFunc.getA(),linearFunc.getB(),linearFunc.getS(),linearFunc.getSigma()),outputFileMode);
        Printer.println("----------------------------------------------------------------------------------------------------",outputFileMode);
        Printer.print(String.format("|y = ax^b                        | %-9.5f| %-9.5f|     -    |     -    | %-9.5f| %-9.5f|\n",powerFunc.getA(),powerFunc.getB(),powerFunc.getS(),powerFunc.getSigma()),outputFileMode);
        Printer.println("----------------------------------------------------------------------------------------------------",outputFileMode);
        Printer.print(String.format( "|y = ae^(bx)                     | %-9.5f| %-9.5f|     -    |     -    | %-9.5f| %-9.5f|\n",expFunc.getA(),expFunc.getB(),expFunc.getS(),expFunc.getSigma()),outputFileMode);
        Printer.println("----------------------------------------------------------------------------------------------------",outputFileMode);
        Printer.print(String.format( "|y = aln(x) + b                  | %-9.5f| %-9.5f|     -    |     -    | %-9.5f| %-9.5f|\n",logFunc.getA(),logFunc.getB(),logFunc.getS(),logFunc.getSigma()),outputFileMode);
        Printer.println("----------------------------------------------------------------------------------------------------",outputFileMode);
        Printer.print( String.format("|y = ax^2 + bx + c               | %-9.5f| %-9.5f| %-9.5f|     -    | %-9.5f| %-9.5f|\n",sqrFunc.getA(),sqrFunc.getB(),sqrFunc.getC(),sqrFunc.getS(),sqrFunc.getSigma()),outputFileMode);
        Printer.println("----------------------------------------------------------------------------------------------------",outputFileMode);
        Printer.print(String.format( "|y = ax^3 + bx^2 + cx + d        | %-9.5f| %-9.5f| %-9.5f| %-9.5f| %-9.5f| %-9.5f|\n",cubicFunc.getA(),cubicFunc.getB(),cubicFunc.getC(),cubicFunc.getD(),cubicFunc.getS(),cubicFunc.getSigma()),outputFileMode);
        Printer.println("----------------------------------------------------------------------------------------------------",outputFileMode);

        double[] sigmaArr = new double[6];
        sigmaArr[0] = linearFunc.getSigma();
        sigmaArr[1] = powerFunc.getSigma();
        sigmaArr[2] = expFunc.getSigma();
        sigmaArr[3] = logFunc.getSigma();
        sigmaArr[4] = sqrFunc.getSigma();
        sigmaArr[5] = cubicFunc.getSigma();


        double min = 1000000;
        int minInd = 0;
        for (int i = 0; i < sigmaArr.length; i++){
            if (sigmaArr[i]<min){
                min = sigmaArr[i];
                minInd = i+1;
            }
        }


        switch (minInd){
            case (1): {
                Printer.println("Наилучшая аппроксимирующая функция - Линейная функция",outputFileMode);
                Printer.println(linearFunc.getA()+" * x + "+linearFunc.getB(),outputFileMode);
                Printer.println("sigma = "+linearFunc.getSigma(),outputFileMode);
//                Printer.println("Коэффициент корреляции Пирсона: "+linearFunc.getCorrelation(),outputFileMode);
                break;
            }
            case (2): {
                Printer.println("Наилучшая аппроксимирующая функция - Степенная функция",outputFileMode);
                Printer.println(powerFunc.getA()+" * x ^ "+powerFunc.getB(),outputFileMode);
                Printer.println("sigma = "+powerFunc.getSigma(),outputFileMode);
                break;
            }
            case (3): {
                Printer.println("Наилучшая аппроксимирующая функция - Экспоненциальная функция",outputFileMode);
                Printer.println(expFunc.getA()+" * e ^ ("+expFunc.getB()+" * x)",outputFileMode);
                Printer.println("sigma = "+expFunc.getSigma(),outputFileMode);
                break;
            }
            case (4): {
                Printer.println("Наилучшая аппроксимирующая функция - Логарифмическая функция",outputFileMode);
                Printer.println(logFunc.getA()+" * ln(x) + "+logFunc.getB(),outputFileMode);
                Printer.println("sigma = "+logFunc.getSigma(),outputFileMode);
                break;
            }
            case (5): {
                Printer.println("Наилучшая аппроксимирующая функция - Полиномиальная функция 2-й степени",outputFileMode);
                Printer.println(sqrFunc.getA()+" * x^2 + "+sqrFunc.getB()+" * x + "+sqrFunc.getC(),outputFileMode);
                Printer.println("sigma = "+sqrFunc.getSigma(),outputFileMode);
                break;
            }
            case (6): {
                Printer.println("Наилучшая аппроксимирующая функция - Полиномиальная функция 3-й степени",outputFileMode);
                Printer.println(cubicFunc.getA()+" * x^3 + "+cubicFunc.getB()+" * x^2 + "+cubicFunc.getC()+" * x + "+ cubicFunc.getD(),outputFileMode);
                Printer.println("sigma = "+cubicFunc.getSigma(),outputFileMode);
                break;
            }
            default: {
            }
        }

        if (outputFileMode) System.out.println("Успех. Результат записан в файл.");
        Chart.setValues(linearFunc,powerFunc,expFunc,logFunc,sqrFunc,cubicFunc);
        Chart.drawFunction();


        Printer.println("Коэффициент корреляции Пирсона: "+linearFunc.getCorrelation(),outputFileMode);

    }

}
