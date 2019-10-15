import { Injectable } from "@angular/core";
import { Http, Headers, RequestOptions, Response } from "@angular/http";
import { User } from "../model/user";
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
      .get(AppComponent.API_URL + "/api/login", { headers: headers })
      .subscribe(response => {
        let data: any;
        data = response;
        const u = data.principal;
        if (response["firstname"]) {
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

    const base64Credential: string = btoa(user.email + ":" + user.password);
    headers.set("Authorization", "Basic " + base64Credential);
    console.log(headers);

    return this.http.get(AppComponent.API_URL + "/login", { headers: headers });
  }
  logOut() {
    return this.http.post(AppComponent.API_URL + "logout", {}).subscribe(
      response => {
        console.log("Response_logout " + response);
        let u = localStorage.getItem("currentUser");
        this.router.navigate(["/logout"]);
      },
      error => {}
    );
  }
}
