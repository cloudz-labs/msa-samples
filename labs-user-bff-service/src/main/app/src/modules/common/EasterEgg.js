import React from 'react';

let timer = null;

export default class EasterEgg extends React.Component {
	constructor(props) {
		super(props);
		this.handleKeydown = this.handleKeydown.bind(this);
		this.state = {
			userSequence : []
		};
	}
	handleKeydown(e) {
		if(this.props.printKeyCode === true) {
			console.log(e.keyCode);
		} else if(typeof this.props.printKeyCode === "function") {
			this.props.printKeyCode(e.keyCode);
		}
		const newSeq = this.state.userSequence.concat([e.keyCode]).slice(-this.props.sequence.length);
		if(timer) {
			clearTimeout(timer);
			timer = null;
		}
		timer = setTimeout(()=>{
			this.setState({userSequence:[]});
		},this.props.clearAfter);
		if(newSeq.toString() === this.props.sequence.toString()) {
			this.performCeremony();	
		}
		this.setState({
			userSequence : newSeq
		});
	}
	performCeremony() {
		if(typeof this.props.onSuccess === "function") {
			this.props.onSuccess();
		}
	}
	componentWillMount() {
		document.addEventListener('keydown', this.handleKeydown, false);
	}
	componentWillUnmount() {
		document.removeEventListener('keydown', this.handleKeydown, false);
	}
	render() {
		return null;
	}
};
EasterEgg.defaultProps = {
	sequence : [40, 37, 40, 39, 90, 88, 38, 38], //↓←↓→zx↑↑
	clearAfter : 1000, //each keydown interval should be under 1000ms
	printKeyCode : false
};