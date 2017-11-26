import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";


import {AppComponent} from "./app.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AccordionModule, StepsModule} from "primeng/primeng";

import {HomeComponent} from "./home/home.component";
import {AppRoutingModule} from "./app-routing.module";
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {CustomerService} from "./services/customer/customer.service";
import {HttpClientModule} from "@angular/common/http";
import { SearchComponent } from './search/search.component';
import {SearchService} from "./services/search/search.service";
import {ReactiveFormsModule} from "@angular/forms";
import { RestaurantItemListComponent } from './search/restaurant-item-list/restaurant-item-list.component';
import { DishItemListComponent } from './search/dish-item-list/dish-item-list.component';
import { DishListComponent } from './dish-list/dish-list.component';
import {InfiniteScrollModule} from "ngx-infinite-scroll";
import { RestaurantListComponent } from './restaurant-list/restaurant-list.component';
import { FavoritesComponent } from './favorites/favorites.component';
import { RestaurantDetailComponent } from './restaurant-detail/restaurant-detail.component';
import {RouterModule} from "@angular/router";
import {OrderService} from "./services/order/order.service";
import { OrderConfirmationComponent } from './order-confirmation/order-confirmation.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    SearchComponent,
    RestaurantItemListComponent,
    DishItemListComponent,
    DishListComponent,
    RestaurantListComponent,
    FavoritesComponent,
    RestaurantDetailComponent,
    OrderConfirmationComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    RouterModule,
    StepsModule,
    AppRoutingModule,
    HttpClientModule,
    InfiniteScrollModule,
    AccordionModule
  ],
  providers: [CustomerService,SearchService,OrderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
