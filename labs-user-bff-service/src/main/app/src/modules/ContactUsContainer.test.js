import React from 'react';
import {shallow} from 'enzyme';
import { BrowserRouter } from 'react-router-dom';
import ContactUsContainer from './ContactUsContainer';

it('renders without crashing', () => {
	shallow(<ContactUsContainer/>);
});