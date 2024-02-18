import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Объявление переменных
        double a, b, c;
        Scanner sc = new Scanner(System.in);

        //Инициализация переменных, хранящих значения сторон треугольника
        System.out.println("Input sides of any triangle");
        System.out.print("\nFirst side - ");
        a = sc.nextDouble();
        sc.nextLine();
        System.out.print("\nSecond side - ");
        b = sc.nextDouble();
        sc.nextLine();
        System.out.print("\nThird side - ");
        c = sc.nextDouble();
        sc.nextLine();

        //Проверка, существует треугольник
        if((a+b<=c)||(c+b<=a)||(a+c<=b))
            System.out.println("Such triangle cannot exist");
        //Если треугольник существует, идет дальнейшая проверка на принадлежность косновным видам треугольников
        else {

            //Проврка, если треугольник - равносторонний
            if((a == b)&&(a == c))
                System.out.println("Your triangle is equilateral");

            //Если не равносторонний - проверка на то, является ли треугольник ранобедренным или(и) прямоугольным
            else {

                //Проверка, если треугольник прямоугольный
                if((Math.pow(a,2) + Math.pow(b,2) == Math.pow(c,2))||
                                (Math.pow(a,2) + Math.pow(c,2) == Math.pow(b,2))||
                                (Math.pow(b,2) + Math.pow(c,2)) == Math.pow(a,2))
                    System.out.println("Your triangle is rectangular");
                //Проверка, если треугольник - равнобедренный
                else if(a == b||b == c||a == c)
                    System.out.println("Your triangle is isosceles");

                //В иных случаях: трегольник - обычный
                else
                    System.out.println("Your triangle is pretty simple");
            }

        }
    }
}