import React from 'react';
import './LabsHeaderIntro.css';

export default class LabsHeaderIntro extends React.Component {
	render() {
		return (
				<div className="labs-header-intro">
					<div className="header-message-container container">
						<div className="visual">
							<div className="header-message">
									<h1>CloudZ Labs</h1>
								<p>
									Cloud Application의 시작<br /> CloudZ Labs와 함께 하세요
								</p>
							</div>
							<a href="/#course">
								<button type="button" className="btn btn-white-line">Register
									your team</button>
							</a>
						</div>
					</div>
				</div>
//			<div className="labs-header-intro">
//				<div className="header-message-container container">
//					<div>
//						<div className="header-message">
//							<p>Cloud Application의 시작</p>
//							<p><span className="point-orange">DT Labs</span>와 함께 하세요</p>
//						</div>
//						<HashLink to="/#course">
//							<button type="button" className="white jump-to-courselist">Register your team</button>
//						</HashLink>
//					</div>
//				</div>
//			</div>
		)
	}
}