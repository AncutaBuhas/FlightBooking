package flightBooking.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer id;

    private final String bookingNo = UUID.randomUUID().toString().substring(0,8);

    @Enumerated(EnumType.STRING)
    private DepartureCity departureCity;

    @Enumerated(EnumType.STRING)
    private DestinationCity destinationCity;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate departureDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureHour;

    @Enumerated(EnumType.STRING)
    private Airline airlineName;

    private Integer economyClassTicketPrice;

    private Integer businessClassTicketPrice;

    private String flightNo;

    private Integer gateNumber;

    private Integer bookedBusinessClassSeats;

    private Integer bookedEconomyClassSeats;

    private Integer checkedBaggage;

    private LocalDateTime bookingDateAndTime = LocalDateTime.now();

    private Integer totalAmount;

    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Passenger> passengers = new ArrayList<>();

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Ticket ticket;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bookings_users",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Set<MyUser> users = new HashSet<>();

    public Booking(Flight flight){
        this.departureCity = flight.getDepartureCity();
        this.destinationCity = flight.getDestinationCity();
        this.departureDate = flight.getDepartureDate();
        this.departureHour = flight.getDepartureHour();
        this.airlineName = flight.getAirlineName();
        this.economyClassTicketPrice = flight.getEconomyClassTicketPrice();
        this.businessClassTicketPrice = flight.getBusinessClassTicketPrice();
        this.flightNo = flight.getFlightNo();
        this.gateNumber = flight.getGateNumber();

    }

    public void addPassenger(String emailAddress, String fullName, String gender, String age,
                             String mobileNumber, String address, String classType, String checkedBaggage) {
        this.passengers.add(new Passenger(emailAddress, fullName, gender, age, mobileNumber,
                address, classType, checkedBaggage,this));

    }
}

