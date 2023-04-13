import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomersComponent } from './customers/customers.component';
import { OrdersComponent } from './orders/orders.component';
import { ProductsComponent } from './products/products.component';

const routes: Routes = [
  {
    path : "products" , component : ProductsComponent
  },
  {
    path : "customers" , component : CustomersComponent
  },
  {
    path : "orders/:customerId" , component : OrdersComponent
  },
  {
    path : "" , component : ProductsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
