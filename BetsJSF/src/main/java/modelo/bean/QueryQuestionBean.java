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
	public String event;
	public String Question;
	public List<Event> events=new ArrayList<Event>();
	public List<Question> questions=new ArrayList<Question>();
	public BLFacade bl=new BLFacadeImplementation();
	Event evento;
	Question pregunta;
	
	public Date getDateOE() {
		return dateOE;
	}

	public void setDateOE(Date dateOE) {
		this.dateOE = dateOE;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public BLFacade getBl() {
		return bl;
	}

	public void setBl(BLFacade bl) {
		this.bl = bl;
	}

	public Event getEvento() {
		return evento;
	}

	public void setEvento(Event evento) {
		this.evento = evento;
	}

	public Question getPregunta() {
		return pregunta;
	}

	public void setPregunta(Question pregunta) {
		this.pregunta = pregunta;
	}
	
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
		setQuestions(even);
	}

	private void setQuestions(Event object) {

		this.questions=object.getQuestions();

	}
}
