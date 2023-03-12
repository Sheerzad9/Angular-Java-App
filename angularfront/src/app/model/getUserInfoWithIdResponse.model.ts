import { PostFeed } from './postFeed.model';
import { UserInformation } from './userInformation.model';

export interface UserInfoWithIdResponse {
  posts: PostFeed[];
  user: UserInformation;
}
