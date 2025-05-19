import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Remboursement } from '../models/remboursement.model';

@Injectable({
  providedIn: 'root'
})
export class RemboursementService {
  private apiUrl = 'http://localhost:8888/api/remboursements';

  constructor(private http: HttpClient) { }

  getAllRemboursements(): Observable<Remboursement[]> {
    return this.http.get<Remboursement[]>(this.apiUrl);
  }

  getRemboursementById(id: number): Observable<Remboursement> {
    return this.http.get<Remboursement>(`${this.apiUrl}/${id}`);
  }

  createRemboursement(remboursement: Remboursement): Observable<Remboursement> {
    return this.http.post<Remboursement>(this.apiUrl, remboursement);
  }

  deleteRemboursement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getRemboursementsByCreditId(creditId: number): Observable<Remboursement[]> {
    return this.http.get<Remboursement[]>(`${this.apiUrl}/credit/${creditId}`);
  }

  getRemboursementsByDateBetween(startDate: string, endDate: string): Observable<Remboursement[]> {
    return this.http.get<Remboursement[]>(`${this.apiUrl}/date-range?startDate=${startDate}&endDate=${endDate}`);
  }

  getTotalPaymentsByCreditId(creditId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/credit/${creditId}/total`);
  }

  getRemainingAmountToPay(creditId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/credit/${creditId}/remaining`);
  }
}
