import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserRole } from '../models/auth.model';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (!authService.isLoggedIn()) {
    // Not logged in, redirect to login page with return url
    router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }

  // Check if route has data.roles and user has one of the required roles
  const roles = route.data?.['roles'] as UserRole[];
  if (roles && roles.length > 0) {
    const hasRequiredRole = roles.some(role => authService.hasRole(role));
    if (!hasRequiredRole) {
      router.navigate(['/']);
      return false;
    }
  }

  return true;
}
