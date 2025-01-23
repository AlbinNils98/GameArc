import alterSize from '../utils/alterSize';
import { IGame } from '../interfaces/interfaces';
import { useState } from 'react';
import GameModal from './GameModal';

interface headerProps {
  games: IGame[];
}

export default function GameList({ games }: headerProps) {

  const [selectedGame, setSelectedGame] = useState<IGame | null>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleClick = (game: IGame) => {
    setSelectedGame(game);
    setIsModalOpen(true);
  }

  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedGame(null);
  }

  return (<div><ul className='m-5 grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4'>{Array.isArray(games) ? 
  <>
  {games.map((game, index) => {
    return (
      <li key={index} className='bg-gaBlue grid grid-rows-[auto,1fr] rounded-md shadow-sm cursor-pointer max-w-44'
      onClick={() => handleClick(game)}>
        <img src={alterSize(game.cover)} alt={`Cover for ${game.title}`} className='w-full h-56 object-cover rounded-t-md col-span-full'/>
        <p className='p-2 font-semibold text-gaWhite'>{game.title}</p>
      </li>
    )
  })}
  </> : 
  <></>}
  </ul>
  {selectedGame && (
    <GameModal game={selectedGame} isOpen={isModalOpen} onClose={closeModal} />
  )}
  </div>
);
}
