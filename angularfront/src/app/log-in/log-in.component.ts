import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css'],
})
export class LogInComponent {
  error: boolean;
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

  constructor(private http: HttpClient) {
    this.isLoginMode = true;
  }

  onAuthenticate(myForm: any) {
    console.log(myForm);
    this.http
      .post(
        `http://localhost:8080/api/${
          this.isLoginMode ? 'authenticate' : 'signup'
        }`,
        this.isLoginMode ? this.loginData : this.signupData
      )
      .subscribe(
        (res) => {
          console.log(res);
        },
        (err) => {
          console.log(err);
          this.error = true;
        }
      );
  }
}
