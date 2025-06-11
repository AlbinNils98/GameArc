import { isAxiosError } from 'axios';
import { useEffect, useState } from 'react';
import { IUserGame } from '../interfaces/interfaces';
import UserGamesList from '../components/UserGamesList';
import api from '../api/axios';

export default function Archive() {
  const [isUserGames, setIsUserGames] = useState<boolean>(false);
  const [userGames, setUserGames] = useState<IUserGame[] | null>(null);

  useEffect(() => {
    fetchUserGames();
  }, []);

  const fetchUserGames = async () => {
    try {
      const response = await api.get(
        '/api/user-games/',
        { withCredentials: true }
      );

      if (response && response.status === 200) {
        setUserGames(response.data);
        setIsUserGames(true);
      }
    } catch (error: unknown) {
      if (isAxiosError(error) && error.response?.status === 404) {
        setIsUserGames(false);
      }
    }
  };

  return (
    <>
            <UserGamesList userGames={userGames} isUserGames={isUserGames} loadGames={fetchUserGames}/>
    </>
  );
}
