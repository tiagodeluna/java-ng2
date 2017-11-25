import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";


import {AppComponent} from "./app.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {StepsModule} from "primeng/primeng";

import {HomeComponent} from "./home/home.component";
import {AppRoutingModule} from "./app-routing.module";
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {CustomerService} from "./services/customer/customer.service";
import {HttpClientModule} from "@angular/common/http";
import { SearchComponent } from './search/search.component';
import {SearchService} from "./services/search/search.service";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    StepsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [CustomerService,SearchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
