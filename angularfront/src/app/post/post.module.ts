import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CustomDatePipe } from '../shared/pipes/customDate.pipe';
import { SpinnerModule } from '../shared/spinner/spinner.module';
import { PostComponent } from './post.component';
import { PostmodalComponent } from './postmodal/postmodal.component';

@NgModule({
  declarations: [PostComponent, PostmodalComponent, CustomDatePipe],
  imports: [ReactiveFormsModule, CommonModule, FormsModule, SpinnerModule],
  exports: [PostComponent, PostmodalComponent],
})
export class PostModule {}
