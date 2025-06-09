
import {createBrowserRouter, RouterProvider} from 'react-router-dom';

import Archive from "./pages/Archive.tsx";
import Discover from "./pages/Discover.tsx";
import Home from "./pages/Home.tsx";
import RedirectToLogin from './components/RedirectLogin.tsx';
import { IUser } from './interfaces/interfaces.ts';
import { useState, useEffect } from 'react';
import axios from 'axios';
import Header from './components/header/Header.tsx';

function App() {

const [user, setUser] = useState<IUser | null>(null);
const [loading, setLoading] = useState<boolean>(true);

useEffect(() => {
  const fetchUserInfo = async () => {
    try {
      const response = await axios.get<IUser>('/user-info', { withCredentials: true });
      setUser(response.data);
    } catch (error: any) {
      if (error.response?.status === 401) {
        setUser(null);
        console.log('User is not authenticated.');
      } else {
        console.error('An unexpected error occurred:', error);
      }
    } finally {
      setLoading(false);
    }
  };

  fetchUserInfo();
}, []);

const router = createBrowserRouter([
  {
    path: '/',
    element: <Home/>
  },
  {
    path: '/home',
    element: <Home/>
  },
  {
    path: '/discover',
    element: <Discover user={user} />
  },
  {
    path: '/archive',
    element: loading ? <div>Loading...</div> : user === null ? <RedirectToLogin /> : <Archive />,
  }
])

  return(
    <>
    <Header user={user} setParentUseState={setUser}/>
    <main className='flex-1 flex flex-col'>
    <div className='flex-1 self-center flex w-screen max-w-7xl flex-col'>
    <RouterProvider router={router}/>
    </div>
    </main>
    </>
  ) 
}

export default App
