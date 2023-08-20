// src/redux/rootReducer.js

import { combineReducers } from "redux";
import emailReducer from "./emailReducer";

const rootReducer = combineReducers({
  email: emailReducer,
  // Other reducers if any
});

export default rootReducer;
