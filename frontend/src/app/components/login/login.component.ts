import { Component, OnInit } from "@angular/core";
import { User } from "../../model/user";
import { AuthService } from "../../services/auth.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  user: User = new User();
  errorMessage: string;
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  login() {
    this.authService.authenticate(this.user, e => {
      this.router.navigateByUrl("/api/home");
      console.log(e);
      let response: any;
      response = e.principal;

      if (response) {
        localStorage.setItem("currentUser", JSON.stringify(response));
      }
    });
  }
}
