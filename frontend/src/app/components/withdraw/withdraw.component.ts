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
    this.accountService.withdrawFromFixedAccount(this.currentUser).subscribe(
      data => {
        this.router.navigate(["/fixedAccount"]);
      },
      err => {
        console.log(err);
        this.errorMessage = "Transaction could not be completed";
      }
    );
  }
}
