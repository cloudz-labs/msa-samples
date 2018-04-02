import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Footer from './main/Footer';

import './App.css';

import MainContainer from './MainContainer';
import RegisterContainer from './RegisterContainer';
import ContactUsContainer from './ContactUsContainer';
import LibraryContainer from './LibraryContainer';

export default class App extends React.Component {
	render() {
		return (
			<BrowserRouter>
				<div className="App wrap">
					<Switch>
						<Route exact path="/" component={MainContainer}/>
						<Route path="/register" component={RegisterContainer}/>
						<Route path="/library" component={LibraryContainer}/>
						<Route path="/contact" component={ContactUsContainer}/>
					</Switch>
					<Footer></Footer>
				</div>
			</BrowserRouter>
		)
	}
}