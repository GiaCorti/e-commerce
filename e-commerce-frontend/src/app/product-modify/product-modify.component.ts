import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product-modify',
  templateUrl: './product-modify.component.html',
  styleUrls: ['./product-modify.component.css']
})
export class ProductModifyComponent implements OnInit {

  product: Product | undefined;
  id: string = "";

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
    ) { }

  ngOnInit(): void {
    this.id = String(this.route.snapshot.paramMap.get('id_product'));
    this.getProduct(this.id);
  }


  getProduct(id: string) {
    this.productService.getProduct(id).subscribe(p => this.product = p);
  }

  modifyProduct() {
    this.productService.modifyProduct(this.id, this.product!)
      .subscribe(_ => this.location.back());
  }
}
