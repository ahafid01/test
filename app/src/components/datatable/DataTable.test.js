import React from 'react';
import { mount, shallow } from 'enzyme';
import * as AppContext from '../../app/context/context';

import DataTable from './DataTable.jsx';

describe('<Datatable />', () => {
  test('it should mock the context', () => {
    const contextValues = {
      data: [
        {
          id: '9b4aa724-6d4e-4fab-9796-d9b374b0057d',
          civilite: 'MONSIEUR',
          nom: 'TOTO',
          prenom: 'TOTO',
          email: 'toto@toto.fr',
          telephone: '0123456789',
          codePostal: '95130',
          profession: 'MEDECIN_GENERALISTE',
          reponseAutreProfession: null,
          accepteEtreRecontacte: true,
          resultatQualif: 'NOK',
          raisonQualif: 'NON_TROUVE',
          dateQualif: [2020, 3, 4, 10, 43, 28, 408035000]
        }
      ]
    };
    jest.spyOn(AppContext, 'useDataContext').mockImplementation(() => contextValues);

    const wrapper = shallow(<DataTable />);

    const table = wrapper.find("[data-test='test-tabulator']");
    expect(table.length).toBe(1);
  });

  test('it should render nothing if empty data', () => {
    const contextValues = { data: [] };
    jest.spyOn(AppContext, 'useDataContext').mockImplementation(() => contextValues);
    const wrapper = mount(<DataTable />);

    const h1 = wrapper.find('h1');
    expect(h1.text()).toBe('Rien à afficher');
  });
});
