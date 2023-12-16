package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.HibernateUtil;

public class HDAO {
	private static SessionFactory sessionFactory ;
	
	public HDAO() {
		SessionFactory s=HibernateUtil.getSessionFactory();
		this.sessionFactory=s;
		initializeDB();
	}
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		Session sess=sessionFactory.getCurrentSession();
		System.out.println(sess);
		sess.beginTransaction();
		try {

			
			   Calendar today = Calendar.getInstance();
			   
			   int month=today.get(Calendar.MONTH);
			   month+=1;
			   int year=today.get(Calendar.YEAR);
			   if (month==12) { month=0; year+=1;}  
		    
				Event ev1=new Event(1, "Atl√©tico-Athletic", UtilDate.newDate(year,month,17));
				Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
				Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
				Event ev4=new Event(4, "Alav√©s-Deportivo", UtilDate.newDate(year,month,17));
				Event ev5=new Event(5, "Espa√±ol-Villareal", UtilDate.newDate(year,month,17));
				Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
				Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
				Event ev8=new Event(8, "Girona-Legan√©s", UtilDate.newDate(year,month,17));
				Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
				Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

				Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
				Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
				Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
				Event ev14=new Event(14, "Alav√©s-Deportivo", UtilDate.newDate(year,month,1));
				Event ev15=new Event(15, "Espa√±ol-Villareal", UtilDate.newDate(year,month,1));
				Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
				

				Event ev17=new Event(17, "M√°laga-Valencia", UtilDate.newDate(year,month,28));
				Event ev18=new Event(18, "Girona-Legan√©s", UtilDate.newDate(year,month,28));
				Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
				Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
				
				
				
				
				Question q1;
				Question q2;
				Question q3;
				Question q4;
				Question q5;
				Question q6;
						
				q1=ev1.addQuestion("Quien gana?",1);
				q2=ev1.addQuestion("Quien mete gol?",2);
				q3=ev11.addQuestion("Quien ganara?",1);
				q4=ev11.addQuestion("Cuantos goles?",2);
				q5=ev17.addQuestion("Quien va  a ganar?",1);
				q6=ev17.addQuestion("Habra gol?",2);
				
				sess.save(q1);
				sess.save(q2);
				sess.save(q3);
				sess.save(q4);
				sess.save(q5);
				sess.save(q6);
				
				sess.save(ev1);
				sess.save(ev2);
				sess.save(ev3);
				sess.save(ev4);
				sess.save(ev5);
				sess.save(ev6);
				sess.save(ev7);
				sess.save(ev8);
				sess.save(ev9);
				sess.save(ev10);
				sess.save(ev11);
				sess.save(ev12);
				sess.save(ev13);
				sess.save(ev14);
				sess.save(ev15);
				sess.save(ev16);
				sess.save(ev17);
				sess.save(ev18);
				sess.save(ev19);
				sess.save(ev20);
				
				sess.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}
				
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws Exception 
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws Exception {
		Session sess = sessionFactory.getCurrentSession();
		sess.getTransaction().begin();
		// Event ev = db.find(Event.class, event.getEventNumber());
		
		Question e = new Question();
		Query q = sess.createQuery("from event lg where lg.eventNumber= :EvNum");
		
		q.setParameter("EvNum", event.getEventNumber());
		
		List ev = q.list();
		
		if(ev.size()==0 || ev.size()!=1) {
			throw new Exception("Evento no existente.");
		}
		
		if (((Event) ev.get(0)).DoesQuestionExists(question)) {
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		}
		
		e.setEvent((Event) sess.get(Event.class, event.getEventNumber()));
		e.setQuestion(question);
		e.setBetMinimum(betMinimum);
		
		((Event) ev.get(0)).addQuestion(question, betMinimum);
		Event ev1= (Event) ev.get(0);
		sess.save(ev1);
		sess.save(e);
		sess.getTransaction().commit();
		
		return e;

	}

	@SuppressWarnings("unchecked")
	public Vector<Event> getEvents(Date date) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Event> result;
		try {
			Query q = session.createQuery("from Event where eventDate= :fechia");
			q.setParameter("fechia", date);
			result = (List<Event>) q.list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println("Error: " + ex.toString());
			result = null;
		}
		Vector<Event> resultFinal = new Vector<Event>();
		Iterator<Event> i = result.iterator();
		while (i.hasNext()) {
			resultFinal.add((Event) i.next());
		}
		return resultFinal;
	}


	/**
	 * mÈtodo para aÒadir un NUEVO usuario a la base de datos
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
	 * mÈtodo para obtener los datos del usuario con nombre pasado por par·metro
	 * 
	 * @param Usuario
	 * @return
	 */
	public User getUser(String Usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Comprobar si el usuario y contraseÒa son correctos (coinciden con los datos
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

	public Vector<Date> getEventsMonth(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
