public class RecMethod {

    private Function function;

    private double a, b, eps;

    public RecMethod(Function function) {
        this.function = function;
        a = function.getLeft();
        b = function.getRight();
        eps = function.getEps();
    }

    public void solve(int n) {
        double left = 0, right = 0, mid = 0;
        int leftN = 0, rightN = 0, midN = 0;
        boolean checkRight = false;
        boolean checkLeft = false;
        boolean checkMid = false;
        double prevLeft = 0, prevRight = 0, prevMid = 0;
        while (true) {

            double h = (b - a) / n;

            left = 0;
            right = 0;
            mid = 0;

            for (int i = 1; i <= n; i++) {
                mid += function.F(a + h * (i - 1 + 0.5));
                right += function.F(a + h * i);
                left += function.F(a + h * (i - 1));
            }


            mid *= h;
            right *= h;
            left *= h;

            if ((Math.abs(prevLeft - left)  <= eps) && !checkLeft){
                leftN = n;
                checkLeft = true;
            }

            if ((Math.abs(prevMid - mid)  <= eps) && !checkMid){
                midN = n;
                checkMid = true;
            }

            if ((Math.abs(prevRight - right)  <= eps) && !checkRight){
                rightN = n;
                checkRight = true;
            }



            if ((Math.abs(prevLeft - left)  <= eps) && (Math.abs(prevMid - mid)  <= eps) && (Math.abs(prevRight - right)  <= eps)) {
                break;
            }

            double certainValue = function.certainIntegral(function.getLeft(),function.getRight());
//
//            if (Math.abs(left - certainValue) <= eps &&  Math.abs(mid - certainValue) <= eps && Math.abs(right - certainValue) <= eps) {
//             break;
//            }

            prevMid = mid;
            prevLeft = left;
            prevRight = right;

            n *= 2;
        }

        System.out.println("Значение интеграла, полученное методом левых прямоугольников: " + left);
        System.out.println("Значение интеграла, полученное методом средних прямоугольников: " + mid);
        System.out.println("Значение интеграла, полученное методом правых прямоугольников: " + right);
        System.out.println("Количество разбиений для метода средних: " + midN);
        System.out.println("Количество разбиений для метода левых: " + leftN);
        System.out.println("Количество разбиений для метода правых: " + rightN);
        System.out.println("Оценка погрешности методом Рунге. k = 2");
        System.out.println("Оценка погрешности метода левых прямоугольников: " + Math.abs(prevLeft - left) / 3);
        System.out.println("Оценка погрешности метода средних прямоугольников: " + Math.abs(prevMid - mid) / 3);
        System.out.println("Оценка погрешности метода правых прямоугольников: " + Math.abs(prevRight - right) / 3);
        System.out.println("Оценка относительной погрешности с точным значением для метода левых: "+Math.abs(function.certainIntegral(function.getLeft(), function.getRight()) - left));
        System.out.println("Оценка относительной погрешности с точным значением для метода средних: "+Math.abs(function.certainIntegral(function.getLeft(), function.getRight()) - mid));
        System.out.println("Оценка относительной погрешности с точным значением для метода правых: "+Math.abs(function.certainIntegral(function.getLeft(), function.getRight()) - right));
    }


}
