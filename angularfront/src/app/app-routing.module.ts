import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { HomepageComponent } from './homepage/homepage.component';
import { LogInComponent } from './log-in/log-in.component';
import { ProfilepageComponent } from './profilepage/profilepage.component';

const routes: Routes = [
  { path: 'authenticate', component: LogInComponent },
  { path: '', redirectTo: '/authenticate', pathMatch: 'full' },
  { path: 'homepage', component: HomepageComponent, canActivate: [AuthGuard] },
  {
    path: 'profile',
    component: ProfilepageComponent,
    canActivate: [AuthGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
