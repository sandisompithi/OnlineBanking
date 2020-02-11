import { Component, OnInit } from "@angular/core";
import { AccountService } from "../../services/account.service";
import { User } from "../../model/model.user";
import { FixedTransaction } from "../../model/fixed-transaction";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { Observable } from "rxjs";

@Component({
  selector: "app-fixed-account",
  templateUrl: "./fixed-account.component.html",
  styleUrls: ["./fixed-account.component.css"]
})
export class FixedAccountComponent implements OnInit {
  currentUser: User;

  constructor(public accountService: AccountService, public router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }

  ngOnInit() {}
}
