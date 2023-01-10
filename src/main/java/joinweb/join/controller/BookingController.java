package joinweb.join.controller;

import joinweb.join.domain.Booking;
import joinweb.join.domain.Member;
import joinweb.join.domain.event.Event;
import joinweb.join.repository.BookingSearch;
import joinweb.join.service.BookingService;
import joinweb.join.service.EventService;
import joinweb.join.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final MemberService memberService;
    private final EventService eventService;

    @GetMapping(value = "/booking")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Event> events = eventService.findEvents();
        model.addAttribute("members", members);
        model.addAttribute("events", events);
        return "booking/bookingForm";
    }
    @PostMapping(value = "/booking")
    public String booking(@RequestParam("memberId") Long memberId,
                        @RequestParam("eventId") Long eventId, @RequestParam("count") int count) {
        bookingService.booking(memberId, eventId, count);
        return "redirect:/bookings";
    }

    @GetMapping(value = "/bookings")
    public String bookingList(@ModelAttribute("bookingSearch") BookingSearch bookingSearch, Model model) {
        List<Booking> bookings = bookingService.findBookings(bookingSearch);
        model.addAttribute("bookings", bookings);
        return "booking/bookingList";
    }

    @PostMapping(value = "/bookings/{bookingId}/cancel")
    public String cancelBooking(@PathVariable("bookingId") Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return "redirect:/bookings";
    }

}
