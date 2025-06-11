import { DesktopHeader } from './DesktopHeader';
import { MobileHeader } from './MobileHeader';
import { useAuth } from '../../contexts/Auth';
import { useNavigate } from 'react-router-dom';

export default function Header() {
  const {user, signOut} = useAuth();
  const navigate = useNavigate();

  const login = () => navigate('/login');

  const register = () => navigate('/register');

  return (
    <header className='h-16 bg-gaBlue'>
      <DesktopHeader onLogin={login} onLogout={signOut} onRegister={register} user={user} />
      <MobileHeader onLogin={login} onLogout={signOut} onRegister={register} user={user} />
    </header>
  );
}
