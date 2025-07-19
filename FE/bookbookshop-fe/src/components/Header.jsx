import {Link} from "react-router-dom";

const Header = () => {
  return (
    <nav className="navbar bg-body-territory mb-4">
     <div className="container-fluid justify-content-start">
        <Link className="navbar-brand" to="/"><h1>Bibliotecarium</h1></Link>
        </div>
    </nav>
  );
}

export default Header;