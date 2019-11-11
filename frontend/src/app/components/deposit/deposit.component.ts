import { Component, OnInit } from "@angular/core";
import { AccountService } from "../../services/account.service";
import { User } from "../../model/model.user";
import { Router } from "@angular/router";
import { from } from "rxjs";

@Component({
  selector: "app-deposit",
  templateUrl: "./deposit.component.html",
  styleUrls: ["./deposit.component.css"]
})
export class DepositComponent implements OnInit {
  currentUser: User;
  errorMessage: string;

  constructor(public accountService: AccountService, public router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }

  ngOnInit() {}

  deposit() {
    this.accountService.depositToFixed(this.currentUser).subscribe(
      data => {
        this.router.navigate(["/fixedAccount"]);
      },
      err => {
        console.log(err);
        this.errorMessage = "Option selected doesn't match the transaction";
      }
    );
  }
}
