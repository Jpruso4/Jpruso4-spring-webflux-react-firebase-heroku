import React, { useEffect } from 'react'
import { connect } from 'react-redux'
import swal from 'sweetalert2'
import { fetchOwnerQuestions, deleteQuestion } from '../actions/questionActions'
import { Question } from '../components/Question'

const OwnerQuestionsPage = ({ dispatch, loading, questions, hasErrors, redirect, userId }) => {
    useEffect(() => {
        dispatch(fetchOwnerQuestions(userId))
    }, [dispatch, userId]);

    useEffect(() => {
        if (redirect) {
            dispatch(fetchOwnerQuestions(userId))
        }
    }, [redirect, dispatch, userId]);

    const onDelete = (id) => {
        swal.fire({
            title: 'Delete Answer?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#07711D',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes Delete'
          }).then((result) => {
            if(result.isConfirmed) {
            dispatch(deleteQuestion(id))
                swal.fire(
                'Deleted!',
                'Your question has been deleted.',
                'success'
              )
            }
          })
    }


    const renderQuestions = () => {
        if (loading) return <p>Loading questions...</p>
        if (hasErrors) return <p>Unable to display questions.</p>

        return questions.map(question => <Question
            key={question.id}
            question={question}
            excerpt onDelete={onDelete} />)
    }

    return (
        <section>
            <h1>Questions</h1>
            {renderQuestions()}
        </section>
    )
}

const mapStateToProps = state => ({
    loading: state.question.loading,
    questions: state.question.questions,
    hasErrors: state.question.hasErrors,
    redirect: state.question.redirect,
    userId: state.auth.uid
})

export default connect(mapStateToProps)(OwnerQuestionsPage)
