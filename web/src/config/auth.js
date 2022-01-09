import { auth } from "../config/firebase";

export const LOGIN = "LOGIN";
export const LOGOUT = "LOGOUT";

export const login = (email, uid) => ({ type: LOGIN, payload: { email, uid } });

export const logout = () => ({
  type: LOGOUT,
});

export function signup(email, password) {
  return auth().createUserWithEmailAndPassword(email, password);
}

export function signin(email, password) {
  return auth().signInWithEmailAndPassword(email, password);
}

export function logoutu() {
  return auth().signOut();
}

export function signInWithGoogle() {
  const provider = new auth.GoogleAuthProvider();
  return auth().signInWithPopup(provider);
}