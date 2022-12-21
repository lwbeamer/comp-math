import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FunctionBuilder functionBuilder = new FunctionBuilder();
        Function function = null;



        System.out.println("""
                Программа запущена.
                Введите -f для чтения из файла.
                Введите -k для чтения с клавиатуры.""");

        Scanner scanner = new Scanner(System.in);

        String mode = scanner.nextLine();

        try {
            switch (mode) {
                case ("-f") -> {
                    System.out.println("Режим чтения из файла. Введите путь до файла:");
                    String path = scanner.nextLine();
                    function = functionBuilder.read(Paths.get(path), scanner, true);
                }
                case ("-k") -> {
                    System.out.println("Режим чтения с клавиатуры");
                    function = functionBuilder.read(null, scanner, false);
                }
                default -> {
                    System.out.println("""
                            Неверный режим. Доступные режимы:
                                                [-f] для чтения из файла
                                                [-k] для ввода с клавиатуры""");
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

        switch (function.getMethodNumber()) {
            case (1) -> {
                System.out.println("Точное значение интеграла по формуле Ньютона-Лейбница: " + function.certainIntegral(function.getLeft(), function.getRight()));
                System.out.println("Решение методом трапеций");
                TrapezoidalMethod trapezoidalMethod = new TrapezoidalMethod(function);
                trapezoidalMethod.solve(4);
            }
            case (2) -> {
                System.out.println("Точное значение интеграла по формуле Ньютона-Лейбница: " + function.certainIntegral(function.getLeft(), function.getRight()));
                System.out.println("Решение методом прямоугольников");
                RecMethod recMethod = new RecMethod(function);
                recMethod.solve(4);
            }
        }

    }
}
