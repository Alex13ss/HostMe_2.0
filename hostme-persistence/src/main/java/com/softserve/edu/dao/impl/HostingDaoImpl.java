package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.HostingDao;
import com.softserve.edu.model.Gender;
import com.softserve.edu.model.Hosting;
import com.softserve.edu.utils.Search;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HostingDaoImpl extends AbstractGenericDao<Hosting, Integer> implements HostingDao {
    public HostingDaoImpl() {
        super(Hosting.class);
    }

    @Autowired
    private SessionFactory sessionFactory;
    
    private static final Integer ROWS = 3;    

    /*
    Get list of the hostels that are required criterias
     */
    public List<Object> getList(List<Search> listSearch, Integer page) {
        Session session = sessionFactory.getCurrentSession();
        ProjectionList projs = Projections.projectionList();
        projs.add(Projections.property("h.hostingId"), "hostingId")
            .add(Projections.property("h.address"), "address")
            .add(Projections.property("h.country"), "country")
            .add(Projections.property("h.region"), "region")
            .add(Projections.property("h.city"), "city")
            .add(Projections.property("u.firstName"), "firstName")
            .add(Projections.property("u.lastName"), "lastName")
            .add(Projections.property("u.userId"), "userId");

        Criteria cr = session.createCriteria(Hosting.class, "h")
                .createAlias("h.owner", "u")
                .setProjection(projs)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        cr.setMaxResults(ROWS);
        cr.setFirstResult(page * ROWS);

        cr = getCriteriasResult(cr, listSearch);
        return cr.list();
    }

    /*
    Get count of pages
     */
    public Integer getCountOfPages(List<Search> listSearch) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Hosting.class, "hosting").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        cr = getCriteriasResult(cr, listSearch);
        if (cr.list().size() % ROWS == 0)
            return (cr.list().size() / ROWS);
        else
            return (cr.list().size() / ROWS + 1);
    }

    public Gender parsingGender(String gender) {
        for (Gender parameterGender : Gender.values()) {
            if (gender.equalsIgnoreCase(parameterGender.toString())) {
                return parameterGender;
            }
        }
        return Gender.UNSPECIFIED;
    }


    /*
    Return the criteria result for hostels
     */
    public Criteria getCriteriasResult(Criteria cr, List<Search> listSearch) {

        String min = new String();
        String valueMin = new String();
        String max = new String();
        String valueMax = new String();

        //add criterias
        for (Search parameter : listSearch) {
            if ((parameter.getName().equals("country") || parameter.getName().equals("region")) && !parameter.getValue().isEmpty() && !parameter.getValue().equals(" ")) {
                cr.add(Restrictions.eq(parameter.getName(), parameter.getValue()));
            }
            if ((parameter.getName().equals("pets") || parameter.getName().equals("children") || parameter.getName().equals("family")
                    || parameter.getName().equals("smoking")) && !parameter.getValue().isEmpty() && !parameter.getValue().equals("false")) {
                cr.add(Restrictions.eq(parameter.getName(), Boolean.parseBoolean(parameter.getValue())));
            }
            if (parameter.getName().equals("minNumberOfGuests") || parameter.getName().equals("maxNumberOfGuests")) {
                if (parameter.getName().equals("minNumberOfGuests") && !parameter.getValue().isEmpty()) {
                    min = parameter.getName();
                    valueMin = parameter.getValue();
                }
                if (parameter.getName().equals("maxNumberOfGuests") && !parameter.getValue().isEmpty()) {
                    max = parameter.getName();
                    valueMax = parameter.getValue();
                }
                if (!valueMax.isEmpty() && !valueMin.isEmpty()) {
                    cr.add(Restrictions.and(Restrictions.ge(max, Integer.parseInt(valueMax)),
                            Restrictions.le(min, Integer.parseInt(valueMin))));
                }
            }
            if (parameter.getName().equals("gender")) {
                cr.add(Restrictions.eq(parameter.getName(), parsingGender(parameter.getValue())));
            }
        }
        return cr;
    }
}

