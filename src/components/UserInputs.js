import { useState } from "react";
import { Button } from "reactstrap";

export default function UserInputs({ user_email, user_pass, onSubmit, registering }) {
  const [email, setEmail] = useState(user_email);
  const [password, setPassword] = useState(user_pass);
  const [confirmPassword, setConfirmPassword] = useState("");
  const [validEmail, setValidEmail] = useState(false);
  const [validPassword, setValidPassword] = useState(false);
  const [validConfirmPassword, setValidConfirmPassword] = useState(false);
  const [isSubmitted, setIsSubmitted] = useState(false);

  function emailBoxChanged(evt) {
    setEmail(evt.target.value);
    if (evt.target.validity.valid) {
      setValidEmail(true);
    } else {
      setValidEmail(false);
    }
  }

  function passBoxChanged(evt) {
    setPassword(evt.target.value);
    if (evt.target.validity.valid) {
      setValidPassword(true);
    } else {
      setValidPassword(false);
    }
  }

  function confirmPassBoxChanged(evt) {
    setConfirmPassword(evt.target.value);
    if (evt.target.validity.valid && evt.target.value === password) {
      setValidConfirmPassword(true);
    } else {
      setValidConfirmPassword(false);
    }
  }

  function buttonClicked() {
    setIsSubmitted(true);
    if (validEmail && validPassword && (validConfirmPassword || !registering)) {
      onSubmit(email, password);
    }
  }

  return (
    <div>
      <div style={{ textAlign: 'right' , paddingBottom:"0.3vw"}}>
        <label htmlFor="email" style={{ paddingRight:"0.3vw"}}>Email:</label>
        <input
          aria-labelledby="email-input"
          name="email"
          id="email"
          type="email"
          value={email}
          onChange={emailBoxChanged}
          required
        />
      </div>
      <div style={{ textAlign: 'right', paddingBottom:"0.3vw" }}>
        <label htmlFor="password" style={{ paddingRight:"0.3vw"}}>Password:</label>
        <input
          aria-labelledby="pass-input"
          name="password"
          id="password"
          type="password"
          value={password}
          onChange={passBoxChanged}
          required
        />
      </div>
      {registering && (
        <div style={{ textAlign: 'right', paddingBottom:"0.3vw" }}>
          <label htmlFor="confirm-password" style={{ paddingRight:"0.3vw"}}>Confirm Password:</label>
          <input
            aria-labelledby="confirm-pass-input"
            name="confirm-password"
            id="confirm-password"
            type="password"
            value={confirmPassword}
            onChange={confirmPassBoxChanged}
            required
          />
        </div>
      )}
      
      <div>
        <Button id="submit-button" 
        type="button" 
        onClick={buttonClicked}
        >
          {registering ? "Register" : "Login"}
        </Button>
      </div>
      <div>
      <div>
        {isSubmitted && (!validEmail || !validPassword)  && (
          <p style={{ color: "red" }}>Invalid email address or password</p>
        )}
        {isSubmitted && (!validConfirmPassword) && registering && (
          <p style={{ color: "red" }}>Passwords do not match</p>
        )}
      </div>
</div>
    </div>
  );
}