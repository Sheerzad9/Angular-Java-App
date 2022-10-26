import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from '../auth/auth.service';
import { PostmodalComponent } from '../post/postmodal/postmodal.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
})
export class HomepageComponent {
  constructor(
    private authService: AuthService,
    private modalService: NgbModal
  ) {}

  onAddPost() {
    let dlg = this.modalService
      .open(PostmodalComponent, { size: 'lg' })
      .result.then(
        (res) => {},
        (err) => {
          console.log(err);
        }
      );
  }
}
