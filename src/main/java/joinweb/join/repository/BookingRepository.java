package joinweb.join.repository;

import joinweb.join.domain.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BookingRepository {

    private final EntityManager em;

    public void save(Booking booking) {
        em.persist(booking);
    }

    public Booking findOne(Long id) {
        return em.find(Booking.class, id);
    }

    // public List<Booking> findAll(BookingSearch bookingSearch) {}
}
