import alterSize from '../utils/alterSize';
import { IGame, IUser } from '../interfaces/interfaces';
import { useEffect, useState } from 'react';
import RedirectToLogin from './RedirectLogin';
import AddToLibraryModal from './addToLibraryModal';

interface ModalProps {
  game: IGame;
  isOpen: boolean;
  onClose: () => void;
  user: IUser | null | undefined;
}

const GameModal: React.FC<ModalProps> = ({ game, isOpen, onClose, user }) => {
  const [isAddOpen, setIsAddOpen] = useState<boolean>(false);

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

  const handleClick = () => {
    if (user === null) {
      window.location.href = 'http://localhost:8080/login'
    } else {
      setIsAddOpen(true);
    }
  };

  const closeAddModal = () => {
    setIsAddOpen(false);
  };

  return (
    <>
      {!isAddOpen ? (
        <div className='fixed inset-0 bg-gaWhite bg-opacity-90 flex justify-center items-center z-50'>
          <div className='bg-gaWhite bg-opacity-0 p-6 w-[90%] max-w-2xl relative max-h-[90vh] overflow-y-auto custom-scrollbar'>
            <h2 className='text-xl text-gaBlue font-bold'>{game.title}</h2>
            <div className='flex'>
            <img
              src={alterSize(game.cover)}
              alt={game.title}
              className='max-h-60 rounded-sm'
            />
            <p className='font-semibold ml-5'>{game.description}</p>
            </div>
            <ul className='border-gaBlue border-4 rounded-md w-fit p-1 mt-4'>
              <li className='font-bold text-gaBlue text-lg'>Genres:</li>
              {game.genres.map((genre, index) => {
                return (
                  <li key={index} className='text-gaBlue font-semibold'>
                    {genre}
                  </li>
                );
              })}
            </ul>
            <div className='flex justify-between mt-4'>
              <button
                onClick={onClose}
                className='bg-blue-500 text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100'
              >
                Close
              </button>
              <button
                className='bg-blue-500 text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100'
                onClick={handleClick}
              >
                Add to library
              </button>
            </div>
          </div>
        </div>
      ) : (
        <AddToLibraryModal
          game={game}
          isOpen={isAddOpen}
          onClose={closeAddModal}
        />
      )}
    </>
  );
};

export default GameModal;
