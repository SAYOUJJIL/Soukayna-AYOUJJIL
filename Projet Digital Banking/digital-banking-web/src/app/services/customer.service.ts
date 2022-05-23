import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, publish} from "rxjs";
import {Customer} from "../model/customer.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  //backendHost : string ="http://localhost:8085";
  constructor(private http:HttpClient) {

  }

  public getCustomers():Observable<any> {
    return this.http.get<Array<Customer>>(environment.backendHost+"/customers")
  }

  public searchCustomers(keyword:string):Observable<any> {
    return this.http.get<Array<Customer>>(environment.backendHost+"/customers/_search?keyword="+keyword)
  }

  public saveCustomer(customer:Customer ):Observable<any> {
    return this.http.post<Customer>(environment.backendHost+"/customers",customer);
  }

  deleteCustomer(id:number) {
    return this.http.delete(environment.backendHost+"/customers/"+id);
  }
}
