import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {Thumbnail ,Grid,Col,Image,Button,Row} from 'react-bootstrap';
import '../Styles/home.css'
export default class Moives extends Component{
    render(){
        return(

            <Grid>

                <Row className= "show-grid text-center">
                    <Col xs={12} sm={4} className="place">
                    <Thumbnail src="assets/img/movies/inception.jpg" alt="242x200">
                        <h3>인셉션</h3>
                        <p>인셉션은 단순한 액션 영화가 아니라, 복잡한 세계관과 창의적인 스토리로 관객을 몰입시키는 작품이다. 꿈속의 꿈을 탐험하며 벌어지는 이야기는 영화 자체가 하나의 거대한 퍼즐처럼 느껴진다. 이를 풀어나가는 재미와, 결국 관객에게 "토템은 멈췄을까?"라는 질문을 던지는 열린 결말은 계속 생각하게 만든다.</p>
                        <p>
                        <Link to="/inception">
                        <Button bsStyle="primary">More</Button>
                        </Link>
                        </p>
                    </Thumbnail>                   
                    </Col>

                    <Col xs={12} sm={4} className="place">
                    <Thumbnail src="assets/img/movies/shutterisland.jpg"  alt="242x200">
                        <h3>셔터 아일랜드</h3>
                        <p>셔터 아일랜드는 관객이 끝까지 진실과 거짓을 구별할 수 없도록 만들며, 강렬한 서스펜스와 심리적 반전의 정수를 보여준다. 테디 다니엘스(레오나르도 디카프리오)가 추적하는 진실이 사실 자신의 내면의 상처와 맞닿아 있다는 사실이 밝혀질 때, 감정적 충격은 이루 말할 수 없다.</p>
                        <p>
                        <Link to="/shutterIsland">
                        <Button bsStyle="primary">More</Button></Link>
                        </p>
                    </Thumbnail>                   
                    </Col>

                    <Col xs={12} sm={4} className="place">
                    <Thumbnail src="assets/img/movies/eternalsunshine.jpg" alt="242x200">
                        <h3>이터널 선샤인</h3>
                        <p>이터널 선샤인은 사랑과 이별, 그리고 인간 관계에 대한 가장 독특하면서도 감동적인 접근을 보여준다. 기억 삭제라는 SF적인 소재를 통해 우리가 잃고 싶은 기억과 잊고 싶지 않은 기억의 갈등을 섬세하게 표현한다.</p>
                        <p>
                        <Link to="/eternalSunshine">
                        <Button bsStyle="primary">More</Button></Link>
                        </p>
                    </Thumbnail>                   
                    </Col>
                </Row>
                       </Grid>
        )}}