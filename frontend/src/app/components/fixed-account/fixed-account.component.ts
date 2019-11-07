import { Component, OnInit } from "@angular/core";
import { AccountService } from "../../services/account.service";
import { ActivatedRoute, Params } from "@angular/router";

@Component({
  selector: "app-fixed-account",
  templateUrl: "./fixed-account.component.html",
  styleUrls: ["./fixed-account.component.css"]
})
export class FixedAccountComponent implements OnInit {
  username: string;
  fixedTransactionList: Object[];

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService
  ) {
    this.route.params.forEach((params: Params) => {
      this.username = params["username"];
    });
  }

  getFixedTransactionList() {
    this.accountService.getFixedTransactionList(this.username).subscribe(
      res => {
        console.log(JSON.parse(JSON.stringify(res))._body);
        this.fixedTransactionList = JSON.parse(
          JSON.parse(JSON.stringify(res))._body
        );
      },
      err => console.log(err)
    );
  }

  ngOnInit() {}
}
