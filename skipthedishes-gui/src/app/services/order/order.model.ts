import {Item} from "./item.model";
export class Order {

  id: string;

  date: Date;

  total: number;

  paymentMethod: string;

  reviewText: string;

  reviewRating: number;

  customerId: string;

  restaurantId: string;

  items: Item[]=[];

}
