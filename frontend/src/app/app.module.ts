import { HttpClientModule } from "@angular/common/http";
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppComponent } from "./app.component";
import { LoginComponent } from "./components/login/login.component";
import { LogoutComponent } from "./components/logout/logout.component";
import { RegisterComponent } from "./components/register/register.component";
import { UseraccountComponent } from "./components/useraccount/useraccount.component";
import { NotFoundComponent } from "./components/not-found/not-found.component";
import { UrlPermission } from "./urlPermission/url.permission";
import { routing } from "./app.routing";
import { HttpModule } from "@angular/http";
import { FormsModule } from "@angular/forms";
import { AccountService } from "./services/account.service";
import { AuthService } from "./services/auth.service";
import { FixedAccountComponent } from "./components/fixed-account/fixed-account.component";
import { SavingsAccountComponent } from "./components/savings-account/savings-account.component";
import { DepositComponent } from "./components/deposit/deposit.component";
import { WithdrawComponent } from "./components/withdraw/withdraw.component";
import { RecipientComponent } from "./components/recipient/recipient.component";
import { BetweenAccountsComponent } from "./components/between-accounts/between-accounts.component";
import { FixedTransactionComponent } from './components/fixed-transaction/fixed-transaction.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    UseraccountComponent,
    NotFoundComponent,
    FixedAccountComponent,
    SavingsAccountComponent,
    DepositComponent,
    WithdrawComponent,
    RecipientComponent,
    BetweenAccountsComponent,
    RecipientComponent,
    FixedTransactionComponent
  ],
  imports: [BrowserModule, HttpModule, HttpClientModule, FormsModule, routing],
  providers: [AccountService, AuthService, UrlPermission],
  bootstrap: [AppComponent]
})
export class AppModule {}
