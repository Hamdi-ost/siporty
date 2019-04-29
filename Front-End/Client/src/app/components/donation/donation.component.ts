import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertService } from 'src/app/services';
import { ContactService } from 'src/app/services/contact.service';
import { DonationService } from 'src/app/services/donation.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-donation',
  templateUrl: './donation.component.html',
  styleUrls: ['./donation.component.css']
})
export class DonationComponent implements OnInit {
  donationForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  id;
  username;
  socialLink;
  constructor(private formBuilder: FormBuilder,
    private alertService: AlertService,
    private donationService: DonationService,
    private route: ActivatedRoute) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      // this.id = params['id'];
      this.username = params['username'];
      this.donationService.getDonationDetailsByUsername(this.username).subscribe(data => {
        this.socialLink = data['socialLink'];
        this.id = data['userInfo'].id;

        this.donationForm = this.formBuilder.group({
          id: this.id,
          name: ['', Validators.required],
          montant: ['', Validators.required],
          message: ['', Validators.required]
        });

      });
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.donationForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    console.log();

    // stop here if form is invalid
    if (this.donationForm.invalid) {
      return;
    }

    console.log(this.donationForm.value);

    this.loading = true;
    this.donationService
      .postDonation(this.donationForm.value)
      .subscribe(
        data => {
          this.alertService.success('Your Donation sent');

        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        }
      );
    //   .postContact(this.donationForm.value)
    //   .subscribe(
    //     data => {
    //       this.alertService.success('Your message sent');
    //       this.donationForm.value.name = '';
    //       this.donationForm.value.amount = '';
    //       this.donationForm.value.message = '';
    //     },
    //     error => {
    //       this.alertService.error(error);
    //       this.loading = false;
    //     }
    //   );
  }
}
