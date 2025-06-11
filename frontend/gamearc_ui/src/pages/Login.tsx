import { IoLockClosedOutline, IoPersonOutline } from 'react-icons/io5';
import api from '../api/axios';
import { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import { useAuth } from '../contexts/Auth';
import { useNavigate } from 'react-router-dom';
import { isAxiosError } from 'axios';

export default function Login() {
    const { user, fetchUser } = useAuth();
    const navigate = useNavigate();

    if (user) navigate('/archive');

    const [username, setUserName] = useState('');
    const [password, setPassword] = useState('');

    const [errorMessage, setErrorMessage] = useState<string | undefined>();

     useEffect(() => {
    if (errorMessage) {
      const timer = setTimeout(() => setErrorMessage(''), 3000); // Clear after 3 seconds
      return () => clearTimeout(timer); // Cleanup if component unmounts or errorMessage changes
    }
  }, [errorMessage]);

    const signIn = async (e: FormEvent) => {
        setErrorMessage('');
        e.preventDefault();

        try {
            const res = await api.post('/login',
                new URLSearchParams({ username, password }),
                {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    withCredentials: true
                })

            if (res.status === 200) {
                fetchUser();
                navigate('/archive');
            }

        } catch (error: unknown) {
            if (isAxiosError(error)) {
                if (error.response) {
                    const data = error.response.data as { error?: string };
                    setErrorMessage(data.error)
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
    setUserName(event.target.value)
}

const handlePassword = (event: ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value)
}

return (
    <div className='flex justify-center items-center h-screen'>
        <section className='credentialBox'>
            <form onSubmit={signIn}>
                <h1 className='credentialTitle'>Login</h1>
                <div className="inputbox">
                    <IoPersonOutline className='ion-icon' />
                    <input
                        value={username}
                        onChange={handleUsername} name="username"
                        id="username"
                        type="name"
                        required />
                    <label>Username</label>
                </div>
                <div className="inputbox">
                    <IoLockClosedOutline className='ion-icon' />
                    <input
                        value={password}
                        onChange={handlePassword} name="password"
                        type="password"
                        id="password"
                        required />
                    <label>Password</label>
                </div>
                <button type="submit">Log in</button>
                <div className={'h-10 flex items-center justify-center my-2 '}>
                    <label className="text-gaWhite font-semibold">{errorMessage}</label>
                </div>
                <div className="register">
                    <p>Don't have a account ?<a href="/register"> Register!</a></p>
                </div>
            </form>
        </section>
    </div>
)
}

