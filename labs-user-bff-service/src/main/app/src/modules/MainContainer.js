import React from 'react';
import {Redirect} from 'react-router';

import CourseList from './main/CourseList';
import LabsHeaderIntro from './main/LabsHeaderIntro';
import LabsIntro from './main/LabsIntro';
import { http }  from './common/ajax';

import Header from './main/Header';

export default class MainContainer extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			courses : [],
			course : null
		};
		this.handleCourseSelect = this.handleCourseSelect.bind(this);
	}
	componentWillMount() {
		http.get('/api/v1/courses')
		.then(data=>{
			this.setState({ courses : data });
		});
	}
	handleCourseSelect(course) {
		this.setState({course})
	}
	render() {
		if(this.state.course) {
			return <Redirect push to={`/register/${this.state.course.id}`}></Redirect>;
		}
		return (
			<div>
				<Header><LabsHeaderIntro></LabsHeaderIntro></Header>
				<LabsIntro></LabsIntro>
				<CourseList 
					courses={this.state.courses}
					onCourseSelect={this.handleCourseSelect}
				></CourseList>
			</div>
		);
	}
}