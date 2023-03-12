import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { User } from '../auth/user.model';
import { PostService } from './post.service';
import { PostFeed } from '../model/postFeed.model';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css'],
})
export class PostComponent implements OnInit {
  user: User;
  posts: PostFeed[];

  constructor(
    private postService: PostService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.authService.user.subscribe((user) => (this.user = user));
    this.getPostFeed();
  }

  getPostFeed() {
    this.postService.getPosts().subscribe(
      (res) => {
        this.posts = res;
        console.log(this.posts);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  onPublishComment(comment: any, postId: number) {
    if (comment.trim().length <= 1) return;

    const commenReqBody = { comment, userId: this.user.id, postId };
    this.postService.publishComment(commenReqBody).subscribe(
      (_) => {
        this.getPostFeed();
      },
      (err) => console.log(err)
    );
  }

  goToProfile() {
    this.router.navigate(['/profile']);
  }
}
