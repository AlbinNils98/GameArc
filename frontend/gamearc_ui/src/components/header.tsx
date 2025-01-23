import {useEffect} from "react";
import axios from "axios";
import { IUser } from "../interfaces/interfaces";

interface headerProps {
    user: IUser | null | undefined;
    setParentUseState: React.Dispatch<React.SetStateAction<IUser | null | undefined>>;
  }


export default function Header({user, setParentUseState}: headerProps) {


    useEffect(() => {
        axios.get<IUser>('http://localhost:8080/user-info', {withCredentials: true})
            .then(response => {
                setParentUseState(response.data)
            })
            .catch(error => {
                setParentUseState(null)
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
        <header className="flex h-16 bg-gaBlue justify-between">
            <a href="/" className="ml-5 max-w-20 flex justify-center">
            <img src="/GameArcLogoSmall.png" alt="GameArc Logo" className=" object-contain"/></a>
            <div className="flex justify-center mr-5">
            <a href="/discover" className="headerLink">Discover</a>
            {user && user.username ? (
                <>
                <a href="/archive" className="headerLink">Archive</a>
                <button onClick={logout} className="headerLink">Logout</button>
                <p className="text-base text-gaWhite self-center ml-8">{user.username}</p>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-5 self-center text-gaWhite">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
                </svg>
                </>
            ) : (
                <>
                <button onClick={login} className="headerLink">Login</button>
                <button onClick={register} className="headerLink">Register</button>
                </>
            )}
            </div>
        </header>
    )
}