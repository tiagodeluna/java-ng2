import {Customer} from "./customer.model";
import { HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";

@Injectable()
export class CustomerService{

  customers:Customer[];

  constructor(private http:HttpClient){

  }

  public loadCustomers():Observable<Customer[]>{
    if(this.customers){
      return Observable.of(this.customers);
    }

    return this.http.get<Customer[]>('/api/customers').do((result:Customer[])=>{
       this.customers = result;
    });

  }
}
