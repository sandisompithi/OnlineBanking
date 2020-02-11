import { ModuleWithProviders } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { UseraccountComponent } from "./components/useraccount/useraccount.component";
import { LogoutComponent } from "./components/logout/logout.component";
import { NotFoundComponent } from "./components/not-found/not-found.component";
import { UrlPermission } from "./urlPermission/url.permission";
import { FixedAccountComponent } from "./components/fixed-account/fixed-account.component";
import { SavingsAccountComponent } from "./components/savings-account/savings-account.component";
import { RecipientComponent } from "./components/recipient/recipient.component";
import { DepositComponent } from "./components/deposit/deposit.component";
import { WithdrawComponent } from "./components/withdraw/withdraw.component";
import { BetweenAccountsComponent } from "./components/between-accounts/between-accounts.component";
import { FixedTransactionComponent } from "./components/fixed-transaction/fixed-transaction.component";

const appRoutes: Routes = [
  {
    path: "",
    component: LoginComponent
  },
  {
    path: "useraccount",
    component: UseraccountComponent,
    canActivate: [UrlPermission]
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "logout",
    component: LogoutComponent
  },
  {
    path: "not-found",
    component: NotFoundComponent
  },
  {
    path: "fixedAccount",
    component: FixedAccountComponent
  },
  {
    path: "savingsAccount",
    component: SavingsAccountComponent
  },
  {
    path: "recipient",
    component: RecipientComponent
  },
  {
    path: "betweenAccounts",
    component: BetweenAccountsComponent
  },
  {
    path: "deposit",
    component: DepositComponent
  },
  {
    path: "withdraw",
    component: WithdrawComponent
  },
  {
    path: "fixedTransaction",
    component: FixedTransactionComponent
  },
  // otherwise redirect to profile
  {
    path: "**",
    redirectTo: "/not-found"
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
