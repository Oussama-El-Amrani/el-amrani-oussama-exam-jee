import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credit, CreditPersonnel, CreditImmobilier, CreditProfessionnel } from '../models/credit.model';

@Injectable({
  providedIn: 'root'
})
export class CreditService {
  private apiUrl = 'http://localhost:8888/api/credits';

  constructor(private http: HttpClient) { }

  getAllCredits(): Observable<Credit[]> {
    return this.http.get<Credit[]>(this.apiUrl);
  }

  getCreditById(id: number): Observable<Credit> {
    return this.http.get<Credit>(`${this.apiUrl}/${id}`);
  }

  getCreditsByClientId(clientId: number): Observable<Credit[]> {
    return this.http.get<Credit[]>(`${this.apiUrl}/client/${clientId}`);
  }

  deleteCredit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  updateCreditStatus(id: number, status: string): Observable<Credit> {
    return this.http.patch<Credit>(`${this.apiUrl}/${id}/status?status=${status}`, {});
  }

  // Credit Personnel
  getAllCreditPersonnel(): Observable<CreditPersonnel[]> {
    return this.http.get<CreditPersonnel[]>(`${this.apiUrl}/personnel`);
  }

  getCreditPersonnelById(id: number): Observable<CreditPersonnel> {
    return this.http.get<CreditPersonnel>(`${this.apiUrl}/personnel/${id}`);
  }

  createCreditPersonnel(credit: CreditPersonnel): Observable<CreditPersonnel> {
    return this.http.post<CreditPersonnel>(`${this.apiUrl}/personnel`, credit);
  }

  getCreditPersonnelByClientId(clientId: number): Observable<CreditPersonnel[]> {
    return this.http.get<CreditPersonnel[]>(`${this.apiUrl}/personnel/client/${clientId}`);
  }

  getCreditPersonnelByMotif(motif: string): Observable<CreditPersonnel[]> {
    return this.http.get<CreditPersonnel[]>(`${this.apiUrl}/personnel/motif/${motif}`);
  }

  // Credit Immobilier
  getAllCreditImmobilier(): Observable<CreditImmobilier[]> {
    return this.http.get<CreditImmobilier[]>(`${this.apiUrl}/immobilier`);
  }

  getCreditImmobilierById(id: number): Observable<CreditImmobilier> {
    return this.http.get<CreditImmobilier>(`${this.apiUrl}/immobilier/${id}`);
  }

  createCreditImmobilier(credit: CreditImmobilier): Observable<CreditImmobilier> {
    return this.http.post<CreditImmobilier>(`${this.apiUrl}/immobilier`, credit);
  }

  getCreditImmobilierByClientId(clientId: number): Observable<CreditImmobilier[]> {
    return this.http.get<CreditImmobilier[]>(`${this.apiUrl}/immobilier/client/${clientId}`);
  }

  getCreditImmobilierByTypeBien(typeBien: string): Observable<CreditImmobilier[]> {
    return this.http.get<CreditImmobilier[]>(`${this.apiUrl}/immobilier/type-bien/${typeBien}`);
  }

  // Credit Professionnel
  getAllCreditProfessionnel(): Observable<CreditProfessionnel[]> {
    return this.http.get<CreditProfessionnel[]>(`${this.apiUrl}/professionnel`);
  }

  getCreditProfessionnelById(id: number): Observable<CreditProfessionnel> {
    return this.http.get<CreditProfessionnel>(`${this.apiUrl}/professionnel/${id}`);
  }

  createCreditProfessionnel(credit: CreditProfessionnel): Observable<CreditProfessionnel> {
    return this.http.post<CreditProfessionnel>(`${this.apiUrl}/professionnel`, credit);
  }

  getCreditProfessionnelByClientId(clientId: number): Observable<CreditProfessionnel[]> {
    return this.http.get<CreditProfessionnel[]>(`${this.apiUrl}/professionnel/client/${clientId}`);
  }

  getCreditProfessionnelByRaisonSociale(raisonSociale: string): Observable<CreditProfessionnel[]> {
    return this.http.get<CreditProfessionnel[]>(`${this.apiUrl}/professionnel/raison-sociale/${raisonSociale}`);
  }
}
