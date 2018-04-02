import React from 'react';
import './CourseListItem.css';
import ReactMarkdown from 'react-markdown';

export default class CourseListItem extends React.Component {
	constructor(props) {
		super(props);
		this.handleClick = this.handleClick.bind(this);
	}
	handleClick(e) {
		if(typeof this.props.onSelect === "function") {
			this.props.onSelect();
		}
	}
	render() {
		return (
			<li className="course-list-item">
				<p className="course-list-item-title">{this.props.course.name}</p>
				<div className="course-list-item-desc">
					<h3>목표</h3>
					<ReactMarkdown source={this.props.course.description} />
				</div>
				<button type="button" className="course-select-button"
					onClick={this.handleClick}>신청</button>
			</li>
//			<li className="course-list-item">
//				<p className="course-list-item-title">{this.props.course.name}</p>
//				<div className="course-list-item-desc">
//					<ReactMarkdown source={this.props.course.description}/>
//				</div>
//				<button type="button" className="course-select-button" onClick={this.handleClick}>신청</button>
//			</li>
		)
	}
}