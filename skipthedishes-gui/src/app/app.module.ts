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


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    StepsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
