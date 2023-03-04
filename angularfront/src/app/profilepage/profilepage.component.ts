import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostFeed } from '../post/post.component';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profilepage',
  templateUrl: './profilepage.component.html',
  styleUrls: ['./profilepage.component.css'],
})
export class ProfilepageComponent implements OnInit {
  id: number;
  postFeed: any;
  constructor(
    private activedRoute: ActivatedRoute,
    private profileService: ProfileService
  ) {
    if (this.activedRoute.snapshot.queryParams['id']) {
      this.activedRoute.queryParams.subscribe((params) => {
        this.id = params.id;
        console.log(params);
      });
    }
  }

  ngOnInit(): void {
    this.getPostfeed();
  }
  getPostfeed() {
    this.profileService.getUserInfo(this.id).subscribe(
      (res) => {
        console.log(res);
        this.postFeed = res;
      },
      (err) => console.log(err)
    );
  }
}
