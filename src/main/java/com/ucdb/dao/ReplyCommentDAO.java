package com.ucdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucdb.model.Comment;
import com.ucdb.model.ReplyComment;

@Repository
public interface ReplyCommentDAO extends JpaRepository<ReplyComment, Long>{
	
	public ReplyComment save(ReplyComment r);

}

