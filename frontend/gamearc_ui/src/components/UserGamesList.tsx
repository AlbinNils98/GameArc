import { useState } from 'react';
import { IUserGame } from '../interfaces/interfaces';
import alterSize from '../utils/alterSize';
import getAverageRating from '../utils/getAverageRating';
import UserGameModal from './UserGameModal';
import { FaStar } from 'react-icons/fa';

interface props {
  userGames: IUserGame[] | null;
  isUserGames: boolean;
  loadGames: () => void;
}

const UserGamesList = ({ userGames, isUserGames, loadGames }: props) => {
  const [isShowGame, setIsShowGame] = useState<boolean>(false);
  const [selectedGame, setSelectedGame] = useState<IUserGame | null>(null)

  const handleShowGame = () => {
    setIsShowGame(!isShowGame);
  };

  return (
    <>
      {!isShowGame ? (
        <ul className='border-gaBlue border-2 m-5 rounded-md overflow-y-auto max-h-[36rem] custom-scrollbar max-w-[30rem]'>
          {isUserGames ? (
            <>
              {userGames?.map((userGame, index) => {
                return (
                  <li
                    key={index}
                    className='border-gaBlue flex justify-between border-b-2 p-2 items-center cursor-pointer hover:bg-gaBlue hover:bg-opacity-10'
                    onClick={() => {handleShowGame(); setSelectedGame(userGame)}}
                  >
                    <div className='flex'>
                      <img
                        src={alterSize(userGame.game.cover)}
                        alt={`Cover for ${userGame.game.title}`}
                        className='h-20 rounded-sm'
                      />
                      <h2 className='text-sm font-semibold text-gaBlue ml-2'>
                        {userGame.game.title}
                      </h2>
                    </div>
                    <div className='flex items-end flex-col ml-5'>
                      <div className='flex items-center'>
                        <p className='text-xs font-semibold text-gaBlue'>
                          {getAverageRating(userGame)}/10
                        </p>
                        <FaStar className='ml-1 text-gaBlue' size={12} />
                          
                        
                        
                      </div>
                      <p className='text-sm font-semibold text-gaBlue'>
                        Status: {userGame.status}
                      </p>
                    </div>
                  </li>
                );
              })}
            </>
          ) : (
            <div className='flex items-center p-5 flex-col'>
              <h2 className='font-bold text-gaBlue'>
                No games found in Archive...
              </h2>
              <button
                className='text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100'
                onClick={loadGames}
              >
                Reload
              </button>
            </div>
          )}
        </ul>
      ) : (
        <UserGameModal userGame={selectedGame} handleClose={handleShowGame}/>
      )}
    </>
  );
};

export default UserGamesList;
