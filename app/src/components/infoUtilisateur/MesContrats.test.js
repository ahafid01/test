import React from 'react';
import { render } from '@testing-library/react';
import MesContrats from './MesContrats.jsx';

it('Should render composant and trigger change', async () => {
  const renderedContrats = render(<MesContrats />);
  expect(renderedContrats).not.toBe(null);
});
