import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { AuthService } from "../../services/auth.service";
import { User } from "../../model/user";
import { Router } from "@angular/router";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {
  currentUser: User;

  constructor(public authService: AuthService, public router: Router) {
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
