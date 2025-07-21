import PropTypes from 'prop-types'
import {Link} from "react-router-dom"

const BookCard = (props) => {
    const isDetail = props.isDetail || false;
    const {id, name, author, publisher, synopsis, coverImage, genres, publicationDate, isbn, price} = props.book;

    const fullCoverImageUrl = `${import.meta.env.VITE_BACKEND_BASE_URL}${coverImage}`;

    const formDate = () => {
        const date = new Date(publicationDate);
        const year = date.getFullYear();
        return `${year}`;
    }

    return ( 
        <div className="card grey">
            <div className="cardtitle">
                {!isDetail && <h2>{name}</h2>}
                <div className="d-flex justify-content-between grey">
                    <div className="grey pt-3">
                        <address><em>Scritto da {author} nel <time>{formDate()}</time></em></address>
                        <address><em>Edito {publisher}</em></address>
                        <address><em>ISBN: {isbn}</em></address>

                    </div>      
                      <div>
                        <img src={fullCoverImageUrl} alt={name} className="book-img"></img>
                    </div>        
                    <div className="px-3 grey">  
                        <p className="fs-1 text-danger">{price.toFixed(2)} €</p>
                    </div>
                 </div>
                <section className="genres">
                    {
                    genres.map((genre) => 
                        <div className="badge bg-primary me-3 {isDetail} ? 'fs-3 : ''" key={genre.id}>{genre.name}</div>)
                    }
                    </section> 
            </div>
                {isDetail ?
                 <div className="card-body grey">
                     <p>Trama:
                        <br></br><br></br>
                        {synopsis}</p>
                 </div> : ""
                }
            <div className="d-flex justify-content-end align-items-center pb-3 px-3 grey">
                {isDetail ?
                <Link to={'/'} className="btn btn-primary">Home</Link> :
                <Link to={`/books/${id}`} className="btn btn-primary">Scopri di più</Link>}
            </div>
        </div>
    )
}

BookCard.proptypes = {
    isDetail: PropTypes.bool,
    book: PropTypes.shape({
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    author: PropTypes.string.isRequired,
    publisher: PropTypes.string.isRequired,
    synopsis: PropTypes.string,
    coverImage: PropTypes.string,
    genres: PropTypes.array,
    publicationDate: PropTypes.string.isRequired,
    isbn: PropTypes.string, 
    price: PropTypes.number
    }).isRequired
}

export default BookCard