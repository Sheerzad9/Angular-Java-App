import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/auth/user.model';
import { ProfileService } from 'src/app/profilepage/profile.service';
import { PostFeed } from '../post.component';
import { PostService } from '../post.service';
import { DeletePostConfirmationModalComponent } from 'src/app/modals/delete-post-confirmation-modal/delete-post-confirmation-modal.component';

@Component({
  selector: 'app-postfeed',
  templateUrl: './postfeed.component.html',
  styleUrls: ['./postfeed.component.css'],
})
export class PostfeedComponent implements OnInit {
  @Input() posts: PostFeed[];
  @Input() profileId: number;
  user: User;

  constructor(
    private postService: PostService,
    private authService: AuthService,
    private profileService: ProfileService,
    private router: Router,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.authService.user.subscribe((user) => {
      console.log('User details: ', user);
      this.user = user;
    });
  }

  getPostFeed() {
    if (this.profileId) {
      this.profileService.getUserInfo(this.profileId).subscribe(
        (res) => (this.posts = res.posts),
        (err) => console.log(err)
      );
      return;
    }

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

  onDeletePost() {
    let dlg = this.modalService
      .open(DeletePostConfirmationModalComponent, {
        size: 'lg',
        animation: true,
      })
      .result.then((res) => console.log(res))
      .catch((err) => console.log(err));
  }
}
