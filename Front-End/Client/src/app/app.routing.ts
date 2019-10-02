import { LoginGuard } from './guards/login.guard';
import { Routes, RouterModule } from '@angular/router';
import { ThanksfordonComponent } from './components/thanksfordon/thanksfordon.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { IncomeComponent } from './components/income/income.component';
import { SettingsComponent } from './components/settings/settings.component';
import { NotificationComponent } from './components/notification/notification.component';
import { DonationComponent } from './components/donation/donation.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { ContactComponent } from './components/contact/contact.component';
import { TermsandconditionsComponent } from './components/termsandconditions/termsandconditions.component';
import { AuthGuard } from './guards';
import { GiffyComponent } from './components/giffy/giffy.component';
import { DonationfailedComponent } from './components/donationfailed/donationfailed.component';

export const  appRoutes: Routes = [

  { path: '', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'home', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'register', component: RegisterComponent },
  { path: 'income', component: IncomeComponent },
  { path: 'settings', component: SettingsComponent, canActivate: [AuthGuard] },
  { path: 'notifications', component: NotificationComponent, canActivate: [AuthGuard] },
  { path: 'donation/:username', component: DonationComponent },
  { path: 'aboutUs', component: AboutUsComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'myGiffy', component: GiffyComponent },
  { path: 'donationsucceeded/:username', component: ThanksfordonComponent },
  { path: 'termsandconditions', component: TermsandconditionsComponent },
  { path : 'donationfailed' , component: DonationfailedComponent},
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes, { scrollPositionRestoration: 'enabled' });
export const routingHash = RouterModule.forRoot(appRoutes) ;
