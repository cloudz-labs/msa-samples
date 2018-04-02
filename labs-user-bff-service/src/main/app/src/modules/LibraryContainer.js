import React from 'react';
import { Switch, Route } from 'react-router-dom';

import {http} from './common/ajax';
import LibraryMain from './library/LibraryMain';
import Header from './main/Header';

export default class LibraryContainer extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			categories : []
		}
	}
	componentWillMount() {
		http.get('/api/v1/library/docs')
			.then(list=>{
				this.setState({categories:list});
			}).catch(err=>{
				console.error('fetching categories failed: ' + err.message, err);
			});
	}
	render() {
		return (
			<div>
				<div className="sub">
					<Header className="shrink"></Header>
				</div>
				<Switch>
					<Route exact path="/library" render={()=>
							<LibraryMain categories={this.state.categories} />
						}/>
				</Switch>
			</div>
		);
	}
}