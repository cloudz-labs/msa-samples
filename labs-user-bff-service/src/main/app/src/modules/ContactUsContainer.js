import React from 'react';

import ContactUs from './contactus/ContactUs';

import Header from './main/Header';

export default class ContactUsContainer extends React.Component {
	render() {
		return (
			<div>
				<div className="sub">
					<Header className="shrink"></Header>
				</div>
				<ContactUs></ContactUs>
			</div>
		);
	}
}