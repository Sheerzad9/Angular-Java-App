import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { PostFeed } from '../model/postFeed.model';

@Injectable({ providedIn: 'root' })
export class PostService {
  constructor(private http: HttpClient) {}

  addNewPostWithImage(formData: FormData) {
    return this.http.post(`${environment.baseUrl}/file/upload`, formData);
  }

  addNewPost(postData: {
    userEmail: string;
    post: {
      title: string;
      content: string;
    };
  }) {
    return this.http.post(`${environment.baseUrl}/post`, postData);
  }

  getPosts(ascendingOrder: boolean = false) {
    return this.http.get<PostFeed[]>(`${environment.baseUrl}/posts`, {
      params: new HttpParams().set('ascending', ascendingOrder),
    });
  }

  publishComment(commentBody: {
    comment: string;
    userId: number;
    postId: number;
  }) {
    return this.http.post(`${environment.baseUrl}/post/comment`, commentBody);
  }
}
