import React from "react";
import "../styles.css";
import { Container, Row, Col } from "reactstrap";


export default function Home() {
  return (
    <main>
      <Hero />
    </main>
  );
}


const Hero = () => (
  <Container>
    <Row>
    <Col md="6">
      <section className="hero">
        {/* image for the hero */}
      </section>
      </Col>
      <Col md="6">
        {/* content for the hero */}
        <div className="hero__content">
          <h1 className="hero__title">Cinematica</h1>
          <p className="hero__subtitle">Unleash your inner cinephile</p>
          <a href="/movies">FIND A MOVIE</a>
        </div>
      </Col>
      
    </Row>
  </Container>
);
