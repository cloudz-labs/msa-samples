import React from 'react';
import {shallow, mount} from 'enzyme';
import { BrowserRouter } from 'react-router-dom';
import LibraryMain from './LibraryMain';

it(`renders without crashing`, () => {
	mount(<LibraryMain/>);
});
it(`renders categories`, ()=>{
	const categories = [
		{id:"0001", name:"test1", order:1, documents:[]},
		{id:"0002", name:"test2", order:2, documents:[]},
		{id:"0003", name:"test3", order:3, documents:[]}
	];
	const wrapper = mount(<LibraryMain categories={categories}></LibraryMain>);
	expect(wrapper.find('.dt-library-category-container').length).toEqual(3);
});
it(`renders docs under category`, ()=>{
	const categories = [
		{id:"categoryid001", name:"a1", order:1, documents:[
			{ id:"a1",categories:["categoryid001"],title:"test",contents:"test1" },
			{ id:"a2",categories:["categoryid001"],title:"test",contents:"test1" },
			{ id:"a4",categories:["categoryid001"],title:"test",contents:"test1" }
		]}
	];
	const wrapper = mount(<LibraryMain categories={categories}></LibraryMain>);
	expect(wrapper.find('.dt-library-category-container').length).toEqual(1);
	expect(wrapper.find('.dt-library-item-container').length).toEqual(3);
});
it(`renders docs with multiple category id under multiple category`,()=>{
	const categories = [
		{id:"001", name:"category1", order:1, documents:[
			{ id:"doc1",categories:["001","002","003"],title:"test",contents:"test1" },
			{ id:"doc2",categories:["001","002","003"],title:"test",contents:"test1" },
			{ id:"doc3",categories:["001","002","003"],title:"test",contents:"test1" },
			{ id:"doc4",categories:["001","002","003"],title:"test",contents:"test1" }
		]},
		{id:"002", name:"category2", order:2, documents:[
			{ id:"doc1",categories:["001","002","003"],title:"test",contents:"test1" },
			{ id:"doc2",categories:["001","002","003"],title:"test",contents:"test1" },
			{ id:"doc3",categories:["001","002","003"],title:"test",contents:"test1" },
			{ id:"doc4",categories:["001","002","003"],title:"test",contents:"test1" }
		]},
		{id:"003", name:"category3", order:3, documents:[
			{ id:"doc1",categories:["001","002","003"],title:"test",contents:"test1" },
			{ id:"doc2",categories:["001","002","003"],title:"test",contents:"test1" },
			{ id:"doc3",categories:["001","002","003"],title:"test",contents:"test1" },
			{ id:"doc4",categories:["001","002","003"],title:"test",contents:"test1" }
		]}
	];
	const wrapper = mount(
		<LibraryMain categories={categories}></LibraryMain>
	);
	expect(wrapper.find('.dt-library-category-container').length)
		.toEqual(categories.length);
	expect(wrapper.find('.dt-library-item-container').length)
		.toEqual(categories.length * 4);
});