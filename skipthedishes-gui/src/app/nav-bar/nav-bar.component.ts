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

  getCurrentDishCoins():number{
    if(this.customerService.currentCustomer){
      return this.customerService.currentCustomer.dishCoin;
    }

    return 0;
  }

  hasValidOrder(){
    return this.orderService.currentRestaurant!=null;
  }
}
