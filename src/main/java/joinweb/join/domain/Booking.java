package joinweb.join.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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
    private BookingStatus status; // 예약상태 [READY, JOINED]

    public void setMember(Member member) {
        this.member = member;
        member.getBooking().add(this);
    }

    public void addBookingEvent(BookingEvent bookingEvent) {
        bookingEvents.add(bookingEvent);
        bookingEvent.setBooking(this);
    }

}
