import React from 'react';
import { render, cleanup, wait } from '@testing-library/react';
import { Router as MemoryRouter } from 'react-router-dom';
import { act } from 'react-dom/test-utils';
import InfoUtilisateur from './InfoUtilisateur.jsx';
import UtilisateurService from './UtilisateurService';

afterEach(() => {
  cleanup();
});

const goodResponse = {
  email: 'toto@gmail.com',
  id: 'c1ccfb42-1984-4305-bb10-0a3042cedeeb',
  nom: 'GENE RPPS',
  prenom: 'ALAIN'
};

const errorResponse = {
  data: {
    code: 'UTILISATEUR_INTROUVABLE_AVEC_EMAIL',
    message: "Aucun utilisateur n'existe avec l'adresse mail totddo@gmail.com",
    path: '/api/utilisateurs'
  },
  status: 404,
  timestamp: '2020-03-11T08:32:29.180+0000'
};

it('Should render composant and trigger change', () => {
  const historyMock = { push: jest.fn(), location: {}, listen: jest.fn() };

  const renderedInfoUtilisateur = render(
    <MemoryRouter history={historyMock}>
      <InfoUtilisateur />
    </MemoryRouter>
  );
  expect(renderedInfoUtilisateur).not.toBe(null);
});

it('Should populate component with resolved data after useEffect', () => {
  jest.mock('./UtilisateurService');

  const mockGetInfoByMail = jest.fn(() => Promise.resolve(goodResponse));
  UtilisateurService.prototype.getInfoByMail = mockGetInfoByMail;
});

it('Should populate component with resolved data after useEffect', async () => {
  jest.mock('./UtilisateurService');
  const historyMock = { push: jest.fn(), location: {}, listen: jest.fn() };

  const mockGetInfoByMail = jest.fn(() => Promise.resolve(errorResponse));
  UtilisateurService.prototype.getInfoByMail = mockGetInfoByMail;

  const renderedInfoUtilisateur = render(
    <MemoryRouter history={historyMock}>
      <InfoUtilisateur />
    </MemoryRouter>
  );
  act(() => {});
  await wait(() => {
    const getByIdMessage = renderedInfoUtilisateur.getByTestId(
      'formulaire-info-user-error-message'
    );
    expect(getByIdMessage).not.toBe(null);
  });
});
