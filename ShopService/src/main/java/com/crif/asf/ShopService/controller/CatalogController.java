package com.crif.asf.ShopService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crif.asf.ShopService.exception.NotAnAdminException;
import com.crif.asf.ShopService.exception.TokenNotValidException;
import com.crif.asf.ShopService.model.Comment;
import com.crif.asf.ShopService.model.Filter;
import com.crif.asf.ShopService.model.Product;
import com.crif.asf.ShopService.service.AuthService;
import com.crif.asf.ShopService.service.CatalogService;
import com.crif.asf.ShopService.service.CommentService;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class CatalogController {

    @Autowired
    CatalogService catalogService;
    @Autowired
    CommentService commentService;
    @Autowired
    AuthService authService;

    @GetMapping
    public List<Product> getAllProduct(
	    @RequestParam(name = "page", required = true) Integer page,
	    @RequestParam(name = "numElements", required = true) Integer numElements,
	    @RequestHeader("Authorization") String token) {

	if (!authService.isTokenValid(token))
	    throw new TokenNotValidException();

	return catalogService.findAll(page, numElements);
    }

    @PostMapping
    public void insertNewProduct(@RequestBody Product p,
	    @RequestHeader("Authorization") String token) {
	if (!authService.isAdmin(token))
	    throw new NotAnAdminException();
	catalogService.insertNewProduct(p);
    }

    @PutMapping("/{id_product}")
    public void editProduct(@RequestBody Product p,
	    @PathVariable(name = "id_product", required = true) Integer id,
	    @RequestHeader("Authorization") String token) {
	if (!authService.isAdmin(token))
	    throw new NotAnAdminException();
	catalogService.editProduct(p, id);
    }

    @DeleteMapping("/{id_product}")
    public void deleteProduct(@PathVariable(name = "id_product", required = true) Integer id,
	    @RequestHeader("Authorization") String token) {
	if (!authService.isAdmin(token))
	    throw new NotAnAdminException();
	catalogService.deleteProduct(id);
    }

    @GetMapping("/{id_product}")
    public Product detail(@PathVariable(name = "id_product", required = true) Integer id,
	    @RequestHeader("Authorization") String token) {
	if (!authService.isTokenValid(token))
	    throw new TokenNotValidException();
	return catalogService.getProductById(id);
    }

    @GetMapping("/{id_product}/comments")
    public List<Comment> getComments(@PathVariable(name = "id_product", required = true) Integer id,
	    @RequestHeader("Authorization") String token) {
	if (!authService.isTokenValid(token))
	    throw new TokenNotValidException();
	return commentService.getComments(id);
    }

    @PostMapping("{id_product}/comments")
    public void insertNewComment(@RequestBody Comment c,
	    @RequestHeader("Authorization") String token) {
	String idUser = authService.getIdUser(token);
	if (idUser == null)
	    throw new TokenNotValidException();
	commentService.insertNewComment(c, idUser);
    }

    @PostMapping("/search")
    public List<Product> getFilteredProducts(
	    @RequestBody Filter f,
	    @RequestParam(name = "page", required = true) Integer page,
	    @RequestParam(name = "numElements", required = true) Integer numElements,
	    @RequestHeader("Authorization") String token) {
	if (!authService.isTokenValid(token))
	    throw new TokenNotValidException();
	return catalogService.findAllFiltered(f, page, numElements);
    }

}
