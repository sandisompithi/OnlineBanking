import { Component, OnInit } from "@angular/core";
import { AccountService } from "../../services/account.service";
import { FixedAccount } from "../../model/model.fixed-account";
import { Router } from "@angular/router";
import { from } from "rxjs";

@Component({
  selector: "app-deposit",
  templateUrl: "./deposit.component.html",
  styleUrls: ["./deposit.component.css"]
})
export class DepositComponent implements OnInit {
  currentUser: FixedAccount;
  errorMessage: string;

  constructor(public accountService: AccountService, public router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }

  ngOnInit() {}

  deposit() {
    this.accountService.depositToFixed(this.currentUser).subscribe(
      data => {
        this.currentUser = JSON.parse(JSON.parse(JSON.stringify(data))._body);
      },
      err => {
        console.log(err);
        this.errorMessage = "Option selected doesn't match the transaction";
      }
    );
  }
}
