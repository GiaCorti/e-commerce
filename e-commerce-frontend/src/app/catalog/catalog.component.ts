import { CartOrder } from '../models/cartOrder';
import { CartService } from './../services/cart.service';
import { AuthService } from './../services/auth.service';
import { Component, OnInit, QueryList } from '@angular/core';
import { Filter } from '../models/filter';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';
import { Subscriber } from 'rxjs';

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
  isAdmin = false;
  initValue = 1;

  constructor(
    private productService: ProductService, 
    private authService: AuthService,
    private cartService: CartService) {
    this.stateOptions = [{ label: 'Ascending', value: 'asc' }, { label: 'Descending', value: 'desc' }];
  }

  ngOnInit(): void {
    this.authService.isAdmin().subscribe(r => {
      console.log(r);
      this.isAdmin = r;
    });
    this.getNumOfProducts();
    this.getFilteredCatalog(0, 1000000);
  }

  getNumOfProducts(): void {
    this.productService.getNumOfProducts().subscribe(tot => this.totProducts = tot);
  }

  getNumOfProductsFiltered(min: number, max: number): void {
    this.productService.getNumOfProductsFiltered(min, max)
      .subscribe(tot => this.totProducts = tot);
  }


  getCatalog(): void {
    this.productService.getCatalog(this.page, this.rows).subscribe(catalog => this.catalog = catalog);
  }

  getFilteredCatalog(min: number, max: number): void {
    const f: Filter = {
      minPrice: min,
      maxPrice: max,
      order: this.value
    }
    this.getNumOfProductsFiltered(min, max);
    this.productService.getFilteredCatalog(f, this.page, this.rows).subscribe(catalog => {
      this.catalog = catalog;
    });
  }

  paginate(event: any) {
    this.rows = event.rows;
    this.page = event.page;
    this.getFilteredCatalog(this.rangeValues[0], this.rangeValues[1]);
  }

  addToCart(idInput: number, qtyInput:number) {
    console.log(qtyInput)
    const cartOrder: CartOrder = {
        idProduct: idInput,
        qty: qtyInput
    }

    this.cartService.addItemToCart(cartOrder).subscribe(_ => window.alert("product added to your cart"));
  }
}
