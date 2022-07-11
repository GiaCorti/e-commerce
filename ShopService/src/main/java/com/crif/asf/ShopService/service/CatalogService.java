package com.crif.asf.ShopService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.crif.asf.ShopService.exception.ProductAlreadyExistsException;
import com.crif.asf.ShopService.exception.ProductNotFoundException;
import com.crif.asf.ShopService.model.Filter;
import com.crif.asf.ShopService.model.Product;
import com.crif.asf.ShopService.repository.CatalogRepository;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    public List<Product> findAll(Integer page, Integer numElements) {
	return catalogRepository.findAll(PageRequest.of(page, numElements)).getContent();
    }

    public void insertNewProduct(Product p) {
	if (catalogRepository.existsByName(p.getName()))
	    throw new ProductAlreadyExistsException();
	catalogRepository.save(p);
    }

    public void editProduct(Product p, Integer id) {
	if (!catalogRepository.existsById(id))
	    throw new ProductNotFoundException();
	p.setId(id);
	catalogRepository.save(p);
    }

    public void deleteProduct(Integer id) {
	if (!catalogRepository.existsById(id))
	    throw new ProductNotFoundException();
	catalogRepository.deleteById(id);
    }

    public Product getProductById(Integer id) {
	if (!catalogRepository.existsById(id))
	    throw new ProductNotFoundException();
	return catalogRepository.findById(id).get();
    }

    public boolean existsById(Integer id) {
	return catalogRepository.existsById(id);
    }

    public List<Product> findAllFiltered(Filter f, Integer page, Integer numElements) {
	if (f.getOrder().equals("desc"))
	    return catalogRepository.findByPriceBetweenOrderByPriceDesc(
		    f.getMinPrice(), f.getMaxPrice(),
		    PageRequest.of(page, numElements)).getContent();

	else
	    return catalogRepository.findByPriceBetweenOrderByPriceAsc(
		    f.getMinPrice(), f.getMaxPrice(),
		    PageRequest.of(page, numElements)).getContent();
    }

}
