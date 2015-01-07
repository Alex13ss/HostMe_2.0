package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.UserDao;
import com.softserve.edu.model.Role;
import com.softserve.edu.model.User;
import com.softserve.edu.model.UserState;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractGenericDao<User, Integer> implements
		UserDao {
	public UserDaoImpl() {
		super(User.class);
	}
	
	private final int USER = 1;

	@Override
	public User getUserByEmail(String email) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
	//	User user = (User) criteria.list().get(0);
        	User user = (User) criteria.uniqueResult();
		return user;
	}

	@Override
	public User getUserByLogin(String login) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
	//	User user = (User) criteria.list().get(0);
        	User user = (User) criteria.uniqueResult();
		return user;
	}
	
	@Override
    	public void activateAccount(Integer userId) {
        	Session session = getSessionFactory().getCurrentSession();
        	Criteria cr = session.createCriteria(User.class);
        	cr.add(Restrictions.eq("userId", userId));
        	User user = (User) cr.uniqueResult();
        	user.setUserState(UserState.ACTIVE);
        	Role registeredUser = new Role();
        	registeredUser.setRoleId(USER);
        	user.setRole(registeredUser);
    }

}
