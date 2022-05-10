package flightBooking.scheduler;

import flightBooking.entity.Booking;
import flightBooking.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NewBookingAlert {

    @Autowired
    BookingRepository bookingRepository;

    @Scheduled(cron = "0 */2 * * * *")
    public void newBookingAlert() {
        List<LocalDateTime> bookingDateTimes = bookingRepository.findAll().stream().map(Booking::getBookingDateAndTime).collect(Collectors.toList());
        bookingDateTimes.forEach(bookingDateTime -> {
            LocalDateTime timePassed = bookingDateTime.plusMinutes(3);
            Duration duration = Duration.between(LocalDateTime.now(), timePassed);
            if (!duration.isNegative()) {
                JOptionPane optionPane = new JOptionPane("New Booking at " + bookingDateTime,JOptionPane.WARNING_MESSAGE);
                JDialog dialog = optionPane.createDialog("Warning!");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }
        });
    }
}
