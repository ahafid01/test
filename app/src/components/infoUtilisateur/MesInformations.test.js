import React from 'react';
import { render, fireEvent, wait } from '@testing-library/react';
import MesInformations from './MesInformations.jsx';

it('Should render composant and trigger change', async () => {
  const renderedInformations = render(<MesInformations />);
  expect(renderedInformations).not.toBe(null);

  let inputIdentifiant;
  let inputUserPrenom;
  let inputUserNom;
  let inputUserNaissance;
  let inputUserTelephone;
  let inputUserTelephoneSecond;
  await wait(() => {
    inputIdentifiant = renderedInformations.getByTestId('info-input-identifiant');
    fireEvent.change(inputIdentifiant, { target: { value: 'testIdentifiant' } });

    inputUserPrenom = renderedInformations.getByTestId('info-input-user-prenom');
    fireEvent.change(inputUserPrenom, { target: { value: 'userPrenom' } });

    inputUserNom = renderedInformations.getByTestId('info-input-user-nom');
    fireEvent.change(inputUserNom, { target: { value: 'userNom' } });

    inputUserNaissance = renderedInformations.container.querySelector('#info-input-naissance');
    fireEvent.change(inputUserNaissance, { target: { value: '99/99/9999' } });

    inputUserTelephone = renderedInformations.container.querySelector('#info-input-telephone');
    fireEvent.change(inputUserTelephone, { target: { value: 'userTelephone' } });

    inputUserTelephoneSecond = renderedInformations.container.querySelector(
      '#info-input-telephone-second'
    );
    fireEvent.change(inputUserTelephoneSecond, { target: { value: 'userTelephoneSecond' } });
  });

  expect(inputIdentifiant.value).toBe('testIdentifiant');
  expect(inputUserPrenom.value).toBe('userPrenom');
  expect(inputUserNom.value).toBe('userNom');
  expect(inputUserTelephone.value).toBe('userTelephone');
  expect(inputUserTelephoneSecond.value).toBe('userTelephoneSecond');
  expect(inputUserNaissance.value).toBe('99/99/9999');
});

it('Should mask correctly', async () => {
  const mockProps = {
    telephonePrincipal: '0123456789',
    telephoneSecondaire: '0123456789',
    dateNaissance: '99/99/9999'
  };
  const renderedInformations = render(<MesInformations {...mockProps} />);

  await wait();
  const inputUserTelephone = renderedInformations.container.querySelector('#info-input-telephone');
  const inputUserTelephoneSecond = renderedInformations.container.querySelector(
    '#info-input-telephone-second'
  );
  const inputUserNaissance = renderedInformations.container.querySelector('#info-input-naissance');

  expect(inputUserTelephone.value).toBe('(+33) 1 23 45 67 89');
  expect(inputUserTelephoneSecond.value).toBe('(+33) 1 23 45 67 89');
  expect(inputUserNaissance.value).toBe('99/99/9999');
});

it('Should mask if no initial props is set', async () => {
  const mockProps = {};
  const renderedInformations = render(<MesInformations {...mockProps} />);

  await wait();
  const inputUserTelephone = renderedInformations.container.querySelector('#info-input-telephone');
  const inputUserTelephoneSecond = renderedInformations.container.querySelector(
    '#info-input-telephone-second'
  );

  expect(inputUserTelephone.value).toBe('');
  expect(inputUserTelephoneSecond.value).toBe('');
});
