import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { environment } from 'environments/environment';
import { RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserRole } from '../models/auth.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
  environment = environment;
  isLoggedIn = false;
  username = '';
  userRole = '';
  userRoles = UserRole;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.currentUser$.subscribe(user => {
      this.isLoggedIn = !!user;
      if (user) {
        this.username = user.username;
        this.userRole = user.role;
      }
    });
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
