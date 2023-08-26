import React from "react";
import { useState } from 'react';
import UserInputs from "../components/UserInputs";
import LoginUser from "../APIcalls/LoginUser";
import RegisterUser from "../APIcalls/RegisterUser";
import { useAPI } from "../APIcalls/GlobalStates";
import { Container, Col, Row } from "reactstrap";
import { useNavigate } from "react-router-dom";



export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [resMsg, setResMsg] = useState("");
  const [error, setError] = useState("");
  const { updateData } = useAPI();
  const navigate = useNavigate();

  function handleRegister(email, password) {
    setEmail(email);
    setPassword(password);
    RegisterUser(email, password, setResMsg, updateData, navigate);
  }

  function handleLogin(email, password) {
    setEmail(email);
    setPassword(password);
    LoginUser(email, password, setError, updateData, navigate)
  }


  return (
    <Container>
        
      <div className="page">
      <section className="features_box">
      <Row style={{ height: "39vw",
        paddingTop:"12.5%"
      }}>
      <Col sm={5}>
        <div className="page_title" style={{paddingLeft:"3vw"}}><h3>Register</h3></div>
        <div className="login-container">
        <div className="word-input">
        <UserInputs user_email={email} user_pass={password} onSubmit={handleRegister} registering={true}/>
        <div style={{ color: "red", textAlign:"center" }}>
        {resMsg === ("User already exists" ) && (
          <p>A user with that email already exists. </p>
        )}
        {resMsg === "Request body incomplete, both email and password are required" && (
          <p>Please fill in all fields</p>
        )}
        </div>
      </div>
      </div>
    </Col>
    <Col sm={1}><div id="line"></div> </Col>
    <Col sm={4}>
      <div className="page_title"><h3>Login</h3></div>
      <div className="login-container">
      <div className="word-input">
        <UserInputs user_email={email} user_pass={password} onSubmit={handleLogin} registering={false}/>
        {error.message === "Request body incomplete, both email and password are required" && (
          <p style={{ color: "red" }}>Please fill in all fields</p>
        )}
        {(error.message === "Incorrect email or password") && (
          <p style={{ color: "red" }}>{error.message}</p>
        )}
      </div>
    </div>
    </Col>
    </Row>
    </section>
    </div>
    
    </Container>
  );
}
