import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { Product } from './products';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit{


  products: Product[];

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient){

  }

  ngOnInit(): void {
    this.http.get<Product[]>(this.apiUrl+"inventory-service/api/products").subscribe({
      next : (data)=>{
        this.products = data;
      },
      error: (err)=>{}
    });
  }

}
