import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/client.model';

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent implements OnInit {
  clientForm!: FormGroup;
  isEditMode = false;
  clientId?: number;
  loading = false;
  error = '';
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private clientService: ClientService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initForm();
    
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.clientId = +params['id'];
        this.isEditMode = true;
        this.loadClient(this.clientId);
      }
    });
  }

  initForm(): void {
    this.clientForm = this.fb.group({
      nom: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(100)]]
    });
  }

  loadClient(id: number): void {
    this.loading = true;
    this.clientService.getClientById(id).subscribe({
      next: (client) => {
        this.clientForm.patchValue(client);
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement du client: ' + err.message;
        this.loading = false;
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;
    
    if (this.clientForm.invalid) {
      return;
    }
    
    this.loading = true;
    const clientData: Client = this.clientForm.value;
    
    if (this.isEditMode && this.clientId) {
      clientData.id = this.clientId;
      this.clientService.updateClient(clientData).subscribe({
        next: () => {
          this.router.navigate(['/clients'], { 
            state: { msgSuccess: 'Client mis à jour avec succès!' } 
          });
        },
        error: (err) => {
          this.error = 'Erreur lors de la mise à jour du client: ' + err.message;
          this.loading = false;
        }
      });
    } else {
      this.clientService.createClient(clientData).subscribe({
        next: () => {
          this.router.navigate(['/clients'], { 
            state: { msgSuccess: 'Client créé avec succès!' } 
          });
        },
        error: (err) => {
          this.error = 'Erreur lors de la création du client: ' + err.message;
          this.loading = false;
        }
      });
    }
  }

  get f() { return this.clientForm.controls; }
}
