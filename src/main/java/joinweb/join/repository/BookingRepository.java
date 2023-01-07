package joinweb.join.repository;

import joinweb.join.domain.Booking;
import joinweb.join.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Booking> findAll(BookingSearch bookingSearch) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Booking> cq = cb.createQuery(Booking.class);
        Root<Booking> b = cq.from(Booking.class);
        Join<Booking, Member> m = b.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        if (bookingSearch.getBookingStatus() != null) {
            Predicate status = cb.equal(b.get("status"),
                    bookingSearch.getBookingStatus());
            criteria.add(status);
        }

        if (StringUtils.hasText(bookingSearch.getMemberName())) {
            Predicate name =
                    cb.like(m.<String>get("name"), "%" +
                            bookingSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Booking> query = em.createQuery(cq).setMaxResults(100);
        return query.getResultList();

    }
}
