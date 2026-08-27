import { useState, useEffect } from 'react'
import { getConnection } from './api/libraryApi'
import './App.css'

function App() {
  const [libs, setLibs] = useState();
  const [error, setError] = useState()

  useEffect(() => {
    getConnection()
    .then(setLibs)
    .catch(setError)
  }, [])

  if (error) return <div>연결 실패</div>
  if (!libs) return <div>불러오는 중...</div>

  return (
    <>
      {JSON.stringify(libs, null, 2)}
    </>
  )
}

export default App
