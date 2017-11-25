import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/primeng";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';

  items: MenuItem[];

  ngOnInit() {
    this.items = [
      {label: 'Search', icon: 'fa-plus'},
      {label: 'Fav', icon: 'fa-download'},
      {label: 'Orders', icon: 'fa-download'},
      {label: 'Profile', icon: 'fa-refresh'}
    ];
  }
}
