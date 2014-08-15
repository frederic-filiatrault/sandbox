package com.openCasa.cloud.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openCasa.cloud.web.model.User;

public class UserTest {
 private final static Logger LOGGER = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	// private final static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

	private static final String PERSISTENCE_UNIT_NAME = "testDB";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		LOGGER.debug("All User:");
		listAllUsers();
		LOGGER.debug("Create user 1");
		createUser("fred1", "Frederic", "Filiatrault");
		LOGGER.debug("All User:");
		listAllUsers();
	}

	private static void createUser(String userName, String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		//
		em.getTransaction().begin();
		User user = new User();
		user.setUserName(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		em.persist(user);
		em.getTransaction().commit();

		em.close();
	}

	private static void listAllUsers() {
		EntityManager em = factory.createEntityManager();
		// read the existing entries and write to console
		Query q = em.createQuery("select u from Users u");
		@SuppressWarnings("unchecked")
		List<User> userList = q.getResultList();
		for (User user : userList) {
			LOGGER.debug(user);
		}
		LOGGER.debug("Size: " + userList.size());

	}

}
