package flightBooking.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Integer id;

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

    private Integer gateNumber;

    private Integer economyClassTicketPrice;

    private Integer businessClassTicketPrice;

    private final String flightNo = UUID.randomUUID().toString().substring(0,8);

    private Integer businessClassSeats;

    private Integer economyClassSeats;

    private Integer bookedSeats;

    private Integer availableSeats;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    private Set<Passenger> passengers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    private Set<Booking> bookings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    private Set<Ticket> tickets = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "flights_users",
            joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Set<MyUser> users= new HashSet<>();



}
