import { Component, OnInit,EventEmitter, OnDestroy } from '@angular/core';
import { EventEmitterService } from 'src/app/services/event-emitter.service';
import { DonationService } from 'src/app/services/donation.service';
import { interval } from 'rxjs';
import { AuthenticationService, UserService } from 'src/app/services';


@Component({
  selector: 'app-giffy',
  templateUrl: './giffy.component.html',
  styleUrls: ['./giffy.component.scss']
})
export class GiffyComponent implements OnInit, OnDestroy {


  buttonHide = true;
  counter;
  soundfile;
  intervalId;
  subscription;
  notifications = [];
  newArraySize;
  arraySize;
  url = '';
  user;
  id;
  message;
  amount;
  donator;

  constructor(
    private eventEmitterService: EventEmitterService,
    private donationService: DonationService,
    private userAuth: AuthenticationService,
    private Userservice: UserService
  ) {

   }

  ngOnInit() {
    document.getElementById('photo_equipe').style.display = 'none';

    this.url = window.location.pathname;
    this.getID();
    // this.userAuth.currentUser.subscribe((data: any) => {
    //   if (data) {

    //     this.user = data.user;
    //     this.donationService.getAllDonationDetails(data['user'].id).subscribe(donation => {
    //         this.notifications = Array.from(donation['donationMessages']).reverse();
    //         this.arraySize = this.notifications.length;
    //         this.checkLength();

    //     });
    //   }
    // });


         this.Userservice.getuserById(this.id).subscribe((data) => {
        this.user = data['username'];
        this.arraySize = data['size'];

        this.checkLength();
  });

    // this.Userservice.getById(this.id).subscribe((data) => {
    //   console.log(data);

    // });



    //   $('body').css('background-color', '#00ff00');
    //   document.getElementById('photo_equipe').style.display = 'none';
    // //   if (this.eventEmitterService.subsVar === undefined) {
    // //   this.eventEmitterService.subsVar = this.eventEmitterService.
    // //   invokeFirstComponentFunction.subscribe(() => {
    // //   //   $('body').css('background-color', 'rgb(255, 255, 255)');
    // //   // this.myfnct();
    // //   // this.counter = 5 ;
    // //   //  this.intervalId = null;

    // //     this.startGif();
    // //   });
    // // }

    // this.subscription = this.eventEmitterService.triggerFunction.subscribe( () => {
    //   this.startGif();
    // });
    this.startGif();

  }



   playSound() {
    this.soundfile = '../../../assets/images/sound.ogg';
    document.getElementById('dummy').innerHTML
    = '<audio autoplay="true" style="display:none;" src="../../../assets/images/sound.ogg">' ;

    }

  myfnct() {
    document.getElementById('photo_equipe').style.display = 'none';
   // document.getElementById('btnGif').style.display = 'block';
  }

  startGif() {
    // this.ngOnInit();

    this.playSound();
    document.getElementById('photo_equipe').style.display = 'block';
   // document.getElementById('btnGif').style.display = 'none';
    setTimeout( this.myfnct, 10000);

  }




  loadData() {
    //     this.donationService.getAllDonationDetails(this.id).subscribe(donation => {
    //     this.notifications = Array.from(donation['donationMessages']);
    //     this.newArraySize = this.notifications.length;

    // });

    this.Userservice.getuserById(this.id).subscribe((data) => {
      this.newArraySize = data['size'];

});




    // this.donationService.getDonationDetailsByUsername(this.user).subscribe( details => {
    //   console.log(details);
    // });

    this.donationService.getDonationDetailsByUsername(this.user).subscribe((donation) => {
        this.message = donation['donationMessages'][donation['donationMessages'].length - 1].message;
        this.amount = donation['donationMessages'][donation['donationMessages'].length - 1].montant;
        this.donator = donation['donationMessages'][donation['donationMessages'].length - 1].name;

});


  }


  checkLength() {
    interval(1000).subscribe(data => {

      this.loadData();

      if(this.newArraySize > this.arraySize) {
         window.location.reload();
         //this.ngOnInit();
        // this.startGif();
        this.arraySize = this.newArraySize ;
      }
    });
  }

  getID() {

    this.url = window.location.href ;
    return this.id = this.url.substring(this.url.lastIndexOf('/') + 1);

  }

  ngOnDestroy(): void {

    this.subscription.unsubscribe();
  }

}
