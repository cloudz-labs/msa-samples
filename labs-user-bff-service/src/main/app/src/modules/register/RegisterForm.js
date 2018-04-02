import React from 'react';

import './RegisterForm.css';
import RegisterQuestionnaire from './RegisterQuestionnaire';

export default class RegisterForm extends React.Component {
	constructor(props) {
		super(props);
		this.handleFillIn = this.handleFillIn.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}
	handleFillIn(answer) {
		this.props.onFillIn(answer);
	}
	handleSubmit() {
		this.props.onSubmit();
	}
	render() {
		let contents = null;
		let notice = null;
		if(this.props.questionnaire) {
			contents = this.props.questionnaire
			.sort((q1,q2)=>(q1.step - q2.step))
			.map((q,idx)=>(
				<RegisterQuestionnaire
					warning={this.props.requiredWarning}
					answers={this.props.answers}
					content={q}
					onFillIn={this.handleFillIn}
					key={idx}
					/>
			));
			if(this.props.notice) {
				notice = <quote className="register-form-notice">{this.props.notice}</quote>
			}
		}
		return (
			<div className="grey-container">
				<div className="register-form container">
					<h1 className="register-form-title-main">CloudZ Labs 등록 질의서{this.props.title ? " - " + this.props.title : ""}</h1>
					{notice}
					<form>
					{contents}
					<div className="register-form-misc">
						<button type="button" className="register-form-submit" onClick={this.handleSubmit}>접수</button>
					</div>
					</form>
				</div>
			</div>
		);
	}
}