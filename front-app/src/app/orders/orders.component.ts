import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {

  orders: any;
  customerId: number;

    private apiUrl = environment.apiUrl;

    constructor(private http: HttpClient, private router: Router, private route:ActivatedRoute){
        this.customerId=route.snapshot.params['customerId'];
    }

    ngOnInit(): void {
      this.http.get(this.apiUrl+"order-service/api/orders/byCustomerId/"+this.customerId).subscribe({
        next : (data)=>{
          this.orders = data;
        },
        error: (err)=>{}
      });
    }

    getOrderDetails(o: any){

    }




}
