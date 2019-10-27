import { Component, OnInit } from '@angular/core';
import { MailService } from 'src/app/services/mail.service';
import { UserService } from 'src/app/services/user.service';
import { AlertService } from 'src/app/services';

@Component({
  selector: 'app-accountactivation',
  templateUrl: './accountactivation.component.html',
  styleUrls: ['./accountactivation.component.scss']
})
export class AccountactivationComponent implements OnInit {
  id;
  url;

  constructor(
    private mailService: MailService,
    private userService: UserService,
    private alertService: AlertService
  ) { }

  ngOnInit() {
        if (this.getID() !== null) {
        this.mailService.registerValidate(this.getID()).subscribe();
        this.alertService.success('Your account has been activated', true);
      } else
         if (this.getID() == null) {
          this.alertService.error('Activation Failed', true);
        }
  }


  getID() {

    this.url = window.location.href ;
    return this.id = this.url.substring(this.url.lastIndexOf('/') + 1);

  }

}
