import {Restaurant} from "../search/restaurant.model";
import {Dish} from "../search/dish.model";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Order} from "./order.model";
import {Item} from "./item.model";
import {Router} from "@angular/router";
import {CustomerService} from "../customer/customer.service";


@Injectable()
export class OrderService {

  constructor(private http: HttpClient,private router:Router,private customerService:CustomerService) {

  }


  currentRestaurant: Restaurant;

  dishes: Dish[] = [];

  currentOrder:Order;

  addDish(dish: Dish) {
    this.checkRestaurant(dish.restaurantId);
    this.dishes.push(dish);
    window.alert("Dish "+dish.name+" added to order");
  }


  checkRestaurant(restaurantId: string) {
    if (!this.currentRestaurant || this.currentRestaurant.id !== restaurantId) {
      this.dishes = [];
      this.http.get<Restaurant>("/api/restaurants/"+restaurantId).subscribe((result: Restaurant) => {
        this.currentRestaurant = result;
      });
    }
  }

  confirmOrder(dishCoin: boolean = false) {

    if(dishCoin){
      this.currentOrder.paymentMethod = "DISH_COINS";
    }else{
      this.currentOrder.paymentMethod = "CASH";
    }

    this.http.post<Order>("/api/orders",this.currentOrder).subscribe((result:Order)=>{
      window.alert("Order Saved!");
      this.dishes =[];
      this.currentRestaurant = null;
      this.router.navigate(["/search"]);
    });


  }


  createOrder(){
    var order:Order = new Order();

    order.restaurantId = this.currentRestaurant.id;
    order.customerId = this.customerService.currentCustomer.id;
    var dishesAdded: { [key: string]: Item } = {};
    this.dishes.forEach((d: Dish) => {
      var item: Item = dishesAdded[d.id];
      var justAdded:boolean = false;
      if (!item) {
        item = new Item();
        justAdded = true;
      }
      item.dish = d;
      item.amount++;
      if(justAdded){
        order.items.push(item);
        dishesAdded[d.id]=item;
      }

    });

    order.total = order.items
      .map((item:Item)=>item.amount * item.dish.price)
      .reduce((v1:number,v2:number)=>v1+v2);

    this.currentOrder = order;

  }

}
