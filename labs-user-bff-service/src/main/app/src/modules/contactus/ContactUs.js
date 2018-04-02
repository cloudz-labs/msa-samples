import React from 'react';
import './ContactUs.css';

import jaehoon from './img/jaehoon.jpg';
import bitna from './img/bitna.jpg';
import jihyun from './img/jihyun.png';
import hunkee from './img/hunkee.jpg';
import jisang from './img/jisang.jpg';
import teamjang from './img/teamjang.jpg';
import seoyoung from './img/seoyoung.jpg';
import bongchan from './img/bongchan.jpg';

const list = [
	{name:"June Park", email:"helloworld@sk.com", img:teamjang},
	{name:"Jihyun Lim", email:"limjh82@sk.com", img:jihyun},
	{name:"Bongchan Kim", email:"bckim0620@sk.com", img:bongchan},
	{name:"Hunkee Kang", email:"hunkee1017@sk.com", img:hunkee},
	{name:"Bitna Kim", email:"blingeee@sk.com", img:bitna},
	{name:"Jaehoon Jung", email:"1000jaeh@sk.com", img:jaehoon},
	{name:"Seoyoung Ahn", email:"sya@sk.com", img:seoyoung},
	{name:"Jisang Yun", email:"jisang@sk.com", img:jisang}
];

export default class ContactUs extends React.Component {
	constructor() {
		super();
		this.state = {
			egg:false,
			list : list
		}
	}
	render() {
		return (
			<div className="container">
				<h2>Contact US</h2>
				<ul className={"contact-list " + (this.state.egg ? 'hidden-treasure' : '')}>
					{this.state.list.map((person,idx)=>(
						<li key={idx}>
							<figure className="contact-profile">
								<div className="contact-img-holder">
									<img src={person.img} alt={person.name}/>
								</div>
								<figcaption>
									<dl>
										<dt>{person.name}<br/><small>{person.email}</small></dt>
									</dl>
								</figcaption>
							</figure>
						</li>
					))}
				</ul>
			</div>
		);
	}
}