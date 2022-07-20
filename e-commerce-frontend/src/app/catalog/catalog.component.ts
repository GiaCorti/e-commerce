import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {


  catalog: Product[] = [];
  format: string = ".jpg";

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getCatalog();
  }

  getCatalog(): void {
    this.productService.getCatalog().subscribe(catalog => this.catalog = catalog);
  }

}
