package com.bridgelabz.fundoo.note.services;

import com.bridgelabz.fundoo.note.dto.NoteDto;

public interface NoteServices {
	public void create(NoteDto noteDto, Long userId);
	public void delete(Long noteId);
//	public void update(Long noteId);
}
