import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { User } from "../model/user";
import { AppComponent } from "../app.component";

@Injectable()
export class UserService {
  constructor(public http: HttpClient) {}

  createUser(user: User) {
    return this.http.post(AppComponent.API_URL + "/api/register", user);
  }
}
