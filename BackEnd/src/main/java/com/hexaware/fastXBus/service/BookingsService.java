package com.hexaware.fastXBus.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.fastXBus.dto.BookingsDTO;
import com.hexaware.fastXBus.entity.Bookings;
import com.hexaware.fastXBus.entity.Buses;
import com.hexaware.fastXBus.entity.UserCustomers;
import com.hexaware.fastXBus.repository.IBookingsRepository;
import com.hexaware.fastXBus.repository.IBusesRepository;
import com.hexaware.fastXBus.repository.IUserCustomersRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class BookingsService implements IBookingsService {
    @Autowired
    private IBookingsRepository repository;
    @Autowired
    IBusesRepository busRepository;
    @Autowired
    IUserCustomersRepository ucrepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JavaMailSender mail;
    private static final Logger logger = LoggerFactory.getLogger(BookingsService.class);

    @Override
    public BookingsDTO createBookings(BookingsDTO bookingdto, Long userId, Long busId) {
        // Fetch the bus
        Buses bus = busRepository.findById(busId).orElse(null);
        if (bus == null) {
            // Handle case where bus is not found
            return null;
        }

        // Check if the bus has available seats
        if (bus.getCapacity() <= 0) {
            // Handle case where bus capacity is zero
            return null;
        }

        // Fetch user details
        UserCustomers user = ucrepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle case where user is not found
            return null;
        }

        // Create the booking only if there are available seats
        if (bus.getCapacity() > 0) {
            Bookings booking = new Bookings();
            booking.setBookingDate(bookingdto.getBookingDate());
            booking.setEmail(bookingdto.getEmail());
            booking.setTotalcustomer(bookingdto.getTotalcustomer());
            booking.setAmount(bookingdto.getAmount());
            booking.setSeatNo(bookingdto.getSeatNo());


            int remainingCapacity = bus.getCapacity() - booking.getTotalcustomer();
            bus.setCapacity(remainingCapacity);


            busRepository.save(bus);
            booking.setUserCustomers(user);
            booking.setBuses(bus);
            // Save the booking
            booking = repository.save(booking);

            return new BookingsDTO(booking.getBookingId(), booking.getBookingDate(), booking.getEmail(), booking.getTotalcustomer(), booking.getAmount(), booking.getSeatNo());
        } else {
            // Handle case where no seats are available
            return null;
        }
    }


    @Override
    public Bookings updateBookings(BookingsDTO bookingsdto, Long bookingId) {
        // Find the existing booking by ID
        Optional<Bookings> bookingOptional = repository.findById(bookingId);

        if (bookingOptional.isPresent()) {
            // Get the existing booking
            Bookings existingBooking = bookingOptional.get();

            // Update the fields with new values from DTO

            existingBooking.setTotalcustomer(bookingsdto.getTotalcustomer());
            existingBooking.setBookingDate(bookingsdto.getBookingDate());
            existingBooking.setAmount(bookingsdto.getAmount());
            existingBooking.setEmail(bookingsdto.getEmail());


            // Save the updated booking
            return repository.save(existingBooking);
        } else {
            logger.error("Booking not found");
            return null;
        }
    }

    @Override
    public void deleteBookings(Long bookingId) {

        repository.deleteById(bookingId);

    }

    @Override
    public BookingsDTO getBookingsById(Long bookingId) {

        Bookings booking = repository.findById(bookingId).orElse(new Bookings());
        return new BookingsDTO(booking.getBookingId(), booking.getBookingDate(), booking.getEmail(), booking.getTotalcustomer(), booking.getAmount(), booking.getSeatNo());
    }

    @Override
    public List<BookingsDTO> getAllBookings() {
        List<Bookings> bookings = repository.findAll(Sort.by(Sort.Direction.ASC, "bookingDate"));
        List<BookingsDTO> bookingsDtoList = new ArrayList<>();

        for (Bookings booking : bookings) {
            BookingsDTO bookingDto = new BookingsDTO();
            bookingDto.setBookingId(booking.getBookingId());
            bookingDto.setBookingDate(booking.getBookingDate());
            bookingDto.setEmail(booking.getEmail());
            bookingDto.setTotalcustomer(booking.getTotalcustomer());
            bookingDto.setAmount(booking.getAmount());
            bookingDto.setSeatNo(booking.getSeatNo());

            bookingsDtoList.add(bookingDto);
        }

        return bookingsDtoList;
    }


    @Override
    public List<String> fetchBookedSeats(LocalDate date, Long busId) {
        return repository.fetchBookedSeats(date, busId);
    }
    @Override
    public void sendBookingConfirmationEmail(Long bookingId) {
        // Fetch the booking details by ID from the database with associated Buses
        Bookings booking = repository.findBookingWithBusesById(bookingId);

        if (booking != null) {
            // Get the associated bus details
            Buses bus = booking.getBuses();

            // Construct the email content
            String subject = "Booking Confirmation";
            String greeting = "Dear " + booking.getEmail() + ",\n\n";
            String bookingDetails = "Booking Details:\n\n" +
                    "Booking ID: " + booking.getBookingId() + "\n" +
                    "Booking Date: " + booking.getBookingDate() + "\n" +
                    "Total Customers: " + booking.getTotalcustomer() + "\n" +
                    "Amount: " + booking.getAmount() + "\n" +
                    "Seat Number: " + booking.getSeatNo() + "\n\n";

            String busDetails = "Bus Details:\n\n" +
                    "Bus Number: " + bus.getBusNumber() + "\n" +
                    "Source City: " + bus.getSourceCity() + "\n" +
                    "Destination City: " + bus.getDestinationCity() + "\n" +
                    "Distance (in Kms): " + bus.getDistanceInKms() + "\n" +
                    "Duration (in Hours): " + bus.getDurationInHours() + "\n" +
                    "Departure Time: " + bus.getDepartureTime() + "\n" +
                    "Date of Journey: " + bus.getDate() + "\n";

            // Create the email content in a tabular format
            String emailContent = greeting + "\n" +
                    bookingDetails +
                    busDetails +
                    "\n\nThanks for choosing FastX. Have a safe journey!";

            // You can use HTML to format the email content as a table
            String htmlContent = "<html><body>" +
                    "<h2>Dear " + booking.getEmail() + ",</h2>" +
                    "<p>⬇️ Below are your booking details: ⬇️</p>" +
                    "<table border='1'>" +
                    "<tr><td>Booking ID</td><td>" + booking.getBookingId() + "</td></tr>" +
                    "<tr><td>Booking Date</td><td>" + booking.getBookingDate() + "</td></tr>" +
                    "<tr><td>Total Customers</td><td>" + booking.getTotalcustomer() + "</td></tr>" +
                    "<tr><td>Amount</td><td>" + booking.getAmount() + "</td></tr>" +
                    "<tr><td>Seat Number</td><td>" + booking.getSeatNo() + "</td></tr>" +
                    "</table>" +
                    "<p>⬇️ Below are the bus details: ⬇️</p>" +
                    "<table border='1'>" +
                    "<tr><td>Bus Number</td><td>" + bus.getBusNumber() + "</td></tr>" +
                    "<tr><td>Source City</td><td>" + bus.getSourceCity() + "</td></tr>" +
                    "<tr><td>Destination City</td><td>" + bus.getDestinationCity() + "</td></tr>" +
                    "<tr><td>Distance (in Kms)</td><td>" + bus.getDistanceInKms() + "</td></tr>" +
                    "<tr><td>Duration (in Hours)</td><td>" + bus.getDurationInHours() + "</td></tr>" +
                    "<tr><td>Departure Time</td><td>" + bus.getDepartureTime() + "</td></tr>" +
                    "<tr><td>Date of Journey</td><td>" + bus.getDate() + "</td></tr>" +
                    "</table>" +
                    "<p>🙏 Thanks for choosing FastX 🙏 Have a safe journey!!!!!</p>" +
                    "</body></html>";

            // Send the email
            MimeMessage message = mail.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(message, true);
                helper.setTo(booking.getEmail());
                helper.setSubject(subject);
                helper.setText(htmlContent, true);

                // Send email
                mail.send(message);
            } catch (MessagingException e) {
                logger.error("Error sending booking confirmation email: " + e.getMessage());
            }
        } else {
            // Handle case where booking is not found
            logger.error("Booking not found");
        }
    }

    @Override
    public Bookings findBookingWithBusesById(Long bookingId) {
        return repository.findBookingWithBusesById(bookingId);
    }


    @Override
    public List<BookingsDTO> getAllBookingsByUserId(Long userId) {
        List<Bookings> bookings = repository.getAllBookingsByUserId(userId);
        List<BookingsDTO> bookingsDto = new ArrayList<>();
        for (Bookings booking : bookings) {
            BookingsDTO bookingDto = new BookingsDTO();
            bookingDto.setBookingId(booking.getBookingId());
            bookingDto.setBookingDate(booking.getBookingDate());
            bookingDto.setEmail(booking.getEmail());
            bookingDto.setTotalcustomer(booking.getTotalcustomer());
            bookingDto.setAmount(booking.getAmount());
            bookingDto.setSeatNo(booking.getSeatNo());


            bookingsDto.add(bookingDto);
        }
        return bookingsDto;
    }


}
