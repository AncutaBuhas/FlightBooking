package flightBooking.repository;

import flightBooking.entity.DepartureCity;
import flightBooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Flight findByFlightNo(String flightNo);

    Set<Flight> findByDepartureCity(DepartureCity departureCity);

}
