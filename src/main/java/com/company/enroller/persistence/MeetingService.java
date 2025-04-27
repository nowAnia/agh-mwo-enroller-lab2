package com.company.enroller.persistence;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingService")
public class MeetingService {

	Session session;
	DatabaseConnector connector;

	public MeetingService() {
	//	session = DatabaseConnector.getInstance().getSession();
			connector = DatabaseConnector.getInstance();
			this.session = connector.getSession();

	}

	public Collection<Meeting> getAll() {
		String hql = "FROM Meeting";
		Query query = this.session.createQuery(hql);
		return query.list();
	}


	public Meeting findById(Long id) {
			return connector.getSession().get(Meeting.class, id);
	}

	public Collection<Meeting> findByTitle(String title) {
		String hql = "FROM Meeting WHERE title =:title";
		Query query = this.connector.getSession().createQuery(hql);
		query.setParameter("title", title);
		return query.list();
	}

	public Meeting add(Meeting meeting) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(meeting);
		transaction.commit();
		return meeting;
	}

	public void delete(Meeting meeting) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().delete(meeting);
		transaction.commit();
	}
}
