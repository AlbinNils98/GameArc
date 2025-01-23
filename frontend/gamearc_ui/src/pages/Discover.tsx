import axios from 'axios';
import { useState, useEffect } from 'react';
import { IGame } from '../interfaces/interfaces';
import { data } from 'react-router-dom';
import GameList from '../components/GameList';

export default function Discover() {
  const [games, setGames] = useState<IGame[]>([]);
  const [inputValue, setInputValue] = useState<string>('');

  useEffect(() => {
    const delayDebounceFn = setTimeout(() => {
      if (inputValue === '') {
        setGames([]);
      } else {
        getGames(inputValue);
      }
    }, 300); 

    return () => clearTimeout(delayDebounceFn);
  }, [inputValue]);

  function getGames(gameTitle: string) {
    axios
      .get(`http://localhost:8080/api/games/name/${gameTitle}`)
      .then((response) => {
        setGames(response.data);
      })
      .catch((error) => {
        console.log(error);
        setGames([]);
      });
  }

  return (
    <>
      <input
        type='text'
        onChange={(e) => setInputValue(e.target.value)
        }
      />
          <GameList games={games} />
    </>
  );
}
