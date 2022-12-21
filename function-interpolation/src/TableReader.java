import java.util.Scanner;

public class TableReader {

    Table table = new Table();

    public Table read(Scanner scanner, boolean funcInput, Function function){

        System.out.println("Введите значения Xi через пробел. Десятичный разделитель - точка");
        String readString = scanner.nextLine();
        String strX[] = readString.split(" ");

        double[] x = new double[strX.length];
        double[] y = new double[strX.length];

        if (!funcInput) {
            System.out.println("Введите значения Yi через пробел. Десятичный разделитель - точка");
            readString = scanner.nextLine();
            String strY[] = readString.split(" ");
            for (int i = 0; i < strX.length; i++) {
                x[i] = Double.parseDouble(strX[i]);
                y[i] = Double.parseDouble(strY[i]);
            }
        } else {
            for (int i = 0; i < strX.length; i++) {
                x[i] = Double.parseDouble(strX[i]);
                y[i] = function.f(x[i]);
            }
        }
        Chart.setABN(x[0],x[x.length-1]); //-1, +1
        Chart.setXY(x,y);
        table.setX(x);
        table.setY(y);
        table.setN(x.length);

        return table;
    }


}
