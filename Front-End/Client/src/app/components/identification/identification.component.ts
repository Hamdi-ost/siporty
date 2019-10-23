import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, EmailValidator } from '@angular/forms';
import { MailService } from 'src/app/services/mail.service';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-identification',
  templateUrl: './identification.component.html',
  styleUrls: ['./identification.component.scss']
})
export class IdentificationComponent implements OnInit {
  emailForm: FormGroup;
  submitted = false;
  email;
  loading = false;
  constructor(
    private formBuilder: FormBuilder,
    private  mailService: MailService,
    private alertService: AlertService
  ) { }

  ngOnInit() {
    this.emailForm = this.formBuilder.group({
      email: ['', Validators.required]
    });


  }

  get f() {
    return this.emailForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if email is invalid
    if (this.emailForm.invalid) {

      return;
    }
    this.loading = true;
    this.email = this.emailForm.value.email;
    this.mailService.identification(this.email).subscribe();
    this.alertService.success('Email sent successfully', true);
   //window.location.reload();
    //this.emailForm.reset();
  }

}
