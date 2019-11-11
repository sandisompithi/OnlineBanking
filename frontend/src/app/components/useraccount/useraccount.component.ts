import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { AuthService } from "../../services/auth.service";
import { User } from "../../model/model.user";
import { AccountService } from "../../services/account.service";
import { Router } from "@angular/router";
import { from } from "rxjs";

@Component({
  selector: "app-useraccount",
  templateUrl: "./useraccount.component.html",
  styleUrls: ["./useraccount.component.css"],
  encapsulation: ViewEncapsulation.None
})
export class UseraccountComponent implements OnInit {
  currentUser: User;
  userList: Object[];
  constructor(
    public authService: AuthService,
    private accountService: AccountService,
    public router: Router
  ) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }

  getUsers() {
    this.accountService.getUsers().subscribe(
      res => {
        this.userList = JSON.parse(JSON.parse(JSON.stringify(res))._body);
      },
      error => console.log(error)
    );
  }

  onSelectFixed(username: string) {
    this.router.navigate(["/fixedTransaction", username]);
  }

  enableUser(username: string) {
    this.accountService.enableUser(username).subscribe();
    location.reload();
  }

  disableUser(username: string) {
    this.accountService.disableUser(username).subscribe();
    location.reload();
  }

  ngOnInit() {
    console.log("currentUser", this.currentUser);
  }

  logOut() {
    localStorage.removeItem("currentUser");
    this.router.navigate(["/logout"]);
  }
}
