package com.ucdb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucdb.dao.DisciplinaDAO;
import com.ucdb.model.Disciplina;

@Service
public class DisciplinaService {

	
	private final DisciplinaDAO disciplinaDao;

	public DisciplinaService(DisciplinaDAO disciplinaDao) {
		this.disciplinaDao = disciplinaDao;
	}
	
	public Disciplina create(Disciplina disciplina) {
		return disciplinaDao.save(disciplina);
	}
	
	public List<List<String>> findBySubString(String disciplina){
		return this.disciplinaDao.findBySubString(disciplina);
	}
	
	public List<List<String>> getAll() {
		return this.disciplinaDao.getAll();
	}
	
	
}
