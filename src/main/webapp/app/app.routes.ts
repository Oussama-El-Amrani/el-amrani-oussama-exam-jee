import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ErrorComponent } from './error/error.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Accueil - Gestion des Crédits Bancaires'
  },
  {
    path: 'clients',
    loadComponent: () => import('./components/client/client-list/client-list.component').then(m => m.ClientListComponent),
    title: 'Liste des Clients'
  },
  {
    path: 'clients/new',
    loadComponent: () => import('./components/client/client-form/client-form.component').then(m => m.ClientFormComponent),
    title: 'Nouveau Client'
  },
  {
    path: 'clients/:id',
    loadComponent: () => import('./components/client/client-detail/client-detail.component').then(m => m.ClientDetailComponent),
    title: 'Détails du Client'
  },
  {
    path: 'clients/:id/edit',
    loadComponent: () => import('./components/client/client-form/client-form.component').then(m => m.ClientFormComponent),
    title: 'Modifier Client'
  },
  {
    path: 'credits',
    loadComponent: () => import('./components/credit/credit-list/credit-list.component').then(m => m.CreditListComponent),
    title: 'Liste des Crédits'
  },
  {
    path: 'credits/personnel/new',
    loadComponent: () => import('./components/credit/credit-personnel-form/credit-personnel-form.component').then(m => m.CreditPersonnelFormComponent),
    title: 'Nouveau Crédit Personnel'
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
