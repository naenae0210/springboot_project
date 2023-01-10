package joinweb.join.domain;

import joinweb.join.domain.event.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookingEvent {

    @Id @GeneratedValue
    @Column(name = "booking_event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event; // 주문상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking; // 주문

    private int count; // 예약 인원

    /** 예약된 이벤트 생성 **/
    public static BookingEvent createBookingEvent(Event event, int count) {
        BookingEvent bookingEvent = new BookingEvent();
        bookingEvent.setEvent(event);
        bookingEvent.setCount(count);

        event.removePeopleNumber(count);
        return bookingEvent;
    }

    /** 예약한 이벤트 취소 **/
    public void cancel() {
        getEvent().addPeopleNumber(count);
    }
}
