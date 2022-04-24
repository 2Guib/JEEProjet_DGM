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
public class Task {

	@Id @GeneratedValue
	private int id_task;
	
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date last_change;

	private boolean done;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date planned_close_date;
	
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
	
	
	
}
