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


  isFavorite():boolean{
    return this.customerService.currentCustomer.favoriteDishes.indexOf(this.dish.id) >=0
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
      this.customerService.removeFavoriteDish(this.dish);
    }else{
      this.customerService.addFavoriteDish(this.dish);
    }
  }


}
