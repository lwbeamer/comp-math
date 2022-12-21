public class SystemSolver {

    final double PHI = (1 + Math.sqrt(5)) / 2;

    private NLESystem nleSystem;

    double a1,a2,b1,b2,eps;

    public SystemSolver(NLESystem nleSystem) {
        this.nleSystem = nleSystem;
        a1 = nleSystem.getA1();
        a2 = nleSystem.getA2();
        b1 = nleSystem.getB1();
        b2 = nleSystem.getB2();
        eps = nleSystem.getEps();
    }



    private double fMaxForPHI1x1(double a1, double b1){
        double x1;
        double x2;


        while (true){
            x1 = b1 - ((b1-a1)/PHI);
            x2 = a1 + ((b1-a1)/PHI);

            if (Math.abs(nleSystem.PHI1DerivativeX1(x1))<=Math.abs(nleSystem.PHI1DerivativeX1(x2))){
                a1 = x1;
            } else b1 = x2;
            if (Math.abs(b1-a1)<eps)
            break;
        }

        return (a1+b1)/2;
    }


    private double fMaxForPHI1x2(double a2, double b2){
        double x1;
        double x2;


        while (true){
            x1 = b2 - ((b2-a2)/PHI);
            x2 = a2 + ((b2-a2)/PHI);
            if (Math.abs(nleSystem.PHI1DerivativeX2(x1))<=Math.abs(nleSystem.PHI1DerivativeX2(x2))){
                a2 = x1;
            } else b2 = x2;
            if (Math.abs(b2-a2)<eps)
                break;
        }

        return (a2+b2)/2;
    }


    private double fMaxForPHI2x1(double a1, double b1){
        double x1;
        double x2;



        while (true){
            x1 = b1 - ((b1-a1)/PHI);
            x2 = a1 + ((b1-a1)/PHI);
            if (Math.abs(nleSystem.PHI2DerivativeX1(x1))<=Math.abs(nleSystem.PHI2DerivativeX1(x2))){
                a1 = x1;
            } else b1 = x2;
            if (Math.abs(b1-a1)<eps)
                break;
        }

        return (a1+b1)/2;
    }

    private double fMaxForPHI2x2(double a2, double b2){
        double x1;
        double x2;


        while (true){
            x1 = b2 - ((b2-a2)/PHI);
            x2 = a2 + ((b2-a2)/PHI);
            if (Math.abs(nleSystem.PHI2DerivativeX2(x1))<=Math.abs(nleSystem.PHI2DerivativeX2(x2))){
                a2 = x1;
            } else b2 = x2;
            if (Math.abs(b2-a2)<eps)
                break;
        }

        return (a2+b2)/2;
    }


    public boolean checkConvergence(){
        System.out.println(fMaxForPHI1x1(a1,b1));
        System.out.println(fMaxForPHI1x2(a2,b2));
        System.out.println(fMaxForPHI2x1(a1,b1));
        System.out.println(fMaxForPHI2x2(a2,b2));

        System.out.println();

        System.out.println(nleSystem.PHI1DerivativeX1(fMaxForPHI1x1(a1,b1)));
        System.out.println(nleSystem.PHI1DerivativeX2(fMaxForPHI1x2(a1,b1)));
        System.out.println(nleSystem.PHI2DerivativeX1(fMaxForPHI2x1(a1,b1)));
        System.out.println(nleSystem.PHI2DerivativeX2(fMaxForPHI2x2(a1,b1)));

        double tmp1 = Math.abs(nleSystem.PHI1DerivativeX1(fMaxForPHI1x1(a1,b1)))+Math.abs(nleSystem.PHI1DerivativeX2(fMaxForPHI1x2(a1,b1)));
        double tmp2 = Math.abs(nleSystem.PHI2DerivativeX1(fMaxForPHI2x1(a1,b1)))+Math.abs(nleSystem.PHI2DerivativeX2(fMaxForPHI2x2(a1,b1)));

        if (Math.max(tmp1,tmp2)<1)
        return true;
        else return false;
    }

    public void solve(){
        if (checkConvergence()){
            double x1_0 = b1;
            double x2_0 = b2;
            double x1_1 = b1;
            double x2_1 = b2;

            System.out.println("Начальное приближение: x1 = "+x1_0+" x2 = "+x2_0);
            double count = 0;
            do{
                count++;
                x1_0 = x1_1;
                x2_0 = x2_1;

                x1_1 = nleSystem.PHI1(x1_0,x2_0);
                x2_1 = nleSystem.PHI2(x1_0,x2_0);

                System.out.println("x1("+count+") = "+x1_1);
                System.out.println("x2("+count+") = "+x2_1);
                System.out.println();

            } while (Math.abs(nleSystem.f1(x1_1,x2_1)) > eps || Math.abs(nleSystem.f2(x1_1,x2_1)) >eps);
            System.out.println("f1(x1,x2) = " + nleSystem.f1(x1_1,x2_1));
            System.out.println("f2(x1,x2) = " + nleSystem.f2(x1_1,x2_1));
            System.out.println("Количество итераций: "+count);
        } else {
            System.out.println("Не выполнено достаточное условие сходимости");
        }
    }
}
