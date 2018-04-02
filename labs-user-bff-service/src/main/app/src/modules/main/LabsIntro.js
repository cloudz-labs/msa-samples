import React from 'react';
import './LabsIntro.css';

import IMG_WORKING from './img/working_board.jpg';
import IMG_SPRING from './img/cloud-laptop-spring.png';
import IMG_GEAR from './img/cloud-gear.png';
import IMG_NETWORK from './img/cloud-network-sign.png';
import IMG_SWITCH from './img/cloud-on-off.png';
import IMG_STORAGE from './img/cloud-storage.png';
import IMG_TOOLS from './img/cloud-tools.png';

export default class LabsIntro extends React.Component {
	render() {
		return (
				<div id="intro" className="labs-intro">

				<div className="grey-container">
					<div className="container">
						<div className="col-md-5">
							<img src={IMG_WORKING} className="img-responsive" alt="" />
						</div>
						<div className="col-md-7">
							<h3 className="pdb-20">CloudZ Labs는 단순한 교육 프로그램이 아닙니다.</h3>
							<p className="course-description">
								운영 중인 애플리케이션을 Cloud Application으로 실제 전환하면서 Cloud Application 개발 역량을
								습득하도록 도와주는 <strong><em>"코칭 프로그램"</em></strong>입니다.
							</p>
							<p className="course-description">CloudZ Labs에서 마련한 공간에 일정기간 상주하면서,
								Cloud Application전환 전 과정을 함께 계획해서 해당 애플리케이션에 특화된 전환을 수행합니다.</p>
							<p className="course-description">
								Cloud Application 특성 파악을 위한 <strong><em>"Point
										Coaching"</em></strong>을 제공하여 성공적으로 전환을 수행할 수 있도록 하고, 궁극적으로는 CloudZ Labs에 참여한 인원
								모두가 Cloud Application 개발역량을 내재화해 에반젤리스트 역할을 할 수 있도록 하는 프로그램입니다.
							</p>
						</div>
					</div>
				</div>

				<div className="white-container">
					<div className="container technology">
						<h2>CloudZ Labs에서 다루는 기술들</h2>

						<div className="row">
							<div className="col-md-4 col-sm-4">
								<img src={IMG_SPRING}
									alt="Spring Boot and Spring Cloud" className="img-responsive" />
								<p>
									Spring Boot <br /> Spring Cloud
								</p>
							</div>
							<div className="col-md-4 col-sm-4">
								<img src={IMG_STORAGE} alt="Backing Service"
									className="img-responsive" />
								<p>Backing Service</p>
							</div>
							<div className="col-md-4 col-sm-4">
								<img src={IMG_NETWORK} alt="Microservices"
									className="img-responsive" />
								<p>Microservices</p>
							</div>
						</div>

						<div className="row mgt-50">
							<div className="col-md-4 col-sm-4">
								<img src={IMG_GEAR} alt="Devops"
									className="img-responsive" />
								<p>Devops</p>
							</div>
							<div className="col-md-4 col-sm-4">
								<img src={IMG_TOOLS}
									alt="Replatforming and Refactoring" className="img-responsive" />
								<p>
									Replatforming <br /> Refactoring
								</p>
							</div>
							<div className="col-md-4 col-sm-4">
								<img src={IMG_SWITCH}
									alt="Platform as a Service" className="img-responsive" />
								<p>PaaS</p>
							</div>
						</div>
					</div>
				</div>
			</div>
//		<div id="intro" className="labs-intro">
//			<div className="grey-container">
//				<div className="container">
//					<div className="labs-intro-content">
//						<h2>DT Labs는 단순한 교육 프로그램이 아닙니다.</h2>
//						<p className="course-description">
//							운영 중인 애플리케이션을 Cloud Application으로 실제 전환하면서 
//							Cloud Application 개발 역량을 습득하도록 도와주는 <strong><em>"코칭 프로그램"</em></strong>입니다.
//						</p>
//						<p className="course-description">
//							DT Labs에서 마련한 공간에 일정기간 상주하면서, Cloud Application전환 전 과정을 
//							함께 계획해서 해당 애플리케이션에 특화된 전환을 수행합니다.
//						</p>
//						<p className="course-description">
//							Cloud Application 특성 파악을 위한 <strong><em>"Point Coaching"</em></strong> 을 제공하여 
//							성공적으로 전환을 수행할 수 있도록 하고, 궁극적으로는 DT Labs에 참여한 인원 모두가 
//							Cloud Application 개발역량을 내재화해 에반젤리스트 역할을 할 수 있도록 하는 프로그램입니다.
//						</p>
//					</div>
//				</div>
//			</div>
//			<div className="white-container">
//				<div className="container">
//					<div className="labs-intro-content-wide">
//						<h2>DT Labs에서 다루는 기술들</h2>
//						<div className="labs-intro-technologies">
//							<div className="labs-intro-technology-row">
//								<div className="labs-intro-technology">
//									<img src={IMG_SPRING} alt="Spring Boot and Spring Cloud"/>
//									<span>Spring Boot</span>
//									<span>Spring Cloud</span>
//								</div>
//								<div className="labs-intro-technology">
//									<img src={IMG_STORAGE} alt="Backing Service"/>
//									<span>Backing Service</span>
//								</div>
//								<div className="labs-intro-technology">
//									<img src={IMG_NETWORK} alt="Microservices"/>
//									<span>Microservices</span>
//								</div>
//							</div>
//							<div className="labs-intro-technology-row">
//								<div className="labs-intro-technology">
//									<img src={IMG_GEAR} alt="Devops"/>
//									<span>Devops</span>
//								</div>
//								<div className="labs-intro-technology">
//									<img src={IMG_TOOLS} alt="Replatforming and Refactoring"/>
//									<span>Replatforming</span>
//									<span>Refactoring</span>
//								</div>
//								<div className="labs-intro-technology">
//									<img src={IMG_SWITCH} alt="Platform as a Service"/>
//									<span>PaaS</span>
//								</div>
//							</div>
//						</div>
//					</div>
//				</div>
//			</div>
//		</div>
		);
	}
}