import { useState, useEffect } from 'react';
import { IGame } from '../interfaces/interfaces';
import GameList from '../components/GameList';
import { IUser } from '../interfaces/interfaces';
import api from '../api/axios';

interface props {
  user: IUser | null;
}

export default function Discover( { user } : props) {
  const [games, setGames] = useState<IGame[]>([]);
  const [inputValue, setInputValue] = useState<string>('');
  const [offset, setOffset] = useState<number>(0);
  const [isNextGames, setIsNextGames] = useState<boolean>(false)

  useEffect(() => {
    const delayDebounceFn = setTimeout(() => {
      if (inputValue === '') {
        setGames([]);
        setOffset(10);
      } else {
        getGames(inputValue, 0, true);
      }
    }, 200); 

    return () => clearTimeout(delayDebounceFn);
  }, [inputValue]);

  const getGames = (gameTitle: string, currentOffset: number, isNewSearch: boolean = false) => {
    api
      .get(`/api/games/name/${gameTitle}?limit=10&offset=${currentOffset}`)
      .then((response) => {
        if (Array.isArray(response.data) && response.data.length > 0) {
          if (isNewSearch) {
            setGames(response.data);
          } else {
            setGames((prevGames) => prevGames.concat(response.data));
          }
          setOffset(currentOffset + 10);
          setIsNextGames(true); 
        } else {
          setIsNextGames(false);
        }
      })
      .catch((error) => {
        console.error(error);
        if (isNewSearch) setGames([]);
        setIsNextGames(false);
      });
  };

  const handleShowMore = () => {
      getGames(inputValue, offset)
  }

  return (
    <>
    <div className='mt-5 '>
      <input
        type='text'
        name='gameSearch'
        onChange={(e) => setInputValue(e.target.value)
        }
        className='ml-5 min-w-80 mb-5 rounded-sm shadow-sm p-1 self-start'
        placeholder='Search for game...'
      />
      </div>
          <GameList games={games} user={user} />
          {isNextGames && <button onClick={handleShowMore} className='bg-gaBlue text-gaWhite w-36 self-center pt-1 pb-1 pl-3 pr-3 rounded-md mb-5 active:opacity-80' >Show more</button>}
    </>
  );
}
