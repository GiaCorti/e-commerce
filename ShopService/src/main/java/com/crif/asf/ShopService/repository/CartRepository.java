package com.crif.asf.ShopService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.crif.asf.ShopService.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByIdUserAndCompletedIsFalse(String idUser);

    @Modifying
    @Query("delete from Cart c where c.product.id=?1 and c.idUser=?2 and c.completed=false")
    void deleteByIdProductAndIdUser(Integer idProduct, String idUser);

    @Query("select case when count(c)> 0 then true else false end "
	    + "from Cart c where c.product.id=?1 and c.idUser=?2 and c.completed=false")
    boolean existsByIdProductAndIdUser(Integer idProduct, String idUser);

}
