import React from 'react';

export default class RegisterQuestion extends React.Component {
	constructor(props) {
		super(props);
		this.handleChange = this.handleChange.bind(this);
		this.handleEtcChange = this.handleEtcChange.bind(this);
		this.state = {
			etc : ""
		};
	}
	updateEtcValueFromAnswer() {
		let answer = this.props.answers[this.props.question.id];
		const reply = this.props.question.reply;
		if(answer && answer.length) {
			if(typeof answer === "string") {
				answer = [answer];
			}
			// const leftover = answer.filter(value=>!reply.includes(value));
			const leftover = answer.filter(value=>reply.indexOf(value) === -1);
			if(leftover.length) {
				this.setState({etc:leftover.pop()})
			}
		}
	}
	componentWillMount() {
		if(this.props.question && 
				(this.props.question.type === "checkbox" || this.props.question.type === "radio")) {
			this.updateEtcValueFromAnswer();
		}
	}
	componentDidUpdate(prevProps, prevState) {
		if(prevState.etc !== this.state.etc) {
			this.handleChange();
		}
		if(this.props.question.hasEtc && prevProps.answers !== this.props.answers) {
			this.updateEtcValueFromAnswer();
		}
	}
	handleChange(e) {
		let value = e ? e.target.value : "";
		if(this.props.question.type === "radio" && e && e.target.checked && !e.target.getAttribute("data-etc")) {
			this.setState({etc:""})
		}
		if(this.props.question.type === "checkbox") {
			value = Array.from(document.querySelectorAll(`input[name="${this.props.question.id}"]`))
				.filter(input=>input.checked)
				.map(input=>input.value);
		}
		if(this.props.question.type === "radio") {
			value = value = Array.from(document.querySelectorAll(`input[name="${this.props.question.id}"]`))
				.filter(input=>input.checked)
				.map(input=>input.value).pop();
		}
		this.props.onQuestionFillIn(
			this.props.question.id, value
		);
	}
	handleEtcChange(e) {
		let value = e.target.value;
		this.setState({
			etc:value
		}, );
	}
	renderQuestion(question) {
		let input = null;
		if(question.type === "text") {
			input = <input type="text" 
					className="register-question-text"
					name={question.id}
					placeholder={"예) " + question.reply[0]}
					onChange={this.handleChange}
					value={this.props.answers[question.id] || ""}
				/>;
		} else if(question.type === "radio" || question.type === "checkbox") {
			input = question.reply.map((item,idx)=>(
				<label key={idx} className="register-question-choice">
					<input 
						type={question.type}
						name={question.id}
						value={item}
						checked={this.props.answers[question.id] === item || Array.from(this.props.answers[question.id]||[]).indexOf(item)!==-1}
						onChange={this.handleChange}
					/>
					{item}
				</label>
			));
			if(question.hasEtc) {
				input = input.concat(
					<label key={question.reply.length} className="register-question-choice">
						<input
							type={question.type}
							name={question.id}
							onChange={this.handleChange}
							data-etc={true}
							value={this.state.etc}
							checked={!!this.state.etc}
						/>
						기타
						<input
							className="register-question-choice-etc"
							type="text"
							value={this.state.etc}
							onChange={this.handleEtcChange}
							/>
					</label>
				);
			}
		} else if(question.type === "textarea") {
			input = <textarea name={question.id}
				className="register-question-textarea"
				placeholder={"예) " + question.reply[0]}
				onChange={this.handleChange}
				value={this.props.answers[question.id] || ""}
				/>
		}
		return input;
	}
	render() {
		if(!this.props.question) return null;
		const input = this.renderQuestion(this.props.question);
		const warning = this.props.warning[this.props.question.id] ? 'required-unfilled' : '';
		return (
			<div 
				className={`register-question ${warning}`}
				data-question-id={this.props.question.id}
				>
				<label className="register-question-query">{this.props.number}. {this.props.question.name}</label>
				{input}
			</div>
		)
	}
}