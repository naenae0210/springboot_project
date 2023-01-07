package joinweb.join.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Booking {
    @Id @GeneratedValue
    @Column(name = "booking_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingEvent> bookingEvents = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // 예약상태 [JOINED, CANCELED]

    /** 연관 관계 **/
    public void setMember(Member member) {
        this.member = member;
        member.getBooking().add(this);
    }

    public void addBookingEvent(BookingEvent bookingEvent) {
        bookingEvents.add(bookingEvent);
        bookingEvent.setBooking(this);
    }

    /** 예약 생성 **/
    public static Booking createBooking(Member member, BookingEvent... bookingEvents) {
        Booking booking = new Booking();
        booking.setMember(member);
        for(BookingEvent bookingEvent : bookingEvents) {
            booking.addBookingEvent(bookingEvent);
        }
        booking.setStatus(BookingStatus.JOINED);

        return booking;
    }

    /** 예약 취소 **/

    public void cancel() {
        this.setStatus(BookingStatus.CANCELED);
        for(BookingEvent bookingevent : bookingEvents) {
            bookingevent.cancel();
        }
    }
}
