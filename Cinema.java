package cinema;

import java.util.Scanner;
import java.util.Arrays;

public class Cinema {

    public static void showSeats(char[][] seating, int rows, int seatsInRow) {
        System.out.println("Cinema:");
        System.out.print("  ");
        // start at -1 to print the seat numbers
        for (int i = -1; i < rows; i++) {
            if (i > -1) {
                System.out.print(i + 1 + " ");
            }
            
            for (int j = 0; j < seatsInRow; j++) {
                if (i == -1) {
                    System.out.print(j + 1 + " ");
                } else {
                    System.out.print(seating[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int getTicketPrice(int totalSeats, int rows, int rowNumber) {
        int ticketPrice = 10;
        if (totalSeats > 60) {
            int frontSeats = rows / 2;

            if (rowNumber > frontSeats) {
                ticketPrice = 8;
            }
        }

        return ticketPrice;
    }

    public static void buyATicket(char[][] seating, int rows, int seatsInRow) {
        Scanner scanner = new Scanner(System.in);

        int rowNumber;
        int seatNumber;
        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
    
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            System.out.println();

            if (rowNumber < 0 || rowNumber > seating.length 
                || seatNumber < 1 || seatNumber > seating.length) {
                System.out.println("Wrong input!");
            } else if (seating[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }

        seating[rowNumber - 1][seatNumber - 1] = 'B';

        int totalSeats = rows * seatsInRow;
        int ticketPrice = getTicketPrice(totalSeats, rows, rowNumber);
        System.out.println("Ticket price: $" + ticketPrice);
    }

    public static void chooseAnAction(char[][] seating, int rows, int seatsInRow) {
        Scanner scanner = new Scanner(System.in);
        
        int input;
        do {
            System.out.println("""
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
            """);
            
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    showSeats(seating, rows, seatsInRow);
                    break;
                case 2:
                    buyATicket(seating, rows, seatsInRow);
                    break;
                case 3:
                    showStatistics(seating, rows, seatsInRow);
                    break;
            }
        } while (input != 0);
    }

    public static void showStatistics(char[][] seating, int rows, int seatsInRow) {

        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalSeats = rows * seatsInRow;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                if (seating[i][j] == 'B') {
                    currentIncome += getTicketPrice(totalSeats, rows, i + 1);
                    purchasedTickets++;
                }
            }
        }

        float percentage = (float) purchasedTickets / totalSeats * 100;
        
        int totalIncome;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            int frontSeatsPrice = rows / 2 * seatsInRow * 10;
            int backSeatsPrice = (rows - rows / 2) * seatsInRow * 8;
            totalIncome = frontSeatsPrice + backSeatsPrice;
        }

        System.out.printf("""
            Number of purchased tickets: %d
            Percentage: %.2f%%
            Current income: $%d
            Total income: $%d%n""", purchasedTickets, percentage, currentIncome, totalIncome);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();

        char[][] seating = new char[rows][seatsInRow];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(seating[i], 0, seatsInRow, 'S');
        }

        chooseAnAction(seating, rows, seatsInRow);        
    }
}