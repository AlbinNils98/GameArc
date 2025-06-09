import React, { useState } from 'react'
import { IUser } from '../../interfaces/interfaces';
import { GiHamburgerMenu } from 'react-icons/gi';
import { MdClose } from 'react-icons/md';
import { IoPersonOutline } from 'react-icons/io5';

type MobileHeaderProps = {
  onLogin: () =>  void;
  onLogout: () => void;
  onRegister: () => void;
  user: IUser | null | undefined;
}

export const MobileHeader: React.FC<MobileHeaderProps> = ({onLogin, onLogout, onRegister, user}) => {
  const [showMenu, setShowMenu] = useState(false);

  const toggleMenu = () => {
    setShowMenu(!showMenu)
  };

  return (
    <div className='sm:hidden flex h-16 bg-gaBlue justify-between content-center relative'>
    <a href='/' className='ml-5 max-w-20 flex justify-center'>
    <img
      src='/GameArcLogoSmall.png'
      alt='GameArc Logo'
      className=' object-contain'
    />
  </a>
  <div className='flex items-center mr-5'>
  {showMenu ? 
  <GiHamburgerMenu color='white' onClick={toggleMenu} size={40} />
:
<MdClose color='white' onClick={toggleMenu} size={40}/>
}
  </div>


  {!showMenu && 
  <div className='flex flex-col justify-center pr-7 pt-2 absolute top-14 right-0 z-10 bg-gaBlue gap-5'>
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
        <div className='flex flex-row items-center gap-1 ml-3'>
        <p className='text-base text-gaWhite self-center'>
          {user.username}
        </p>
        <IoPersonOutline />
        </div>
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
  }
  </div>
  )
}