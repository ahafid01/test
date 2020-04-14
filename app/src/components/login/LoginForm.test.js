import React from 'react';
import '@testing-library/jest-dom/extend-expect';
import { cleanup } from '@testing-library/react';
import { renderHook } from '@testing-library/react-hooks';
import { Router as MemoryRouter, useHistory } from 'react-router-dom';
import LoginForm from './LoginForm.jsx';

afterEach(() => {
  cleanup();
});

const mockHistoryPush = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useHistory: () => ({
    push: mockHistoryPush
  })
}));

describe('LoginFormComponent', () => {
  it('should render without crashing', () => {
    const historyMock = { push: jest.fn(), location: {}, listen: jest.fn() };

    const wrapper = () => (
      <MemoryRouter history={historyMock}>
        <LoginForm />
      </MemoryRouter>
    );
    const renderLoginWrapper = renderHook(() => useHistory(), { wrapper });

    expect(renderLoginWrapper).not.toBe(null);
  });
});
