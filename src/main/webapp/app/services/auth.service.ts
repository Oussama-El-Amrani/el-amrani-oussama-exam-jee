import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { LoginRequest, RegisterRequest, AuthResponse, UserRole } from '../models/auth.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.apiPath}/api/auth`;
  private currentUserSubject = new BehaviorSubject<any>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient) {
    // For demo purposes, set a mock user
    // In a real application, you would decode the JWT token
    this.currentUserSubject.next({
      username: 'admin',
      role: UserRole.ROLE_ADMIN
    });
  }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, loginRequest)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          // For demo purposes, set user based on username
          let role = UserRole.ROLE_CLIENT;
          if (loginRequest.username.includes('admin')) {
            role = UserRole.ROLE_ADMIN;
          } else if (loginRequest.username.includes('employe')) {
            role = UserRole.ROLE_EMPLOYE;
          }

          this.currentUserSubject.next({
            username: loginRequest.username,
            role: role
          });
        })
      );
  }

  register(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, registerRequest)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          this.currentUserSubject.next({
            username: registerRequest.username,
            role: registerRequest.role || UserRole.ROLE_CLIENT
          });
        })
      );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.currentUserSubject.next(null);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.currentUserSubject.value;
  }

  hasRole(role: UserRole): boolean {
    const currentUser = this.currentUserSubject.value;
    return currentUser && currentUser.role === role;
  }

  isAdmin(): boolean {
    return this.hasRole(UserRole.ROLE_ADMIN);
  }

  isEmploye(): boolean {
    return this.hasRole(UserRole.ROLE_EMPLOYE);
  }

  isClient(): boolean {
    return this.hasRole(UserRole.ROLE_CLIENT);
  }
}
