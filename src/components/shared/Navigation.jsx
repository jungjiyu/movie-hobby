import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {Navbar, Nav, NavItem,Image} from 'react-bootstrap';
import '../../Styles/navigation.css'



export default class Navigation extends Component{
    render(){
        return(
           <Navbar fixedTop default collapseOnSelect>
                <Navbar.Header>
                <Navbar.Brand>
                <Link to="/"><Image className="logo"src="/assets/logo.png"/> </Link>
                </Navbar.Brand>
                <Navbar.Toggle/>
                </Navbar.Header>
                <Navbar.Collapse>
                    <Nav pullRight>
                    <NavItem eventKey={1} componentClass={Link} href="/" to="/">
                    Home
                    </NavItem>
                    <NavItem eventKey={2} componentClass={Link} href="/inception" to="/inception">
                    Inception
                    </NavItem>
                    <NavItem eventKey={3} componentClass={Link} href="/eternalSunshine" to="/eternalSunshine">
                    Eternal Sunshine
                    </NavItem>
                    <NavItem eventKey={4} componentClass={Link} href="/shutterIsland" to="/shutterIsland">
                    Shutter Island
                    </NavItem>
                    </Nav>
           </Navbar.Collapse>
           </Navbar>
        )
    }
}