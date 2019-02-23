package com.bridgelabz.fundoo.note.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Note_Details")
@Setter
@Getter
@ToString
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long noteId;
	private Long userId;
	private String title;
	private String content;
	private LocalDate createStamp = LocalDate.now();
	private LocalDate lastModifiedStamp = LocalDate.now();
	private boolean isPinned;
	private String color;
	private boolean isArchive;
	private boolean isTrash;
	private LocalDateTime remainder;
	
	public Long getNoteId() {
		return noteId;
	}
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDate getCreateStamp() {
		return createStamp;
	}
	public void setCreateStamp(LocalDate createStamp) {
		this.createStamp = createStamp;
	}
	public LocalDate getLastModifiedStamp() {
		return lastModifiedStamp;
	}
	public void setLastModifiedStamp(LocalDate lastModifiedStamp) {
		this.lastModifiedStamp = lastModifiedStamp;
	}
	public boolean isPinned() {
		return isPinned;
	}
	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isArchive() {
		return isArchive;
	}
	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}
	public boolean isTrash() {
		return isTrash;
	}
	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}
	public LocalDateTime getRemainder() {
		return remainder;
	}
	public void setRemainder(LocalDateTime remainder) {
		this.remainder = remainder;
	}
}
