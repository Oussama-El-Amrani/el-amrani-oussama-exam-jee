import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/client.model';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  loading = false;
  error = '';
  searchTerm = '';

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.loading = true;
    this.clientService.getAllClients().subscribe({
      next: (data) => {
        this.clients = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des clients: ' + err.message;
        this.loading = false;
      }
    });
  }

  deleteClient(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce client?')) {
      this.clientService.deleteClient(id).subscribe({
        next: () => {
          this.clients = this.clients.filter(client => client.id !== id);
        },
        error: (err) => {
          this.error = 'Erreur lors de la suppression du client: ' + err.message;
        }
      });
    }
  }

  get filteredClients(): Client[] {
    return this.clients.filter(client => 
      client.nom.toLowerCase().includes(this.searchTerm.toLowerCase()) || 
      client.email.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}
