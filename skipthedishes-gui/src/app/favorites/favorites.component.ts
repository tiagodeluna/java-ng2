import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../services/customer/customer.service";

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {

  constructor(public customerService:CustomerService) { }

  ngOnInit() {
    this.customerService.loadFavorites();
  }

}
