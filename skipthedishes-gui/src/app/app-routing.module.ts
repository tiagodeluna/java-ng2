import {NgModule} from "@angular/core";
import {PreloadAllModules, RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {SearchComponent} from "./search/search.component";
import {FavoritesComponent} from "./favorites/favorites.component";
import {RestaurantDetailComponent} from "./restaurant-detail/restaurant-detail.component";

const routes:Routes=[
  {path:'', component:HomeComponent},
  {path:'search', component:SearchComponent},
  {path:'restaurant', component:RestaurantDetailComponent},
  {path:'favorites', component:FavoritesComponent}
  ];


@NgModule({
  imports:[
    RouterModule.forRoot(routes/*,{preloadingStrategy:PreloadAllModules}*/)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule{

}
