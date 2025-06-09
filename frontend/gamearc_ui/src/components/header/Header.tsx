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
    window.location.href = 'http://localhost:8080/login';
  };

  const logout = () => {
    window.location.href = 'http://localhost:8080/logout';
  };

  const register = () => {
    window.location.href = 'http://localhost:8080/register';
  };

  return (
    <header className='h-16 bg-gaBlue'>
      <DesktopHeader onLogin={login} onLogout={logout} onRegister={register} user={user} />
      <MobileHeader onLogin={login} onLogout={logout} onRegister={register} user={user} />
    </header>
  );
}
