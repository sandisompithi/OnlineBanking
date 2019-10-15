import { HttpClientModule } from "@angular/common/http";
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { RegisterComponent } from "./components/register/register.component";
import { AuthService } from "./services/auth.service";
import { HttpModule } from "@angular/http";
import { UserService } from "./services/user.service";
import { LoginComponent } from "./components/login/login.component";
import { UrlPermission } from "./urlPermission/url.permission";
import { from } from "rxjs";
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [AppComponent, RegisterComponent, LoginComponent, HomeComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AuthService, UserService, UrlPermission],
  bootstrap: [AppComponent]
})
export class AppModule {}
