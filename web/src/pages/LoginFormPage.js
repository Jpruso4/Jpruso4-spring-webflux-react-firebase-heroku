import React, { Component } from "react";
import { Link } from "react-router-dom";
import { signInWithGoogle, signin } from "../config/auth";

export default class Login extends Component {
    constructor(props) {
      super(props);
      this.state = {
        error: null,
        email: "",
        password: "",
      };
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    handleChange(event) {
      this.setState({
        [event.target.name]: event.target.value,
      });
    }
  
    async handleSubmit(event) {
      event.preventDefault();
      this.setState({ error: "" });
      try {
        await signin(this.state.email, this.state.password);
      } catch (error) {
        this.setState({ error: error.message });
      }
    }
  
    async googleSignIn() {
      try {
        await signInWithGoogle();
      } catch (error) {
       
      }
    }
  
    render() {
      return (
        <div class="login-wrap">
          <form
            className="mt-5 py-5 px-5"
            autoComplete="off"
            onSubmit={this.handleSubmit}
          >
            <h1>Iniciar Sesión</h1>
            <p className="lead">Complete elformulario para iniciar sesión</p>
            <div className="form-group container-input">
              <input
                className="form-control"
                placeholder="Email"
                name="email"
                type="email"
                onChange={this.handleChange}
                value={this.state.email}
              />
              <input
                className="form-control"
                placeholder="Password"
                name="password"
                type="password"
                onChange={this.handleChange}
                value={this.state.password}
              />
            </div>
  
            {this.state.error ? (
              <p className="text-danger">{this.state.error}</p>
            ) : null}
            <button className="boton-login px-5" type="submit">
              Iniciar
            </button>
  
            <button
              className="boton-cerrar"
              onClick={this.googleSignIn}
              type="button"
            >
              Iniciar sesión con Google
            </button>
            <p>
              ¿No tienes una cuenta?{" "}
              <Link className="a" to="/register">
                Registrate
              </Link>
            </p>
  
            <hr />
          </form>
        </div>
      );
    }
  }