package joinweb.join.service;

import joinweb.join.domain.Member;
import joinweb.join.domain.event.Event;
import joinweb.join.repository.BookingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookingServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired BookingService bookingService;
    @Autowired BookingRepository bookingRepository;

    @Test
    public void booking() throws Exception {

        // given
        Member member =
        // when

        // then
    }

    @Test
    public void cancelBooking() {
    }
}