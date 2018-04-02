import React from 'react';
import { Switch, Route } from 'react-router-dom';

import RegisterComplete from './register/RegisterComplete';
import RegisterFormContainer from './register/RegisterFormContainer';

import Header from './main/Header';

export default class RegisterContainer extends React.Component {
	render() {
		return (
			<div>
				<div className="sub">
					<Header className="shrink"></Header>
				</div>
				<Switch>
					<Route exact path="/register/complete" component={RegisterComplete}/>
					<Route path="/register/:id" component={RegisterFormContainer}/>
				</Switch>
			</div>
		);
	}
}