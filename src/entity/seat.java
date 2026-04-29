package entity;

public class seat {
    private int seatId;
    private int showId;
    private String seatNumber;
    private boolean is_booked;

    public seat(int seatId, int showId, String seatNumber, boolean is_booked) {
        this.seatId = seatId;
        this.showId = showId;
        this.seatNumber = seatNumber;
        this.is_booked = is_booked;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isIs_booked() {
        return is_booked;
    }

    public void setIs_booked(boolean is_booked) {
        this.is_booked = is_booked;
    }
}
