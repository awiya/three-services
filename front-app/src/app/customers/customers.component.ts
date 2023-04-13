import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Customer } from './customers';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent {

  customers: Customer[];

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private router: Router){

  }

  ngOnInit(): void {
    this.http.get<Customer[]>(this.apiUrl+"customer-service/api/customers").subscribe({
      next : (data)=>{
        this.customers = data;
      },
      error: (err)=>{}
    });
  }

  getOrders(c : Customer){
    this.router.navigateByUrl("/orders/"+ c.id)
  }

}
