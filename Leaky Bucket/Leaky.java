import java.util.*;

public class Leaky {
    static int min(int x, int y) {
        if (x < y)
            return x;
        else
            return y;
    }

    public static void main(String[] args) {
        int drop = 0, psent, nsec, cap, pleft = 0, i, outputRate;
        int inp[] = new int[25];
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter bucket size (capacity): ");
        cap = sc.nextInt();
        System.out.println("Enter the operation rate (output rate per second): ");
        outputRate = sc.nextInt();
        System.out.println("Enter the number of seconds you want to simulate (traffic arrival): ");
        nsec = sc.nextInt();

        for (i = 0; i < nsec; i++) {
            System.out.println("Enter the size of the packet entering at " + (i + 1) + " sec");
            inp[i] = sc.nextInt();
        }

        System.out.println("\nSecond | Packet Received | Packet Sent | Packet left | Packet Dropped |");
        System.out.println("-----------------------------------------------------------------------");

        for (i = 0; i < nsec; i++) {
            drop = 0;

            int currentInput = inp[i];

            if ((pleft + currentInput) > cap) {
                drop = (pleft + currentInput) - cap;
                pleft = cap;
            } else {
                pleft += currentInput;
            }

            psent = min(pleft, outputRate);
            pleft = pleft - psent;

            System.out.print(i + 1);
            System.out.print("\t\t" + currentInput);
            System.out.print("\t\t" + psent);
            System.out.print("\t\t" + pleft);
            System.out.print("\t\t" + drop);
            System.out.println();
        }

        drop = 0;

        for (; pleft > 0; i++) {

            System.out.print(i + 1);
            System.out.print("\t\t" + 0);

            psent = min(pleft, outputRate);
            pleft = pleft - psent;

            System.out.print("\t\t" + psent);
            System.out.print("\t\t" + pleft);
            System.out.print("\t\t" + drop);
            System.out.println();
        }

        sc.close();
    }
}