import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
})
export class HomepageComponent implements OnInit {
  active: number = 1;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {}

  logOut() {
    this.authService.logout();
  }
}
