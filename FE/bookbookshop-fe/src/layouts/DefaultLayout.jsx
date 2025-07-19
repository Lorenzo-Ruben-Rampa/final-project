import { useContext } from "react";
import { Outlet } from "react-router-dom";
import GlobalContext from "../contexts/GlobalContext";
import Header from "../components/Header";
import Footer from "../components/Footer";
import Loader from "../components/Loader";

const DefaultLayout = () => {
  const { isLoading } = useContext(GlobalContext);

  return (
    <>
      <Header />
      <main className="container">
          <Outlet />
      </main>
      {isLoading && < Loader />}
      <Footer />
    </>
  )
}

export default DefaultLayout;