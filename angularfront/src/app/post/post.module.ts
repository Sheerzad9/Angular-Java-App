import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CustomDatePipe } from '../shared/pipes/customDate.pipe';
import { SpinnerModule } from '../shared/spinner/spinner.module';
import { PostComponent } from './post.component';
import { PostfeedComponent } from './postfeed/postfeed.component';
import { PostmodalComponent } from './postmodal/postmodal.component';

@NgModule({
  declarations: [
    PostComponent,
    PostmodalComponent,
    CustomDatePipe,
    PostfeedComponent,
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    FormsModule,
    SpinnerModule,
    RouterModule,
  ],
  exports: [PostComponent, PostmodalComponent, PostfeedComponent],
})
export class PostModule {}
