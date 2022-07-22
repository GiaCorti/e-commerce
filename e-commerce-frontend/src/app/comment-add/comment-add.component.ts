import { Component, OnInit } from '@angular/core';
import { newComment } from '../models/comment';
import { ProductID } from '../models/product';
import { AuthService } from '../services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-comment-add',
  templateUrl: './comment-add.component.html',
  styleUrls: ['./comment-add.component.css']
})
export class CommentAddComponent implements OnInit {

  constructor(private authService: AuthService,private route: ActivatedRoute,private prodService: ProductService) { }

  product : ProductID = {
    id: 0
  }
  comment : newComment ={
    idUser : '',
    text: '',
    product: this.product
  };
  
  idprod: string = ''
  text: string = ''

  ngOnInit(): void {
    this.authService.getUser().subscribe(r => {
      this.comment.idUser = r;
      this.setComment();
    })
    
  }
  setComment() {
    this.idprod = String(this.route.snapshot.paramMap.get('id_product'));
    this.product.id = Number(this.route.snapshot.paramMap.get('id_product'));
    this.comment.product = this.product;
  }

  addReview(){
    this.comment.text = this.text;
    this.prodService.addComment(this.idprod,this.comment).subscribe(()=>window.location.reload())

  }

 


}
