import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  users: any[];
  constructor(private http: HttpClient) {}

  fetchData() {
    this.http.get('http://localhost:8080/api/users').subscribe((data: any) => {
      this.users = data;
      console.log(data);
    });
  }
}
