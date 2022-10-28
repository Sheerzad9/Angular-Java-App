import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profilepage',
  templateUrl: './profilepage.component.html',
  styleUrls: ['./profilepage.component.css'],
})
export class ProfilepageComponent implements OnInit {
  constructor(
    private activedRoute: ActivatedRoute,
    private profileService: ProfileService
  ) {}

  ngOnInit(): void {
    let id;
    if (this.activedRoute.snapshot.queryParams['id']) {
      this.activedRoute.queryParams.subscribe((params) => {
        id = params.id;
        console.log(params);
      });
    }
    this.profileService.getUserInfo(id).subscribe(
      (res) => {
        console.log(res);
      },
      (err) => console.log(err)
    );
  }
}
