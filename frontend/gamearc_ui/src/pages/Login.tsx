import { IoLockClosedOutline, IoPersonOutline } from 'react-icons/io5';
import api from '../api/axios';
import { ChangeEvent, FormEvent, useState } from 'react';
import { useAuth } from '../contexts/Auth';

export default function Login() {
    const {fetchUser} = useAuth();


    const [username, setUserName] = useState('');
    const [password, setPassword] = useState('')

    const signIn = async (e: FormEvent) => {
        e.preventDefault();

        console.log(import.meta.env.VITE_API_URL);
        try {
            const res = await api.post('/login',
                new URLSearchParams({ username, password }),
                {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    withCredentials: true
                })

                if(res.status === 200){
                    fetchUser();
                }
        } catch (error: unknown) {
            console.log(error)
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
                    <div className="dialog-row">
                        <label className="text-center redText"></label>
                    </div>
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
                    <div className="register">
                        <p>Don't have a account ?<a href="/register"> Register!</a></p>
                    </div>
                </form>
            </section>
        </div>
    )
}

