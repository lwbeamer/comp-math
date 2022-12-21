public class CubicFunc {

    private Table table;
    private int n;

    private double sx, sxx, sxxx, sxxxx, s5x, s6x, sy, sxy, sxxy, sxxxy;
    private double a, b, c, d, s, sigma;


    public CubicFunc(Table table) {
        this.table = table;
        n = table.getN();
        sx = calculateSX();
        sxx = calculateSXX();
        sxxx = calculateSXXX();
        sxxxx = calculateSXXXX();
        s5x = calculateS5X();
        s6x = calculateS6X();
        sy = calculateSY();
        sxy = calculateSXY();
        sxxy = calculateSXXY();
        sxxxy = calculateSXXXY();

        solveByGauss();

        s = deviationMeasure();
        sigma = Math.sqrt(s/n);
    }

    private double deviationMeasure(){
        double e;
        double s = 0;

        for (int i = 0; i < n; i++) {
            e = p(table.getX()[i])- table.getY()[i];
            s+=e*e;
        }
        return s;
    }

    public double p(double x){
        return a*x*x*x+b*x*x+c*x+d;
    }


    private void solveByGauss() {
        int n = 4;
        int m = 4;
        double[][] A = new double[100][100];
        double[] b = new double[100];

        A[0][0] = this.n;
        A[0][1] = sx;
        A[0][2] = sxx;
        A[0][3] = sxxx;
        A[1][0] = sx;
        A[1][1] = sxx;
        A[1][2] = sxxx;
        A[1][3] = sxxxx;
        A[2][0] = sxx;
        A[2][1] = sxxx;
        A[2][2] = sxxxx;
        A[2][3] = s5x;
        A[3][0] = sxxx;
        A[3][1] = sxxxx;
        A[3][2] = s5x;
        A[3][3] = s6x;
        b[0] = sy;
        b[1] = sxy;
        b[2] = sxxy;
        b[3] = sxxxy;

        int N = n;
        for (int p = 0; p < N; p++) {

            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            if (Math.abs(A[p][p]) <= 1e-10) {
                System.out.print("Не удалось решить систему для кубической функции");
                return;
            }

            for (int i = p + 1; i < N; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }


        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }
        if (n < m) {
            System.out.print("Не удалось решить систему для кубической функции");
        } else {
            for (int i = 0; i < N; i++) {
                //  System.out.print(x[i] + " ");
            }
        }

        this.d = x[0];
        this.c = x[1];
        this.b = x[2];
        this.a = x[3];
    }

    private double calculateSX() {
        double[] x = table.getX();
        double sx = 0;

        for (int i = 0; i < n; i++) {
            sx += x[i];
        }
        return sx;
    }

    private double calculateSXX() {
        double[] x = table.getX();
        double sxx = 0;

        for (int i = 0; i < n; i++) {
            sxx += x[i] * x[i];
        }
        return sxx;
    }

    private double calculateSXXX() {
        double[] x = table.getX();
        double sxxx = 0;

        for (int i = 0; i < n; i++) {
            sxxx += x[i] * x[i] * x[i];
        }
        return sxxx;
    }

    private double calculateSXXXX() {
        double[] x = table.getX();
        double sxxxx = 0;

        for (int i = 0; i < n; i++) {
            sxxxx += x[i] * x[i] * x[i] * x[i];
        }
        return sxxxx;
    }

    private double calculateS5X() {
        double[] x = table.getX();
        double s5x = 0;

        for (int i = 0; i < n; i++) {
            s5x += x[i] * x[i] * x[i] * x[i] * x[i];
        }
        return s5x;
    }

    private double calculateS6X() {
        double[] x = table.getX();
        double s6x = 0;

        for (int i = 0; i < n; i++) {
            s6x += x[i] * x[i] * x[i] * x[i] * x[i] * x[i];
        }
        return s6x;
    }

    private double calculateSY() {
        double[] y = table.getY();
        double sy = 0;

        for (int i = 0; i < n; i++) {
            sy += y[i];
        }
        return sy;
    }

    private double calculateSXY() {
        double[] x = table.getX();
        double[] y = table.getY();
        double sxy = 0;

        for (int i = 0; i < n; i++) {
            sxy += x[i] * y[i];
        }
        return sxy;
    }

    private double calculateSXXY() {
        double[] x = table.getX();
        double[] y = table.getY();
        double sxxy = 0;

        for (int i = 0; i < n; i++) {
            sxxy += x[i] * x[i] * y[i];
        }
        return sxxy;
    }

    private double calculateSXXXY() {
        double[] x = table.getX();
        double[] y = table.getY();
        double sxxxy = 0;

        for (int i = 0; i < n; i++) {
            sxxxy += x[i] * x[i] * x[i] * y[i];
        }
        return sxxxy;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getS() {
        return s;
    }

    public double getSigma() {
        return sigma;
    }
}
