import React from 'react';
import { shallow } from 'enzyme';
import LoginFormTemplate from './LoginFormTemplate.jsx';

it('Should mount the app without crashing', () => {
  const component = shallow(<LoginFormTemplate />);

  expect(component).not.toBe(null);
});
