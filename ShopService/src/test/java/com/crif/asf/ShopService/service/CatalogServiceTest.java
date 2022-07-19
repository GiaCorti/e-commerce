package com.crif.asf.ShopService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.crif.asf.ShopService.model.Filter;
import com.crif.asf.ShopService.model.Product;
import com.crif.asf.ShopService.repository.CatalogRepository;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceTest {

    @InjectMocks
    private CatalogService catalogService;
    @Mock
    private CatalogRepository catalogRepository;

    @Test
    void findAllProductTest() {
	List<Product> productList = List.of(new Product(0, "bike", "a modern bike", 120.0),
		new Product(1, "hammer", "an incredible hammer", 11.5));

	Mockito.when(catalogRepository.findAll(PageRequest.of(0, 2)))
		.thenReturn(new PageImpl<>(productList));
	List<Product> result = catalogService.findAll(0, 2);
	assertEquals(result, productList);
    }

    @Test
    void findAllProductFilteredTest() {
	List<Product> productList = List.of(new Product(0, "bike", "a modern bike", 120.0),
		new Product(1, "hammer", "an incredible hammer", 11.5),
		new Product(2, "laptop", "a new gen computer", 1299.99));

	Mockito.when(catalogRepository.findByPriceBetweenOrderByPriceDesc(10.0, 130.0, PageRequest.of(0, 2)))
		.thenReturn(new PageImpl<>(productList));
	List<Product> result = catalogService.findAllFiltered(new Filter(10.0, 130.0, "desc"), 0, 2);
	assertEquals(result, productList);
    }

    @Test
    void findByIdTest() {
	Integer id = 1;
	Product p1 = new Product(id, "hammer", "an incredible hammer", 11.5);

	Mockito.when(catalogRepository.existsById(id)).thenReturn(true);
	Mockito.when(catalogRepository.findById(1)).thenReturn(Optional.of(p1));
	Product result = catalogService.getProductById(id);
	assertEquals(result, p1);
    }

    @Test
    void insertNewProductTest() {
	Product p1 = new Product(1, "hammer", "an incredible hammer", 11.5);
	catalogService.insertNewProduct(p1);
	verify(catalogRepository, times(1)).save(p1);
    }

    @Test
    void editProductTest() {
	Integer id = 1;
	Product p1 = new Product(id, "hammer", "an incredible hammer", 10.0);
	Mockito.when(catalogRepository.existsById(id)).thenReturn(true);
	catalogService.editProduct(p1, id);
	verify(catalogRepository, times(1)).save(p1);
    }

    @Test
    void deleteProductTest() {
	Integer id = 1;
	Mockito.when(catalogRepository.existsById(id)).thenReturn(true);
	catalogService.deleteProduct(id);
	verify(catalogRepository, times(1)).deleteById(id);
    }

}
