import firebase from "firebase/app";
import "firebase/auth";
import "firebase/firestore";

const config = {
    apiKey: "AIzaSyCIOFRImo09JF5s9oJGT3j_ZaktnR2OLJ0",
    authDomain: "questions-and-answer-1f476.firebaseapp.com",
    projectId: "questions-and-answer-1f476",
    storageBucket: "questions-and-answer-1f476.appspot.com",
    messagingSenderId: "452642488451",
    appId: "1:452642488451:web:9629147722734b3b5b9bb6"
}

firebase.initializeApp(config);
export const auth = firebase.auth;