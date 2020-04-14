import React from 'react';
import { shallow } from 'enzyme';
import Dashboard from './Dashboard.jsx';

it('Should mount the app without crashing', () => {
  const component = shallow(<Dashboard />);

  expect(component).not.toBe(null);
});
