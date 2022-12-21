public class Table {


    private double[] x;
    private double[] y;
    private int n;





    public void printX(){
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i]+" ");
        }
        System.out.println();
    }


    public void printY(){
        for (int i = 0; i < y.length; i++) {
            System.out.print(y[i]+" ");
        }
        System.out.println();
    }


    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public void setY(double[] y) {
        this.y = y;
    }


}
