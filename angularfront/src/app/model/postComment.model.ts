import { UserInformation } from './userInformation.model';
import { Comment } from './comment.model';

export interface postComment {
  comment: Comment;
  user: UserInformation;
}
