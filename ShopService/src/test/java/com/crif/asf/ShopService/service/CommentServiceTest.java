package com.crif.asf.ShopService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crif.asf.ShopService.model.Comment;
import com.crif.asf.ShopService.model.Product;
import com.crif.asf.ShopService.repository.CommentRepository;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CatalogService catalogService;

    @Test
    void getCommentsTest() {
	Integer idProduct = 1;
	Product p1 = new Product(idProduct, "hammer", "an incredible hammer", 11.5);
	List<Comment> comments = List.of(
		new Comment(1, "user1",
			p1, "Nice product", LocalDateTime.now()),
		new Comment(2, "user2",
			p1, "Bad product", LocalDateTime.now()));

	Mockito.when(catalogService.existsById(idProduct)).thenReturn(true);
	Mockito.when(commentService.getComments(idProduct)).thenReturn(comments);

	List<Comment> res = commentService.getComments(idProduct);
	assertEquals(res, comments);

    }

    @Test
    void insertNewCommentTest() {
	Integer idProduct = 1;
	String user = "user1";
	Product p1 = new Product(idProduct, "hammer", "an incredible hammer", 11.5);
	Comment c1 = new Comment(1, "user1", p1, "Nice product", LocalDateTime.now());

	commentService.insertNewComment(c1, user);
	verify(commentRepository, times(1)).save(c1);
    }

}
