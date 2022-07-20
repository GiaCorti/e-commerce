import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ButtonModule} from 'primeng/button';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import {InputTextModule} from 'primeng/inputtext';
import {PasswordModule} from 'primeng/password';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './nav/nav.component';
import { CatalogComponent } from './catalog/catalog.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductModifyComponent } from './product-modify/product-modify.component';
import { ProductAddComponent } from './product-add/product-add.component';
import { CartComponent } from './cart/cart.component';
import { ReceiptComponent } from './receipt/receipt.component';
import { AccountRegisterComponent } from './account-register/account-register.component';
import { AccountModifyComponent } from './account-modify/account-modify.component';
import { AccountDetailComponent } from './account-detail/account-detail.component';
import { AccountsListComponent } from './accounts-list/accounts-list.component';
import { CommentsListComponent } from './comments-list/comments-list.component';
import { CommentAddComponent } from './comment-add/comment-add.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavComponent,
    CatalogComponent,
    ProductDetailComponent,
    ProductModifyComponent,
    ProductAddComponent,
    CartComponent,
    ReceiptComponent,
    AccountRegisterComponent,
    AccountModifyComponent,
    AccountDetailComponent,
    AccountsListComponent,
    CommentsListComponent,
    CommentAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    ReactiveFormsModule,
    FormsModule,
    InputTextModule,
    PasswordModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
