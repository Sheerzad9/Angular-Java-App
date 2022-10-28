import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(private http: HttpClient) {}

  getUserInfo(id: number) {
    return this.http.get(`${environment.baseUrl}/user`, {
      params: new HttpParams().set('id', id),
    });
  }
}
