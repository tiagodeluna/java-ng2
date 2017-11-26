import { Component, OnInit } from '@angular/core';
import {Customer} from "../services/customer/customer.model";
import {CustomerService} from "../services/customer/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  customers:Customer[];
  constructor(private customerService:CustomerService,private router:Router) { }



  ngOnInit() {
    this.customerService.loadCustomers().subscribe((result:Customer[])=>{
       this.customers=result;
    });


  }
  setCurrentCustomer(customer:Customer){
      this.customerService.currentCustomer = customer;
      this.router.navigate(['/search']);
  }

  isCurrentCustomer(customer:Customer):string{
    if(this.customerService.isCurrentCustomer(customer)){
      return "active";
    }

    return "";

  }

}
