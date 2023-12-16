package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;
import domain.Question;

public class QueryQuestionBean {
	public Date dateOE;
	public List<Event> listEv;
	public List<domain.Question> listQ;
	public String event;
	public String Question;
	public List<Event> events=new ArrayList<Event>();
	public List<Question> questions=new ArrayList<Question>();
	public BLFacade bl=new BLFacadeImplementation();
	
	public Date getFecha() {
		return dateOE;
	}
	
	public void setFecha(Date dateOE) {
		this.dateOE = dateOE;
	}
	
	public void setEvents(Date d){
		events.clear();

		List<Event> eventsVector = bl.getEvents(d);
		if(eventsVector.size()<0) {

		}else {
			Iterator<Event> i= eventsVector.iterator();
			while(i.hasNext()) {
				events.add(i.next());
			}
		}
	}
	
	public void onDateSelect(SelectEvent event) {

		setEvents((Date)event.getObject());
	}
	
	public void onRowSelect(SelectEvent eve) {
		Event even=(Event)eve.getObject();
		setQuestions((Event) eve.getObject());
	}

	private void setQuestions(Event object) {

		this.questions = questions;

	}
}
