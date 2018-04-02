import React from 'react';
import CourseListItem from './CourseListItem';
import './CourseList.css';

export default class CourseList extends React.Component {
	constructor(props) {
		super(props);
		this.handleCourseSelect = this.handleCourseSelect.bind(this);
	}
	handleCourseSelect(course) {
		if(typeof this.props.onCourseSelect === "function") {
			this.props.onCourseSelect(course);
		}
	}
	render() {
		//HashLink 가 MutationObserver를 이용하여 #course 엘리먼트에 접근을 하는데
		//MainContainer를 구성하는 요소들이 모두 로드 되기 전에 로드되어 있으면
		//스크롤이 컨테이너 로드중인 상태에서 일어나며 스크롤이 충분히 진행되지 않기도 함.
		//일단은 이를 막기 위하여 div#course 엘리먼트의 로드를 데이터 바인드 이후로 지연시킴
		//하지만 확실한 해결책은 아님.
		if(!this.props.courses || !this.props.courses.length) return null;
		return (
				<div id="course">
					<div className="grey-container">
						<div className="container course-container">
							<p>코스를 선택하십시오</p>
							<ul className="course-list-items">
								{this.props.courses.map((course)=>(
								<CourseListItem course={course} key={course.id} onSelect={()=>this.handleCourseSelect(course)}
								></CourseListItem>
								))}
							</ul>
						</div>
					</div>
				</div>
//			<div id="course">
//				<div className="grey-container">	
//					<div className="container course-container">
//						<p>코스를 선택하십시오</p>
//						<ul className="course-list-items">
//						{this.props.courses.map((course)=>(
//							<CourseListItem
//								course={course} key={course.id}
//								onSelect={()=>this.handleCourseSelect(course)}
//							></CourseListItem>
//						))}
//						</ul>
//					</div>
//				</div>
//			</div>
		);
	}
}