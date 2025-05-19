export interface Remboursement {
  id?: number;
  date: string;
  montant: number;
  type: string;
  creditId: number;
}

export const RemboursementType = {
  MENSUALITE: 'Mensualité',
  ANTICIPE: 'Remboursement anticipé'
};
