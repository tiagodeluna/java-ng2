import {Component, Input, OnInit} from '@angular/core';
import {Restaurant} from "../services/search/restaurant.model";
import {SearchService} from "../services/search/search.service";
import {Dish} from "../services/search/dish.model";

@Component({
  selector: 'app-restaurant-detail',
  templateUrl: './restaurant-detail.component.html',
  styleUrls: ['./restaurant-detail.component.css']
})
export class RestaurantDetailComponent implements OnInit {

  restaurant:Restaurant;

  dishes:Dish[]=[];

  constructor(public searchService:SearchService) { }

  ngOnInit() {
    this.restaurant = this.searchService.selectedRestaurant;
    this.searchService.getDishesFromRestaurant(this.restaurant.id).subscribe((result:Dish[])=>{
      this.dishes = result;
    })

  }

}
