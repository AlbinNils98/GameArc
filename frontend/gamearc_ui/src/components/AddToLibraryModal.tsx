import { isAxiosError } from 'axios';
import { IGame } from '../interfaces/interfaces';
import alterSize from '../utils/alterSize';
import { useState } from 'react';
import api from '../api/axios';

interface props {
  game: IGame;
  isOpen: boolean;
  onClose: () => void;
}

const AddToLibraryModal = ({ game, isOpen, onClose }: props) => {
  const [isAdded, setIsAdded] = useState<boolean>(false);
  const [responseText, setResponseText] = useState<string>(
    'Game added to library'
  );

  if (!isOpen) return null;

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    // Create a FormData object from the form
    const formData = new FormData(event.target as HTMLFormElement);

    // Manually create an object combining game data and form data
    const formValues = {
      game,
      comment: formData.get('comment'),
      storyRating: formData.get('storyRating'),
      graphicsRating: formData.get('graphicsRating'),
      gameplayRating: formData.get('gameplayRating'),
      status: formData.get('status'),
    };

    try {
      const response = await api.post(
        '/api/user-games/',
        formValues,
        { withCredentials: true }
      );

      if (response && response.status === 201) {
        setIsAdded(true);
      }
    } catch (error: unknown) {
      console.error('Error adding game to library', error);
      if (isAxiosError(error) && error.response?.status === 409) {
        setResponseText('Game already in library');
        setIsAdded(true);
      }
    }
  };

  const ratingOptions = () => {
    const options: number[] = [];

    for (let i = 1; i <= 10; i++) {
      options.push(i);
    }

    return options;
  };

  const statusOptions = () => {
    //TODO: These should be fetched from api as they can be changed in DB.
    // Need endpoint for fetching them on server
    return [
      'playing',
      'owned',
      'wishlist',
      'completed',
      'abandoned',
      'on hold',
    ];
  };

  return (
    <>
      <div className='fixed inset-0 bg-gaWhite bg-opacity-90 flex justify-center items-center z-50'>
        <div className='bg-gaWhite bg-opacity-0 p-6 w-[90%] max-w-2xl relative max-h-[90vh] overflow-y-auto custom-scrollbar flex justify-center items-center flex-col'>
          <div>
            <div className='flex flex-wrap'>
              <div className='flex flex-col'>
                <h2 className=' text-xl font-bold text-gaBlue'>{game.title}</h2>
                <img
                  src={alterSize(game.cover)}
                  alt={`Cover for game ${game.title}`}
                  className='h-60'
                />
              </div>
              <div className='flex flex-col flex-wrap'>
                <form
                  onSubmit={handleSubmit}
                  className='flex flex-col min-[450px]:ml-5 mt-10 gap-2 flex-wrap'
                >
                  <textarea
                    name='comment'
                    id='comment'
                    placeholder='Add comment...'
                    className='p-1'
                  ></textarea>
                  <div>
                    <select name='storyRating' id='storyRating' className=''>
                      {ratingOptions().map((value, index) => {
                        return (
                          <option key={index} value={value}>
                            {value}
                          </option>
                        );
                      })}
                    </select>
                    <label htmlFor='storyRating' className='ml-2'>
                      Story Rating
                    </label>
                  </div>
                  <div>
                    <select
                      name='graphicsRating'
                      id='graphicsRating'
                      className=''
                    >
                      {ratingOptions().map((value, index) => {
                        return (
                          <option key={index} value={value}>
                            {value}
                          </option>
                        );
                      })}
                    </select>
                    <label htmlFor='graphicsRating' className='ml-2'>
                      Graphics Rating
                    </label>
                  </div>
                  <div>
                    <select
                      name='gameplayRating'
                      id='gameplayRating'
                      className=''
                    >
                      {ratingOptions().map((value, index) => {
                        return (
                          <option key={index} value={value}>
                            {value}
                          </option>
                        );
                      })}
                    </select>
                    <label htmlFor='gameplayRating' className='ml-2'>
                      Gameplay Rating
                    </label>
                  </div>
                  <div>
                    <select name='status' id='status'>
                      {statusOptions().map((value, index) => {
                        return (
                          <option key={index} value={value}>
                            {value}
                          </option>
                        );
                      })}
                    </select>
                    <label htmlFor='status' className='ml-2'>
                      Status
                    </label>
                  </div>
                  {isAdded && (
                    <p className='text-gaWhite rounded-md px-5 py-1 bg-gaBlue cursor-default'>
                      {responseText}
                    </p>
                  )}
                  {!isAdded && (
                    <button
                      type='submit'
                      className='text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100'
                    >
                      Add to library
                    </button>
                  )}
                </form>
              </div>
            </div>
            <button
              onClick={onClose}
              className='text-gaWhite rounded-md px-5 py-1 bg-gaBlue hover:opacity-80 active:opacity-100 mt-5 self-start'
            >
              Go back
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default AddToLibraryModal;
