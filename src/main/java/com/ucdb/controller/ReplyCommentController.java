package com.ucdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucdb.model.ReplyComment;
import com.ucdb.service.ReplyCommentService;

@RestController
@RequestMapping({"/v1/perfil"})
public class ReplyCommentController {

	@Autowired
	private ReplyCommentService replyCommentService;
	
	@PostMapping(value = "/comentar/reply/{idComment}/{email}")
	@ResponseBody
	public ResponseEntity<ReplyComment> replyComment(@PathVariable long idComment, @PathVariable String email,
			@RequestBody ReplyComment reply) {

		return new ResponseEntity<ReplyComment>(this.replyCommentService.replyComment(idComment, email, reply),
				HttpStatus.OK);
	}
	
	@PutMapping(value = "/removecommentreply/{idComment}/{idReplyComment}/{idPerfil}/{email}")
	@ResponseBody
	public ResponseEntity<ReplyComment> removeComment(@PathVariable long idComment,@PathVariable long idReplyComment, @PathVariable long idPerfil, 
			@PathVariable String email) {
		ReplyComment r = this.replyCommentService.removeReplyComment(idComment, idReplyComment, idPerfil, email);
		if (r == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<ReplyComment>(r, HttpStatus.ACCEPTED);
		}
	}
}
