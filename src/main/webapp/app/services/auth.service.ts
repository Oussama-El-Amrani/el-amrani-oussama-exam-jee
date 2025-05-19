import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { LoginRequest, RegisterRequest, AuthResponse, UserRole } from '../models/auth.model';
import { environment } from '../../environments/environment';
import { jwtDecode } from 'jwt-decode';

interface JwtPayload {
  sub: string;
  exp: number;
  authorities: string[];
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.apiPath}/api/auth`;
  private currentUserSubject = new BehaviorSubject<any>(null);
  public currentUser$ = this.currentUserSubject.asObservable();
  
  constructor(private http: HttpClient) {
    this.loadUserFromStorage();
  }
  
  private loadUserFromStorage(): void {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decodedToken = jwtDecode<JwtPayload>(token);
        const currentTime = Date.now() / 1000;
        
        if (decodedToken.exp > currentTime) {
          this.currentUserSubject.next({
            username: decodedToken.sub,
            role: decodedToken.authorities[0]
          });
        } else {
          this.logout();
        }
      } catch (error) {
        this.logout();
      }
    }
  }
  
  login(loginRequest: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, loginRequest)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          this.loadUserFromStorage();
        })
      );
  }
  
  register(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, registerRequest)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          this.loadUserFromStorage();
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
    return !!this.getToken();
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
