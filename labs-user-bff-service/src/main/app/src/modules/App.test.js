import React from 'react';
import {shallow} from 'enzyme';
import { BrowserRouter } from 'react-router-dom';
import App from './App';

it('renders without crashing', () => {
	shallow(<App/>);
});
it('contains BrowserRouter for routing', () => {
	const wrapper = shallow(<App/>);
	expect(wrapper.find(BrowserRouter)).toHaveLength(1);
})