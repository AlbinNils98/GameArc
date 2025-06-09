import React from 'react'
import { IUser } from '../../interfaces/interfaces';

type DesktopHeaderProps = {
  onLogin: () =>  void;
  onLogout: () => void;
  onRegister: () => void;
  user: IUser | null | undefined;
}

export const DesktopHeader: React.FC<DesktopHeaderProps> = ({onLogin, onLogout, onRegister, user}) => {

  return (
    <div className='hidden sm:flex h-16 bg-gaBlue justify-between'>
    <a href='/' className='ml-5 max-w-20 flex justify-center'>
    <img
      src='/GameArcLogoSmall.png'
      alt='GameArc Logo'
      className=' object-contain'
    />
  </a>
  <div className='flex justify-center mr-5'>
    <a href='/discover' className='headerLink'>
      Discover
    </a>
    {user ? (
      <>
        <a href='/archive' className='headerLink'>
          Archive
        </a>
        <button onClick={onLogout} className='headerLink'>
          Logout
        </button>
        <p className='text-base text-gaWhite self-center ml-8'>
          {user.username}
        </p>
        <svg
          xmlns='http://www.w3.org/2000/svg'
          fill='none'
          viewBox='0 0 24 24'
          strokeWidth={1.5}
          stroke='currentColor'
          className='size-5 self-center text-gaWhite'
        >
          <path
            strokeLinecap='round'
            strokeLinejoin='round'
            d='M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z'
          />
        </svg>
      </>
    ) : (
      <>
        <button onClick={onLogin} className='headerLink'>
          Login
        </button>
        <button onClick={onRegister} className='headerLink'>
          Register
        </button>
      </>
    )}
  </div>
  </div>
  )
}