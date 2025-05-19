import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CreditService } from '../../../services/credit.service';
import { ClientService } from '../../../services/client.service';
import { CreditPersonnel, CreditStatut, CreditType } from '../../../models/credit.model';
import { Client } from '../../../models/client.model';

@Component({
  selector: 'app-credit-personnel-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './credit-personnel-form.component.html',
  styleUrls: ['./credit-personnel-form.component.scss']
})
export class CreditPersonnelFormComponent implements OnInit {
  creditForm!: FormGroup;
  isEditMode = false;
  creditId?: number;
  loading = false;
  error = '';
  submitted = false;
  clients: Client[] = [];
  preselectedClientId?: number;

  constructor(
    private fb: FormBuilder,
    private creditService: CreditService,
    private clientService: ClientService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadClients();
    this.initForm();
    
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.creditId = +params['id'];
        this.isEditMode = true;
        this.loadCredit(this.creditId);
      }
    });

    this.route.queryParams.subscribe(params => {
      if (params['clientId']) {
        this.preselectedClientId = +params['clientId'];
        this.creditForm.patchValue({ clientId: this.preselectedClientId });
      }
    });
  }

  initForm(): void {
    this.creditForm = this.fb.group({
      clientId: ['', Validators.required],
      montant: ['', [Validators.required, Validators.min(0)]],
      dureeRemboursement: ['', [Validators.required, Validators.min(1)]],
      tauxInteret: ['', [Validators.required, Validators.min(0), Validators.max(1)]],
      motif: ['', Validators.required]
    });
  }

  loadClients(): void {
    this.clientService.getAllClients().subscribe({
      next: (data) => {
        this.clients = data;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des clients: ' + err.message;
      }
    });
  }

  loadCredit(id: number): void {
    this.loading = true;
    this.creditService.getCreditPersonnelById(id).subscribe({
      next: (credit) => {
        this.creditForm.patchValue({
          clientId: credit.clientId,
          montant: credit.montant,
          dureeRemboursement: credit.dureeRemboursement,
          tauxInteret: credit.tauxInteret,
          motif: credit.motif
        });
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement du crédit: ' + err.message;
        this.loading = false;
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;
    
    if (this.creditForm.invalid) {
      return;
    }
    
    this.loading = true;
    const creditData: CreditPersonnel = {
      ...this.creditForm.value,
      dateDemande: new Date().toISOString().split('T')[0],
      statut: CreditStatut.EN_COURS,
      creditType: CreditType.PERSONNEL
    };
    
    if (this.isEditMode && this.creditId) {
      creditData.id = this.creditId;
      // Update logic would go here if needed
      this.error = 'La modification des crédits n\'est pas prise en charge.';
      this.loading = false;
    } else {
      this.creditService.createCreditPersonnel(creditData).subscribe({
        next: () => {
          this.router.navigate(['/credits'], { 
            state: { msgSuccess: 'Crédit personnel créé avec succès!' } 
          });
        },
        error: (err) => {
          this.error = 'Erreur lors de la création du crédit: ' + err.message;
          this.loading = false;
        }
      });
    }
  }

  get f() { return this.creditForm.controls; }
}
