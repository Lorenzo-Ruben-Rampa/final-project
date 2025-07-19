import {BrowserRouter, Routes, Route} from "react-router-dom";
import DefaultLayout from "./layouts/DefaultLayout";
import GlobalContext from "./contexts/GlobalContext";
import { useState } from "react";
import HomePage from "./pages/Homepage";
import BookPage from "./pages/Bookpage";

function App() {
  const [isLoading, setIsLoading] = useState(false);

  return (
    <GlobalContext.Provider value={{ isLoading, setIsLoading }}>
      <BrowserRouter>
        <Routes>
          <Route element={<DefaultLayout  />}>
            <Route index path="/" element={<HomePage />} />
            <Route path="/books/:id" element={<BookPage />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </GlobalContext.Provider>
  )
}

export default App
