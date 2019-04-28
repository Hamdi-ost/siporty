import { Routes, RouterModule } from '@angular/router';

import { ProfileComponent } from './components/profile/profile.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { IncomeComponent } from './components/income/income.component';
import { SettingsComponent } from './components/settings/settings.component';
import { NotificationComponent } from './components/notification/notification.component';
import { DonationComponent } from './components/donation/donation.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { ContactComponent } from './components/contact/contact.component';
import { AuthGuard } from './guards';

const appRoutes: Routes = [

  { path: '', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'income', component: IncomeComponent },
  { path: 'settings', component: SettingsComponent },
  { path: 'notifications', component: NotificationComponent },
  { path: 'donation/:username', component: DonationComponent },
  { path: 'aboutUs', component: AboutUsComponent },
  { path: 'contact', component: ContactComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes, { scrollPositionRestoration: 'enabled' });
