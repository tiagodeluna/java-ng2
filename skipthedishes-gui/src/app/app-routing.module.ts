import {NgModule} from "@angular/core";
import {PreloadAllModules, RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {SearchComponent} from "./search/search.component";
import {FavoritesComponent} from "./favorites/favorites.component";
import {RestaurantDetailComponent} from "./restaurant-detail/restaurant-detail.component";
import {OrderConfirmationComponent} from "./order-confirmation/order-confirmation.component";
import {AuthCanActivate} from "./auth-can-activate.service";

const routes:Routes=[
  {path:'', redirectTo:'home',pathMatch:'full'},
  {path:'home',component:HomeComponent},
  {path:'search', component:SearchComponent,canActivate:[AuthCanActivate]},
  {path:'restaurant', component:RestaurantDetailComponent,canActivate:[AuthCanActivate]},
  {path:'checkout', component:OrderConfirmationComponent,canActivate:[AuthCanActivate]},
  {path:'favorites', component:FavoritesComponent,canActivate:[AuthCanActivate]}

  ];


@NgModule({
  imports:[
    RouterModule.forRoot(routes/*,{preloadingStrategy:PreloadAllModules}*/)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule{

}
