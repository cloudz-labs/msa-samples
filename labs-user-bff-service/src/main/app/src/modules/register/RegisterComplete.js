import React from 'react';
import { Link } from 'react-router-dom';

export default class RegisterComplete extends React.Component {
	render() {
		return (
			<div className="register-complete container">
				<h3>등록완료!</h3>
				<p>접수사항 확인 후 운영자가 개별적으로 연락을 드리도록 하겠습니다</p>
				<Link to="/">메인화면으로 돌아갑니다.</Link>
			</div>
		);
	}
}