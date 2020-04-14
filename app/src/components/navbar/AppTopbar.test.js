import React from 'react';
import { shallow } from 'enzyme';
import AppTopbar from './AppTopbar';

it('Should mount the app without crashing', () => {
  const component = shallow(<AppTopbar />);

  expect(component).not.toBe(null);
});
