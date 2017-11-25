
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";


@Injectable()
export class SearchService{


  constructor(private http:HttpClient){

  }

  currentText:string;


  doSearch(text:string){
    if(this.currentText && this.currentText.length >=3 && this.currentText!==text){
      this.currentText = text;
       this.http.get("api/search/restaurants",{params:{text:this.currentText}});
       this.http.get("api/search/dishes",{params:{text:this.currentText}});
    }

  }

}
