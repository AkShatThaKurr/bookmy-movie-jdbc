package service;

import config.DataBaseConfig;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import java.sql.SQLException;

public class BookMyMovieSys {

    // Display Movies:
    public void displayMovies() {

        try {
            Connection con = DataBaseConfig.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movies");
            System.out.println("---------- Available Movies ----------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("movieId") + " " +
                                rs.getString("title") + " " +
                                "(" + rs.getString("genre") + ")"
                );
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  Show Theatres in a city:
    public void displayTheatres(String city) {
        try {
            Connection con = DataBaseConfig.getConnection();
            PreparedStatement pr = con.prepareStatement("select * from theatres where city = ?");
            pr.setString(1, city);
            ResultSet rs = pr.executeQuery();
            System.out.println("Theatres in " + city + ":");
            while (rs.next()) {
                System.out.println(rs.getInt("theatreId") + " " + rs.getString("name"));
            }
            rs.close();
            pr.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display shows available in Theatre
    public void displayShows(int movieId, int theatreId) {
        try {
            Connection con = DataBaseConfig.getConnection();
            PreparedStatement pr = con.prepareStatement("select * from shows where movieId = ? and theatreId = ?");
            pr.setInt(1, movieId);
            pr.setInt(2, theatreId);
            ResultSet rs = pr.executeQuery();
            System.out.println("Available shows in movieId = " + movieId + " and theatreId = " + theatreId + " are: ");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("showId") + " " +
                                rs.getString("timing") + " " +
                                "Seats available:" + rs.getInt("availableSeats") + " "
                );
            }
            rs.close();
            pr.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Book Ticket:
    public void bookTicket(int userId, int showId, List<String> selectedSeats) {
        try {
            Connection con = DataBaseConfig.getConnection();
            con.setAutoCommit(false);

            // Check if seats are Available
            boolean alreadyBookedSeat = false;
            for (String seat : selectedSeats) {
                PreparedStatement pr = con.prepareStatement("select * from seats where seatNumber = ? and showId = ?");
                pr.setString(1, seat);
                pr.setInt(2, showId);
                ResultSet rs = pr.executeQuery();
                if (rs.next() && rs.getBoolean("is_booked")) {
                    alreadyBookedSeat = true;
                    System.out.println("seat " + seat + " is already booked, Choose another seat.");
                }
                rs.close();
                pr.close();
            }
            if (alreadyBookedSeat) {
                System.out.println("Booking Failed! Some seats are already booked.");
                con.rollback();
                con.close();
                return;
            }

            for (String seat : selectedSeats) {
                PreparedStatement pr1 = con.prepareStatement("update seats set is_booked = true where seatNumber = ? and showId = ?");
                pr1.setString(1, seat);
                pr1.setInt(2, showId);
                pr1.executeUpdate();
                pr1.close();
            }

            double seatPrice = 200;
            double tp = (selectedSeats.size() * seatPrice);
            PreparedStatement pr2 = con.prepareStatement(
                    "insert into bookings (userId, showId, seatsBooked,totalPrice) values (?,?,?,?)"
            );
            pr2.setInt(1, userId);
            pr2.setInt(2, showId);
            pr2.setString(3, String.join(",", selectedSeats));
            pr2.setDouble(4, tp);
            pr2.executeUpdate();
            //con.commit();
            System.out.println("Booking Successful, Seats: " + selectedSeats + " | Total Price " + tp);
            pr2.close();

            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

