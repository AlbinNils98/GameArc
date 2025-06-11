import { Outlet } from 'react-router-dom';
import Header from '../components/header/Header';


export default function Layout() {
  return (
    <div>
      <Header />
      <main className='flex-1 flex flex-col'>
        <div className='flex-1 self-center flex w-screen max-w-7xl flex-col'>
          <Outlet />
        </div>
      </main>
    </div>
  )
}