import {Customer} from "./customer.model";
import { HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import {Dish} from "../search/dish.model";
import {Restaurant} from "../search/restaurant.model";

@Injectable()
export class CustomerService{

  customers:Customer[];

  currentCustomer:Customer;

  dishes:Dish[]=[];
  restaurants:Restaurant[]=[];


  constructor(private http:HttpClient){

  }

  public loadCustomers():Observable<Customer[]>{
    if(this.customers){
      return Observable.of(this.customers);
    }

    return this.http.get<Customer[]>('/api/customers').do((result:Customer[])=>{
       this.customers = result;
    });

  }

  isCurrentCustomer(customer:Customer):boolean{
    if(this.currentCustomer && customer && this.currentCustomer.id===customer.id){
      return true;
    }

    return false;

  }

  public loadFavorites(){
    this.http.get<Dish[]>("/api/dishes",{params:{
      "customerId":this.currentCustomer.id
    }}).subscribe((result:Dish[])=>{
       this.dishes = result;
    });

    this.http.get<Restaurant[]>("/api/restaurants",{params:{
      "customerId":this.currentCustomer.id
    }}).subscribe((result:Restaurant[])=>{
      this.restaurants = result;
    });

  }

  public addFavoriteRestaurant(restaurant:Restaurant){
    this.http.put<Customer>("/api/customers/"+this.currentCustomer.id+"/add-favorite-restaurant",null,{params:{
      "restaurantId":restaurant.id
    }}).subscribe((customer:Customer)=>{
       this.currentCustomer.favoriteRestaurants = customer.favoriteRestaurants;
    })
  }

  public removeFavoriteRestaurant(restaurant:Restaurant){
    this.http.put<Customer>("/api/customers/"+this.currentCustomer.id+"/remove-favorite-restaurant",null,{params:{
      "restaurantId":restaurant.id
    }}).subscribe((customer:Customer)=>{
      this.currentCustomer.favoriteRestaurants = customer.favoriteRestaurants;
    })
  }

  public addFavoriteDish(restaurant:Dish){
    this.http.put<Customer>("/api/customers/"+this.currentCustomer.id+"/add-favorite-dish",null,{params:{
      "dishId":restaurant.id
    }}).subscribe((customer:Customer)=>{
      this.currentCustomer.favoriteDishes = customer.favoriteDishes;
    })
  }

  public removeFavoriteDish(restaurant:Dish){
    this.http.put<Customer>("/api/customers/"+this.currentCustomer.id+"/remove-favorite-dish",null,{params:{
      "dishId":restaurant.id
    }}).subscribe((customer:Customer)=>{
      this.currentCustomer.favoriteDishes = customer.favoriteDishes;
    })
  }

  updateCurrentCustomer(){
    this.http.get<Customer>("api/customers/"+this.currentCustomer.id).subscribe((result:Customer)=>{
      this.currentCustomer.dishCoin = result.dishCoin;
    });
  }

}
