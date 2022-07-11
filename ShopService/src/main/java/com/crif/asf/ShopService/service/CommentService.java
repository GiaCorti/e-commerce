package com.crif.asf.ShopService.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crif.asf.ShopService.exception.ProductNotFoundException;
import com.crif.asf.ShopService.model.Comment;
import com.crif.asf.ShopService.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CatalogService catalogService;

    public List<Comment> getComments(Integer id) {
	if (!catalogService.existsById(id))
	    throw new ProductNotFoundException();
	return commentRepository.findByIdProduct(id);
    }

    public void insertNewComment(Comment c, String idUser) {

	c.setCommentDate(LocalDateTime.now());
	c.setIdUser(idUser);
	commentRepository.save(c);
    }

}
