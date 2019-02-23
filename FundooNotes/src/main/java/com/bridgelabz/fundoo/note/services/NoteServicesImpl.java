package com.bridgelabz.fundoo.note.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.repository.NoteRepository;

public class NoteServicesImpl implements NoteServices{
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void create(NoteDto noteDto, Long userId) {
		Optional<Note> noteAvailable = noteRepository.findById(userId);
		Note note = modelMapper.map(noteDto, Note.class);
		noteRepository.save(note);
		noteDto.setUserId(userId);
	}

	@Override
	public void delete(Long noteId) {
		Note note = noteRepository.findById(noteId).get();
		noteRepository.delete(note);
	}
}
