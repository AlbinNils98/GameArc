import { useNavigate } from 'react-router-dom';
import { IGame } from '../interfaces/interfaces';

interface headerProps {
  games: IGame[];
}

export default function GameList({ games }: headerProps) {

  const coverPlaceholder: string = "https://placehold.co/720x1280?text=No+image+found";

  function alterSize(coverUrl: string | null): string {
    if (coverUrl != '' && coverUrl != null) {
      return coverUrl.replace('thumb', '720p');
    }
    return coverPlaceholder;
  }

  return <ul className='m-5 grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4'>{Array.isArray(games) ? 
  <>
  {games.map((game, index) => {
    return (
      <li key={index} className='bg-gaBlue grid grid-rows-[auto,1fr] rounded-md shadow-sm cursor-pointer'>
        <img src={alterSize(game.cover)} alt={`Cover for ${game.title}`} className='w-full h-56 object-cover rounded-t-md col-span-full'/>
        <p className='p-2 font-semibold text-gaWhite'>{game.title}</p>
      </li>
    )
  })}
  </> : 
  <></>}
  </ul>;
}
