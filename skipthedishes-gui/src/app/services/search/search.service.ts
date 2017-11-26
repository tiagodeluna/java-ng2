import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Dish} from "./dish.model";
import {Restaurant} from "./restaurant.model";
import {Observable} from "rxjs/Observable";


@Injectable()
export class SearchService {


  constructor(private http: HttpClient) {

  }

  currentText: string="";
  currentRestaurantLines: number=0;
  currentDishesLines: number=0;

  currentRestaurantsAllLoaded:boolean = false;
  currentDishesAllLoaded:boolean = false;

  currentDishesSearch: Dish[] = [];
  currentRestaurantSearch: Restaurant[] = [];

  sizeSearch: number=20;

  selectedRestaurant:Restaurant;

  doSearch(text: string) {
    this.currentText = "";
    this.currentDishesLines = 0;
    this.currentRestaurantLines = 0;
    this.currentDishesAllLoaded=false;
    this.currentRestaurantsAllLoaded=false;
    this.currentDishesSearch.splice(0);
    this.currentRestaurantSearch.splice(0);
    this.loadRestaurants(text);
    this.loadDishes(text);


  }

  public loadRestaurants(text:string = this.currentText) {
    if(this.currentRestaurantsAllLoaded){
      return;
    }
    this.http.get<Restaurant[]>("api/restaurants", {
      params: {
        "text": text,
        "offset": String(this.currentRestaurantLines),
        "size": String(this.sizeSearch)
      }
    }).subscribe((result:Restaurant[])=>{
      if(result.length<this.sizeSearch){
        this.currentRestaurantsAllLoaded = true;
      }
      if(result.length>0){
        this.currentRestaurantLines+=result.length;
        this.currentRestaurantSearch.push(...result);
      }
      this.currentText = text;
    });
    console.log(this.currentRestaurantSearch);
  }

  public loadDishes(text:string = this.currentText) {
    if(this.currentDishesAllLoaded){
      return;
    }
    this.http.get<Dish[]>("api/dishes", {
      params: {
        "text": text,
        "offset": String(this.currentDishesLines),
        "size": String(this.sizeSearch)
      }
    }).subscribe((result:Dish[])=>{
      if(result.length<this.sizeSearch){
        this.currentDishesAllLoaded = true;
      }
      if(result.length>0){
        this.currentDishesLines+=result.length;
        this.currentDishesSearch.push(...result);
      }
      this.currentText = text;
    });
    console.log(this.currentDishesSearch);
  }

  public getDishesFromRestaurant(restaurantId:string):Observable<Dish[]>{
    return this.http.get<Dish[]>('/api/restaurants/'+restaurantId+'/dishes');
  }

}
