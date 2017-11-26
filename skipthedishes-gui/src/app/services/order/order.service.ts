import {Restaurant} from "../search/restaurant.model";
import {Dish} from "../search/dish.model";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";


@Injectable()
export class OrderService{

  constructor(private http:HttpClient){

  }


  currentRestaurant:Restaurant;

  dishes:Dish[]=[];



  addDish(dish:Dish){
    this.dishes.push(dish);
  }


  checkRestaurant(restaurantId:string){
    if(!this.currentRestaurant || this.currentRestaurant.id !==restaurantId){
      this.dishes=[];
      this.http.get<Restaurant>("").subscribe((result:Restaurant)=>{
         this.currentRestaurant=result;
       });
    }
  }

  confirmOrder(dishCoin:boolean=false){

  }




}
