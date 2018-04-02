import React from 'react';
import './Footer.css';

export default class Footer extends React.Component {
	render() {
		return (

//				<footer className="footer">
//				<div className="container">
//					<div className="footer-point-phrase"><strong>Let's <span>g</span>o Together</strong></div>
//					<div className="footer-copyright">Copyright© 2017 SK Holdings. All rights reserved.</div>
//					<div className="footer-copyright-cc">
//						<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/">
//							<img alt="Creative Commons License" style={{borderWidth:0}} src="https://i.creativecommons.org/l/by-sa/3.0/80x15.png" />
//						</a><br/>
//						Some images used in this site are licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/">
//							Creative Commons Attribution-ShareAlike 3.0 Unported License
//						</a>
//					</div>
//				</div>
//			</footer>
		
				<footer className="footer">
					<div className="container">
						<div className="footer-point-phrase">
							<strong>Let's<span>g</span>o Together
							</strong>
						</div>
						<div className="footer-copyright">Copyright© 2017 SK Holdings. All
							rights reserved.</div>
						<div className="footer-copyright-cc">
							<a rel="license"
								href="http://creativecommons.org/licenses/by-sa/3.0/">
							<img
								alt="Creative Commons License"
								src="https://i.creativecommons.org/l/by-sa/3.0/80x15.png"
								 /></ a><br /> Some images used in this
								site are licensed under a <a rel="license"
								href="http://creativecommons.org/licenses/by-sa/3.0/"> Creative
									Commons Attribution-ShareAlike 3.0 Unported License </a>
						</div>
					</div>
				</footer>
		);
	}
}