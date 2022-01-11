import React, { Component } from "react";
import { signUp } from "../actions/authActions";

export default class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
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
        try {
            await signUp(this.state.email, this.state.password);
        } catch (error) {
            this.setState({ error: error.message });
        }
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <h1 style={{ textAlign: "center" }}>Register</h1>
                    <p style={{ textAlign: "center" }}>Complete the form to register</p>
                    <div className="form-group container-input">
                        <div className="input">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" onChange={this.handleChange} value={this.state.email}/>
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" name="password" onChange={this.handleChange} value={this.state.password} />
                            <button className="buttons"><strong>Register</strong></button>
                        </div>
                    </div>
                </form>
            </div>
        )
    }
}