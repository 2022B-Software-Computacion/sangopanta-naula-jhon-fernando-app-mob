import { useState } from 'react'
import reactLogo from './assets/react.svg'
import {BrowserRouter, Route, Routes} from "react-router-dom"
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<div className="App">Hola</div>}/>
        <Route path='/About' element={<div className="App">About</div>}/>
      </Routes>
    </BrowserRouter>

  )
}

export default App
