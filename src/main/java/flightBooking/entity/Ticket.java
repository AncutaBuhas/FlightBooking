package flightBooking.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private final String ticketNo = UUID.randomUUID().toString().substring(0,8);

    private Integer seatNo;

    @OneToOne()
    private Booking booking;

    @OneToOne(mappedBy = "ticket")
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private MyUser user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

}
