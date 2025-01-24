import { useState } from 'react';
import { IUserGame } from '../interfaces/interfaces';
import alterSize from '../utils/alterSize';
import getAverageRating from '../utils/getAverageRating';
import UserGameModal from './UserGameModal';

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
        <ul className='border-2 m-5 rounded-md overflow-y-auto max-h-[36rem] custom-scrollbar max-w-[30rem]'>
          {isUserGames ? (
            <>
              {userGames?.map((userGame, index) => {
                return (
                  <li
                    key={index}
                    className='flex justify-between border-b-2 p-2 items-center cursor-pointer hover:bg-gaBlue hover:bg-opacity-10'
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
                        <svg
                          xmlns='http://www.w3.org/2000/svg'
                          viewBox='0 0 24 24'
                          fill='currentColor'
                          className='size-6 text-gaBlue w-4'
                        >
                          <path
                            fill-rule='evenodd'
                            d='M10.788 3.21c.448-1.077 1.976-1.077 2.424 0l2.082 5.006 5.404.434c1.164.093 1.636 1.545.749 2.305l-4.117 3.527 1.257 5.273c.271 1.136-.964 2.033-1.96 1.425L12 18.354 7.373 21.18c-.996.608-2.231-.29-1.96-1.425l1.257-5.273-4.117-3.527c-.887-.76-.415-2.212.749-2.305l5.404-.434 2.082-5.005Z'
                            clip-rule='evenodd'
                          />
                        </svg>
                      </div>
                      <p className='text-sm font-semibold'>
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
                className='bg-blue-500 text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100'
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
