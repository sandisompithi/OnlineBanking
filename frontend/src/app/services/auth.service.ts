import { Injectable } from "@angular/core";
import { Http, Headers, RequestOptions, Response } from "@angular/http";
import { User } from "../model/model.user";
import { AppComponent } from "../app.component";
import { Router } from "@angular/router";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  private router: Router;

  constructor(public http: HttpClient) {}

  public errorMessage = "";

  authenticated = false;

  authenticate(credentials, callback) {
    const headers = new HttpHeaders(
      credentials
        ? {
            authorization:
              "Basic " + btoa(credentials.email + ":" + credentials.password)
          }
        : {}
    );

    this.http
      .get(AppComponent.apiUrl + "/api/login", { headers: headers })
      .subscribe(response => {
        let data: any;
        data = response;
        const u = data.principal;
        if (response["firstName"] || response["surname"]) {
          this.authenticated = true;
        } else {
          this.authenticated = false;
        }
        return callback && callback(data);
      });
  }
  public logIn(user: User) {
    console.log(user);
    let headers = new HttpHeaders();
    headers.set("Accept", "application/json");

    // creating base64 encoded String from user name and password
    const base64Credential: string = btoa(user.email + ":" + user.password);
    headers.set("Authorization", "Basic " + base64Credential);
    console.log(headers);

    return this.http.get(AppComponent.apiUrl + "/api/login", {
      headers: headers
    });
  }

  logOut() {}
}
