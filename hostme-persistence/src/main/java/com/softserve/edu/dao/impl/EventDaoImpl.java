/*package com.softserve.edu.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.softserve.edu.dao.City;
import com.softserve.edu.dao.EventDao;
import com.softserve.edu.dao.PriceCategory;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.User;

public class EventDaoImpl extends AbstractGenericDao<Event, Long> implements
		EventDao {

	public EventDaoImpl() {
		super(Event.class);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> getEventByStartDate(Calendar startDate) {

		List<Event> event = getSessionFactory().getCurrentSession()
				.createCriteria(Event.class)
				.add(Restrictions.eq("startDate", startDate)).list();

		return event;
	}

	@Override
	public List<Event> getEventByCity(City city) {
		List<Event> event = getSessionFactory().getCurrentSession()
				.createCriteria(Event.class)
				.add(Restrictions.eq("city", city)).list();

		return event;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> getEventByOwner(String owner) {
		List<Event> event = getSessionFactory().getCurrentSession()
				.createCriteria(Event.class)
				.add(Restrictions.eq("owner", owner)).list();

		return event;
	}

	@Override
	public List<Event> getEventByPriceCategory(PriceCategory priceCategory) {
		List<Event> event = getSessionFactory().getCurrentSession()
				.createCriteria(Event.class)
				.add(Restrictions.eq("priceCategory", priceCategory)).list();

		return event;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> getEventByWebSite(String website) {
		List<Event> event = getSessionFactory().getCurrentSession()
				.createCriteria(Event.class)
				.add(Restrictions.eq("website", website)).list();

		return event;
	}

}
*/