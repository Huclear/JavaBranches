import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static String[] firms = { "RHYTHM", "BULOVA", "HOWARD MILLER", "KIENINGER"};
    static Object[][] storeArray = { firms, {
            new String[]{ "RHYTHM CMJ529NR06", "RHYTHM CMJ546NR06", "RHYTHM CMJ507NR06", "RHYTHM CMJ562NR06"},
            new String[]{"BULOVA C3388", "BULOVA C4334", "BULOVA C3326", "BULOVA C3735"},
            new String[]{"HOWARD MILLER 625-279", "HOWARD MILLER 625-525", "HOWARD MILLER 625-753", "HOWARD MILLER 625-401"},
            new String[]{"KIENINGER 2169-23-02", "KIENINGER 2518-31-01", "KIENINGER 2851-41-02", "KIENINGER 2722-53-02"}
    } };
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int firmPosition, userIndex = 0;
        ArrayList<Object[]> consumers = new ArrayList<>();
        ArrayList<Object[]> orders = new ArrayList<>();
        boolean isLoggedIn = false;
        Object[] currentConsumer;
        while(true){
            if(!isLoggedIn) {
                currentConsumer = new Object[]{"", "", "", "", ""};
                System.out.println("Input your Firstname, Surname and Patronymic");
                System.out.println("Firstname : ");
                currentConsumer[0] = sc.nextLine();
                System.out.println("Surname : ");
                currentConsumer[1] = sc.nextLine();
                System.out.println("Patronymic : ");
                currentConsumer[2] = sc.nextLine();
                System.out.println("Email : ");
                currentConsumer[3] = sc.nextLine();
                System.out.println("Phone number : ");
                currentConsumer[4] = sc.nextLine();
                System.out.print("\n\n\n\n");

                consumers.add(currentConsumer);
                consumers = (ArrayList<Object[]>)consumers.stream().distinct().collect(Collectors.toList());
                userIndex = consumers.indexOf(currentConsumer);
                isLoggedIn = true;
            }
            getFirms();
            System.out.println("Select the firm number");
            firmPosition = sc.nextInt();
            sc.nextLine();
            if(firmPosition<0 || firmPosition >= firms.length){
                System.out.println("Firm with this index doesn't exist");
                continue;
            }
            getGoods(firmPosition);
            orders.add(new Object[]{userIndex, firmPosition, selectGoods(firmPosition)});
            System.out.println("Type '-1' to stop this hell-machine");
            if(sc.nextInt() == -1)
                break;
            sc.nextLine();
        }
        System.out.println("Well, comrade, here is the info about you and your bought goods...");
        System.out.printf("\nYou call yourself %s %s, %s", consumers.get(userIndex)[0], consumers.get(userIndex)[1], consumers.get(userIndex)[2]);
        System.out.printf("\n Your email : %s\n Telephone: %s", consumers.get(userIndex)[3], consumers.get(userIndex)[4]);
        System.out.println("\nYour orders: ");
        for (int i = 0; i < orders.size(); i++){
            if((int)orders.get(i)[0] == userIndex){
                int firmInd = (int)orders.get(i)[1];
                List<Integer[]> goods = (List<Integer[]>)orders.get(i)[2];
                System.out.println("\nFirm: " + firms[firmInd]);
                for (int j = 0; j < goods.size(); j++){
                    int amount = goods.get(j)[1];
                    String name = ((String[])storeArray[1][firmInd])[goods.get(j)[0]];
                    System.out.printf("\n\tGood: %s amount: %d", name, amount);
                }

            }
        }

    }
    public static void getFirms(){
        for (int i = 0; i< firms.length; i++){
            System.out.print(i + ") " + firms[i] + "\t");
        }
    }

    public static void getGoods(Integer firmIndex){
        for (int i = 0; i< ((String[])storeArray[1][firmIndex]).length; i++){
            System.out.print(i + ") " +  ((String[])storeArray[1][firmIndex])[i] + "\t");
        }
    }
    public static List<Integer[]> selectGoods(Integer firmIndex){
        ArrayList<Integer[]> currentOrder = new ArrayList<>();
        boolean isInArray = false;
        while(true){
            int selectedGood, amount;
            System.out.println("\nprint '-1', if you want to stop selecting goods");
            System.out.println("Select the good");
            selectedGood = sc.nextInt();
            sc.nextLine();
            if(selectedGood == -1)
                return currentOrder.stream().toList();
            else if (selectedGood < 0 || selectedGood >= ((String[])storeArray[1][firmIndex]).length) {
                System.out.println("Good with this index doesn't exist");
                continue;
            }
            System.out.println("Write down the amount of good you want to own");
            amount = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < currentOrder.size(); i++)
                if(selectedGood == currentOrder.get(i)[0]){
                    currentOrder.get(i)[1] += amount;
                    isInArray = true;
                }
            if(!isInArray)
                currentOrder.add(new Integer[]{selectedGood, amount});
            isInArray = false;
        }
    }

}