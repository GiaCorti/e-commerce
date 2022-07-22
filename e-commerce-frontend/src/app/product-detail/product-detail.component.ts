import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product: Product | undefined;
  id = "";
  format = ".jpg";
  constructor(private productService: ProductService, private route: ActivatedRoute ) { }

  ngOnInit(): void {
    this.id = String(this.route.snapshot.paramMap.get('id_product'));
    this.getProduct(this.id);
  }

  getProduct(id: string) {
    this.productService.getProduct(id).subscribe(p => this.product = p);
  }

}
