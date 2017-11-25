import { Component, OnInit } from '@angular/core';
import {Customer} from "../services/customer/customer.model";
import {CustomerService} from "../services/customer/customer.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  customers:Customer[];
  constructor(private customerService:CustomerService) { }



  ngOnInit() {
    this.customerService.loadCustomers().subscribe((result:Customer[])=>{
       this.customers=result;
    });


  }
  setCurrentCustomer(customer:Customer){
      this.customerService.currentCustomer = customer;
  }

  isCurrentCustomer(customer:Customer):string{
    if(this.customerService.isCurrentCustomer(customer)){
      return "active";
    }

    return "";

  }

}
