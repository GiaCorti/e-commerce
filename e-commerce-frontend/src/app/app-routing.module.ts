import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountDetailComponent } from './account-detail/account-detail.component';
import { AccountModifyComponent } from './account-modify/account-modify.component';
import { AccountRegisterComponent } from './account-register/account-register.component';
import { AccountsListComponent } from './accounts-list/accounts-list.component';
import { CartComponent } from './cart/cart.component';
import { CatalogComponent } from './catalog/catalog.component';
import { CommentAddComponent } from './comment-add/comment-add.component';
import { CommentsListComponent } from './comments-list/comments-list.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProductAddComponent } from './product-add/product-add.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductModifyComponent } from './product-modify/product-modify.component';
import { ReceiptComponent } from './receipt/receipt.component';


const routes: Routes = [
  {path: 'home', component: HomeComponent },
  {path: 'login', component: LoginComponent},
  {path: "", redirectTo: "home", pathMatch: "full"},
  {path: 'account/detail/:id_account', component: AccountDetailComponent},
  {path: 'account/modify/:id_account', component: AccountModifyComponent},
  {path: 'account/register', component:AccountRegisterComponent},
  {path: 'account/list', component: AccountsListComponent},
  {path: 'cart', component:CartComponent},
  {path: 'product/catalog', component:CatalogComponent},
  {path: 'comment/list/:id_product', component:CommentsListComponent},
  {path: 'comment/add/:id_product', component:CommentAddComponent},
  {path: 'product/add', component:ProductAddComponent},
  {path: 'product/detail/:id_product', component:ProductDetailComponent},
  {path: 'product/modify/:id_product', component:ProductModifyComponent},
  {path: 'receipt', component: ReceiptComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
