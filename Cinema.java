package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        System.out.print("""
            Cinema:
              1 2 3 4 5 6 7 8
            1 S S S S S S S S
            2 S S S S S S S S
            3 S S S S S S S S
            4 S S S S S S S S
            5 S S S S S S S S
            6 S S S S S S S S
            7 S S S S S S S S""");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();

        System.out.println("Total Income:");
        int totalSeats = rows * seatsInRow;

        int profit;
        if (totalSeats <= 60) {
            profit = totalSeats * 10;
        } else {
            int frontSeatsPrice = rows / 2 * seatsInRow * 10;
            int backSeatsPrice = (rows - rows / 2) * seatsInRow * 8;
            profit = frontSeatsPrice + backSeatsPrice;
        }

        System.out.printf("$%d", profit);
    }
}