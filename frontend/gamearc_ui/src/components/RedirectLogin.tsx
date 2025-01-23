import { useEffect } from 'react';

const RedirectToLogin = () => {
  useEffect(() => {
    window.location.href = 'http://localhost:8080/login';
  }, []);

  return null;
};

export default RedirectToLogin;