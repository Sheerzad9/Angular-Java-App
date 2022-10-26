import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SpinnerComponent } from '../shared/spinner/spinner.component';
import { SpinnerModule } from '../shared/spinner/spinner.module';
import { LogInFooterComponent } from './log-in-footer/log-in-footer';
import { LogInComponent } from './log-in.component';

@NgModule({
  declarations: [LogInComponent, LogInFooterComponent],
  imports: [FormsModule, CommonModule, SpinnerModule],
  exports: [LogInComponent, LogInFooterComponent],
})
export class LogInModule {}
