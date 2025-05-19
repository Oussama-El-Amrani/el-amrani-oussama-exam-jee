import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ErrorComponent } from './error/error.component';
import { authGuard } from './guards/auth.guard';
import { UserRole } from './models/auth.model';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Accueil - Gestion des Crédits Bancaires'
  },
  {
    path: 'login',
    loadComponent: () => import('./components/auth/login/login.component').then(m => m.LoginComponent),
    title: 'Connexion'
  },
  {
    path: 'register',
    loadComponent: () => import('./components/auth/register/register.component').then(m => m.RegisterComponent),
    title: 'Inscription'
  },
  {
    path: 'clients',
    loadComponent: () => import('./components/client/client-list/client-list.component').then(m => m.ClientListComponent),
    title: 'Liste des Clients',
    canActivate: [authGuard],
    data: { roles: [UserRole.ROLE_ADMIN, UserRole.ROLE_EMPLOYE] }
  },
  {
    path: 'clients/new',
    loadComponent: () => import('./components/client/client-form/client-form.component').then(m => m.ClientFormComponent),
    title: 'Nouveau Client',
    canActivate: [authGuard],
    data: { roles: [UserRole.ROLE_ADMIN, UserRole.ROLE_EMPLOYE] }
  },
  {
    path: 'clients/:id',
    loadComponent: () => import('./components/client/client-detail/client-detail.component').then(m => m.ClientDetailComponent),
    title: 'Détails du Client',
    canActivate: [authGuard],
    data: { roles: [UserRole.ROLE_ADMIN, UserRole.ROLE_EMPLOYE] }
  },
  {
    path: 'clients/:id/edit',
    loadComponent: () => import('./components/client/client-form/client-form.component').then(m => m.ClientFormComponent),
    title: 'Modifier Client',
    canActivate: [authGuard],
    data: { roles: [UserRole.ROLE_ADMIN, UserRole.ROLE_EMPLOYE] }
  },
  {
    path: 'credits',
    loadComponent: () => import('./components/credit/credit-list/credit-list.component').then(m => m.CreditListComponent),
    title: 'Liste des Crédits',
    canActivate: [authGuard],
    data: { roles: [UserRole.ROLE_ADMIN, UserRole.ROLE_EMPLOYE, UserRole.ROLE_CLIENT] }
  },
  {
    path: 'credits/personnel/new',
    loadComponent: () => import('./components/credit/credit-personnel-form/credit-personnel-form.component').then(m => m.CreditPersonnelFormComponent),
    title: 'Nouveau Crédit Personnel',
    canActivate: [authGuard],
    data: { roles: [UserRole.ROLE_ADMIN, UserRole.ROLE_EMPLOYE] }
  },
  {
    path: 'error',
    component: ErrorComponent,
    title: 'Erreur'
  },
  {
    path: '**',
    component: ErrorComponent,
    title: 'Page non trouvée'
  }
];
