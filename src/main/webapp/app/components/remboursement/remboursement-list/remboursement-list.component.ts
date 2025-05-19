import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RemboursementService } from '../../../services/remboursement.service';
import { Remboursement, RemboursementType } from '../../../models/remboursement.model';

@Component({
  selector: 'app-remboursement-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './remboursement-list.component.html',
  styleUrls: ['./remboursement-list.component.scss']
})
export class RemboursementListComponent implements OnInit {
  remboursements: Remboursement[] = [];
  loading = false;
  error = '';
  searchTerm = '';
  typeFilter = '';
  remboursementType = RemboursementType;

  constructor(private remboursementService: RemboursementService) { }

  ngOnInit(): void {
    this.loadRemboursements();
  }

  loadRemboursements(): void {
    this.loading = true;
    this.remboursementService.getAllRemboursements().subscribe({
      next: (data) => {
        this.remboursements = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des remboursements: ' + err.message;
        this.loading = false;
      }
    });
  }

  deleteRemboursement(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce remboursement?')) {
      this.remboursementService.deleteRemboursement(id).subscribe({
        next: () => {
          this.remboursements = this.remboursements.filter(remboursement => remboursement.id !== id);
        },
        error: (err) => {
          this.error = 'Erreur lors de la suppression du remboursement: ' + err.message;
        }
      });
    }
  }

  get filteredRemboursements(): Remboursement[] {
    return this.remboursements.filter(remboursement => {
      const matchesSearch = 
        remboursement.id?.toString().includes(this.searchTerm) ||
        remboursement.montant.toString().includes(this.searchTerm) ||
        remboursement.creditId.toString().includes(this.searchTerm);
      
      const matchesType = this.typeFilter ? remboursement.type === this.typeFilter : true;
      
      return matchesSearch && matchesType;
    });
  }
}
