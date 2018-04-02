import React from 'react';
import {Redirect} from 'react-router-dom';

import { http } from '../common/ajax';
import RegisterForm from './RegisterForm';

function isNull(value) {
	return value === "" || value === null || value === undefined || false;
}
// function compareVersion(v1, v2) {
// 	const v1parts = v1.split(".");
// 	const v2parts = v2.split(".");
// 	for(let i=0; i<Math.max(v1parts.length, v2parts.length); i++) {
// 		const v1val = parseInt(String(v1parts[i] || 0).replace(/\D/g,''), 10);
// 		const v2val = parseInt(String(v1parts[2] || 0).replace(/\D/g,''), 10);
// 		if(v1val > v2val) return -1;
// 		if(v2val > v1val) return 1;
// 	}
// 	return 0;
// }
const localStorageKey = "labs-answers-object";

export default class RegisterFormContainer extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			id : null,
			version : null,
			contents : null,
			notice : null,
			questionnaire : null,
			title : null,
			requiredWarning : null,
			answers : null,
			submitted : false
		};
		this.handleFillIn = this.handleFillIn.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
		this.migrateOldAnswers();
	}
	componentWillMount() {
		this.setState({
			id : null,
			version : null,
			contents : null,
			notice : null,
			questionnaire : null,
			title : null,
			requiredWarning : null,
			answers : null,
			submitted : false
		});
		const categoryId = this.props.match.params.id;
		http.get('/api/v1/registration/' + categoryId)
		.then(data=>{
			const questionSheet = data.questionnaire;
			const questionContents = questionSheet.contents;
			const questionVersion = questionSheet.version;
			const questionId = questionSheet.id;
			const categoryName = data.course.name;
			this.setState({
				id : questionId,
				categoryId : questionSheet.category,
				version : questionVersion,
				contents : questionContents,
				notice : questionContents.notice,
				questionnaire : questionContents.questionnaire,
				title : categoryName,
				requiredWarning : {},
				answers : {},
				submitted : false
			});
			if(this.checkForSavedAnswers() && window.confirm("이전에 작성중인 항목이 있습니다. 불러오시겠습니까?") === true){
				this.loadSavedAnswers();
			} else {
				this.clearSavedAllAnswers();
			}
		})
		.catch(err=>{
			console.error('fetch question sheet failed:',err);
		});
	}
	handleFillIn(answer) {
		const answers = Object.assign({}, this.state.answers, answer);
		const warning = Object.assign({}, this.state.requiredWarning);
		for(let prop in answer) {
			delete warning[prop];
		}
		this.saveAnswers(answers);
		this.setState({
			answers : answers,
			requiredWarning: warning
		});
	}
	getUnansweredQuestions(questionnaire, answers) {
		return questionnaire
			.reduce((prev,curr)=>prev.concat(curr ? curr.questions : []), [])
			.filter(question=>question.required)
			.filter(question=>isNull(answers[question.id]));
	}
	validateForm(questionnaire, answers) {
		const unanseredQuestions = this.getUnansweredQuestions(questionnaire, answers);
		if(unanseredQuestions.length) return false;
		return true;
	}
	mergeAnswer(contents, answers) {
		const sheet = {questionnaire:[]};
		contents.questionnaire.forEach(qsheet=>{
			sheet.questionnaire.push({
				category : qsheet.category,
				step : qsheet.step,
				questions : qsheet.questions.map(question=>({
					id : question.id,
					name : question.name,
					reply : isNull(answers[question.id]) ? "" : answers[question.id]
				}))
			});
		});
		return sheet;
	}
	clearFilledAnswers() {
		this.clearSavedAnswers(this.state.questionId);
		this.setState({answers:{}});
	}
	handleSubmit() {
		if(!this.validateForm(this.state.questionnaire, this.state.answers)) {
			console.error('validate 실패');
			alert("필수 입력 항목을 확인해 주십시오.")
			const requiredWarning = {};
			this.getUnansweredQuestions(this.state.questionnaire, this.state.answers)
				.forEach((question)=>{
					requiredWarning[question.id] = true
				});
			this.setState({requiredWarning});
			return false;
		}
		const data = {
			questionId: this.state.id,
			contents: JSON.stringify(this.mergeAnswer(this.state.contents, this.state.answers))
		};
		http.post('/api/v1/registration', data)
		.then(res=>{
			console.log('submitted')
			this.clearFilledAnswers();
			this.setState({
				submitted:true
			});
		}).catch(err=>{
			console.error('submit failed:',err.message,err);
		});
	}
	migrateOldAnswers() {
		localStorage.removeItem("answers");
	}
	checkForSavedAnswers() {
		const answers = this.getSavedAnswers();
		return !!answers && Object.keys(answers).length > 0;
	}
	loadSavedAnswers() {
		this.setState({
			answers : this.getSavedAnswers()
		});
	}
	getSavedAnswers() {
		const a = localStorage.getItem(localStorageKey);
		if(!a) return null;
		let parsed = null;
		try {
			parsed = JSON.parse(a);
		} catch(e) {

		}
		return parsed[this.state.id];
	}
	saveAnswers(answers) {
		const answersByQuestionId = JSON.parse(localStorage.getItem(localStorageKey)) || {};
		//TODO id, version 연동하여, 특정 ID의 버전이 올라가거나 id가 바뀌었을 때 별도 처리를 할 수 있는가.
		answersByQuestionId[this.state.id] = answers;
		localStorage.setItem(localStorageKey, JSON.stringify(answersByQuestionId));
	}
	clearSavedAnswers() {
		const answersByQuestionId = JSON.parse(localStorage.getItem(localStorageKey)) || {};
		delete answersByQuestionId[this.state.id];
		if(!Object.keys(answersByQuestionId).length) {
			this.clearSavedAllAnswers();
		} else {
			localStorage.setItem(localStorageKey, JSON.stringify(answersByQuestionId));
		}
	}
	clearSavedAllAnswers() {
		localStorage.removeItem(localStorageKey);
	}
	render() {
		if(this.state.submitted) {
			return <Redirect to="/register/complete"/>;
		}
		return (
			<RegisterForm
				notice={this.state.notice}
				questionnaire={this.state.questionnaire}
				title={this.state.title}
				requiredWarning={this.state.requiredWarning}
				answers={this.state.answers}
				onFillIn={this.handleFillIn}
				onSubmit={this.handleSubmit}>
			</RegisterForm>
		)
	}
}