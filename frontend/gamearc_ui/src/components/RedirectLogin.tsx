import { useEffect } from 'react';

const RedirectToLogin = () => {
  useEffect(() => {
    window.location.href = `${import.meta.env.VITE_API_URL}/login`;
  }, []);

  return null;
};

export default RedirectToLogin;