package flightBooking.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(nullable = false, length = 30)
    private String fullName;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Column
    private String randomToken;

    @Transient
    private String randomTokenEmail;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Transient
    private String passwordConfirm;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Passenger> passengers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Flight> flights = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    private List<Booking> bookings = new ArrayList<>();

    public MyUser(MyUser user) {
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.id = user.getId();
        this.accountNonExpired = user.isAccountNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.email = user.getEmail();
        this.passengers = user.getPassengers();
        this.tickets = user.getTickets();
        this.flights = user.getFlights();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }


}
