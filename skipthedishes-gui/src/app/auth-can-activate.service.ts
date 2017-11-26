import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {CustomerService} from "./services/customer/customer.service";
import {Injectable} from "@angular/core";

@Injectable()
export class AuthCanActivate implements CanActivate{

  constructor(private customerService:CustomerService,private router:Router){

  }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(this.customerService.currentCustomer==null){
      this.router.navigate(['/']);
      return false;
    }

    return true;
  }

}
