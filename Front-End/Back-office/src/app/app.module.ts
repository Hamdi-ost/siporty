import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { LoginComponent } from './components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuard } from './guards';
import { RegisterComponent } from './components/register/register.component';
import { AlertComponent } from './components/alert/alert.component';
import { UserComponent } from './components/user/user.component';
import { DonationsComponent } from './components/donations/donations.component';
import { TableComponent } from './components/table/table.component';
import { AdminComponent } from './components/admin/admin.component';
import { FilterPipe } from './filter.pipe';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { MessagesComponent } from './components/messages/messages.component';
import { MessageDetailComponent } from './components/message-detail/message-detail.component'; // <-- import the module
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmationDialogueComponent } from './components/confirmation-dialogue/confirmation-dialogue.component';
import { InformationsComponent } from './components/informations/informations.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'dashboard', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'messages', component: MessagesComponent },
  { path: 'messages/:id', component: MessageDetailComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'infos', component: InformationsComponent },
  { path: 'users', component: UserComponent },
  { path: 'donations', component: DonationsComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
]
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    SidebarComponent,
    LoginComponent,
    RegisterComponent,
    AlertComponent,
    UserComponent,
    DonationsComponent,
    TableComponent,
    AdminComponent,
    FilterPipe,
    MessagesComponent,
    MessageDetailComponent,
    ConfirmationDialogueComponent,
    InformationsComponent
  ],
  entryComponents: [ConfirmationDialogueComponent],
  imports: [
    NgbModule,
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule
  ],
  providers: [
    {provide: LocationStrategy, useClass: PathLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
