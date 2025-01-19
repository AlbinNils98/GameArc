
import {createBrowserRouter, RouterProvider} from 'react-router-dom';

import ProtectedRoute from "./components/ProtectedRoute.tsx";
import Library from "./pages/Library.tsx";
import Discover from "./pages/Discover.tsx";
import Home from "./pages/Home.tsx";

const isAuthenticated = false;

const router = createBrowserRouter([
  {
    path: '/',
    element: <Home/>
  },
  {
    path: '/home',
    element: <Home/>
  },
  {
    path: '/discover',
    element: <Discover/>
  },
  {
    path: '/library',
    element:
        <ProtectedRoute isAuthenticated={isAuthenticated}>
          <Library/>
        </ProtectedRoute>
  }
])

function App() {

  return <RouterProvider router={router}/>
}

export default App
