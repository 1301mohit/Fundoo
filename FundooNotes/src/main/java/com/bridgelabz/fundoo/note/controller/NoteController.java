package com.bridgelabz.fundoo.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.services.NoteServices;

public class NoteController {
	
	@Autowired
	private NoteServices noteServices;
	
	@PostMapping("/note/create")
	public ResponseEntity<String> create(@RequestBody NoteDto noteDto, Long userId){
		noteServices.create(noteDto, userId);
		return new ResponseEntity<String>("Create a new note", HttpStatus.OK);
	}
}
