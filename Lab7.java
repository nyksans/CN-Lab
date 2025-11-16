import java.util.Random;
import java.util.Scanner;

public class Lab7 {

    static final int MAX = 50;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter the total number of frames to send: ");
        int totalFrames = sc.nextInt();

        System.out.print("Enter the window size: ");
        int windowSize = sc.nextInt();

        int framesSent = 0;

        while (framesSent < totalFrames) {
            int framesToSend = Math.min(windowSize, totalFrames - framesSent);

            System.out.println("\nSending frames: ");
            for (int i = 0; i < framesToSend; i++) {
                System.out.println("Sent frame #" + (framesSent + i));
            }
            boolean allAcked = true;
            for (int i = 0; i < framesToSend; i++) {
                boolean ackReceived = rand.nextInt(10) < 9;

                if (ackReceived) {
                    System.out.println("ACK received for frame #" + (framesSent + i));
                } else {
                    System.out.println("ACK lost for frame #" + (framesSent + i) + " — retransmitting from here...");
                    framesSent += i;
                    allAcked = false;
                    break;
                }
            }

            if (allAcked) {
                framesSent += framesToSend;
            }
        }

        System.out.println("\nAll frames sent and acknowledged successfully.");
    }
}

/*
 * Sample Output
 * 
 * 
 * Enter the total number of frames to send: 10
 * Enter the window size: 4
 * 
 * Sending frames:
 * Sent frame #0
 * Sent frame #1
 * Sent frame #2
 * Sent frame #3
 * ACK received for frame #0
 * ACK received for frame #1
 * ACK received for frame #2
 * ACK lost for frame #3 — retransmitting from here...
 * 
 * Sending frames:
 * Sent frame #3
 * Sent frame #4
 * Sent frame #5
 * Sent frame #6
 * ...
 * All frames sent and acknowledged successfully.
 * 
 * 
 * Assumptions
 * 
 * Window size is configurable.
 * Number of frames to send is known.
 * Simulations are done via user input or random generation (e.g., simulate lost
 * ACK).
 * Sender will retransmit from the frame that was not acknowledged.
 */
