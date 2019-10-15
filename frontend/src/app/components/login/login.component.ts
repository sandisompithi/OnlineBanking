import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { User } from "../../model/model.user";
import { AuthService } from "../../services/auth.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
  user: User = new User();
  errorMessage: string;
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  login() {
    this.authService.authenticate(this.user, e => {
      this.router.navigateByUrl("/useraccount");
      console.log(e);
      let resp: any;
      resp = e.principal;

      if (resp) {
        // store user details  in local storage to keep user logged in between page refreshes
        localStorage.setItem("currentUser", JSON.stringify(resp));
      }
    });
  }
}
