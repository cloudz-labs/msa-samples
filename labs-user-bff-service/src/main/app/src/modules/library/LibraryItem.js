import React from 'react';

import './LibraryItem.css';
import '../common/fileicon.css';

export default class LibraryItem extends React.Component {
	render() {
		const item = this.props.item;
		if(!item) return null;
		let resources = [];
		if(item.links && item.links.length) {
			resources = resources.concat(
				item.links
				.filter(link=>!!link.path)
				.map((link,idx)=>(
					<a key={idx} className="dt-library-item-link" href={encodeURI(link.path)}>
						<i className="fa fa-link">{link.title}</i>
					</a>
				))
			);
		}
		if(item.attachments && item.attachments.length) {
			resources = resources.concat(
				item.attachments
				.filter(attachment=>!!attachment.path)
				.map((attachment,idx)=>(
					<a key={resources.length + idx}
						className="dt-library-item-download"
						href={`/api/v1/library/attachment/${item.id}/${encodeURI(attachment.path)}`}
						>
						<i class="fa fa-file-pdf-o"> {attachment.title}</i>
					</a>
				))
			)
		}
		return (
				<div className="dt-library-item">
				<div className="dt-library-item-title dt-library-item-property">
					<h3>{item.title}</h3>
				</div>
				<div className="dt-library-item-property">
					<p className="box dt-library-item-description">{item.contents}</p>
				</div>

				{resources.length ?
				<div className="dt-library-item-property dt-library-item-resources">
					<div className="dt-library-item-resources-container">{resources}</div>
				</div>
				: null}

			</div>
//			<div className="dt-library-item">
//				<div className="dt-library-item-title dt-library-item-property">
//					<h3>{item.title}</h3>
//				</div>
//				<div className="dt-library-item-property">
//					<p className="dt-library-item-description">{item.contents}</p>
//				</div>
//				{resources.length ? 
//					<div className="dt-library-item-property dt-library-item-resources">
//						<div className="dt-library-item-resources-container">
//							{resources}
//						</div>
//					</div>
//					: null}
//			</div>
		);
	}
}