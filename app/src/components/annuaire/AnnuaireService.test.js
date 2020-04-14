import axios from 'axios';

import moxios from 'moxios';
import sinon from 'sinon';
import AnnuaireService from './AnnuaireService';

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

beforeEach(() => {
  moxios.install();
});

afterEach(() => {
  moxios.uninstall();
});

it('should return the correct response with axios mocked', async () => {
  const annuaireService = new AnnuaireService();

  moxios.stubRequest('/api/annuaire', {
    status: 200,
    response: mockDataResponse
  });

  const onFulfilled = sinon.spy();
  axios.get('/api/annuaire').then(onFulfilled);
  const result = await annuaireService.getDataByPageAndSize(0, 5);

  moxios.wait(() => {
    expect(onFulfilled).toHaveBeenCalled();
    expect(result).toBe(mockResponse);
  });
});

jest.mock('./AnnuaireService');

it('should get correct result with method mocked', async () => {
  const mockGetDataByPageAndSize = jest.fn();
  AnnuaireService.prototype.getDataByPageAndSize = mockGetDataByPageAndSize;
  mockGetDataByPageAndSize.mockReturnValue(Promise.resolve(mockResponse));
  const annuaireService = new AnnuaireService();

  const result = await annuaireService.getDataByPageAndSize(0, 5);
  expect(result).toBe(mockResponse);
});
