import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/auth/user.model';
import { PostService } from '../post.service';

@Component({
  selector: 'app-postmodal',
  templateUrl: './postmodal.component.html',
  styleUrls: ['./postmodal.component.css'],
})
export class PostmodalComponent implements OnInit {
  user: User;
  error: boolean = false;
  userFile: File;
  postForm: FormGroup;

  constructor(
    public activeModal: NgbActiveModal,
    private fb: FormBuilder,
    private authService: AuthService,
    private postService: PostService
  ) {
    this.authService.user.subscribe((user) => (this.user = user));
  }

  ngOnInit() {
    this.initForm();
  }

  selected() {
    console.log();
  }

  initForm() {
    this.postForm = this.fb.group({
      title: new FormControl('', [
        Validators.required,
        Validators.minLength(5),
      ]),
      content: new FormControl('', [
        Validators.required,
        Validators.minLength(15),
      ]),
    });
  }

  onFileSelected(event: any) {
    console.log(event.target.files[0]);
    this.error = !event.target.files[0].type.startsWith('image');
    console.log(event.target.files[0].type.startsWith('image'));
    if (this.error) return;

    this.userFile = <File>event.target.files[0];
  }

  onSubmit() {
    let filename =
      this.user.firstName +
      Date.now() +
      '.' +
      this.userFile.name.split('?')[0].split('.').pop();

    const formData = new FormData();
    formData.append('file', this.userFile, filename);
    formData.append('email', this.user.email);
    formData.append('title', this.postForm.value.title);
    formData.append('content', this.postForm.value.content);

    this.postService.addNewPostWithImage(formData).subscribe(
      (res) => console.log(),
      (err) => console.log()
    );
  }

  onCloseModal() {
    this.activeModal.close();
  }
}
