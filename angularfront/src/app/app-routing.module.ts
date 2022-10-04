import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';

const routes: Routes = [
  { path: 'authenticate', component: LogInComponent },
  { path: '', redirectTo: '/authenticate', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
