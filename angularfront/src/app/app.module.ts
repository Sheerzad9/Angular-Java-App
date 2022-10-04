import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { LogInComponent } from './log-in/log-in.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MyAngularMaterialModule } from './myangularmaterial.module';
import { LogInModule } from './log-in/log-in.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MyAngularMaterialModule,
    LogInModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
