import { Link } from "react-router-dom";

const Footer = () => {
  const currentYear = new Date().getFullYear(); 

  return (
    <footer className="footer mt-auto bg-body-tertiary">
      <div className="container-fluid d-flex justify-content-center align-items-center">
        <span>
          &copy; {currentYear} Bibliotecarium. Tutti i diritti riservati.
        </span>
        <nav>
          <ul className="nav justify-content-end">
            <li className="nav-item">
              <Link to="/about" className="nav-link px-2">
                Chi Siamo
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/contact" className="nav-link px-2">
                Contatti
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/privacy" className="nav-link px-2">
                Privacy
              </Link>
            </li>
          </ul>
        </nav>
      </div>
    </footer>
  );
};

export default Footer;