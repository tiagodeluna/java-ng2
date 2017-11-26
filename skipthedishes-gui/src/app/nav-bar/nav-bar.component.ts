import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../services/customer/customer.service";
import {OrderService} from "../services/order/order.service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(public customerService:CustomerService,public orderService:OrderService) { }

  ngOnInit() {

  }

  getCustomerFirstName():string{
    if(this.customerService.currentCustomer){
      return this.customerService.currentCustomer.firstName;
    }

    return "Select a customer";
  }

  getCurrentDishCoins():string{
    if(this.customerService.currentCustomer){
      return "DC$ " + this.customerService.currentCustomer.dishCoin;
    }

    return "";
  }

  hasValidOrder(){
    return this.orderService.currentRestaurant!=null;
  }
}
