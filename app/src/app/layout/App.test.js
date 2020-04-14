import React from 'react';
import { shallow } from 'enzyme';
import App from './App.jsx';

it('Should mount the app without crashing', () => {
  const component = shallow(<App />);

  expect(component).not.toBe(null);
});
