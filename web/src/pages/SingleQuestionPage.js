import React, { useEffect } from 'react'
import { connect } from 'react-redux'
import swal from 'sweetalert2'

import { fetchQuestion, deleteAnswer } from '../actions/questionActions'

import { Question } from '../components/Question'
import { Answer } from '../components/Answer'
import { Link } from 'react-router-dom'

const SingleQuestionPage = ({
  match,
  dispatch,
  question,
  hasErrors,
  loading,
  redirect,
  userId
}) => {
  const { id } = match.params
  useEffect(() => {
    dispatch(fetchQuestion(id))
  }, [dispatch, redirect, id])

  const renderQuestion = () => {
    if (loading.question) return <p>Loading question...</p>
    if (hasErrors.question) return <p>Unable to display question.</p>

    return <Question question={question} />
  }

  const renderAnswers = () => {
    return (question.answers && question.answers.length) ? question.answers.map(answer => (
      <Answer key={answer.id} answer={answer} onDelete={onDelete}/>
    )) : <p>Empty answer!</p>;
  }

  const onDelete = (id) => {
    swal.fire({
      title: 'Delete Question?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#07711D',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes Delete'
    }).then((result) => {
      if (result.isConfirmed) {
        dispatch(deleteAnswer(id))
        swal.fire(
          'Deleted!',
          'Your question has been deleted.',
          'success'
        )
      }
    })
  }

  return (
    <section>
      {renderQuestion()}
      {userId && <Link to={"/answer/" + id} className="button right">
        Reply
      </Link>}

      <h2>Answers</h2>
      {renderAnswers()}
    </section>
  )
}

const mapStateToProps = state => ({
  question: state.question.question,
  loading: state.question.loading,
  hasErrors: state.question.hasErrors,
  redirect: state.question.redirect,
  userId: state.auth.uid
})

export default connect(mapStateToProps)(SingleQuestionPage)
