import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class TableReader {

    Table table = new Table();

    public Table read(Path path, Scanner scanner, boolean fileMode,boolean outputFileMode) throws IOException {
        if (fileMode) scanner = new Scanner(path);

        if (!fileMode) System.out.println("Введите значения Xi через пробел. Десятичный разделитель - точка");
        String readString = scanner.nextLine();
        String strX[] = readString.split(" ");

        Printer.println("Xi:",outputFileMode);
        for (int i = 0; i < strX.length; i++) {
            Printer.print(strX[i]+" ",outputFileMode);
        }
        Printer.println("",outputFileMode);

        if (!fileMode) System.out.println("Введите значения Yi через пробел. Десятичный разделитель - точка");
        readString = scanner.nextLine();
        String strY[] = readString.split(" ");


        Printer.println("Yi:",outputFileMode);
        for (int i = 0; i < strY.length; i++) {
            Printer.print(strY[i]+" ",outputFileMode);
        }
        Printer.println("",outputFileMode);

        double[] x = new double[strX.length];
        double[] y = new double[strY.length];
        for (int i = 0; i < strX.length; i++) {
            x[i] = Double.parseDouble(strX[i]);
            y[i] = Double.parseDouble(strY[i]);
        }

        Chart.setABN(x[0]-0.5,x[x.length-1]+0.5,x.length); //-1, +1
        Chart.setXY(x,y);
        table.setX(x);
        table.setY(y);
        table.setN(x.length);


        return table;
    }

}
