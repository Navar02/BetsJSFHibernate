package businessLogic;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import configuration.ConfigXML;
import dataAccess.DataAccessInterface;
import dataAccess.HDAO;
import domain.Question;
import domain.User;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.HibernateUtil;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation  implements BLFacade {
	HDAO newDB;

	//No hace falta llamar a sesion aqui
	//Con llamar a los metodos de la HDAO es suficiente
	public BLFacadeImplementation()  {		
		newDB=new HDAO();

	}



	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{

		/*
		 * //The minimum bed must be greater than 0 dbManager.open(); Question qry=null;
		 * 
		 */
		if(new Date().compareTo(event.getEventDate())>0) throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		Question qry= null;
		try {
			qry = newDB.createQuestion(event,question,betMinimum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date)  {
		return newDB.getEvents(date);
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		Vector<Date> dates = newDB.getEventsMonth(date);
		return dates;
	}


	public void close() {
		//DataAccess dB4oManager=new DataAccess(false);

		//dB4oManager.close();
		//		dbManager.close();


	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeBD(){
		newDB.initializeDB(); 
		/*
		 * dbManager.open(); 
		 * dbManager.close();
		 */
	}


	public User addUsuario(String Usuario, String password) {
		return newDB.addUsuario(Usuario, password);
	}


	public User getUser(String Usuario) {
		return newDB.getUser(Usuario);
	}


	public boolean canLogIn(String Usuario, String password) {
		return newDB.canLogIn(Usuario, password);
	}

}

