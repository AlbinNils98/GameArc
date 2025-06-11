import { IUserGame } from '../interfaces/interfaces';
import alterSize from '../utils/alterSize';
import getAverageRating from '../utils/getAverageRating';

interface props {
  userGame: IUserGame | null;
  handleClose: () => void;
}

const UserGameModal = ({ userGame, handleClose }: props) => {
  if (!userGame) return null;

  return (
      <div className='flex flex-wrap justify-between'>
        <div className='p-6'>
          <h2 className='text-xl text-gaBlue font-bold'>
            {userGame.game.title}
          </h2>
          <div className='flex flex-wrap'>
            <img
              src={alterSize(userGame.game.cover)}
              alt={userGame.game.title}
              className='max-h-64 rounded-md object-cover'
            />
            <p className='font-semibold sm:ml-5 max-w-96'>{userGame.game.description}</p>
          </div>
          <ul className='border-gaBlue border-4 rounded-md w-fit p-1 mt-4'>
            <li className='font-bold text-gaBlue text-lg'>Genres:</li>
            {userGame.game.genres.map((genre, index) => {
              return (
                <li key={index} className='text-gaBlue font-semibold'>
                  {genre}
                </li>
              );
            })}
          </ul>
          <div className='flex justify-between mt-4'>
            <button
              onClick={handleClose}
              className='text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100'
            >
              Go back
            </button>
            <p className='text-gaBlue font-semibold'>Status: {userGame.status}</p>
          </div>
        </div>
        <div className='flex flex-col m-6 p-6 rounded-md border-4 border-gaBlue flex-1 min-w-52 max-w-96 text-gaBlue justify-evenly'>
          <div>
          <h2 className='text-gaBlue font-semibold'>Your comment:</h2>
          <p className='border-2 p-1 rounded-md border-gaBlue min-h-20'>{userGame.comment}</p>
          </div>
          <div className='flex flex-col'>
          <p className='text-gaBlue font-semibold'>Story rating: {userGame.storyRating}/10</p>
          <p className='text-gaBlue font-semibold'>Graphics rating: {userGame.graphicsRating}/10</p>
          <p className='text-gaBlue font-semibold'>Gameplay rating: {userGame.gameplayRating}/10</p>
          <p className='text-gaBlue font-semibold mt-2'>Average rating: {getAverageRating(userGame)}/10</p>
          </div>
          <button className='text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100 self-end justify-self-end'>Edit</button>
        </div>
      </div>
  );
};

export default UserGameModal;
