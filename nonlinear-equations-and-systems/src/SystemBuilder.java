import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class SystemBuilder {

    double a1,a2,b1,b2,eps;

    NLESystem nleSystem = new NLESystem();


    public NLESystem read(Path path, Scanner scanner, boolean fileMode) throws IOException {
        if (fileMode) scanner = new Scanner(path);

        if (!fileMode) System.out.println("Введите левую границу интервала для x1:");
        a1 = Double.parseDouble(scanner.nextLine());
        nleSystem.setA1(a1);

        if (!fileMode) System.out.println("Введите правую границу интервала для x1:");
        b1 = Double.parseDouble(scanner.nextLine());
        nleSystem.setB1(b1);

        if (!fileMode) System.out.println("Введите левую границу интервала для x2:");
        a2 = Double.parseDouble(scanner.nextLine());
        nleSystem.setA2(a2);

        if (!fileMode) System.out.println("Введите правую границу интервала для x2:");
        b2 = Double.parseDouble(scanner.nextLine());
        nleSystem.setB2(b2);

        if (!fileMode) System.out.println("Введите погрешность вычислений:");
        eps = Double.parseDouble(scanner.nextLine());
        nleSystem.setEps(eps);

        System.out.println("Система уравнений: ");
        System.out.println("1: 0.1x₁^2 + x₁ + 0.2x₂^2 - 0.3");
        System.out.println("2: sin(x₁) + x₂");
        System.out.println("C интервалом x1 от "+a1+" до "+b1);
        System.out.println("C интервалом x2 от "+a2+" до "+b2);
        System.out.println("С погрешностью E = "+eps);

        return nleSystem;
    }
}
