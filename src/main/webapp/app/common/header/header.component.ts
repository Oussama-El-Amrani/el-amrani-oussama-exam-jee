import { Component, OnInit } from '@angular/core';
import { CommonModule, NgOptimizedImage } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserRole } from '../../models/auth.model';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage, RouterLink, RouterLinkActive],
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
  isLoggedIn = false;
  username = '';
  userRole = '';
  userRoles = UserRole;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.currentUser$.subscribe(user => {
      this.isLoggedIn = !!user;
      if (user) {
        this.username = user.username;
        this.userRole = user.role;
      }
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  isEmploye(): boolean {
    return this.authService.isEmploye();
  }

  isClient(): boolean {
    return this.authService.isClient();
  }
}
