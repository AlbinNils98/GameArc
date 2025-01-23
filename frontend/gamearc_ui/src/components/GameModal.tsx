import alterSize from '../utils/alterSize';
import { IGame } from '../interfaces/interfaces';
import { useEffect } from 'react';

interface ModalProps {
  game: IGame;
  isOpen: boolean;
  onClose: () => void;
}

const GameModal: React.FC<ModalProps> = ({ game, isOpen, onClose }) => {
  useEffect(() => {
    if (isOpen) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = '';
    }

    return () => {
      document.body.style.overflow = '';
    };
  }, [isOpen]);

  if (!isOpen) return null;

  return (
    <div className='fixed inset-0 bg-gaWhite bg-opacity-80 flex justify-center items-center z-50'>
      <div className='bg-gaWhite bg-opacity-20 p-6 w-[90%] max-w-2xl relative max-h-[90vh] overflow-y-auto custom-scrollbar'>
        <h2 className='text-xl text-gaBlue font-bold'>{game.title}</h2>
        <img
          src={alterSize(game.cover)}
          alt={game.title}
          className='w-full h-[300px] object-cover rounded-md my-4'
        />
        <p className='font-semibold' >{game.description}</p>
        <ul className='border-gaBlue border-4 rounded-md w-fit p-1 mt-4'>
          <li className='font-bold text-gaBlue text-lg'>Genres:</li>
          {game.genres.map((genre, index) => {
            return <li key={index} className='text-gaBlue font-semibold'>{genre}</li>;
          })}
        </ul>
        <div className='flex justify-between mt-4'>
          <button
            onClick={onClose}
            className='bg-blue-500 text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100'
          >
            Close
          </button>
          <button className='bg-blue-500 text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100'>Add to library</button>
        </div>
      </div>
    </div>
  );
};

export default GameModal;
