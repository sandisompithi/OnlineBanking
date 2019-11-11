import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "../model/model.user";
import { Http } from "@angular/http";
import { AppComponent } from "../app.component";
import { Observable, from } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class AccountService {
  constructor(public http: HttpClient) {}

  createAccount(user: User) {
    return this.http.post(AppComponent.apiUrl + "/api/register", user);
  }

  getUsers() {
    return this.http.get(AppComponent.apiUrl + "/api/user/all");
  }

  onSelectFixed() {
    return this.http.get(AppComponent.apiUrl + "/api/fixedAccount");
  }

  onSelectSavings() {
    return this.http.get(AppComponent.apiUrl + "/api/savingsAccount");
  }

  getFixedTransactionList(username: string) {
    return this.http.get(
      AppComponent.apiUrl + "/api/fixedTransaction?username=" + username
    );
  }

  getSavingsTransactionList(username: string) {
    return this.http.get(
      AppComponent.apiUrl + "/api/savingsTransaction?username=" + username
    );
  }

  enableUser(username: string) {
    return this.http.get(
      AppComponent.apiUrl + "/api/user/" + username + "/enable"
    );
  }

  disableUser(username: string) {
    return this.http.get(
      AppComponent.apiUrl + "/api/user/" + username + "/disable"
    );
  }

  depositToFixed(user: User) {
    return this.http.post(AppComponent.apiUrl + "/api/deposit", user);
  }

  withdrawFromFixedAccount(user: User) {
    return this.http.post(AppComponent.apiUrl + "/api/withdraw", user);
  }
}
