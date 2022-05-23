import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validator, Validators} from "@angular/forms";
import {Customer} from "../model/customer.model";
import {CustomerService} from "../services/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css']
})
export class NewCustomerComponent implements OnInit {

  newCustomerFormGroup ! :FormGroup;
  constructor(private customerService:CustomerService,private fb : FormBuilder,private router : Router) { }

  ngOnInit(): void {
    this.newCustomerFormGroup=this.fb.group(
      {
            name:this.fb.control(null,[Validators.required, Validators.minLength(4)]),
            email:this.fb.control(null,Validators.email)
      }
    )
  }

  handelSaveCustomer() {
    let customer:Customer = this.newCustomerFormGroup.value;
    this.customerService.saveCustomer(customer).subscribe(
      {
        next:data=>{
          alert("Customer has been successfully");
         // this.newCustomerFormGroup.reset();
          this.router.navigateByUrl("/customers");
        },error:err => {
          console.log(err);
        }
      }
    );
  }
}
