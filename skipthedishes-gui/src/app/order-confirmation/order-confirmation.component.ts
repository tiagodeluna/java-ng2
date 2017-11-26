import { Component, OnInit } from '@angular/core';
import {OrderService} from "../services/order/order.service";
import {Order} from "../services/order/order.model";
import {Restaurant} from "../services/search/restaurant.model";

@Component({
  selector: 'app-order-confirmation',
  templateUrl: './order-confirmation.component.html',
  styleUrls: ['./order-confirmation.component.css']
})
export class OrderConfirmationComponent implements OnInit {

  constructor(public orderService:OrderService) { }

  currentOrder:Order;

  currentRestaurant:Restaurant;

  ngOnInit() {
    this.orderService.createOrder();
    this.currentOrder = this.orderService.currentOrder;
    this.currentRestaurant = this.orderService.currentRestaurant;
  }

  confirmOrder(dishCash:boolean){
    this.orderService.confirmOrder(dishCash);
  }

}
