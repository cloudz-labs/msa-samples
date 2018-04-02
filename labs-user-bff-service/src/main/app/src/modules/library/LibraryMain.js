import React from 'react';

import './LibraryMain.css';
import LibraryItem from './LibraryItem';

export default class LibraryMain extends React.Component {
	render() {
		return (
			<div className="dt-library-main">
				<section className="container">
				<h1>Library</h1>
				<p>CloudZ Labs에서 제공하는 자료의 목록입니다.</p>
				<ul className="dt-library-category-list">
					{this.props.categories.map((category)=>(
						<li key={category.id} className="dt-library-category-container">
							<h2>{category.name}</h2>
							<ul className="dt-library-item-list">
								{category.documents
									.map((doc)=>(
										<li key={doc.id} className="dt-library-item-container">
											<LibraryItem item={doc}></LibraryItem>
										</li>
									))}
							</ul>
						</li>
					))}
				</ul>
				</section>
			</div>
		);
	}
}
LibraryMain.defaultProps = {
	categories : [],
	docs : []
};