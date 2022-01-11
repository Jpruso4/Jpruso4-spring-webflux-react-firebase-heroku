import firebase from "firebase/app";
import "firebase/auth";
import "firebase/firestore";

const config = {
    apiKey: "AIzaSyA2fbTyMWrzaFqeXHDJQLg8mHwS2oltWLQ",
    authDomain: "questionandanswerreact.firebaseapp.com",
    projectId: "questionandanswerreact",
    storageBucket: "questionandanswerreact.appspot.com",
    messagingSenderId: "804558679794",
    appId: "1:804558679794:web:984611cdb4c44b3ff52a6b"
}

firebase.initializeApp(config);
export const auth = firebase.auth;