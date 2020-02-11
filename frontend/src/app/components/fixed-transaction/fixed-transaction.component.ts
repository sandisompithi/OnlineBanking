import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { AccountService } from "../../services/account.service";
import { Observable } from "rxjs";
import { User } from "../../model/model.user";
import { FixedTransaction } from "../../model/fixed-transaction";
import { ActivatedRoute, Params, Router } from "@angular/router";

@Component({
  selector: "app-fixed-transaction",
  templateUrl: "./fixed-transaction.component.html",
  styleUrls: ["./fixed-transaction.component.css"]
})
export class FixedTransactionComponent implements OnInit {
  currentUser: User;
  username: string;
  fixedTransactionList: Object[];

  constructor(
    private route: ActivatedRoute,
    public accountService: AccountService,
    public router: Router
  ) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.route.params.forEach((params: Params) => {
      this.username = params["username"];
    });
  }

  ngOnInit() {}

  getFixedTransactionList() {
    this.accountService.getFixedTransactionList(this.username).subscribe(
      res => {
        console.log(JSON.parse(JSON.stringify(res))._body);
        this.getFixedTransactionList = JSON.parse(
          JSON.parse(JSON.stringify(res))._body
        );
      },
      error => console.log(error)
    );
  }
}
