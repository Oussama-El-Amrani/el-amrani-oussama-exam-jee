import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../../../services/client.service';
import { CreditService } from '../../../services/credit.service';
import { Client } from '../../../models/client.model';
import { Credit, CreditStatut } from '../../../models/credit.model';

@Component({
  selector: 'app-client-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './client-detail.component.html',
  styleUrls: ['./client-detail.component.scss']
})
export class ClientDetailComponent implements OnInit {
  client?: Client;
  credits: Credit[] = [];
  loading = false;
  error = '';
  creditStatut = CreditStatut;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private creditService: CreditService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        const clientId = +params['id'];
        this.loadClient(clientId);
        this.loadClientCredits(clientId);
      }
    });
  }

  loadClient(id: number): void {
    this.loading = true;
    this.clientService.getClientById(id).subscribe({
      next: (data) => {
        this.client = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement du client: ' + err.message;
        this.loading = false;
      }
    });
  }

  loadClientCredits(clientId: number): void {
    this.creditService.getCreditsByClientId(clientId).subscribe({
      next: (data) => {
        this.credits = data;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des crédits: ' + err.message;
      }
    });
  }

  deleteClient(): void {
    if (this.client && confirm('Êtes-vous sûr de vouloir supprimer ce client?')) {
      this.clientService.deleteClient(this.client.id!).subscribe({
        next: () => {
          this.router.navigate(['/clients'], { 
            state: { msgSuccess: 'Client supprimé avec succès!' } 
          });
        },
        error: (err) => {
          this.error = 'Erreur lors de la suppression du client: ' + err.message;
        }
      });
    }
  }

  getCreditTypeLabel(type: string): string {
    switch (type) {
      case 'CreditPersonnel':
        return 'Personnel';
      case 'CreditImmobilier':
        return 'Immobilier';
      case 'CreditProfessionnel':
        return 'Professionnel';
      default:
        return type;
    }
  }

  getStatusClass(status: string): string {
    switch (status) {
      case CreditStatut.ACCEPTE:
        return 'badge bg-success';
      case CreditStatut.REJETE:
        return 'badge bg-danger';
      case CreditStatut.EN_COURS:
        return 'badge bg-warning';
      default:
        return 'badge bg-secondary';
    }
  }
}
