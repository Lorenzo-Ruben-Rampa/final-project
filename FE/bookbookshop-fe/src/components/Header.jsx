import {Link} from "react-router-dom";

const Header = () => {
  return (
    <nav className="navbar bg-body-territory mb-4">
     <div className="container-fluid justify-content-center flex-wrap">
        <Link className="navbar-brand" to="/"><h1>Bibliotecarium</h1></Link>
      </div> 
      <div className="container-fluid justify-content-center flex-wrap">
        <p><i>Verba volant, scripta manent</i></p>
      </div>
    </nav>
  );
}

export default Header;