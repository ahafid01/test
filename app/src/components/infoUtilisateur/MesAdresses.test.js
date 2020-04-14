import React from 'react';
import { render } from '@testing-library/react';
import { Router as MemoryRouter } from 'react-router-dom';
import MesAdresses from './MesAdresses.jsx';

it('Should render composant', () => {
  const historyMock = { push: jest.fn(), location: {}, listen: jest.fn() };

  const renderedMesAdresses = render(
    <MemoryRouter history={historyMock}>
      <MesAdresses />
    </MemoryRouter>
  );
  expect(renderedMesAdresses).not.toBe(null);
});
