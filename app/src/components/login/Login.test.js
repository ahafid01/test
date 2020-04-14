import React from 'react';
import { shallow } from 'enzyme';
import Login from './Login.jsx';

it('Should mount the app without crashing', () => {
  const component = shallow(<Login />);

  expect(component).not.toBe(null);
});
