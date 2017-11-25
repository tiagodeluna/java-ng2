import {Component, Input, OnInit} from '@angular/core';
import {CustomerService} from "../../services/customer/customer.service";
import {Dish} from "../../services/search/dish.model";

@Component({
  selector: 'app-dish-item-list',
  templateUrl: './dish-item-list.component.html',
  styleUrls: ['./dish-item-list.component.css']
})
export class DishItemListComponent implements OnInit {



  @Input() dish:Dish = new Dish();

  constructor(private customerService:CustomerService) { }

  ngOnInit() {
  }

}
