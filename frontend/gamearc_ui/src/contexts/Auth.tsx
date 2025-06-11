// AuthContext.tsx
import React, { createContext, useState, useEffect, useContext } from 'react';
import { isAxiosError } from 'axios';
import { IUser } from '../interfaces/interfaces';
import api from '../api/axios';

interface AuthContextType {
  user: IUser | null;
  loading: boolean;
  signOut: () => void;
  fetchUser: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<IUser | null>(null);
  const [loading, setLoading] = useState(true);

  const signOut = async () => {
    try {
      await api.post('/logout', {}, { withCredentials: true })
    } catch (error: unknown) {
      console.log(error)
    } finally {
      setUser(null)
    }
  }

  const fetchUser = async () => {
    try {
      const res = await api.get<IUser>('/user-info', { withCredentials: true });
      setUser(res.data);
    } catch (error: unknown) {
      if (isAxiosError(error) && error.response?.status === 401) {
        setUser(null);
      }
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {

    fetchUser();
  }, []);

  return (
    <AuthContext.Provider value={{ user, loading, signOut, fetchUser }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = (): AuthContextType => {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used within AuthProvider');
  return ctx;
};