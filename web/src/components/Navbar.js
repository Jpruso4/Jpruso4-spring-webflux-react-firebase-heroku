import React from 'react'
import { Link } from 'react-router-dom'

export const PublicNavbar = () => (
  <nav>
    <section>
      <Link><img src="pregunta.png" alt='Icono de la app' height="30px"/></Link>
      <Link to="/">Home</Link>
      <Link to="/questions">Questions</Link>
      <Link to="/login">Login</Link>
      <Link to="/register">Create new Account</Link>
    </section>
  </nav>
)

export const PrivateNavbar = () => (
  <nav>
    <section>
      <Link><img src="pregunta.png" alt='Icono de la app' height="30px"/></Link>
      <Link to="/">Home</Link>
      <Link to="/questions">Questions</Link>
      <Link to="/new">New</Link>
      <Link to="/list">List</Link>
    </section>
  </nav>
)
