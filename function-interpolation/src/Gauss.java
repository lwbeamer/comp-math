public class Gauss {

    private Table table;
    private double t;
    private double a;

    private int zeroIndex;
    private double yMatrix[][];

    public Gauss(Table table) {
        this.table = table;
        this.yMatrix = new double[table.getN()][table.getN()];
    }


    public double calculate(double x){
        double t = (x - findNearestX(x))/(table.getX()[table.getX().length-1] - table.getX()[table.getX().length-2]);
        t = t * 1000;
        int tmp =  (int) Math.round(t);
        t = (double) tmp/1000;
        this.t = t;
        fillMatrix();

        if (x>a) return firstFormula();
        else return secondFormula();
    }

    public double findNearestX(double x){
        double minDif = 9999999;
        double nearestX = 0;
        for (int i = 0; i < table.getX().length; i++){
            if (Math.abs(x-table.getX()[i]) < minDif){
                minDif = Math.abs(x-table.getX()[i]);
                nearestX = table.getX()[i];
                zeroIndex = i;
            }
        }
        a = nearestX;
        return nearestX;
    }
    public void fillMatrix(){
        for (int i = 0; i < table.getN(); i++){
            for (int j = 0; j < table.getN()-i; j++){
                if (i == 0) yMatrix[j][i] = table.getY()[j];
                else{
                    yMatrix[j][i] = yMatrix[j+1][i-1]-yMatrix[j][i-1];
                }
            }
        }
    }

    public void printMatrix(){
        System.out.println("Конечные разности: ");
        for (int j = 0; j < table.getN(); j++){
            System.out.printf("  ∆^"+j+"y   ");
        }
        System.out.println();
        for (int i = 0; i < table.getN(); i++){
            for (int j = 0; j < table.getN(); j++){
                System.out.printf("%-9.5f",yMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }

    public double firstFormula(){
        double mul = t;
        int fact = 1;
        int count = 0;
        int dif = 1;
        double sum = 0;
        if (zeroIndex-1 < 0){
            sum = yMatrix[zeroIndex][0];
            System.out.println("Используем: "+yMatrix[zeroIndex][0]);
            return sum;
        }
        sum = yMatrix[zeroIndex][0]+ yMatrix[zeroIndex][1]*t;
        System.out.println("Из таблицы конечных разностей используем: "+yMatrix[zeroIndex][0]);
        System.out.println("Из таблицы конечных разностей используем: "+yMatrix[zeroIndex][1]);
        for (int i = 2; i < table.getN(); i++){
            if (i % 2 == 0){
                mul*= (t- i/2);
            } else {
                mul*= (t+ i/2);
            }
            fact*=i;
            count++;

            if (zeroIndex-dif < 0) break;
            sum+= (mul*yMatrix[zeroIndex-dif][i])/fact;
            System.out.println("Из таблицы конечных разностей используем: "+yMatrix[zeroIndex-dif][i]);
            if (count == 2) {
                count = 0;
                dif++;
            }
        }
        return sum;
    }

    public double secondFormula(){
        double mul = t;
        int fact = 1;
        int count = 1;
        int dif = 1;
        double sum = 0;
        if (zeroIndex-1 < 0){
            sum = yMatrix[zeroIndex][0];
            System.out.println("Из таблицы конечных разностей используем: "+yMatrix[zeroIndex][0]);
            return sum;
        }
        sum = yMatrix[zeroIndex][0]+ yMatrix[zeroIndex-1][1]*t;
        System.out.println("Из таблицы конечных разностей используем: "+yMatrix[zeroIndex][0]);
        System.out.println("Из таблицы конечных разностей используем: "+yMatrix[zeroIndex-1][1]);
        for (int i = 2; i < table.getN(); i++){
            if (i % 2 == 0){
                mul*= (t+ i/2);
            } else {
                mul*= (t- i/2);
            }
            fact*=i;
            count++;

            if (zeroIndex-dif < 0) break;
            sum+= (mul*yMatrix[zeroIndex-dif][i])/fact;
            System.out.println("Из таблицы конечных разностей используем: "+yMatrix[zeroIndex-dif][i]);
            if (count == 2) {
                count = 0;
                dif++;
            }
        }
        return sum;
    }



}
