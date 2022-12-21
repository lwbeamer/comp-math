public class Lagranzh {
    private Table table;

    public Lagranzh(Table table) {
        this.table = table;
    }

    public double calculate(double x){
        int size = table.getN();
        double sum = 0;
        for (int i = 0; i < size; i++){
            double mul = 1;
            for (int j = 0; j < size; j++){
                if (i != j){
                    mul *= (x-table.getX()[j])/(table.getX()[i] - table.getX()[j]);
                }
            }
            sum+=table.getY()[i]*mul;
        }
        return sum;
    }
}
