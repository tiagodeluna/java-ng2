import {Component, Input, OnInit} from '@angular/core';
import {Restaurant} from "../../services/search/restaurant.model";
import {CustomerService} from "../../services/customer/customer.service";
import {SearchService} from "../../services/search/search.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-restaurant-item-list',
  templateUrl: './restaurant-item-list.component.html',
  styleUrls: ['./restaurant-item-list.component.css']
})
export class RestaurantItemListComponent implements OnInit {


  @Input() restaurant:Restaurant = new Restaurant();

   distance:number;

  constructor(private customerService:CustomerService,private searchService:SearchService,private router:Router) {
    this.distance =this.getRandomDistance();

  }

  ngOnInit() {
  }


  getRandomDistance():number{
    return Math.random();
  }

  isFavorite():boolean{
    return this.customerService.currentCustomer.favoriteRestaurants.indexOf(this.restaurant.id) >=0
  }

  getFavIcon():string{
    if(this.isFavorite()){
      return "fa-heart";
    }else{
      return "fa-heart-o";
    }
  }

  toggleFavorite(){
    if(this.isFavorite()){
       this.customerService.removeFavoriteRestaurant(this.restaurant);
    }else{
      this.customerService.addFavoriteRestaurant(this.restaurant);
    }
  }

  goToRestaurant(){
    this.searchService.selectedRestaurant = this.restaurant;

    this.router.navigate(['/restaurant']);


  }

}
