import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {

  constructor(private productService: ProductService, private location: Location) { }

  addProductForm = new FormGroup({
    name: new FormControl(''),
    price: new FormControl(''),
    description: new FormControl('')
  });


  ngOnInit(): void {
    
  }

  onSubmit(): void {
    let nameInput = this.addProductForm.get('name')?.value!;
    let priceInput = parseInt(this.addProductForm.get('price')?.value!);
    let descriptionInput = this.addProductForm.get('description')?.value!;
    let product: Product = {
      name: nameInput,
      price: priceInput,
      description: descriptionInput
    }
    this.productService.addProduct(product).subscribe(_ => 
      {
        this.location.back();
      })
  }

}
