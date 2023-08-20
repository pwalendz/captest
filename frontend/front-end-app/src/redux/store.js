import { createStore } from "redux";

const initialState = {
  email: null, // You might want to initialize this to null
};

function rootReducer(state = initialState, action) {
  switch (action.type) {
    case "SET_EMAIL":
      return { ...state, email: action.payload };
    default:
      return state;
  }
}

const store = createStore(rootReducer);

export default store;
