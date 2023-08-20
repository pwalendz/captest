import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { useDispatch } from "react-redux";
import { setEmail } from "./redux/emailActions"; // Import your action creator

function Login() {
  const [emailid, setEmailId] = useState("");
  const [password, setPassword] = useState("");
  const [typeofuser, setTypeOfUser] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const signIn = async (event) => {
    event.preventDefault();
    const login = { emailid, password, typeofuser };
    try {
      const result = await axios.post("http://localhost:8080/login/signIn", login);
      if (result.data === "Admin Success") {
        dispatch(setEmail(emailid)); // Dispatch action to set email
        navigate("/Admin", { replace: true });
      } else if (result.data === "Customer success") {
        dispatch(setEmail(emailid)); // Dispatch action to set email
        navigate("/Customer", { replace: true });
      } else {
        alert(result.data);
      }
    } catch (ex) {
      console.log(ex);
    }
  };

  return (
    <div>
      <div>Login Page</div>
      <form onSubmit={signIn}>
        <label>EmailId</label>
        <input
          type="email"
          name="emidlid"
          onChange={(e) => setEmailId(e.target.value)}
        />
        <br />
        <label>Password</label>
        <input
          type="password"
          name="password"
          onChange={(e) => setPassword(e.target.value)}
        />
        <br />
        <label>TypeOfUser</label>
        <input
          type="radio"
          name="typeofuser"
          value="admin"
          onChange={(e) => setTypeOfUser(e.target.value)}
        />
        Admin
        <input
          type="radio"
          name="typeofuser"
          value="customer"
          onChange={(e) => setTypeOfUser(e.target.value)}
        />
        Customer
        <br />
        <input type="submit" value="submit" />
        <input type="reset" value="reset" />
        <br />
        <Link to="signup">SignUp</Link>
      </form>
    </div>
  );
}

export default Login;
