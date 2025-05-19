import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CreditService } from '../../../services/credit.service';
import { Credit, CreditStatut } from '../../../models/credit.model';

@Component({
  selector: 'app-credit-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './credit-list.component.html',
  styleUrls: ['./credit-list.component.scss']
})
export class CreditListComponent implements OnInit {
  credits: Credit[] = [];
  loading = false;
  error = '';
  searchTerm = '';
  statusFilter = '';
  typeFilter = '';
  creditStatut = CreditStatut;

  constructor(private creditService: CreditService) { }

  ngOnInit(): void {
    this.loadCredits();
  }

  loadCredits(): void {
    this.loading = true;
    this.creditService.getAllCredits().subscribe({
      next: (data) => {
        this.credits = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des crédits: ' + err.message;
        this.loading = false;
      }
    });
  }

  deleteCredit(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce crédit?')) {
      this.creditService.deleteCredit(id).subscribe({
        next: () => {
          this.credits = this.credits.filter(credit => credit.id !== id);
        },
        error: (err) => {
          this.error = 'Erreur lors de la suppression du crédit: ' + err.message;
        }
      });
    }
  }

  updateCreditStatus(id: number, status: string): void {
    this.creditService.updateCreditStatus(id, status).subscribe({
      next: (updatedCredit) => {
        const index = this.credits.findIndex(c => c.id === id);
        if (index !== -1) {
          this.credits[index] = updatedCredit;
        }
      },
      error: (err) => {
        this.error = 'Erreur lors de la mise à jour du statut: ' + err.message;
      }
    });
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

  get filteredCredits(): Credit[] {
    return this.credits.filter(credit => {
      const matchesSearch = 
        credit.id?.toString().includes(this.searchTerm) ||
        credit.montant.toString().includes(this.searchTerm);
      
      const matchesStatus = this.statusFilter ? credit.statut === this.statusFilter : true;
      const matchesType = this.typeFilter ? credit.creditType === this.typeFilter : true;
      
      return matchesSearch && matchesStatus && matchesType;
    });
  }
}
