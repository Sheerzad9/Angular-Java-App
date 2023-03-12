import { Component } from '@angular/core';
import { LoadingService } from '../loading.service';

@Component({
  selector: 'spinner',
  template:
    "<ng-container *ngIf='loadingService.isLoading | async'><div class='loader'><div class='loader-inner'><div class='lds-ring'><div></div><div></div><div></div><div></div></div></div></div></ng-container>",
  styleUrls: ['./spinner.component.css'],
})
export class SpinnerComponent {
  constructor(public loadingService: LoadingService) {}
}
