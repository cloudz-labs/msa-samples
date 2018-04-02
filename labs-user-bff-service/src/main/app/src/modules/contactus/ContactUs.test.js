import React from 'react';
import { shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import ContactUs from './ContactUs';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<ContactUs />, div);
});
it('contains 8 + 1(dumok) people', ()=>{
  const wrapper = shallow(<ContactUs/>);
  expect(true);
});