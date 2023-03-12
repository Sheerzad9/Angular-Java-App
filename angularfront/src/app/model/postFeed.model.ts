import { Post } from './post.model';
import { postComment } from './postComment.model';
import { UserInformation } from './userInformation.model';

export interface PostFeed {
  post: Post;
  creator: UserInformation;
  postComments: postComment[];
}
