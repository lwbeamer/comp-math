import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        EquationBuilder equationBuilder = new EquationBuilder();
        SystemBuilder systemBuilder = new SystemBuilder();
        NLESystem nleSystem = null;
        Equation equation = null;
        Scanner scanner = new Scanner(System.in);
        String target = null;

        System.out.println("Программа запущена.\n" +
                "Введите -f для чтения из файла.\n" +
                "Введите -k для чтения с клавиатуры.");


        String mode = scanner.nextLine();
        try {
            switch (mode) {
                case ("-f"): {
                    System.out.println("Режим чтения из файла. Что вы хотите решить?");
                    System.out.println("1 - Уравнение");
                    System.out.println("2 - Систему");
                    target = scanner.nextLine();
                    System.out.println("Режим чтения из файла. Введите путь до файла:");
                    String path = scanner.nextLine();
                    if (target.equals("1")) {
                        System.out.println("target equals 1 filemode");
                        equation = equationBuilder.read(Paths.get(path), scanner, true);
                    }
                    else nleSystem = systemBuilder.read(Paths.get(path),scanner,true);
                    break;
                }
                case ("-k"): {
                    System.out.println("Режим чтения с клавиатуры");
                    System.out.println("Что вы хотите решить?");
                    System.out.println("1 - Уравнение");
                    System.out.println("2 - Систему уравнений");
                    target = scanner.nextLine();
                    if (target.equals("1")) {
                        equation = equationBuilder.read(null, scanner, false);
                    }
                    else {
                        nleSystem = systemBuilder.read(null,scanner,false);
                    }
                    break;
                }
                default: {
                    System.out.println("Неверный режим. Доступные режимы:\n" +
                            "                    [-f] для чтения из файла\n" +
                            "                    [-k] для ввода с клавиатуры");
                    System.exit(0);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Неверное количество аргументов или ключ отсутствует");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Неверный файл");
        } catch (NumberFormatException e) {
            System.err.println("Точность должна быть представлена числом от 0.0000000001 до 1");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.err.println("Неправильный формат текста в файле");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("Похоже вы ничего не передали. Проверьте ввод");
        }


        if (target.equals("1")) {
            switch (equation.getMethodNumber()) {
                case (1): {
                    ChordMethod chordMethod = new ChordMethod(equation);
                    chordMethod.solve();
                    Chart.setValues(equation);
                    Chart.drawFunction();
                    break;
                }
                case (2): {
                    SimpleIterationMethod simpleIterationMethod = new SimpleIterationMethod(equation);
                    simpleIterationMethod.solve();
                    Chart.setValues(equation);
                    Chart.drawFunction();
                }
            }
        } else {
            SystemSolver systemSolver = new SystemSolver(nleSystem);
            systemSolver.solve();
            Chart.setValues(nleSystem);
            Chart.drawSystemOfFunctions();
        }




    }
}
