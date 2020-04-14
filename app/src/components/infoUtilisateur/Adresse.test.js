import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import Adresse from './Adresse.jsx';

it('Should render composant and trigger change', () => {
  const renderAdresse = render(<Adresse />);
  expect(renderAdresse).not.toBe(null);

  const inputNomAdresse = renderAdresse.getByTestId('info-input-nom-adresse');
  fireEvent.change(inputNomAdresse, { target: { value: 'nomAdresse' } });

  const inputLigneAdresse = renderAdresse.getByTestId('info-input-ligne-adresse');
  fireEvent.change(inputLigneAdresse, { target: { value: 'ligneAdresse' } });

  const inputPrenomAdresse = renderAdresse.getByTestId('info-input-ligne2');
  fireEvent.change(inputPrenomAdresse, { target: { value: 'prenomAdresse' } });

  const inputPostalcodeAdresse = renderAdresse.getByTestId('info-input-postalcode');
  fireEvent.change(inputPostalcodeAdresse, { target: { value: 'postalCodeAdresse' } });

  const inputVilleAdresse = renderAdresse.getByTestId('info-input-ville');
  fireEvent.change(inputVilleAdresse, { target: { value: 'villeAdresse' } });

  expect(inputNomAdresse.value).toBe('nomAdresse');
  expect(inputLigneAdresse.value).toBe('ligneAdresse');
  expect(inputPrenomAdresse.value).toBe('prenomAdresse');
  expect(inputPostalcodeAdresse.value).toBe('postalCodeAdresse');
  expect(inputVilleAdresse.value).toBe('villeAdresse');
});
