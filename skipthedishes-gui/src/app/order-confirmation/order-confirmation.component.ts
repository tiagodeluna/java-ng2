import { Component, OnInit } from '@angular/core';
import {OrderService} from "../services/order/order.service";
import {Order} from "../services/order/order.model";
import {Restaurant} from "../services/search/restaurant.model";
import {CustomerService} from "../services/customer/customer.service";
import {Item} from "../services/order/item.model";

@Component({
  selector: 'app-order-confirmation',
  templateUrl: './order-confirmation.component.html',
  styleUrls: ['./order-confirmation.component.css']
})
export class OrderConfirmationComponent implements OnInit {

  constructor(public orderService:OrderService,private customerService:CustomerService) {
    this.orderService.createOrder();
    this.currentOrder = this.orderService.currentOrder;
    this.currentRestaurant = this.orderService.currentRestaurant; }

  currentOrder:Order;

  currentRestaurant:Restaurant;

  ngOnInit() {
  }

  confirmOrder(dishCash:boolean){
    this.orderService.confirmOrder(dishCash);
  }

  isDishCashAvailable(){
    return this.customerService.currentCustomer.dishCoin >= (this.currentOrder.total* this.orderService.dishCoinRate);
  }

  deleteItem(item:Item){
      this.orderService.dishes.splice(this.orderService.dishes.indexOf(item.dish),1);
      this.orderService.createOrder();
      this.currentOrder = this.orderService.currentOrder;
  }

}
