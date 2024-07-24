package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public Page<CityDTO> findAll(Pageable pageable){
		Page<City> page=cityRepository.findAll(pageable);
		return page.map(x->new CityDTO(x));
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		City city=new City();
		city.setName(dto.getName());
		city=cityRepository.save(city);
		return new CityDTO(city);
	}

}
