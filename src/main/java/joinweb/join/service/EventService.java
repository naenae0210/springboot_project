package joinweb.join.service;

import joinweb.join.domain.event.Event;
import joinweb.join.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    public List<Event> findEvents() {
        return eventRepository.findAll();
    }

    public Event findOne(Long eventId) {
        return eventRepository.findOne(eventId);
    }



}
