import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | boolean
    | UrlTree
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree> {
    return this.authService.user.pipe(
      map((user) => {
        // with '!!' infront variable, js will falsy values (null | undefined) to false type and any other than true (like object array..) to true type
        const isAuth = !!user;
        if (isAuth) return true;
        // else return new urltree/ redirect to auth page
        return this.router.createUrlTree(['/authenticate']);
      })
    );
  }
}
