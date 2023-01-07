package joinweb.join.service;

import joinweb.join.domain.Booking;
import joinweb.join.domain.BookingEvent;
import joinweb.join.domain.Member;
import joinweb.join.domain.event.Event;
import joinweb.join.repository.BookingRepository;
import joinweb.join.repository.BookingSearch;
import joinweb.join.repository.EventRepository;
import joinweb.join.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookingService {

    private final MemberRepository memberRepository;
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;

    @Transactional
    public Long booking(Long memberId, Long eventId, int count) {

        // 예약하는 고객, 예약할 이벤트를 먼저 찾아와야함
        Member member = memberRepository.findOne(memberId);
        Event event = eventRepository.findOne(eventId);

        BookingEvent bookingEvent = BookingEvent.createBookingEvent(event, count);

        Booking booking = Booking.createBooking(member, bookingEvent);

        bookingRepository.save(booking);
        return booking.getId();
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        // 삭제할 예약 조회후 삭제
        Booking booking = bookingRepository.findOne(bookingId);
        booking.cancel();
    }


    public List<Booking> findBookings(BookingSearch bookingSearch) {
        return bookingRepository.findAll(bookingSearch);
    }

}

