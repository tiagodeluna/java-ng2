import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SearchService} from "../services/search/search.service";
import {Dish} from "../services/search/dish.model";

@Component({
  selector: 'app-dish-list',
  templateUrl: './dish-list.component.html',
  styleUrls: ['./dish-list.component.css']
})
export class DishListComponent implements OnInit {

  constructor() { }
  @Input() dishes:Dish[]=[];
  @Output() scroll = new EventEmitter();
  @Input()  maxHeight:number=500;


  ngOnInit() {

  }

  onScroll(){
     this.scroll.emit("");
  }

}
