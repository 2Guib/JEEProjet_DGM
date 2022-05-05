package com.deguibert.todolist.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity @Table(name = "tasks")
public class Task implements Comparable<Task>{

	@Id @GeneratedValue
	private int id_task;
	
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date last_change;

	private boolean done;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date planned_close_date;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date close_date;
	
	private String description;
	
	private String img;
	
	@ManyToOne 
    @JoinColumn( name="id_user", nullable=false )
	private User user;
	
	@ManyToMany
	@JoinTable(name = "affected", joinColumns = @JoinColumn ( name = "id_task" ), inverseJoinColumns = @JoinColumn ( name = "id_tag"))
	private List<Tag> tags;
	
	@Override
	public String toString() {
		return this.title;
	}
	
	@Override
	public int compareTo(Task t) {
		//On compare si la tache est finie
		int comp = Boolean.compare(this.isDone(), t.isDone());
		
		if (comp == 0 && !this.isDone()) {
			//Si les deux non finie on compare par date prévue de fermeture si existante
			comp = Boolean.compare(this.getPlanned_close_date() == null, t.getPlanned_close_date() == null);
			if (comp == 0 && this.getPlanned_close_date() != null) {
				comp = this.getPlanned_close_date().compareTo(t.getPlanned_close_date());
			}
		} else if (comp == 0 && this.isDone() ) {
			//Si les deux finie on compare par date de cloture
			comp = this.getClose_date().compareTo(t.getClose_date());
		}
		
		//Si equivalente on compare par titre
		if (comp == 0) {
			comp = this.getTitle().compareTo(t.getTitle());
		}
		return comp;
	}

	public int getId_task() {
		return id_task;
	}

	public void setId_task(int id_task) {
		this.id_task = id_task;
	}
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getLast_change() {
		return last_change;
	}

	public void setLast_change(Date last_change) {
		this.last_change = last_change;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getPlanned_close_date() {
		return planned_close_date;
	}

	public void setPlanned_close_date(Date planned_close_date) {
		this.planned_close_date = planned_close_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Date getClose_date() {
		return close_date;
	}

	public void setClose_date(Date close_date) {
		this.close_date = close_date;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}
	
}
