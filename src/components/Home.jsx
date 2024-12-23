import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {Jumbotron,Grid,Col,Image,Button,Row} from 'react-bootstrap';
import Movies from "./Movies";
import '../Styles/home.css'
export default class Home extends Component{
    render(){
        return(
            <div>
                <div className="header">
               
               <Grid>
                <Jumbotron>
                    <h2>Introducing my favorite movies</h2>
                    <h3>2024 웹 서버 프로그래밍 과제- React 와 SpringBoot 를 연동하여 취미 데이터 출력하기</h3>
                    <Button bsStyle="primary" href="https://github.com/jungjiyu/movie-hobby/tree/master">GitHub</Button>
                </Jumbotron>
                </Grid>
                </div>                 
                <Grid>             


                <div className="banner text-center">
                    <h2 >스크린 위의 마법, 그 속으로 빠져보세요</h2>
                    <p>우리는 모두 각자의 이야기를 가지고 있지만, 때로는 다른 사람의 이야기를 통해 더 큰 감동을 느낍니다. 이곳은 그런 영화들을 모아둔 공간입니다. 사랑, 용기, 희망, 그리고 삶의 모든 감정이 담긴 작품들. 지금 이 순간, 당신의 마음을 움직일 특별한 이야기를 만나보세요.</p>
                </div>
                
                <Movies/>


            </Grid>
            </div>
        )
    }
}