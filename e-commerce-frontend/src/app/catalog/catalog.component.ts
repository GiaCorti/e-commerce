import { Component, OnInit } from '@angular/core';
import { Filter } from '../models/filter';
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
  rangeValues: number[] = [0, 1000];
  stateOptions: any[];
  value: string = "asc";
  page: number = 0;
  rows: number = 5;
  totProducts: number = 0;

  constructor(private productService: ProductService) {
    this.stateOptions = [{ label: 'Ascending', value: 'asc' }, { label: 'Descending', value: 'desc' }];
  }

  ngOnInit(): void {
    this.getNumOfProducts();
    this.getCatalog();
  }

  getNumOfProducts(): void {
    this.productService.getNumOfProducts().subscribe(tot => this.totProducts = tot);
  }

  getNumOfProductsFiltered(): void {
    this.productService.getNumOfProductsFiltered(this.rangeValues[0], this.rangeValues[1])
      .subscribe(tot => this.totProducts = tot);
  }


  getCatalog(): void {
    this.productService.getCatalog(this.page, this.rows).subscribe(catalog => this.catalog = catalog);
  }

  getFilteredCatalog(): void {
    const f: Filter = {
      minPrice: this.rangeValues[0],
      maxPrice: this.rangeValues[1],
      order: this.value
    }
    this.getNumOfProductsFiltered();
    this.productService.getFilteredCatalog(f, this.page, this.rows).subscribe(catalog => {
      this.catalog = catalog;
    });
  }

  paginate(event: any) {
    this.rows = event.rows;
    this.page = event.page;
    this.getFilteredCatalog();
  }

}
