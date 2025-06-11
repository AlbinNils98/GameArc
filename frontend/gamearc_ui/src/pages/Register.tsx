import { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import { IoLockClosedOutline, IoPersonOutline } from 'react-icons/io5';
import api from '../api/axios';
import { useNavigate } from 'react-router-dom';
import { isAxiosError } from 'axios';

export default function Register() {
  const navigate = useNavigate();

  const [username, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState<string | undefined>();

  useEffect(() => {
    if (errorMessage) {
      const timer = setTimeout(() => setErrorMessage(''), 3000);
      return () => clearTimeout(timer);
    }
  }, [errorMessage]);

  const register = async (e: FormEvent) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setErrorMessage('Passwords not matching');
      return;
    }

    try {
      const res = await api.post('/register',
        { username, password },
        { withCredentials: true })

      if (res.status === 200) {
        navigate('/login');
      }
    } catch (error: unknown) {
      if (isAxiosError(error)) {
        if (error.response) {
          const data = error.response.data as { message: string, details?: string, timestamp?: string };
          setErrorMessage(data.message)
        } else if (error.request) {
          alert("No response from server");
        } else {
          alert("Error: " + error.message);
        }
      } else {
        alert("Unknown error");
      }
    }

  }

  const handleUsername = (event: ChangeEvent<HTMLInputElement>) => {
    setUserName(event.target.value);
  }

  const handlePassword = (event: ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  }
  const handleConfirmPassword = (event: ChangeEvent<HTMLInputElement>) => {
    setConfirmPassword(event.target.value);
  }
  return (
    <div className='flex justify-center items-center h-screen'>
      <section className='credentialBox'>
        <form onSubmit={register}>
          <h1>Sign Up</h1>
          <div className="inputbox">
            <IoPersonOutline className='ion-icon' />
            <input
              value={username}
              onChange={handleUsername}
              type="text"
              id="username"
              name="username"
              required />
            <label >Username</label>
          </div>
          <div className="inputbox">
            <IoLockClosedOutline className='ion-icon' />
            <input
              value={password}
              onChange={handlePassword}
              type="password"
              id="password"
              name="password"
              required />
            <label>Password</label>
          </div>
          <div className="inputbox">
            <IoLockClosedOutline className='ion-icon' />
            <input
              value={confirmPassword}
              onChange={handleConfirmPassword}
              type="password"
              id="passwordcon"
              name="confirm-password"
              required />
            <label>Confirm Password</label>
          </div>
          <button
            id="submit"
            type="submit"
          >Sign Up</button>
          <div className={'h-10 flex items-center justify-center my-2 '}>
            <label className="text-gaWhite font-semibold">{errorMessage}</label>
          </div>
          <div className="register">
            <p>Already have an account? <a href="/login">Log In</a></p>
          </div>
        </form>
      </section>
    </div>
  )
}