import React from 'react';
import { shallow } from 'enzyme';
import Navbar from './Navbar.jsx';

it('Should mount the app without crashing', () => {
  const component = shallow(<Navbar />);

  expect(component).not.toBe(null);
});
