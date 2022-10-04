import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css'],
})
export class LogInComponent {
  error: {
    login: boolean;
    signup: boolean;
  } = { login: false, signup: false };
  isLoginMode: boolean;
  loginData: {
    email: string;
    password: string;
  } = { email: null, password: null };
  signupData: {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
  } = { firstName: null, lastName: null, email: null, password: null };

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {
    this.isLoginMode = true;
    this.authService.user.subscribe((user) => {
      console.log('user:');
      console.log(user);
    });
  }

  onAuthenticate(myForm: any) {
    console.log(myForm);

    this.isLoginMode
      ? this.authService.handleLogin(this.loginData).subscribe(
          (res) => {
            this.router.navigate(['/homepage']);
          },
          (err) => (this.error.login = true)
        )
      : this.authService.handleSignup(this.signupData).subscribe(
          (res) => {
            this.router.navigate(['/homepage']);
          },
          (err) => {
            this.error.signup = true;
          }
        );
  }
}
