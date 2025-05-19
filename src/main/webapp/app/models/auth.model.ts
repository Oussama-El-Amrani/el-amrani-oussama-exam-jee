export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string;
  role?: string;
}

export interface AuthResponse {
  token: string;
}

export enum UserRole {
  ROLE_CLIENT = 'ROLE_CLIENT',
  ROLE_EMPLOYE = 'ROLE_EMPLOYE',
  ROLE_ADMIN = 'ROLE_ADMIN'
}
