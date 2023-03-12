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
    fullName: string;
    email: string;
    profilePicUrl: string; // bcrypted
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
            resData.user.id,
            resData.user.firstName,
            resData.user.lastName,
            resData.user.fullName,
            resData.user.email,
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
            resData.user.id,
            resData.user.firstName,
            resData.user.lastName,
            resData.user.fullName,
            resData.user.email,
            resData.jwtResponse.jwtToken,
            resData.jwtResponse.expiryDateInMillis
          );
        })
      );
  }

  handleAuthentication(
    id: number,
    firstName: string,
    lastName: string,
    fullName: string,
    email: string,
    _token: string,
    _tokenExpirationDate: number
  ) {
    // We will get _tokenExpirationDate as milliseconds to two hours in future from our backend, so we have to convert millis to date
    const tokenExpirationInDate = new Date(_tokenExpirationDate);
    const tempUser = new User(
      id,
      firstName,
      lastName,
      fullName,
      email,
      _token,
      new Date(tokenExpirationInDate)
    );
    this.user.next(tempUser);
    localStorage.setItem('userData', JSON.stringify(tempUser));
    // setting autologout
    this.autoLogout(tokenExpirationInDate.getTime() - new Date().getTime());
  }

  autoLogin() {
    let userData: {
      id: string;
      firstName: string;
      lastName: string;
      fullName: string;
      email: string;
      _token: string;
      _tokenExpirationDate: string;
    };
    userData = JSON.parse(window.localStorage.getItem('userData'));
    if (!userData) return;

    const tempUser = new User(
      +userData.id,
      userData.firstName,
      userData.lastName,
      userData.fullName,
      userData.email,
      userData._token,
      new Date(userData._tokenExpirationDate)
    );

    // Checking if expirytime of token is exceeded (the expirytime is setted automatically to 2hours ahead of time from last login)
    if (!tempUser.token) {
      window.localStorage.removeItem('userData');
      return;
    }

    // Everyhting should be ok ak this point, so we save new user & set autoLogout time and process to log in;
    this.user.next(tempUser);
    this.autoLogout(
      new Date(userData._tokenExpirationDate).getTime() - new Date().getTime()
    );
  }

  autoLogout(timeInMillis: number) {
    setTimeout(() => {
      this.logout();
    }, timeInMillis);
  }

  logout() {
    this.user.next(null);
    window.localStorage.removeItem('userData');
    this.router.navigate(['/authenticate']);
  }
}
