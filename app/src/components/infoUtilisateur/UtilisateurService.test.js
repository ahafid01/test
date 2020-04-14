import moxios from 'moxios';
import { cleanup } from '@testing-library/react';
import UtilisateurService from './UtilisateurService';

beforeEach(() => {
  moxios.install();
});

afterEach(() => {
  moxios.uninstall();
  cleanup();
});

jest.mock('./UtilisateurService');

const mockResponse = {
  adresseFacturation: {
    codePostal: 92150,
    ligne1: '13-17 rue Pagès',
    nomComplet: 'M. Patrice MARTIN - Ingenico Healthcare',
    ville: 'Suresnes'
  },
  adresseLivraison: {
    codePostal: 0,
    email: 'patrice.martin@ingenico.com',
    id: 'c86f1c47-a49e-4ee7-a0e1-fd88a64a903d',
    nom: 'Martin',
    prenom: 'Patrice'
  }
};

it('should get correct result with method mocked', async () => {
  const mockGetInfoByMail = jest.fn();
  UtilisateurService.prototype.getInfoByMail = mockGetInfoByMail;
  mockGetInfoByMail.mockReturnValue(Promise.resolve(mockResponse));
  const utilisateureService = new UtilisateurService();

  const result = await utilisateureService.getInfoByMail('patrice.martin@ingenico.com');
  expect(result).toBe(mockResponse);
});
