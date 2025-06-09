import { Dispatch, SetStateAction } from 'react';
import { IUser } from '../../interfaces/interfaces';
import { DesktopHeader } from './DesktopHeader';
import { MobileHeader } from './MobileHeader';

interface headerProps {
  user: IUser | null | undefined;
  setParentUseState: Dispatch<SetStateAction<IUser | null>>
}

export default function Header({ user }: headerProps) {
  const login = () => {
    window.location.href = `${import.meta.env.VITE_API_URL}/login`;
  };

  const logout = () => {
    window.location.href = `${import.meta.env.VITE_API_URL}/logout`;
  };

  const register = () => {
    window.location.href = `${import.meta.env.VITE_API_URL}/register`;
  };

  return (
    <header className='h-16 bg-gaBlue'>
      <DesktopHeader onLogin={login} onLogout={logout} onRegister={register} user={user} />
      <MobileHeader onLogin={login} onLogout={logout} onRegister={register} user={user} />
    </header>
  );
}
