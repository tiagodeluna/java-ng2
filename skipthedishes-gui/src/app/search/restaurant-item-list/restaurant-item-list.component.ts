import {Component, Input, OnInit} from '@angular/core';
import {Restaurant} from "../../services/search/restaurant.model";
import {CustomerService} from "../../services/customer/customer.service";

@Component({
  selector: 'app-restaurant-item-list',
  templateUrl: './restaurant-item-list.component.html',
  styleUrls: ['./restaurant-item-list.component.css']
})
export class RestaurantItemListComponent implements OnInit {


  @Input() restaurant:Restaurant = new Restaurant();

  constructor(private customerService:CustomerService) { }

  ngOnInit() {
  }

}
