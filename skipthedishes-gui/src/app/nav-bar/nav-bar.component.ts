import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../services/customer/customer.service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(public customerService:CustomerService) { }

  ngOnInit() {



  }

  getCurrentDishCoins():number{
    if(this.customerService.currentCustomer){
      return this.customerService.currentCustomer.dishCoin;
    }

    return 0;
  }

}
