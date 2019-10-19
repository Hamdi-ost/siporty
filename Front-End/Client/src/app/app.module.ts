import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { routing, routingHash } from './app.routing';
import { GiffyComponent } from './components/giffy/giffy.component';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { IncomeComponent } from './components/income/income.component';
import { SettingsComponent } from './components/settings/settings.component';
import { NotificationComponent } from './components/notification/notification.component';
import { DonationComponent } from './components/donation/donation.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HttpClientModule } from '@angular/common/http';
import { AlertComponent } from './components/alert/alert.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { ContactComponent } from './components/contact/contact.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { NgxPaginationModule } from 'ngx-pagination';
import { ThanksfordonComponent } from './components/thanksfordon/thanksfordon.component';
import { TermsandconditionsComponent } from './components/termsandconditions/termsandconditions.component';
import { DonationfailedComponent } from './components/donationfailed/donationfailed.component';
import { EventEmitterService } from './services/event-emitter.service';
import { RouterModule } from '@angular/router';
import {LocationStrategy, Location, PathLocationStrategy} from '@angular/common';
import { IdentificationComponent } from './components/identification/identification.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    IncomeComponent,
    SettingsComponent,
    NotificationComponent,
    DonationComponent,
    NotFoundComponent,
    AlertComponent,
    AboutUsComponent,
    ContactComponent,
    ThanksfordonComponent,
    TermsandconditionsComponent,
    GiffyComponent,
    DonationfailedComponent,
    IdentificationComponent

  ],
  imports: [
    NgxPaginationModule,
    AngularFontAwesomeModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    routing,
    routingHash

  ],
  providers: [
    EventEmitterService,
    Location,
    {provide: LocationStrategy, useClass: PathLocationStrategy}

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
