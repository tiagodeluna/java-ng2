import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Dish} from "./dish.model";
import {Restaurant} from "./restaurant.model";


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

  doSearch(text: string) {
    if (text && text.length >= 3 && this.currentText !== text) {
      this.currentText = text;
      this.currentDishesLines = 0;
      this.currentRestaurantLines = 0;
      this.currentDishesAllLoaded=false;
      this.currentRestaurantsAllLoaded=false;
      this.loadRestaurants();
      this.loadDishes();
    }

  }

  private loadRestaurants() {
    this.http.get<Restaurant[]>("api/restaurants", {
      params: {
        "text": this.currentText,
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
    });
    console.log(this.currentRestaurantSearch);
  }

  private loadDishes() {
    this.http.get<Dish[]>("api/dishes", {
      params: {
        "text": this.currentText,
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
    });
    console.log(this.currentDishesSearch);
  }

}
