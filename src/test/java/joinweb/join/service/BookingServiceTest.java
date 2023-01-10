package joinweb.join.service;

import joinweb.join.domain.Booking;
import joinweb.join.domain.BookingStatus;
import joinweb.join.domain.Member;
import joinweb.join.domain.event.Event;
import joinweb.join.domain.event.Study;
import joinweb.join.exception.NoMoreBookingAllowedException;
import joinweb.join.repository.BookingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        Member member = createMember();
        Event event = createStudy("스프링프로젝트", 5, "신촌");
        int count = 1;

        // when
        Long bookingId = bookingService.booking(member.getId(), event.getId(), count);

        // then
        Booking getBooking = bookingRepository.findOne(bookingId);

        assertEquals("예약후상태 : JOINED)", BookingStatus.JOINED, getBooking.getStatus());
        assertEquals("예약한 이벤트(종류) 수",1,
                getBooking.getBookingEvents().size());
        assertEquals("예약한만큼 예약가능인원 감소",4, event.getPeopleNumber());
    }

    @Test(expected = NoMoreBookingAllowedException.class)
    public void NoMoreBookingAllowedException() throws Exception {
        // given
        Member member = createMember();
        Event event = createStudy(" spring", 5,"신촌");

        int count = 10; // 예약가능인원 초과

        // when
        bookingService.booking(member.getId(), event.getId(), count);

        // then
        fail("예외 발생해야 테스트 통과");
    }

    @Test
    public void cancelBooking() {

        // given
        Member member = createMember();
        Event event = createStudy("스프링", 5, "신촌");
        int count = 2;

        Long bookingId = bookingService.booking(member.getId(), event.getId(), count);

        // when
        bookingService.cancelBooking(bookingId);

        // then
        Booking getBooking = bookingRepository.findOne(bookingId);

        assertEquals("주문취소후 주문 상태(CANCELED)", BookingStatus.CANCELED, getBooking.getStatus());
        assertEquals("주문취소후 예약가능인원 증가", 5, event.getPeopleNumber());
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("nae");
        em.persist(member);
        return member;
    }

    private Study createStudy(String name, int peopleNumber, String address) {
        Study study = new Study();
        study.setName(name);
        study.setPeopleNumber(peopleNumber);
        study.setAddress(address);
        em.persist(study);
        return study;
    }
}