import java.util.List;
import java.util.Scanner;
//longitude - долгота
//latitude - широта
public class Main {
    public static void main(String[] args) {

        //Объявление переменных
        Scanner sc = new Scanner(System.in);
        Double result;
        Double[] point1 = new Double[2];
        Double[] point2 = new Double[2];
        final Integer EARTHRAD = 6_371;

        //Инициализация координат точек
        System.out.println("Now you should input 2 point (their coordinates) of our planet, then we calculate the range between them");

        //инициализация координат 1 точки
        System.out.println("Print coordinates of first point (first latitude, then - longitude)");
        point1[0] = Math.toRadians(sc.nextDouble());
        sc.nextLine();
        point1[1] = Math.toRadians(sc.nextDouble());
        sc.nextLine();

        //инициализация координат 1 точки
        System.out.println("Print coordinates of second point (first latitude, then - longitude)");
        point2[0] = Math.toRadians(sc.nextDouble());
        sc.nextLine();
        point2[1] = Math.toRadians(sc.nextDouble());
        sc.nextLine();

        //Применение сферической теоремы косинусов
        result = EARTHRAD * Math.acos(
                Math.sin(point1[0]) * Math.sin(point2[0]) +
                        Math.cos(point1[0]) * Math.cos(point2[0]) * Math.cos(point1[1] - point2[1])
        );
        System.out.printf("\nThe approximately range between your points is %.2f", result);
    }
}