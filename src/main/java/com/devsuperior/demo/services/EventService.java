package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Transactional( readOnly = true)
	public Page<EventDTO> findAll(Pageable pageable){
		Page<Event> page=eventRepository.findAll(pageable);
		return page.map(x-> new EventDTO(x));
	}
	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event event=new Event();
		event.setName(dto.getName());
		event.setUrl(dto.getUrl());
		event.setDate(dto.getDate());
		event.setCity(new City(dto.getCityId(),null));
		event=eventRepository.save(event);
		return new EventDTO(event);
	}
}
