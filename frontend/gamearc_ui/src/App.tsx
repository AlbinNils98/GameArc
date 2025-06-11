
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Archive from "./pages/Archive.tsx";
import Discover from "./pages/Discover.tsx";
import Home from "./pages/Home.tsx";
import Login from './pages/Login.tsx';
import { AuthProvider } from './contexts/Auth.tsx';
import ProtectedRoute from './components/ProtectedRoute.tsx';
import Layout from './pages/Layout.tsx';
import Register from './pages/Register.tsx';

function App() {

  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path='/' element={<Layout />}>
            <Route index element={<Home />} />
            <Route path='/home' element={<Home />} />
            <Route path='/discover' element={<Discover />} />
            <Route path='/archive' element={
              <ProtectedRoute>
                <Archive />
              </ProtectedRoute>
            } />
            <Route path='/login' element={<Login />} />
            <Route path='/register' element={<Register />} />
          </Route>
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  )
}

export default App
