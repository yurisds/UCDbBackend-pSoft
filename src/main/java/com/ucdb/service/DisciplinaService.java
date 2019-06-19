package com.ucdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucdb.dao.DisciplinaDAO;
import com.ucdb.dao.UserDAO;
import com.ucdb.model.Disciplina;
import com.ucdb.model.User;

@Service
public class DisciplinaService {

	
	private final DisciplinaDAO disciplinaDao;
	
	@Autowired
	private UserDAO userDAO;

	public DisciplinaService(DisciplinaDAO disciplinaDao) {
		this.disciplinaDao = disciplinaDao;
	}
	
	public Disciplina create(Disciplina disciplina) {
		return this.disciplinaDao.save(disciplina);
	}
	
	public List<List<String>> findBySubString(String disciplina){
		return this.disciplinaDao.findBySubString(disciplina);
	}
	
	public List<List<String>> getAll() {
		return this.disciplinaDao.getAll();
	}
	
	public Disciplina getById(long codigo) {
		return this.disciplinaDao.findById(codigo);
	}

	public Disciplina usuarioCurtiu(long id, String email) {
		User u = this.userDAO.findByEmail(email);
		Disciplina d = this.disciplinaDao.findById(id);	
		d.getUsers().add(u);
		
		return this.disciplinaDao.save(d);
		
	}	
}
