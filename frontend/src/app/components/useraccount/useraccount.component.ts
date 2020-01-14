import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { AuthService } from "../../services/auth.service";
import { User } from "../../model/model.user";
import { AccountService } from "../../services/account.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-useraccount",
  templateUrl: "./useraccount.component.html",
  styleUrls: ["./useraccount.component.css"],
  encapsulation: ViewEncapsulation.None
})
export class UseraccountComponent implements OnInit {
  currentUser: User;
  constructor(
    public authService: AuthService,
    private accountService: AccountService,
    public router: Router
  ) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }

  ngOnInit() {
    console.log("currentUser", this.currentUser);
  }

  logOut() {
    localStorage.removeItem("currentUser");
    this.router.navigate(["/logout"]);
  }
}
