package cinema;

import java.util.Scanner;

public class Cinema {
    public static int Row;
    public static int Seats;
    public static int CurrentIncome = 0;
    public static int BoughtTickets = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        Row = rows;
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        Seats = seatsInRow;
        String[][] cinema = new String[rows][seatsInRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                cinema[i][j] = "S";
            }
        }
        System.out.println();

        System.out.println("1. Show the seats\n" + "2. Buy a ticket\n" + "3. Statistics\n" + "0. Exit");
        int choise = 0;
        choise = scanner.nextInt();
        System.out.println();

        while (choise != 0) {
            menu(cinema, rows, seatsInRow, choise);
            System.out.println("1. Show the seats\n" + "2. Buy a ticket\n" + "3. Statistics\n" + "0. Exit");
            choise = scanner.nextInt();
            System.out.println();
        }


    }

    public static void visualiseSeats(String[][] cinema, int seatsInrow) {

        System.out.print("Cinema:\n" + "  ");
        for (int i = 1; i <= seatsInrow; i++) {
            System.out.print(i + " ");
        }
        for (int i = 0; i < cinema.length; i++) {
            System.out.println();
            System.out.print(i + 1);
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }

        }
        System.out.println();
    }

    public static int incomeCount(int rows, int seatsInRow) {
        System.out.println();
        final int smallRoom = 60;
        final int firstPrice = 10;
        final int secondPrice = 8;
        int income;
        int allSeats = rows * seatsInRow;
        if (allSeats <= smallRoom) {
            income = allSeats * firstPrice;
        } else {
            if (rows % 2 == 0) {
                income = allSeats / 2 * firstPrice + allSeats / 2 * secondPrice;
            } else {
                int firsHalf = rows / 2 * seatsInRow;
                int secondHalf = (rows / 2 + 1) * seatsInRow;
                income = firsHalf * firstPrice + secondHalf * secondPrice;
            }
        }
        return income;
    }

    public static void priceOfSeat(int rows, int seatsInRow, int row) {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        final int smallRoom = 60;
        final int firstPrice = 10;
        final int secondPrice = 8;
        int ticketPrice = firstPrice;
        int allSeats = rows * seatsInRow;
        if (allSeats > smallRoom && row > rows / 2) {
            ticketPrice = secondPrice;
        }
        CurrentIncome = CurrentIncome + ticketPrice;
        BoughtTickets++;
        System.out.println("Ticket price: $" + ticketPrice);
        System.out.println();
    }

    public static void menu(String[][] cinema, int rows, int seatsInRow, int choise) {
        Scanner scanner = new Scanner(System.in);
        switch (choise) {
            case 1: {
                visualiseSeats(cinema, seatsInRow);
                System.out.println();
                break;
            }
            case 2: {
                int x = 0;
                while (x == 0) {
                    System.out.println("Enter a row number:");
                    int row = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int seat = scanner.nextInt();
                    if (row <= Row && seat <= Seats) {
                        if (cinema[row - 1][seat - 1] == "S") {
                            cinema[row - 1][seat - 1] = "B";
                            priceOfSeat(rows, seatsInRow, row);
                            x = 1;
                        } else {
                            System.out.println("That ticket has already been purchased!");
                        }
                    } else {
                        System.out.println("Wrong input!");
                    }
                }
                break;
            }
            case 3: {
                double percent = (BoughtTickets / (double) (Seats * Row)) * 100;
                System.out.println("Number of purchased tickets: " + BoughtTickets);
                System.out.println("Percentage: " + String.format("%.2f", percent) + "%");
                System.out.print("Current income: $" + CurrentIncome);
                System.out.println("Total income: $" + incomeCount(Row, Seats));
                break;
            }
            case 0: {
                break;
            }
        }

    }
}
