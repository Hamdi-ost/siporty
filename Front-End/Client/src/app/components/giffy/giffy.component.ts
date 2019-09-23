import { Component, OnInit,EventEmitter } from '@angular/core';
import { EventEmitterService } from 'src/app/services/event-emitter.service';

@Component({
  selector: 'app-giffy',
  templateUrl: './giffy.component.html',
  styleUrls: ['./giffy.component.scss']
})
export class GiffyComponent implements OnInit {


  counter;
  soundfile;
  intervalId;
  constructor(
    private eventEmitterService: EventEmitterService
  ) {

   }

  ngOnInit() {

    if (this.eventEmitterService.subsVar === undefined) {
      this.eventEmitterService.subsVar = this.eventEmitterService.
      invokeFirstComponentFunction.subscribe(() => {
        $('body').css('background-color', 'rgb(255, 255, 255)');
      this.myfnct();
      this.counter = 5 ;
       this.intervalId = null;
        this.startGif();


      });
    }
  }

   playSound() {
    this.soundfile = '../../../assets/images/sound.ogg';
    document.getElementById('dummy').innerHTML
    = '<audio autoplay="true" style="display:none;" src="../../../assets/images/sound.ogg">' ;

    }

  myfnct() {
    document.getElementById('photo_equipe').style.display = 'none';
  }

  startGif() {
    this.ngOnInit();
    this.playSound();
    document.getElementById('photo_equipe').style.display = 'block';
    setTimeout( this.myfnct
      , 5000);

  }

}
