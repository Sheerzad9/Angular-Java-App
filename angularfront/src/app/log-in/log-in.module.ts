import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LogInFooterComponent } from './log-in-footer/log-in-footer';
import { LogInComponent } from './log-in.component';

@NgModule({
  declarations: [LogInComponent, LogInFooterComponent],
  imports: [FormsModule, CommonModule],
  exports: [LogInComponent, LogInFooterComponent],
})
export class LogInModule {}
