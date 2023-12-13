package dataAccess;

import java.util.Date;
import java.util.Vector;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class HDAO {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("../hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Fallo creando el SessionFactory." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

	}

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {

	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {

	}

	public void close() {

	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeBD() {

	}

	/**
	 * método para añadir un NUEVO usuario a la base de datos
	 * 
	 * @param Usuario
	 * @param password
	 * @return
	 */
	public User addUsuario(String Usuario, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * método para obtener los datos del usuario con nombre pasado por parámetro
	 * 
	 * @param Usuario
	 * @return
	 */
	public User getUser(String Usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Comprobar si el usuario y contraseña son correctos (coinciden con los datos
	 * almacenados en la base de datos)
	 * 
	 * @param Usuario
	 * @param password
	 * @return
	 */
	public boolean canLogIn(String Usuario, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}
