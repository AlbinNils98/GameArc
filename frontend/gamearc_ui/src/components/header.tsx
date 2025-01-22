import {useEffect, useState} from "react";
import axios from "axios";


export default function Header() {

    interface IUser {
        username: string | null;
    }

    const [user, setUser] = useState<IUser | null>();

    useEffect(() => {
        axios.get<IUser>('http://localhost:8080/user-info', {withCredentials: true})
            .then(response => {
                setUser(response.data)
            })
            .catch(error => {
                setUser(null)
            })

    },[]);

    const login = () => {
        window.location.href = 'http://localhost:8080/login'
    };

    const logout = () => {
        window.location.href = 'http://localhost:8080/logout'
    };

    const register = () => {
        window.location.href = 'http://localhost:8080/register'
    }

    return(
        <header className="flex h-16 bg-gaBlue">
            <a href="/" className="ml-5 max-w-20 flex justify-center">
            <img src="/GameArcLogoSmall.png" alt="GameArc Logo" className=" object-contain"/></a>
            <a href="/discover" className="text-gaWhite text-sm">Discover</a>
            {user && user.username ? (
                <>
                <p>{user.username}</p>
                <button onClick={logout} className="text-white">Logout</button>
                </>
            ) : (
                <>
                <button onClick={login} className="text-gaWhite text-sm">Login</button>
                <button onClick={register} className="text-gaWhite text-sm text-center content-center">Register</button>
                </>
            )}
        </header>
    )
}