
import {createBrowserRouter, Navigate, RouterProvider} from 'react-router-dom';

import Archive from "./pages/Archive.tsx";
import Discover from "./pages/Discover.tsx";
import Home from "./pages/Home.tsx";
import Header from './components/Header.tsx';
import RedirectToLogin from './components/RedirectLogin.tsx';
import { IUser } from './interfaces/interfaces.ts';
import { useState } from 'react';

function App() {

const [user, setUser] = useState<IUser | null>();

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
    element: <Discover/>
  },
  {
    path: '/archive',
    element: user === undefined ? <div>Checking credentials...</div> : user ? <Archive /> : <RedirectToLogin />,
  }
])

  return(
    <>
    <Header user={user} setParentUseState={setUser}/>
    <main className='flex-1 flex flex-col'>
    <div className='flex-1 self-center flex max-w-7xl flex-col'>
    <RouterProvider router={router}/>
    </div>
    </main>
    </>
  ) 
}

export default App
