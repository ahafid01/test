import React from 'react';
import { render } from '@testing-library/react';
import { Router as MemoryRouter } from 'react-router-dom';
import FormulaireInfo from './FormulaireInfo.jsx';

it('Should render composant and trigger change', () => {
  const historyMock = { push: jest.fn(), location: {}, listen: jest.fn() };

  const renderedFormulaire = render(
    <MemoryRouter history={historyMock}>
      <FormulaireInfo />
    </MemoryRouter>
  );
  expect(renderedFormulaire).not.toBe(null);
});
