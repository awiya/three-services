import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent {
  
  orderDetails: any;
  orderId: number;

    private apiUrl = environment.apiUrl;

    constructor(private http: HttpClient, private router: Router, private route:ActivatedRoute){
        this.orderId=route.snapshot.params['orderId'];
    }

    ngOnInit(): void {
      this.http.get(this.apiUrl+"order-service/api/orders/fullOrder/"+this.orderId).subscribe({
        next : (data)=>{
          this.orderDetails = data;
        },
        error: (err)=>{}
      });
    }

    

}
