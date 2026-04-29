package service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookMyMovieApp {
    public static void main(String[] args) {
        BookMyMovieSys bSystem = new BookMyMovieSys();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        bSystem.displayTheatres(city);

        System.out.println("Enter Theatre Id and Movie Id: ");
        int theatreId = sc.nextInt();
        int movieId = sc.nextInt();

        bSystem.displayShows(movieId, theatreId);

        System.out.print("Enter Show Id: ");
        int showId = sc.nextInt();

        System.out.println("Enter seats (comma separated like A1,A2): ");
        sc.nextLine(); // clear buffer
        String seatInput = sc.nextLine();
        List<String> selectSeats = Arrays.asList(seatInput.split(","));


        System.out.print("Enter User Id: ");
        int userId = sc.nextInt();
        bSystem.bookTicket(userId,showId,selectSeats);

        sc.close();
    }
}
