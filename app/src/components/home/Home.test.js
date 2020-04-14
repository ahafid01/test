import React from 'react';
import { shallow } from 'enzyme';
import Home from './Home.jsx';

it('Should mount the app without crashing', () => {
  const component = shallow(<Home />);

  expect(component).not.toBe(null);
});
