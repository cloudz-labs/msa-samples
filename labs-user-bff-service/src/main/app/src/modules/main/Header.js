import React from 'react';
import './Header.css';

export default class Header extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			egg : false
		};
		this.handleEasterEgg = this.handleEasterEgg.bind(this);
	}
	handleEasterEgg() {
		this.setState({egg:true});
	}
	render() {
		return (
			<div data-reactroot="">
				<div className="header image-background">
					<div>
						<a className="logo" href="/">CloudZ Labs</a>
						<div className="nav container">
							<ul className="nav-bar">
								<li><a href="/#course">Register</a></li>
								<li><a href="/library">Library</a></li>
								<li><a href="https://tech.cloudz-labs.io/">Blog</a></li>
								<li><a href="/contact">Contact US</a></li>
							</ul>
						</div>
					</div>
					{this.props.children}
				</div>
			</div>
//			<div className={`app-header blue-container image-background ${ this.props.className || '' } ${ this.state.egg ? 'hidden-treasure' : '' }`}>
//				<EasterEgg onSuccess={this.handleEasterEgg}/>
//				<div className="cover"></div>
//				<div>
//					<div className="nav container">
//						<ul className="nav-bar">
//							<li><HashLink to="/#course">Register</HashLink></li>
//							<li><NavLink to="/library">DT Library</NavLink></li>
//							<li><NavLink to="/contact">Contact US</NavLink></li>
//						</ul>
//						<NavLink to="/"><h1>DT LABS</h1></NavLink>
//					</div>
//					{this.props.children}
//				</div>
//			</div>
		);
	}
}