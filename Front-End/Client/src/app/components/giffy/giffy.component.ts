import { Component, OnInit,EventEmitter, OnDestroy } from '@angular/core';
import { EventEmitterService } from 'src/app/services/event-emitter.service';

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

  constructor(
    private eventEmitterService: EventEmitterService
  ) {

   }

  ngOnInit() {
      $('body').css('background-color', 'rgb(255, 255, 255)');
      document.getElementById('photo_equipe').style.display = 'none';
    //   if (this.eventEmitterService.subsVar === undefined) {
    //   this.eventEmitterService.subsVar = this.eventEmitterService.
    //   invokeFirstComponentFunction.subscribe(() => {
    //   //   $('body').css('background-color', 'rgb(255, 255, 255)');
    //   // this.myfnct();
    //   // this.counter = 5 ;
    //   //  this.intervalId = null;

    //     this.startGif();
    //   });
    // }

    this.subscription = this.eventEmitterService.triggerFunction.subscribe( () => {
      this.startGif();
    });

  }



   playSound() {
    this.soundfile = '../../../assets/images/sound.ogg';
    document.getElementById('dummy').innerHTML
    = '<audio autoplay="true" style="display:none;" src="../../../assets/images/sound.ogg">' ;

    }

  myfnct() {
    document.getElementById('photo_equipe').style.display = 'none';
    document.getElementById('btnGif').style.display = 'block';
  }

  startGif() {
    this.ngOnInit();
    this.playSound();
    document.getElementById('photo_equipe').style.display = 'block';
    document.getElementById('btnGif').style.display = 'none';
    setTimeout( this.myfnct, 5000);

  }


  ngOnDestroy(): void {

    this.subscription.unsubscribe();
  }

}
