package com.crif.asf.ShopService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crif.asf.ShopService.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select c from Comment c where c.product.id = ?1")
    List<Comment> findByIdProduct(Integer id);
}
