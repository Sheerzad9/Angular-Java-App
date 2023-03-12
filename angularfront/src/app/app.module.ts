import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule, NgbToastModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LogInModule } from './log-in/log-in.module';
import { HomepageComponent } from './homepage/homepage.component';
import { ProfilepageComponent } from './profilepage/profilepage.component';
import { MenuComponent } from './menu/menu.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptorService } from './auth/auth-intertceptor.service';
import { PostModule } from './post/post.module';
import { LoadingInterceptor } from './shared/loading-interceptor.service';
import { SpinnerModule } from './shared/spinner/spinner.module';
import { DeletePostConfirmationModalComponent } from './modals/delete-post-confirmation-modal/delete-post-confirmation-modal.component';
import { ToastsContainer } from './shared/toast/toast-container.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    ProfilepageComponent,
    MenuComponent,
    DeletePostConfirmationModalComponent,
    ToastsContainer,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    NgbToastModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LogInModule,
    ReactiveFormsModule,
    PostModule,
    SpinnerModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoadingInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
