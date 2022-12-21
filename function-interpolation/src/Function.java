public class Function {

    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public double f(double x){
        switch (number){
            case (1):{
                return f1(x);
            }
            case (2):{
                return f2(x);
            }
            case (3):{
                return f3(x);
            }
            default:{
                System.out.println("что-то не так");
                System.exit(0);
            }
        }
        return 0;
    }

    //y = sin(x)
    public double f1(double x){
        return Math.sin(x);
    }

    //y = e^x
    public double f2(double x){
        return Math.pow(Math.E,x);
    }

    //y = x^2 + 2*x + 3
    public double f3(double x){
        return x*x + 2*x + 3;
    }
}
