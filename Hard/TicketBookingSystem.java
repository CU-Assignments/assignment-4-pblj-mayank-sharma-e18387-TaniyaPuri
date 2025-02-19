import java.util.*;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10; 
    private static boolean[] seats = new boolean[TOTAL_SEATS]; 
    private static final Object lock = new Object();

    public void bookSeat(String userType, int seatNumber) {
        synchronized (lock) {
            if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
                System.out.println(userType + " " + Thread.currentThread().getName() + ": Invalid seat number " + seatNumber);
                return;
            }

            if (!seats[seatNumber - 1]) {
                seats[seatNumber - 1] = true; 
                System.out.println(userType + " " + Thread.currentThread().getName() + ": Seat " + seatNumber + " booked successfully.");
            } else {
                System.out.println(userType + " " + Thread.currentThread().getName() + ": Seat " + seatNumber + " is already booked.");
            }
        }
    }

    public void displayAvailableSeats() {
        synchronized (lock) {
            System.out.print("Available seats: ");
            for (int i = 0; i < TOTAL_SEATS; i++) {
                if (!seats[i]) {
                    System.out.print((i + 1) + " ");
                }
            }
            System.out.println();
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem bookingSystem;
    private String userType;
    private int seatNumber;


    public BookingThread(TicketBookingSystem bookingSystem, String userType, int seatNumber) {
        this.bookingSystem = bookingSystem;
        this.userType = userType;
        this.seatNumber = seatNumber;
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(userType, seatNumber);
    }
}

public class Main{
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        Random random = new Random();

        bookingSystem.displayAvailableSeats();

        for (int i = 1; i <= 15; i++) {
            int seatNumber = random.nextInt(10) + 1;
            String userType = (i % 3 == 0) ? "VIP" : "Regular";
            BookingThread thread = new BookingThread(bookingSystem, userType, seatNumber);

            if (userType.equals("VIP")) {
                thread.setPriority(Thread.MAX_PRIORITY);
            } else {
                thread.setPriority(Thread.NORM_PRIORITY);
            }

            thread.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bookingSystem.displayAvailableSeats();
    }
}
