package joinweb.join.repository;

import joinweb.join.domain.BookingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookingSearch {
    private String memberName;
    private BookingStatus bookingStatus;
}
