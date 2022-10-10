import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from './user.model';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';

interface AuthResponse {
  user: {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string; // bcrypted
  };
  jwtResponse: {
    jwtToken: string;
    expiryDateInMillis: number;
  };
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  user = new BehaviorSubject<User>(null);

  constructor(private http: HttpClient, private router: Router) {}

  handleLogin(bodyData: { email: string; password: string }) {
    return this.http
      .post<AuthResponse>(`${environment.baseUrl}/authenticate`, bodyData)
      .pipe(
        tap((resData) => {
          this.handleAuthentication(
            resData.user.firstName,
            resData.user.lastName,
            resData.user.email,
            bodyData.password,
            resData.jwtResponse.jwtToken,
            resData.jwtResponse.expiryDateInMillis
          );
        })
      );
  }

  handleSignup(bodyData: {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
  }) {
    return this.http
      .post<AuthResponse>(`${environment.baseUrl}/signup`, bodyData)
      .pipe(
        tap((resData) => {
          this.handleAuthentication(
            resData.user.firstName,
            resData.user.lastName,
            resData.user.email,
            bodyData.password,
            resData.jwtResponse.jwtToken,
            resData.jwtResponse.expiryDateInMillis
          );
        })
      );
  }

  handleAuthentication(
    firstName: string,
    lastName: string,
    email: string,
    password: string,
    _token: string,
    _tokenExpirationDate: number
  ) {
    // We will get _tokenExpirationDate as milliseconds to two hours in future from our backend, so we have to convert millis to date
    const tokenExpirationInDate = new Date(_tokenExpirationDate);
    const tempUser = new User(
      firstName,
      lastName,
      email,
      password,
      _token,
      new Date(tokenExpirationInDate)
    );
    this.user.next(tempUser);
    localStorage.setItem('userData', JSON.stringify(tempUser));
  }

  autoLogin() {
    let tempUser: User;
    tempUser = JSON.parse(window.localStorage.getItem('userData'));
    if (!tempUser) return;

    this.handleLogin({
      email: tempUser.email,
      password: tempUser.password,
    }).subscribe(
      (res) => {
        this.router.navigate(['/homepage']);
      },
      (err) => {}
    );
  }

  logout() {
    this.user.next(null);
    window.localStorage.removeItem('userData');
    this.router.navigate(['/authenticate']);
  }
}
