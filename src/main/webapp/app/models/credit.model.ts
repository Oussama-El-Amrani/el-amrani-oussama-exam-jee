export interface Credit {
  id?: number;
  dateDemande: string;
  statut: string;
  dateAcception?: string;
  montant: number;
  dureeRemboursement: number;
  tauxInteret: number;
  clientId: number;
  creditType: string;
}

export interface CreditPersonnel extends Credit {
  motif: string;
}

export interface CreditImmobilier extends Credit {
  typeBien: string;
}

export interface CreditProfessionnel extends Credit {
  motif: string;
  raisonSociale: string;
}

export const CreditStatut = {
  EN_COURS: 'En cours',
  ACCEPTE: 'Accepté',
  REJETE: 'Rejeté'
};

export const CreditType = {
  PERSONNEL: 'CreditPersonnel',
  IMMOBILIER: 'CreditImmobilier',
  PROFESSIONNEL: 'CreditProfessionnel'
};
