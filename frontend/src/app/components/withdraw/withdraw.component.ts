import { Component, OnInit } from "@angular/core";
import { User } from "../../model/model.user";
import { AccountService } from "../../services/account.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-withdraw",
  templateUrl: "./withdraw.component.html",
  styleUrls: ["./withdraw.component.css"]
})
export class WithdrawComponent implements OnInit {
  currentUser: User;
  errorMessage: string;

  constructor(public accountService: AccountService, public router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }

  ngOnInit() {}

  withdraw() {
    this.accountService.withdraw(this.currentUser).subscribe(
      data => {
        this.currentUser = JSON.parse(JSON.parse(JSON.stringify(data))._body);
        this.router.navigateByUrl("/useraccount");
      },
      err => {
        console.log(err);
        this.errorMessage = "Option selected doesn't match the transaction";
      }
    );
  }
}
