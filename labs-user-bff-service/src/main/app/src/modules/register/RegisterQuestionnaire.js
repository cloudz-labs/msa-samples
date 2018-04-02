import React from 'react';

import RegisterQuestion from './RegisterQuestion';

export default class RegisterQuestionnaire extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			answer : {}
		}
		this.handleQuestionFillIn = this.handleQuestionFillIn.bind(this);
	}
	handleQuestionFillIn(questionId, value) {
		const answer = Object.assign({}, this.state.answer);
		answer[questionId] = value;
		this.setState({
			answer : answer
		});
		this.props.onFillIn(answer);
	}
	render() {
		let questions = null;
		if(this.props.content && this.props.content.questions) {
			questions = this.props.content.questions.map((question,idx) => 
				<RegisterQuestion 
					warning={this.props.warning}
					question={question}
					answers={this.props.answers}
					key={idx}
					number={`${this.props.content.step}-${idx+1}`}
					onQuestionFillIn={this.handleQuestionFillIn}
				/>
			)
		}
		return (
			<div className="register-questionnaire">
				<h2 className="register-form-title-questionnaire">{this.props.content.step}. {this.props.content.category}</h2>
				{questions}
			</div>
		)
	}
}