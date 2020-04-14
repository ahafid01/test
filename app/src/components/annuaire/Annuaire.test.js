import React from 'react';
import { cleanup } from '@testing-library/react';

import { shallow } from 'enzyme';
import axios from 'axios';

import moxios from 'moxios';
import sinon from 'sinon';
import Annuaire from './Annuaire.jsx';
import AnnuaireService from './AnnuaireService';

jest.mock('./AnnuaireService');

afterEach(() => {
  cleanup();
});

beforeEach(() => {
  moxios.install();
});

afterEach(() => {
  moxios.uninstall();
});

const content = [
  {
    adresseEmailCoordStructure: 'dirg@ch-bourg01.fr',
    identifiantPP: '016038697',
    libelleCivilite: 'Madame',
    libelleModeExercice: 'Salariï¿½',
    libelleProfession: 'Infirmier',
    nomExercice: 'BOISSEAU',
    prenomExercice: 'Gisele',
    raisonSocialeSite: 'CENTRE HOSPITALIER DE FLEYRIAT',
    telephoneCoordStructure: '0474454647'
  }
];

const totalElements = 10;

const mockResponse = {
  content,
  totalElements
};

const mockDataResponse = { data: mockResponse };

it('Should be an instance that contains  method of annuaireService', () => {
  const mockGetDataByPageAndSize = jest.fn(() => Promise.resolve(mockResponse));

  AnnuaireService.prototype.getDataByPageAndSize = mockGetDataByPageAndSize;
  const shallowAnnuaire = shallow(<Annuaire />);

  shallowAnnuaire.instance().annuaireService = new AnnuaireService();
  shallowAnnuaire.instance().annuaireService.getDataByPageAndSize = mockGetDataByPageAndSize;
  shallowAnnuaire.update();
  shallowAnnuaire.setState({
    rows: 10,
    page: 0
  });
  expect(shallowAnnuaire.instance().annuaireService.getDataByPageAndSize).toBeCalled();
  expect(shallowAnnuaire.instance().annuaireService.getDataByPageAndSize).toBeCalledWith(0, 10);
});

it('Should call axios on component did mount', () => {
  moxios.stubRequest('/api/annuaire', {
    status: 200,
    response: mockDataResponse
  });

  // const onFulfilled = sinon.spy();
  // axios.get('/api/annuaire').then(onFulfilled);
  const onFulfilled = jest.fn();
  axios.get('/api/annuaire').then(onFulfilled);

  const shallowAnnuaire = shallow(<Annuaire />);
  shallowAnnuaire.update();

  shallowAnnuaire.instance().componentDidMount();

  moxios.wait(() => {
    expect(onFulfilled).toHaveBeenCalled();
  });
});

it('Should call axios with handleOnPage method and change state', () => {
  moxios.stubRequest('/api/annuaire', {
    status: 200,
    response: mockDataResponse
  });

  const onFulfilled = sinon.spy();
  axios.get('/api/annuaire').then(onFulfilled);

  const shallowAnnuaire = shallow(<Annuaire />);

  shallowAnnuaire.update();

  const mockEvent = {
    rows: 5
  };

  shallowAnnuaire.instance().handleOnPage(mockEvent);

  moxios.wait(() => {
    expect(onFulfilled).toHaveBeenCalled();
  });
});

it('Should not render datable when error response and render message instead', async () => {
  const errResp = {
    status: 422,
    response: { message: 'problem' }
  };
  moxios.wait(() => {
    const request = moxios.requests.mostRecent();
    request.reject(errResp);
  });

  const element = shallow(<Annuaire />);

  const resolvedComponent = element.find("[data-testid='annuaire-error-message']");

  expect(resolvedComponent).not.toBe(null);
  cleanup();
});

it('Should render message when state by default contains error', () => {
  const element = shallow(<Annuaire />);
  element.state = {
    errors: [
      {
        message: 'problem'
      }
    ]
  };

  const resolvedComponent = element.find("[data-testid='annuaire-error-message']");
  expect(resolvedComponent).not.toBe(null);
});

it('Should not render message with default state that does not contains error', () => {
  const element = shallow(<Annuaire />);

  element.update();

  const resolvedComponent = element.find("[data-testid='annuaire-error-message']");
  expect(resolvedComponent.exists()).toBe(false);
});

it('Should render message when setState errors', () => {
  const element = shallow(<Annuaire />);
  element.setState({
    errors: [
      {
        message: 'errros'
      }
    ],
    initialTableLoading: false
  });

  const resolvedComponent = element.find("[data-testid='annuaire-error-message']");
  expect(resolvedComponent.exists()).toBe(true);
});

it('Should not render message when setstate doest not contains error or by changing state', () => {
  const element = shallow(<Annuaire />);
  element.setState({
    errors: []
  });

  const resolvedComponent = element.find("[data-testid='annuaire-error-message']");
  expect(resolvedComponent.exists()).toBe(false);
});

it('should setstate with some error messages when failed to fetch ', () => {
  const errResp = {
    status: 422,
    response: { message: 'problem' }
  };
  moxios.wait(() => {
    const request = moxios.requests.mostRecent();
    request.reject(errResp);
  });

  const shallowAnnuaire = shallow(<Annuaire />);

  shallowAnnuaire.instance().componentDidMount(() => {
    const { errors } = shallowAnnuaire.state();

    expect(errors.length).toBeGreaterThan(0);
  });
  cleanup();
});

it('should call setState when failed to fetch default when failed to fetch', () => {
  const errResp = {
    status: 422,
    response: { message: 'problem' }
  };
  moxios.wait(() => {
    const request = moxios.requests.mostRecent();
    request.reject(errResp);
  });

  const setStateSpy = jest.spyOn(Annuaire.prototype, 'setState');

  const shallowAnnuaire = shallow(<Annuaire />);

  expect(shallowAnnuaire.state().errors.length).toEqual(0);

  shallowAnnuaire.instance().componentDidMount(() => {
    expect(setStateSpy).toHaveBeenCalled();
    expect(setStateSpy).toHaveBeenCalledWith(() => ({
      errors: [
        {
          message: 'Erreur lors de la récupération des données'
        }
      ]
    }));
  });
  cleanup();
});
