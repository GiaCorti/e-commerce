import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { Comment } from '../models/comment';

@Component({
  selector: 'app-comments-list',
  templateUrl: './comments-list.component.html',
  styleUrls: ['./comments-list.component.css']
})
export class CommentsListComponent implements OnInit {

  constructor(private prodService: ProductService,
    private route: ActivatedRoute) { }

  comments: Comment[] = []
  id_prod: string = ''


  ngOnInit(): void {
    this.getComments();
  }

  getComments() {
    this.id_prod = String(this.route.snapshot.paramMap.get('id_product'));
    
   this.prodService.getComments(this.id_prod).subscribe(res => {this.comments = res
  console.log(res)})

  }

}
