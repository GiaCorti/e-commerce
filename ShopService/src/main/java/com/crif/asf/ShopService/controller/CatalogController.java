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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crif.asf.ShopService.model.Comment;
import com.crif.asf.ShopService.model.Filter;
import com.crif.asf.ShopService.model.Product;
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

    @GetMapping
    public List<Product> getAllProduct(
	    @RequestParam(name = "page", required = true) Integer page,
	    @RequestParam(name = "numElements", required = true) Integer numElements) {
	return catalogService.findAll(page, numElements);
    }

    @PostMapping
    public void insertNewProduct(@RequestBody Product p) {
	catalogService.insertNewProduct(p);
    }

    @PutMapping("/{id_product}")
    public void editProduct(@RequestBody Product p,
	    @PathVariable(name = "id_product", required = true) Integer id) {
	catalogService.editProduct(p, id);
    }

    @DeleteMapping("/{id_product}")
    public void deleteProduct(@PathVariable(name = "id_product", required = true) Integer id) {
	catalogService.deleteProduct(id);
    }

    @GetMapping("/{id_product}")
    public Product detail(@PathVariable(name = "id_product", required = true) Integer id) {
	return catalogService.getProductById(id);
    }

    @GetMapping("/{id_product}/comments")
    public List<Comment> getComments(@PathVariable(name = "id_product", required = true) Integer id) {
	return commentService.getComments(id);
    }

    @PostMapping("{id_product}/comments")
    public void insertNewComment(@RequestBody Comment c) {
	commentService.insertNewComment(c);
    }

    @PostMapping("/search")
    public List<Product> getFilteredProducts(
	    @RequestBody Filter f,
	    @RequestParam(name = "page", required = true) Integer page,
	    @RequestParam(name = "numElements", required = true) Integer numElements) {
	return catalogService.findAllFiltered(f, page, numElements);
    }

}
