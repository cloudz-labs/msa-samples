import React from 'react';
import {shallow, mount} from 'enzyme';
import { BrowserRouter } from 'react-router-dom';
import LibraryItem from './LibraryItem';

const doc = {
		"id": "594cb41c83d1990007176759",
		"categories": [
		"593f74d1d12c0c1004cbd414"
		],
		"title": "PaaS 개요",
		"contents": "PaaS Platform 특징을 설명합니다.",
		"links": [
			{ "title" : "링크 샘플", "path" : "https://dtlabs.skcc.com" },
			{ "title" : "링크 샘플2", "path" : "https://dtlabs.skcc.com" }
		],
		"attachments": [
			{ "title": "PaaS 개요", "path": "PaaS 개요.pdf" },
			{ "title": "PaaS 개요2", "path": "PaaS 개요2.pdf" },
			{ "title": "PaaS 개요3", "path": "PaaS 개요3.pdf" },
		],
		"regDate": "2017-06-23 15:24:28"
	};

it(`renders without crashing without item prop provided.`, () => {
	const wrapper = mount(<LibraryItem/>);
	expect(wrapper.find('.dt-library-item').length).toEqual(0);
});
it(`renders doc property`,()=>{
	const wrapper = mount(<LibraryItem item={doc}/>);
	expect(wrapper.find('.dt-library-item').length).toEqual(1);
	expect(wrapper.find('.dt-library-item-link').length).toEqual(2);
	expect(wrapper.find('.dt-library-item-download').length).toEqual(3);
	expect(wrapper.find('h3').text()).toEqual(doc.title);
	expect(wrapper.find('p.dt-library-item-description').text()).toEqual(doc.contents);
});