package flightBooking.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer id;

    private String fullName;

    private String emailAddress;

    private String gender;

    private String age;

    private String mobileNumber;

    private String address;

    private String classType;

    private String checkedBaggage;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Passenger( String fullName, String emailAddress, String gender, String age,
                     String mobileNumber, String address, String classType, String checkedBaggage, Booking booking) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.classType = classType;
        this.checkedBaggage = checkedBaggage;
        this.booking = booking;

    }

}
