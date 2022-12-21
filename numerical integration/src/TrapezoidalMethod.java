public class TrapezoidalMethod {
    private Function function;

    private double a, b, eps;

    public TrapezoidalMethod(Function function) {
        this.function = function;
        a = function.getLeft();
        b = function.getRight();
        eps = function.getEps();
    }

    public void solve(int n) {
        double resPrev = 0, res;
        while (true) {
            double y = 0;
            double h = (b - a) / n;

            y += function.F(a) + function.F(b);
            for (int i = 1; i < n; i++) {
                y += 2 * (function.F(a + h * i));
            }

            res = (h/2) * y;

            if (Math.abs(resPrev - res) / 3 <= eps) break;

            resPrev = res;

            n *= 2;
        }

        System.out.println("Значение интеграла: " + res);
        System.out.println("Количество разбиений: " + n);
        System.out.println("Оценка погрешности методом Рунге. k = 2: " + Math.abs(resPrev - res) / 3);
        System.out.println("Оценка относительной погрешности с точным значением: "+Math.abs(function.certainIntegral(function.getLeft(), function.getRight()) - res));

    }
}
