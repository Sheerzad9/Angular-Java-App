import { Component } from '@angular/core';
import { LoadingService } from '../loading.service';

@Component({
  selector: 'spinner',
  template:
    "<ng-container *ngIf='loadingService.isLoading | async'><div class='center'><div class='spinner-border text-info' style='width: 10rem; height: 10rem; margin-top: 50%;' role='status'><span class='sr-only'>Loading...</span></div></div></ng-container>",
  styleUrls: ['./spinner.component.css'],
})
export class SpinnerComponent {
  constructor(public loadingService: LoadingService) {}
}
