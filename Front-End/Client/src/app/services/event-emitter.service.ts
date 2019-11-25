import { Injectable, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {

  invokeFirstComponentFunction = new EventEmitter();
  subsVar: Subscription;
  triggerFunction: Subject<void> = new Subject<void>() ;

  triggerArray = new Subject();

  constructor() { }

    // onFirstComponentButtonClick() {
    //   this.invokeFirstComponentFunction.emit();
    // }

    // launchMyFunction() {
    //   console.log('service');
    //   this.triggerFunction.next();
    // }

}
