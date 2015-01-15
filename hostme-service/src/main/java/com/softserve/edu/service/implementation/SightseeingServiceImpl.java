package com.softserve.edu.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.repositories.SightseeingRepository;
import com.softserve.edu.service.SightseeingService;

@Service
public class SightseeingServiceImpl implements SightseeingService {

	@Autowired
	private SightseeingRepository sightseeingRepository;

	@Override
	@Transactional
	public Set<Sightseeing> findAll() {
		List<Sightseeing> list = (List<Sightseeing>) sightseeingRepository
				.findAll();
		Set<Sightseeing> items = new HashSet<Sightseeing>(list);
		return items;
	}

	@Override
	@Transactional
	public Sightseeing findOne(Integer id) {
		return sightseeingRepository.findOne(id);
	}

	@Override
	public void deleteSightseeing(Sightseeing sightseeing) {
		sightseeingRepository.delete(sightseeing);
	}

	@Override
	@Transactional
	public void saveSightseeing(Sightseeing sightseeing) {
		sightseeingRepository.save(sightseeing);
	}

}
