import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "../model/model.user";
import { Http } from "@angular/http";
import { AppComponent } from "../app.component";
import { Observable, from } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class AccountService {
  user: User = new User();
  constructor(public http: HttpClient) {}

  createAccount(user: User) {
    return this.http.post(AppComponent.apiUrl + "/api/register", user);
  }

  getFixedAccount(accountNumber: number): Observable<Object> {
    return this.http.get(
      AppComponent.apiUrl + "/api/fixedAccount/" + { accountNumber }
    );
  }

  getFixedTransactionList(username: string) {
    return this.http.get(
      AppComponent.apiUrl + "/fixedTransaction?username=" + username
    );
  }

  deposit(user: User) {
    const url = AppComponent.apiUrl + "/api/deposit";
    const headers = new HttpHeaders({
      Authorization: "Basic " + btoa(user.username + ":" + user.password)
    });
    return this.http.get(url + { headers });
  }

  withdraw(user: User) {
    const url = AppComponent.apiUrl + "/api/withdraw";
    const headers = new HttpHeaders({
      Authorization: "Basic " + btoa(user.username + ":" + user.password)
    });
    return this.http.get(url + { headers });
  }
}
