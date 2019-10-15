import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { User } from "../../model/user";
import { UserService } from "../../services/user.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
  encapsulation: ViewEncapsulation.None
})
export class RegisterComponent implements OnInit {
  user: User = new User();
  errorMessage: string;

  constructor(public userService: UserService, public router: Router) {}

  ngOnInit() {}

  register() {
    this.userService.createUser(this.user).subscribe(
      data => {
        this.router.navigate(["/api/login"]);
      },
      error => {
        console.log(error);
        this.errorMessage = "username already exist";
      }
    );
  }
}
