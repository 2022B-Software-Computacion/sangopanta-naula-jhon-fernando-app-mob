import {BrowserRouter, Route, Routes} from "react-router-dom"
import './App.css'
import Inicio from './pages/InicioPage'
import NavBar from './components/NavBar'
import UserPage from './pages/UserPage'
import PostPage from './pages/PostPage'



function App() {

  return (
    <BrowserRouter>
      <NavBar/>
      <Routes>
        <Route path='/' element={<Inicio/>}/>
        <Route path='/users' element={<UserPage/>}/>
        <Route path="/posts" element={<PostPage/>}/>
          {/* Para una pagina no encontrada  */}
      </Routes>
    </BrowserRouter>

  )
}

export default App
