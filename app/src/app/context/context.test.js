import React, { useContext } from 'react';
import { render, fireEvent, wait } from '@testing-library/react';
import { renderHook } from '@testing-library/react-hooks';

import { act } from 'react-dom/test-utils';
import { Router as MemoryRouter } from 'react-router-dom';
import axios from 'axios';
import { DataContext, DataProvider } from './context';
import LoginForm from '../../components/login/LoginForm.jsx';

jest.mock('axios');

describe('useContext tests', () => {
  beforeEach(() => {
    jest.mock('react-router-dom', () => ({
      useHistory: () => ({
        push: jest.fn()
      })
    }));
  });

  test('should get value from context provider', () => {
    const wrapper = ({ children }) => (
      <DataContext.Provider value="bar">{children}</DataContext.Provider>
    );

    const { result } = renderHook(() => useContext(DataContext), { wrapper });

    expect(result.current).toBe('bar');
  });

  test('should  call fetchQualificatedUsersWithCreds function', async () => {
    const fetchQualificatedUsersWithCreds = jest.fn();

    const historyMock = { push: jest.fn(), location: {}, listen: jest.fn() };

    const loginRender = render(
      <MemoryRouter history={historyMock}>
        <DataContext.Provider value={{ fetchQualificatedUsersWithCreds }}>
          <LoginForm />
        </DataContext.Provider>
      </MemoryRouter>
    );

    const userNameNode = loginRender.getByPlaceholderText("Nom d'utilisateur");
    const passwordNode = loginRender.getByPlaceholderText('Mot de passe');
    const loginButtonNode = loginRender.getByTestId('valider');

    act(() => {
      fireEvent.change(userNameNode, { target: { value: 'admin' } });
      fireEvent.change(passwordNode, { target: { value: '123456' } });
      fireEvent.click(loginButtonNode);
    });

    await wait(() => {
      expect(fetchQualificatedUsersWithCreds).toHaveBeenCalledTimes(1);
    });
  });

  test('should  call hooks', async () => {
    const historyMock = { push: jest.fn(), location: {}, listen: jest.fn() };
    const data = {
      data: [
        {
          dateQualif: [2020, 3, 5, 12, 59, 18, 571030000]
        }
      ]
    };

    axios.post.mockImplementationOnce(() => Promise.resolve(data));
    axios.get.mockImplementationOnce(() => Promise.resolve(data));
    const loginRender = render(
      <MemoryRouter history={historyMock}>
        <DataProvider>
          <LoginForm />
        </DataProvider>
      </MemoryRouter>
    );

    const userNameNode = loginRender.getByPlaceholderText("Nom d'utilisateur"); // ok
    const passwordNode = loginRender.getByPlaceholderText('Mot de passe'); // ok
    const loginButtonNode = loginRender.getByTestId('valider'); // ok

    act(() => {
      fireEvent.change(userNameNode, { target: { value: 'admin' } });
      fireEvent.change(passwordNode, { target: { value: '123456' } });
      fireEvent.click(loginButtonNode);
    });

    await wait(() => {
      expect(axios.post).toHaveBeenCalled();
      // expect(fetchQualificatedUsersWithCreds).toHaveBeenCalledTimes(1);
    });
  });
});
