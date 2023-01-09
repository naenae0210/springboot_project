package joinweb.join.controller;

import joinweb.join.domain.event.Event;
import joinweb.join.domain.event.Study;
import joinweb.join.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.print.Book;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping(value = "/events/new")
    public String createForm(Model model) {
        model.addAttribute("form", new StudyForm());
        return "events/createEventForm";
    }

    @PostMapping("/events/new")
    public String create(StudyForm studyForm) {

        Study study = new Study();
        study.setName(studyForm.getName());
        study.setPeopleNumber(studyForm.getPeopleNumber());
        study.setAddress(studyForm.getAddress());

        eventService.saveEvent(study);
        return "redirect:/events";
    }


    @GetMapping("/events")
    public String List(Model model) {
        List<Event> events = eventService.findEvents();
        model.addAttribute("events", events);
        return "events/eventList";
    }

    @GetMapping(value = "/events/{eventId}/edit")
    public String updateEventForm(@PathVariable("eventId") Long eventId, Model model) {
        Study event = (Study) eventService.findOne(eventId);
        StudyForm studyForm = new StudyForm();
        studyForm.setId(event.getId());
        studyForm.setName(event.getName());
        studyForm.setPeopleNumber(event.getPeopleNumber());
        studyForm.setAddress(event.getAddress());
        model.addAttribute("form", studyForm);
        return "events/updateEventForm";
    }

    @PostMapping(value = "/events/{eventId}/edit")
    public String updateEvent(@ModelAttribute("form") StudyForm studyForm) {
        Study study = new Study();
        study.setId(studyForm.getId());
        study.setName(studyForm.getName());
        study.setPeopleNumber(studyForm.getPeopleNumber());
        study.setAddress(studyForm.getAddress());
        eventService.saveEvent(study);
        return "redirect:/events";
    }
}
