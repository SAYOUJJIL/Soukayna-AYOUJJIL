import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CustomerService} from "../services/customer.service";
import {catchError, map, Observable, throwError} from "rxjs";
import {Customer} from "../model/customer.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  //any
  customers!:Observable<Array<Customer>>;
  //quand on affecte à une variable quia un type definie ila faut l'initialiser
  //errorMessage:string | undefined;
  //! moi je gere la partie d'initialisation
  //erreur est un object
  // ? je lui pas donner une valeur par defaut
  errorMessage ! :string;
  customersFormGroup:FormGroup | undefined;
  constructor(private customerService:CustomerService, private fb : FormBuilder, private router : Router) { }
  //methode qui s'execute au demarrage le composant au momont du chargement cette methde s'execute
  ngOnInit(): void {
   /*   this.customerService.getCustomers().subscribe({
          next:(data)=>{
            this.customers=data;
          },error: (err) =>{
            this.errorMessage=err.message;
      }
        }
      )*/
    this.customersFormGroup=this.fb.group(
      {
        keyword:this.fb.control("")
      }
    )
    this.hadleSearchCustomers()
  }

  hadleSearchCustomers() {
    let kw = this.customersFormGroup?.value.keyword;
    this.customers=this.customerService.searchCustomers(kw).pipe(
      catchError(err => {
        this.errorMessage=err.message;
        return throwError(err);
      })
    )
  }

  handelDeleteCustomer(c:Customer) {
    let conf = confirm("Are you sure?");
    if (!conf) return;
    this.customerService.deleteCustomer(c.id).subscribe({
      next : (resp)=>{
          this.customers=this.customers.pipe(
            map(data=>{
              let index=data.indexOf(c);
              data.slice(index,1)
              return data;
            })
          )
      },
      error : err => {
        console.log(err);
      }
    })

  }

  handelCustomerAccounts(c:Customer) {
    this.router.navigateByUrl("/customer-accounts/"+c.id,{state:c});
  }
}
