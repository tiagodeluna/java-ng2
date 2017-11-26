import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Restaurant} from "../services/search/restaurant.model";

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {

  constructor() { }
  @Input() restaurants:Restaurant[]=[];
  @Output() scroll = new EventEmitter();
  @Input()  maxHeight:number=500;


  ngOnInit() {

  }

  onScroll(){
    this.scroll.emit("");
  }

}
