import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from './user.model';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

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
      .post<AuthResponse>('http://localhost:8080/api/authenticate', bodyData)
      .pipe(
        tap((resData) => {
          this.handleAuthentication(
            resData.user.firstName,
            resData.user.lastName,
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
      .post<AuthResponse>('http://localhost:8080/api/signup', bodyData)
      .pipe(
        tap((resData) => {
          this.handleAuthentication(
            resData.user.firstName,
            resData.user.lastName,
            resData.user.email,
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
    _token: string,
    _tokenExpirationDate: number
  ) {
    // We will get _tokenExpirationDate as milliseconds to two hours in future from our backend, so we have to convert millis to date
    const tokenExpirationInDate = new Date(_tokenExpirationDate);
    const tempUser = new User(
      firstName,
      lastName,
      email,
      _token,
      new Date(tokenExpirationInDate)
    );
    this.user.next(tempUser);
  }

  logout() {
    this.user.next(null);
    this.router.navigate(['/authenticate']);
  }
}
